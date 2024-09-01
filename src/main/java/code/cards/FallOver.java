package code.cards;

import static code.TheFoolMod.makeID;

import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.CardModifierPatches;
import code.cardmodifiers.ImpulsiveCardModifier;
import com.megacrit.cardcrawl.actions.common.EndTurnAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FallOver extends AbstractEasyCard
{
    public final static String ID = makeID("FallOver");
    // intellij stuff skill, self, rare, , , 10, 4, , 

    public FallOver()
    {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseBlock = 10;
        CardModifierManager.addModifier(this, new ImpulsiveCardModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        applyBlock();
        addToBot(new EndTurnAction());
    }

    public void upp()
    {
        upgradeBlock(4);
    }
}