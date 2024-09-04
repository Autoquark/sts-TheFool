package code.cards;

import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class GainStrengthLoseDex extends AbstractEasyCard
{
    public final static String ID = makeID("GainStrengthLoseDex");
    // intellij stuff skill, self, rare, , , , , 2, 1

    public GainStrengthLoseDex()
    {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -secondMagic)));
    }

    public void upp()
    {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}