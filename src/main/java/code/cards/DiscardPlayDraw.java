package code.cards;

import static code.TheFoolMod.makeID;

import code.actions.PlayRandomCardsFromHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DiscardPlayDraw extends AbstractEasyCard
{
    public final static String ID = makeID("DiscardPlayDraw");
    // intellij stuff skill, self, rare, , , , , , 

    public DiscardPlayDraw()
    {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DiscardAction(p, p, 1, false));
        addToBot(new PlayRandomCardsFromHandAction(2));
        addToBot(new DrawCardAction(1));
    }

    public void upp()
    {
        upgradeBaseCost(1);
    }
}