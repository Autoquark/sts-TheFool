package code.cards;

import code.util.Wiz;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

import static code.TheFoolMod.makeID;

public class BlockIfNotFrail extends AbstractEasyCard
{
    public final static String ID = makeID("BlockIfNotFrail");
    // intellij stuff attack, self, common, , , 8, 10, 2, 

    public BlockIfNotFrail()
    {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 11;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(!p.hasPower(FrailPower.POWER_ID))
        {
            applyBlock();
            Wiz.applyToSelf(new FrailPower(p,magicNumber, false));
        }
    }

    public void upp()
    {
        upgradeBlock(3);
    }
}