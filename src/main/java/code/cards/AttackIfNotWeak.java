package code.cards;

import static code.TheFoolMod.makeID;

import code.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class AttackIfNotWeak extends AbstractEasyCard
{
    public final static String ID = makeID("AttackIfNotWeak");
    // intellij stuff attack, enemy, common, 12, 15, , , 2, 

    public AttackIfNotWeak()
    {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 12;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(!p.hasPower(WeakPower.POWER_ID))
        {
            applyDamage(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            Wiz.applyToSelf(new WeakPower(p, magicNumber, false));
        }
    }

    public void upp()
    {
        upgradeDamage(3);
    }
}