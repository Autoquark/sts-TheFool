package code.cards;

import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.powers.GainBlockOnDebuffPower;
import code.powers.LambdaPower;
import code.util.Wiz;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GainBlockOnDebuff extends AbstractEasyCard
{
    public final static String ID = makeID("GainBlockOnDebuff");
    // intellij stuff power, self, uncommon, , , , , 6, 8

    public GainBlockOnDebuff()
    {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.applyToSelf(new GainBlockOnDebuffPower(p, magicNumber));
    }

    public void upp()
    {
        upgradeMagicNumber(1);
    }
}