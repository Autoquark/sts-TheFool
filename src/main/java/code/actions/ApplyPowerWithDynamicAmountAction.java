package code.actions;

import code.TheFoolMod;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.PowerBuffEffect;
import com.megacrit.cardcrawl.vfx.combat.PowerDebuffEffect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Supplier;

// Largely copy pasted from the decompiled
public class ApplyPowerWithDynamicAmountAction extends AbstractGameAction
{
    public static final Logger logger = LogManager.getLogger(TheFoolMod.class.getName());
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private String powerToApplyID;
    private float startingDuration;
    private int amountModifier = 0;
    private final Supplier<AbstractPower> powerFactory;

    public ApplyPowerWithDynamicAmountAction(AbstractCreature target, AbstractCreature source, String powerToApplyID, Supplier<AbstractPower> powerFactory, boolean isFast, AbstractGameAction.AttackEffect effect) {
        if (Settings.FAST_MODE) {
            this.startingDuration = 0.1F;
        } else if (isFast) {
            this.startingDuration = Settings.ACTION_DUR_FASTER;
        } else {
            this.startingDuration = Settings.ACTION_DUR_FAST;
        }

        this.setValues(target, source);
        this.duration = this.startingDuration;
        this.powerToApplyID = powerToApplyID;
        this.powerFactory = powerFactory;
        if (AbstractDungeon.player.hasRelic("Snake Skull") && source != null && source.isPlayer && target != source && powerToApplyID.equals("Poison")) {
            AbstractDungeon.player.getRelic("Snake Skull").flash();
            amountModifier++;
        }

        if (powerToApplyID.equals("Corruption")) {
            Iterator<AbstractCard> iterator = AbstractDungeon.player.hand.group.iterator();

            AbstractCard card;
            while(iterator.hasNext()) {
                card = iterator.next();
                if (card.type == AbstractCard.CardType.SKILL) {
                    card.modifyCostForCombat(-9);
                }
            }

            iterator = AbstractDungeon.player.drawPile.group.iterator();

            while(iterator.hasNext()) {
                card = iterator.next();
                if (card.type == AbstractCard.CardType.SKILL) {
                    card.modifyCostForCombat(-9);
                }
            }

            iterator = AbstractDungeon.player.discardPile.group.iterator();

            while(iterator.hasNext()) {
                card = iterator.next();
                if (card.type == AbstractCard.CardType.SKILL) {
                    card.modifyCostForCombat(-9);
                }
            }

            iterator = AbstractDungeon.player.exhaustPile.group.iterator();

            while(iterator.hasNext()) {
                card = iterator.next();
                if (card.type == AbstractCard.CardType.SKILL) {
                    card.modifyCostForCombat(-9);
                }
            }
        }

        this.actionType = ActionType.POWER;
        this.attackEffect = effect;
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.duration = 0.0F;
            this.startingDuration = 0.0F;
            this.isDone = true;
        }

    }

    public void update() {
        if (this.target != null && !this.target.isDeadOrEscaped()) {
            if (this.duration == this.startingDuration) {
                if (this.powerToApplyID.equals(NoDrawPower.POWER_ID) && this.target.hasPower(this.powerToApplyID)) {
                    this.isDone = true;
                    return;
                }

                AbstractPower powerToApply = powerFactory.get();
                powerToApply.amount += amountModifier;
                this.amount = powerToApply.amount;
                if(powerToApply.ID != powerToApplyID)
                {
                    logger.error("ApplyPowerWithDynamicAmountAction: powerToApplyID was \"{}\" but supplied factory function returned a \"{}\"!", powerToApplyID, powerToApply.ID);
                }

                if (this.source != null) {

                    for (AbstractPower pow : this.source.powers)
                    {
                        pow.onApplyPower(powerToApply, this.target, this.source);
                    }
                }

                if (AbstractDungeon.player.hasRelic("Champion Belt") && this.source != null && this.source.isPlayer && this.target != this.source && powerToApply.ID.equals("Vulnerable") && !this.target.hasPower("Artifact")) {
                    AbstractDungeon.player.getRelic("Champion Belt").onTrigger(this.target);
                }

                if (this.target instanceof AbstractMonster && this.target.isDeadOrEscaped()) {
                    this.duration = 0.0F;
                    this.isDone = true;
                    return;
                }

                if (AbstractDungeon.player.hasRelic("Ginger") && this.target.isPlayer && powerToApply.ID.equals("Weakened")) {
                    AbstractDungeon.player.getRelic("Ginger").flash();
                    this.addToTop(new TextAboveCreatureAction(this.target, TEXT[1]));
                    this.duration -= Gdx.graphics.getDeltaTime();
                    return;
                }

                if (AbstractDungeon.player.hasRelic("Turnip") && this.target.isPlayer && powerToApply.ID.equals("Frail")) {
                    AbstractDungeon.player.getRelic("Turnip").flash();
                    this.addToTop(new TextAboveCreatureAction(this.target, TEXT[1]));
                    this.duration -= Gdx.graphics.getDeltaTime();
                    return;
                }

                if (this.target.hasPower("Artifact") && powerToApply.type == AbstractPower.PowerType.DEBUFF) {
                    this.addToTop(new TextAboveCreatureAction(this.target, TEXT[0]));
                    this.duration -= Gdx.graphics.getDeltaTime();
                    CardCrawlGame.sound.play("NULLIFY_SFX");
                    this.target.getPower("Artifact").flashWithoutSound();
                    this.target.getPower("Artifact").onSpecificTrigger();
                    return;
                }

                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
                boolean hasBuffAlready = false;
                Iterator<AbstractPower> var6 = this.target.powers.iterator();

                label148:
                while(true) {
                    AbstractPower p;
                    do {
                        do {
                            if (!var6.hasNext()) {
                                if (powerToApply.type == AbstractPower.PowerType.DEBUFF) {
                                    this.target.useFastShakeAnimation(0.5F);
                                }

                                if (!hasBuffAlready) {
                                    this.target.powers.add(powerToApply);
                                    Collections.sort(this.target.powers);
                                    powerToApply.onInitialApplication();
                                    powerToApply.flash();
                                    if (this.amount >= 0 || !powerToApply.ID.equals("Strength") && !powerToApply.ID.equals("Dexterity") && !powerToApply.ID.equals("Focus")) {
                                        if (powerToApply.type == AbstractPower.PowerType.BUFF) {
                                            AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, powerToApply.name));
                                        } else {
                                            AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, powerToApply.name));
                                        }
                                    } else {
                                        AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, powerToApply.name + TEXT[3]));
                                    }

                                    AbstractDungeon.onModifyPower();
                                    if (this.target.isPlayer) {
                                        int buffCount = 0;

                                        for (AbstractPower power : this.target.powers)
                                        {
                                            if (power.type == AbstractPower.PowerType.BUFF)
                                            {
                                                ++buffCount;
                                            }
                                        }

                                        if (buffCount >= 10) {
                                            UnlockTracker.unlockAchievement("POWERFUL");
                                        }
                                    }
                                }
                                break label148;
                            }

                            p = var6.next();
                        } while(!p.ID.equals(powerToApply.ID));
                    } while(p.ID.equals("Night Terror"));

                    p.stackPower(this.amount);
                    p.flash();
                    if ((p instanceof StrengthPower || p instanceof DexterityPower) && this.amount <= 0) {
                        AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, powerToApply.name + TEXT[3]));
                    } else if (this.amount > 0) {
                        if (p.type != AbstractPower.PowerType.BUFF && !(p instanceof StrengthPower) && !(p instanceof DexterityPower)) {
                            AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, "+" + Integer.toString(this.amount) + " " + powerToApply.name));
                        } else {
                            AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, "+" + Integer.toString(this.amount) + " " + powerToApply.name));
                        }
                    } else if (p.type == AbstractPower.PowerType.BUFF) {
                        AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, powerToApply.name + TEXT[3]));
                    } else {
                        AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, powerToApply.name + TEXT[3]));
                    }

                    p.updateDescription();
                    hasBuffAlready = true;
                    AbstractDungeon.onModifyPower();
                }
            }

            this.tickDuration();
        } else {
            this.isDone = true;
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ApplyPowerAction");
        TEXT = uiStrings.TEXT;
    }
}
