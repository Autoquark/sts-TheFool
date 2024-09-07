package code.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

import static code.TheFoolMod.makeID;

public class DrawReductionSelfAppliedPower extends AbstractEasyPower
{
    public final static String POWER_ID = makeID("DrawReductionSelfAppliedPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public DrawReductionSelfAppliedPower(AbstractCreature owner, int amount)
    {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
        this.region48 = atlas.findRegion("48/lessdraw");
        this.region128 = atlas.findRegion("128/lessdraw");
    }

    @Override
    public void onInitialApplication() {
        --AbstractDungeon.player.gameHandSize;
    }

    @Override
    public void onRemove() {
        ++AbstractDungeon.player.gameHandSize;
    }

    public void atEndOfRound()
    {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
    }

    @Override
    public void updateDescription()
    {
        if (this.amount == 1) {
            this.description = powerStrings.DESCRIPTIONS[0];
        } else {
            this.description = powerStrings.DESCRIPTIONS[1] + this.amount + powerStrings.DESCRIPTIONS[2];
        }
    }
}
