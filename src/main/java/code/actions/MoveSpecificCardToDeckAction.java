package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

public class MoveSpecificCardToDeckAction extends AbstractGameAction
{
    private final AbstractCard card;
    private final CardGroup group;
    private final boolean shuffleIn;

    public MoveSpecificCardToDeckAction(AbstractCard card, CardGroup group)
    {
        this(card, group, false);
    }
    public MoveSpecificCardToDeckAction(AbstractCard card, CardGroup group, boolean shuffleIn)
    {
        this.card = card;
        this.group = group;
        this.shuffleIn = shuffleIn;
        actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update()
    {
        if(group.contains(card))
        {
            group.moveToDeck(card, shuffleIn);
        }
        isDone = true;
    }
}
