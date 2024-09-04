package code.cards;

import code.actions.DrawPerPowerAction;
import code.actions.PowerTypeFilter;
import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DrawPerDebuff extends AbstractEasyCard
{
    public final static String ID = makeID("DrawPerDebuff");
    // intellij stuff skill, self, Uncommon, , , , , , 

    public DrawPerDebuff()
    {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(upgraded)
        {
            addToBot(new PutOnDeckAction(p, p, 1, false));
        }
        addToBot(new DrawPerPowerAction(p, PowerTypeFilter.DebuffsOnly, magicNumber));
    }

    public void upp()
    {
        upgradeMagicNumber(1);
    }
}