package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ModifyCostForCombatAction extends AbstractGameAction
{
    private final AbstractCard card;

    public ModifyCostForCombatAction(AbstractCard card, int amount)
    {
        this.card = card;
        this.amount = amount;
    }

    @Override
    public void update()
    {
        card.modifyCostForCombat(amount);
        this.isDone = true;
    }
}
