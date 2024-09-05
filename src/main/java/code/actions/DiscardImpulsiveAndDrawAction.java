package code.actions;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.ImpulsiveCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.stream.Stream;

public class DiscardImpulsiveAndDrawAction extends AbstractGameAction
{
    @Override
    public void update()
    {
        AbstractCard[] impulsiveCards = AbstractDungeon.player.hand.group.stream().filter(x -> CardModifierManager.hasModifier(x, ImpulsiveCardModifier.ID)).toArray(AbstractCard[]::new);
        for(AbstractCard impulsiveCard : impulsiveCards)
        {
            addToBot(new DiscardSpecificCardAction(impulsiveCard, AbstractDungeon.player.hand));
        }

        addToBot(new DrawCardAction(impulsiveCards.length));
    }
}
