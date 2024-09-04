package code.actions;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CopyPowersAction extends AbstractGameAction
{
    private final PowerTypeFilter filter;

    public CopyPowersAction(AbstractCreature source, AbstractCreature target, PowerTypeFilter filter)
    {
        this.filter = filter;
        this.source = source;
        this.target = target;
    }

    @Override
    public void update()
    {
        for(AbstractPower power : source.powers)
        {
            if(filter.powerMatchesFilter(power))
            {
                if(power instanceof CloneablePowerInterface)
                {
                    addToBot(new ApplyPowerAction(target, source, ((CloneablePowerInterface) power).makeCopy()));
                }
            }
        }
    }
}
