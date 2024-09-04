package code.cards;

import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.util.Wiz;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

public class ConvertBlockToNextTurnBlock extends AbstractEasyCard
{
    public final static String ID = makeID("ConvertBlockToNextTurnBlock");
    // intellij stuff skill, self, RARE, , , 8, 3, , 

    public ConvertBlockToNextTurnBlock()
    {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        applyBlock();
        addToBot(Wiz.actionify(() -> Wiz.applyToSelf(new NextTurnBlockPower(AbstractDungeon.player, AbstractDungeon.player.currentBlock))));
        addToBot(new LoseBlockAction(p, p, 9999));
    }

    public void upp()
    {
        upgradeBlock(3);
    }
}