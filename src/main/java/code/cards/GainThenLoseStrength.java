package code.cards;

import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class GainThenLoseStrength extends AbstractEasyCard
{
    public final static String ID = makeID("GainThenLoseStrength");
    // intellij stuff skill, self, Uncommon, , , , , 4, 2

    public GainThenLoseStrength()
    {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        baseSecondMagic = secondMagic = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.applyToSelf(new StrengthPower(p, magicNumber));
        Wiz.applyToSelf(new LoseStrengthPower(p, secondMagic));
    }

    public void upp()
    {
        upgradeMagicNumber(2);
        upgradeSecondMagic(2);
    }
}