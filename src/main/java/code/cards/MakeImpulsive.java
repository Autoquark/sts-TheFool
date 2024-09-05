package code.cards;

import basemod.devcommands.draw.Draw;
import basemod.helpers.CardModifierManager;
import code.actions.MoveSpecificCardToDeckAction;
import code.actions.SelectCardsAndDoThingAction;

import static code.TheFoolMod.makeID;

import code.cardmodifiers.ImpulsiveCardModifier;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MakeImpulsive extends AbstractEasyCard
{
    public final static String ID = makeID("MakeImpulsive");
    // intellij stuff skill, self, uncommon, , , , , , 

    public MakeImpulsive()
    {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DrawCardAction(1));
        addToBot(new SelectCardsAndDoThingAction(p, p, 1, false,
                (card, group) ->
                {
                    CardModifierManager.addModifier(card, new ImpulsiveCardModifier(false));
                    if(upgraded)
                    {
                        addToBot(new MoveSpecificCardToDeckAction(card, group));
                    }
                    else
                    {
                        addToBot(new DiscardSpecificCardAction(card, group));
                    }
                }));
    }

    public void upp()
    {

    }
}