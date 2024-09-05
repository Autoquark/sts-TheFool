package code.cards;

import basemod.helpers.CardModifierManager;
import code.actions.DiscardImpulsiveAndDrawAction;
import code.cardmodifiers.ImpulsiveCardModifier;
import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DiscardImpulsiveAndDraw extends AbstractEasyCard
{
    public final static String ID = makeID("DiscardImpulsiveAndDraw");
    // intellij stuff skill, self, uncommon, , , , , , 

    public DiscardImpulsiveAndDraw()
    {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DiscardImpulsiveAndDrawAction());
    }

    public void upp()
    {
        CardModifierManager.addModifier(this, new ImpulsiveCardModifier(true));
    }
}