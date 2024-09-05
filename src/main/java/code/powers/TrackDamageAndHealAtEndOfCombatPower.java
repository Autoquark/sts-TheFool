package code.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.TheFoolMod.makeID;

public class TrackDamageAndHealAtEndOfCombatPower extends AbstractEasyPower
{
    public final static String POWER_ID = makeID("TrackDamageAndHealAtEndOfCombatPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public TrackDamageAndHealAtEndOfCombatPower(AbstractCreature target, int amount)
    {
        super(POWER_ID, NAME, PowerType.BUFF, false, target, amount);
        isTwoAmount = true;
        amount2 = 0;

        updateDescription();
    }

    public void atStartOfTurn() {
        this.addToBot(new LoseEnergyAction(this.amount));
        this.flash();
    }

    @Override
    public int onLoseHp(int damageAmount)
    {
        amount2 += damageAmount;
        updateDescription();
        return super.onLoseHp(damageAmount);
    }

    @Override
    public void onVictory()
    {
        super.onVictory();

        this.flash();
        AbstractPlayer player = AbstractDungeon.player;
        if (player.currentHealth > 0) {
            player.heal(amount2);
        }
    }

    @Override
    public void updateDescription()
    {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1] + amount + powerStrings.DESCRIPTIONS[2] + amount2;
    }
}
