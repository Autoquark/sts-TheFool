package code.cards;

import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.powers.EndOfTurnDamageIfNoBlockPower;
import code.util.Wiz;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EndOfTurnDamageIfNoBlock extends AbstractEasyCard
{
    public final static String ID = makeID("EndOfTurnDamageIfNoBlock");
    // intellij stuff skill, self, uncommon, , , , , 10, 3

    public EndOfTurnDamageIfNoBlock()
    {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.applyToSelf(new EndOfTurnDamageIfNoBlockPower(p, magicNumber));
    }

    public void upp()
    {
        upgradeMagicNumber(3);
    }
}