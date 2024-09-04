package code.powers;

import code.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.TheFoolMod.makeID;

public class GainBlockOnDebuffPower extends AbstractEasyPower implements OnReceivePowerPower
{
    public final static String POWER_ID = makeID("GainBlockOnDebuffPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public GainBlockOnDebuffPower(AbstractCreature owner, int amount)
    {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source)
    {

    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1)
    {
        if(abstractPower.type == PowerType.DEBUFF)
        {
            Wiz.addToTop(new GainBlockAction(owner, amount));
        }
        return true;
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
