package code.cards;

import code.actions.ConditionalAction;
import code.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

import static code.TheFoolMod.makeID;

public class BlockAndGainFrail extends AbstractEasyCard
{
    public final static String ID = makeID("BlockAndGainFrail");
    // intellij stuff attack, self, common, , , 8, 10, 2,

    public BlockAndGainFrail()
    {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 12;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        applyBlock();
        Wiz.applyToSelf(new FrailPower(p,magicNumber, false));
    }

    public void upp()
    {
        upgradeBlock(4);
    }
}