package code.cards;

import basemod.helpers.CardModifierManager;
import code.actions.DamageWithFatalEffectAction;
import code.actions.RemoveFromMasterDeckAction;
import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LoseOnFatalAttack extends AbstractEasyCard
{
    public final static String ID = makeID("LoseOnFatalAttack");
    // intellij stuff attack, enemy, RARE, 15, 5, , , , 

    public LoseOnFatalAttack()
    {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DamageWithFatalEffectAction(m, new DamageInfo(p, damage), new RemoveFromMasterDeckAction(this)));
    }

    public void upp()
    {
        upgradeDamage(5);
    }
}