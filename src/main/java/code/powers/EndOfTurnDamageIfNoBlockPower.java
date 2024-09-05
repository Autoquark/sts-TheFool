package code.powers;

import code.actions.ConditionalAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.TheFoolMod.makeID;

public class EndOfTurnDamageIfNoBlockPower extends AbstractEasyPower
{
    public final static String POWER_ID = makeID("EndOfTurnDamageIfNoBlockPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public EndOfTurnDamageIfNoBlockPower(AbstractCreature owner, int amount)
    {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer)
    {
        super.atEndOfTurn(isPlayer);
        // Do the check now so that other end of turn effects that grant block don't affect it
        if(isPlayer && AbstractDungeon.player.currentBlock == 0)
        {
            addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.LIGHTNING));
        }
    }

    @Override
    public void updateDescription()
    {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
