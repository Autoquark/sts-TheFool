package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.logging.log4j.util.BiConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SelectCardsAndDoThingAction extends AbstractGameAction
{
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final BiConsumer<AbstractCard, CardGroup> thingToDo;
    private AbstractPlayer player;
    private boolean isRandom;
    private boolean endTurn;
    public static int numDiscarded;
    private static final float DURATION;

    public SelectCardsAndDoThingAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, BiConsumer<AbstractCard, CardGroup> thingToDo) {
        this(target, source, amount, isRandom, false, thingToDo);
    }

    public SelectCardsAndDoThingAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean endTurn, BiConsumer<AbstractCard, CardGroup> thingToDo) {
        this.player = (AbstractPlayer)target;
        this.isRandom = isRandom;
        this.setValues(target, source, amount);
        this.actionType = ActionType.DISCARD;
        this.endTurn = endTurn;
        this.duration = DURATION;
        this.thingToDo = thingToDo;
    }

    // Adapted from DiscardAction
    public void update()
    {
        if (this.duration == DURATION)
        {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }

            if (this.player.hand.size() <= this.amount) {
                this.amount = this.player.hand.size();
                int handSize = this.player.hand.size();

                for(int i = 0; i < handSize; ++i) {
                    AbstractCard card = this.player.hand.getTopCard();
                    thingToDo.accept(card, this.player.hand);
                }

                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }

            if (!this.isRandom) {
                if (this.amount < 0) {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
                    AbstractDungeon.player.hand.applyPowers();
                    this.tickDuration();
                    return;
                }

                numDiscarded = this.amount;
                if (this.player.hand.size() > this.amount) {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false);
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

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
        TEXT = uiStrings.TEXT;
        DURATION = Settings.ACTION_DUR_XFAST;
    }
}
