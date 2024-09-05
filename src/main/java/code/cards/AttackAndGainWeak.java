package code.cards;

import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class AttackAndGainWeak extends AbstractEasyCard
{
    public final static String ID = makeID("AttackAndGainWeak");
    // intellij stuff , , , , , , , , 

    public AttackAndGainWeak()
    {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        applyDamage(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        Wiz.applyToSelf(new WeakPower(p, magicNumber, false));
    }

    public void upp()
    {
        upgradeDamage(4);
    }
}