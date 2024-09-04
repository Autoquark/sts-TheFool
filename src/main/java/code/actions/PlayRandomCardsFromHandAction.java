package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlayRandomCardsFromHandAction extends AbstractGameAction
{
    private final int cardCount;

    public PlayRandomCardsFromHandAction(int cardCount)
    {
        this.cardCount = cardCount;
    }

    @Override
    public void update()
    {
        this.isDone = true;

        for(int i = 0; i < cardCount; i++)
        {
            if(AbstractDungeon.player.hand.isEmpty())
            {
                return;
            }

            AbstractCard card = AbstractDungeon.player.hand.getRandomCard(true);
            AbstractDungeon.player.hand.removeCard(card);
            AbstractDungeon.player.limbo.group.add(card);
            card.current_y = -200.0F * Settings.scale;
            card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            card.target_y = (float)Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.lighten(false);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            card.applyPowers();

            AbstractMonster target = AbstractDungeon.getRandomMonster();
            this.addToTop(new NewQueueCardAction(card, target, false, true));
            this.addToTop(new UnlimboAction(card));
            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }
        }

    }
}
