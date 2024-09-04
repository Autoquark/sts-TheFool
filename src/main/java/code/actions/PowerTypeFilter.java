package code.actions;

import com.megacrit.cardcrawl.powers.AbstractPower;

public enum PowerTypeFilter
{
    BuffsOnly,
    DebuffsOnly,
    Any;

    public boolean powerMatchesFilter(AbstractPower power)
    {
        if(this == Any)
        {
            return true;
        }

        return this == BuffsOnly && power.type == AbstractPower.PowerType.BUFF || this == DebuffsOnly && power.type == AbstractPower.PowerType.DEBUFF;
    }
}


