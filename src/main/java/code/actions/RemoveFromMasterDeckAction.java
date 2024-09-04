package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemoveFromMasterDeckAction extends AbstractGameAction
{
    private final AbstractCard card;

    public RemoveFromMasterDeckAction(AbstractCard card)
    {
        this.card = card;
    }

    @Override
    public void update()
    {
        AbstractDungeon.player.masterDeck.removeCard(card);
    }
}
