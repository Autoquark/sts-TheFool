package code.cardmodifiers;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import code.TheFool;
import code.TheFoolMod;
import code.actions.PlayCardRandomTargetAction;
import code.util.Wiz;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AutoplayCardAction;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.KeywordStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImpulsiveCardModifier extends AbstractCardModifier
{
    public static String ID = TheFoolMod.makeID("Impulsive");
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
            Wiz.addToTop(new PlayCardRandomTargetAction(card, AbstractDungeon.player.hand));
        }
    }

    @Override
    public String identifier(AbstractCard card)
    {
        return ID;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb)
    {
        renderIcon(card, sb, TheFoolMod.impulsiveIconRegion, 0);
        super.onRender(card, sb);
    }

    @Override
    public boolean shouldApply(AbstractCard card)
    {
        return super.shouldApply(card) && !CardModifierManager.hasModifier(card, ID);
    }

    private float renderIcon(AbstractCard card, SpriteBatch sb, TextureAtlas.AtlasRegion icon, float offset) {
        float cardScale = Settings.scale * card.drawScale * 0.45f; //0.45 because I want some space from the size, but technically 0.5 could also work.
        float iconScale = Settings.scale * card.drawScale * 0.35f; //0.35 is to get the icon to reasonable size. Note: changing the dimensions of files affects how big they are.

        sb.draw( icon,
                card.current_x + AbstractCard.RAW_W * cardScale * -0.4f + (icon.offsetX - (float) icon.originalWidth) * iconScale,
                card.current_y + AbstractCard.RAW_H * cardScale * 1.14f + (icon.offsetY - (float) icon.originalHeight) * iconScale + offset,
                (icon.originalWidth - icon.offsetX) * iconScale - AbstractCard.RAW_W * cardScale, //Center of a card, to rotate around with the card.
                (icon.originalHeight - icon.offsetY) * iconScale - AbstractCard.RAW_H * cardScale - offset,
                (float) icon.packedWidth * iconScale, //Render scale
                (float) icon.packedHeight * iconScale,
                1, //Don't touch this scale, as it also to distance from origin.
                1,
                card.angle);

        return icon.originalHeight * iconScale; //Returns height offset for the next icon;
    }

    @Override
    public void onCardModified(AbstractCard card)
    {
        super.onCardModified(card);
    }

    @Override
    public boolean isInherent(AbstractCard card)
    {
        return isInherent;
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card)
    {
        if(!isInherent)
        {
            return Collections.singletonList(new TooltipInfo(BaseMod.getKeywordTitle("thefool:impulsive"), BaseMod.getKeywordDescription("thefool:impulsive")));
        }
        return Collections.emptyList();
    }

    private boolean isInherent = true;
}
