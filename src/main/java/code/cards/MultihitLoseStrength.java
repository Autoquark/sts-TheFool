package code.cards;

import code.actions.MultiHitDamageAction;
import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class MultihitLoseStrength extends AbstractEasyCard
{
    public final static String ID = makeID("MultihitLoseStrength");
    // intellij stuff ATTACK, ENEMY, COMMON, 1, , , , 6, 

    public MultihitLoseStrength()
    {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 1;
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new MultiHitDamageAction(m, new DamageInfo(p, damage), 6, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        Wiz.applyToSelf(new StrengthPower(p, -1));
    }

    public void upp()
    {
        upgradeMagicNumber(2);
    }
}