package code.cardmodifiers;

import basemod.abstracts.AbstractCardModifier;
import code.TheFool;
import code.TheFoolMod;
import code.actions.PlayCardRandomTargetAction;
import code.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AutoplayCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImpulsiveCardModifier extends AbstractCardModifier
{
    public static final Logger logger = LogManager.getLogger(TheFoolMod.class.getName());

    public ImpulsiveCardModifier(boolean isInherent)
    {
        this.isInherent = isInherent;
    }

    @Override
    public AbstractCardModifier makeCopy()
    {
        return new ImpulsiveCardModifier(isInherent);
    }

    @Override
    public void onDrawn(AbstractCard card)
    {
        super.onDrawn(card);
        if(!TheFoolMod.getIsInStartOfTurnDraw())
        {
            logger.error("Played impulsively!");
            Wiz.addToTop(new PlayCardRandomTargetAction(card, AbstractDungeon.player.hand));
        }
    }

    @Override
    public void onCardModified(AbstractCard card)
    {
        super.onCardModified(card);
        card.description.add(0, new DescriptionLine("Impulsive", 100));
    }

    @Override
    public boolean isInherent(AbstractCard card)
    {
        return isInherent;
    }

    private boolean isInherent = true;
}
