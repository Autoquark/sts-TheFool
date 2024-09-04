package code.cards;

import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.powers.GainEnergyCannotPlayStrikesDefendsPower;
import code.util.Wiz;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GainEnergyCannotPlayStrikesDefends extends AbstractEasyCard
{
    public final static String ID = makeID("GainEnergyCannotPlayStrikesDefends");
    // intellij stuff Power, self, Rare, , , , , 1, 

    public GainEnergyCannotPlayStrikesDefends()
    {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.applyToSelf(new GainEnergyCannotPlayStrikesDefendsPower(p, 1));
    }

    public void upp()
    {
        upgradeBaseCost(1);
    }
}