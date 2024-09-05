package code.cards;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.ImpulsiveCardModifier;

import static code.TheFoolMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ImpulsiveAttack extends AbstractEasyCard
{
    public final static String ID = makeID("ImpulsiveAttack");
    // intellij stuff attack, enemy, common, 9, 3, , , , 

    public ImpulsiveAttack()
    {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;

        CardModifierManager.addModifier(this, new ImpulsiveCardModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        applyDamage(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    public void upp()
    {
        upgradeDamage(3);
    }
}