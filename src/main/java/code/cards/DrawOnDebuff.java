package code.cards;

import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.powers.DrawOnDebuffPower;
import code.util.Wiz;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DrawOnDebuff extends AbstractEasyCard
{
    public final static String ID = makeID("DrawOnDebuff");
    // intellij stuff power, self, uncommon, , , , , 1, 

    public DrawOnDebuff()
    {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.applyToSelf(new DrawOnDebuffPower(p, magicNumber));
    }

    public void upp()
    {
        upgradeBaseCost(1);
    }
}