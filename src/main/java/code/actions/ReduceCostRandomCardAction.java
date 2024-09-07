package code.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ReduceCostRandomCardAction extends AbstractGameAction
{
    private static final float baseDuration = Settings.ACTION_DUR_FAST;
    private final boolean onlyForTurn;

    public ReduceCostRandomCardAction(int amount, boolean onlyForTurn) {
        this.onlyForTurn = onlyForTurn;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = baseDuration;
        this.amount = amount;
    }

    @Override
    public void update()
    {
        if (this.duration == baseDuration)
        {
            AbstractCard[] candidates = new AbstractCard[0];
            // If we are reducing the cost for the entire combat, we prefer to reduce something that costs more than 0
            // this turn, even if the base cost for this combat is already 0. This is the same logic as Madness.
            // Downside is that we might pick a card where the cost for this combat is already 0 but is increased this turn,
            // over one where the base cost is > 0. I think it's best to be consistent with the base game though
            if(!onlyForTurn)
            {
                candidates = AbstractDungeon.player.hand.group.stream().filter(x -> x.cost > 0).toArray(AbstractCard[]::new);
            }
            if(candidates.length == 0)
            {
                candidates = AbstractDungeon.player.hand.group.stream().filter(x -> x.costForTurn > 0).toArray(AbstractCard[]::new);
            }

            if(candidates.length > 0)
            {
                AbstractCard card = candidates[AbstractDungeon.cardRandomRng.random(candidates.length - 1)];
                if(onlyForTurn)
                {
                    card.setCostForTurn(card.costForTurn - amount);
                }
                else
                {
                    card.modifyCostForCombat(-amount);
                }
                card.superFlash(Color.GOLD.cpy());
            }
        }

        this.tickDuration();
    }
}
