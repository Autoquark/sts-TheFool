package code.cards;

import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.unique.RandomCardFromDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TopdeckAndDrawFromDiscard extends AbstractEasyCard
{
    public final static String ID = makeID("TopdeckAndDrawFromDiscard");
    // intellij stuff skill, self, UNCOMMON, , , , , , 

    public TopdeckAndDrawFromDiscard()
    {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new PutOnDeckAction(p, p, 1, false));
        addToBot(new RandomCardFromDiscardPileToHandAction());
    }

    public void upp()
    {
        upgradeBaseCost(0);
    }
}