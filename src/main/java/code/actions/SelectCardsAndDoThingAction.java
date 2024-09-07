package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.util.BiConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;
import java.util.function.Predicate;

public class SelectCardsAndDoThingAction extends AbstractGameAction
{
    private final BiConsumer<AbstractCard, CardGroup> thingToDo;

    // Filters which cards are eligible to be selected. Only supported for random, because the card select dialog doesn't support it
    private final Predicate<AbstractCard> filter;
    private final String prompt;
    private AbstractPlayer player;
    private boolean isRandom;
    public static int numDiscarded;
    private static final float DURATION;

    public static SelectCardsAndDoThingAction makeRandom(AbstractCreature target, AbstractCreature source, int amount, BiConsumer<AbstractCard, CardGroup> thingToDo)
    {
        return makeRandom(target, source, amount, thingToDo, x -> true);
    }
    public static SelectCardsAndDoThingAction makeRandom(AbstractCreature target, AbstractCreature source, int amount, BiConsumer<AbstractCard, CardGroup> thingToDo, Predicate<AbstractCard> filter)
    {
        return new SelectCardsAndDoThingAction(target, source, amount, true, "", thingToDo, filter);
    }

    public static SelectCardsAndDoThingAction makeNonRandom(AbstractCreature target, AbstractCreature source, int amount, String prompt, BiConsumer<AbstractCard, CardGroup> thingToDo)
    {
        return new SelectCardsAndDoThingAction(target, source, amount, false, prompt, thingToDo, x -> true);
    }

    protected SelectCardsAndDoThingAction(AbstractCreature target,
                                          AbstractCreature source,
                                          int amount,
                                          boolean isRandom,
                                          String prompt,
                                          BiConsumer<AbstractCard, CardGroup> thingToDo,
                                          Predicate<AbstractCard> filter) {
        this.player = (AbstractPlayer)target;
        this.isRandom = isRandom;
        this.prompt = prompt;
        this.filter = filter;
        this.setValues(target, source, amount);
        this.actionType = ActionType.DISCARD;
        this.duration = DURATION;
        this.thingToDo = thingToDo;
    }

    // Adapted from DiscardAction
    public void update()
    {
        AbstractCard[] filteredHand = AbstractDungeon.player.hand.group.stream().filter(filter).toArray(AbstractCard[]::new);
        if (this.duration == DURATION)
        {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }

            if (filteredHand.length <= this.amount) {
                this.amount = filteredHand.length;

                for (AbstractCard card : filteredHand)
                {
                    thingToDo.accept(card, this.player.hand);
                }

                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }

            if (!this.isRandom) {
                if (this.amount < 0) {
                    AbstractDungeon.handCardSelectScreen.open(prompt, 99, true, true);
                    AbstractDungeon.player.hand.applyPowers();
                    this.tickDuration();
                    return;
                }

                numDiscarded = this.amount;
                if (this.player.hand.size() > this.amount)
                {
                    AbstractDungeon.handCardSelectScreen.open(prompt, this.amount, false);
                }

                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }

            ArrayList<AbstractCard> handCopy = new ArrayList<>(this.player.hand.group);
            for(int i = 0; i < this.amount; ++i) {
                AbstractCard card = handCopy.get(AbstractDungeon.cardRandomRng.random(0, handCopy.size() - 1));
                handCopy.remove(card);
                thingToDo.accept(card, player.hand);
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
        {
            for (AbstractCard abstractCard : AbstractDungeon.handCardSelectScreen.selectedCards.group)
            {
                thingToDo.accept(abstractCard, AbstractDungeon.handCardSelectScreen.selectedCards);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }

    static
    {
        DURATION = Settings.ACTION_DUR_XFAST;
    }
}
