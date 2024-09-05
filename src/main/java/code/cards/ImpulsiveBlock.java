package code.cards;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.ImpulsiveCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.TheFoolMod.makeID;

public class ImpulsiveBlock extends AbstractEasyCard
{
    public final static String ID = makeID("ImpulsiveBlock");
    // intellij stuff attack, enemy, common, 9, 3, , , ,

    public ImpulsiveBlock()
    {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;

        CardModifierManager.addModifier(this, new ImpulsiveCardModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        applyBlock();
    }

    public void upp()
    {
        upgradeBlock(3);
    }
}