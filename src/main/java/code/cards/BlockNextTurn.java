package code.cards;

import static code.TheFoolMod.makeID;

import code.util.Wiz;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

public class BlockNextTurn extends AbstractEasyCard
{
    public final static String ID = makeID("BlockNextTurn");
    // intellij stuff skill, self, rare, , , 8, 11, , 

    public BlockNextTurn()
    {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 11;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.applyToSelf(new NextTurnBlockPower(p, block));
    }

    public void upp()
    {
        upgradeBlock(4);
    }
}