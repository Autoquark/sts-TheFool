package code.cards;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.ImpulsiveCardModifier;

import static code.TheFoolMod.makeID;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ImpulsiveDraw extends AbstractEasyCard
{
    public final static String ID = makeID("ImpulsiveDraw");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public ImpulsiveDraw()
    {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;

        CardModifierManager.addModifier(this, new ImpulsiveCardModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DrawCardAction(magicNumber));
    }

    public void upp()
    {
        upgradeMagicNumber(1);
    }
}