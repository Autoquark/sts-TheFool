package code.cards;

import static code.TheFoolMod.makeID;

import code.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DiscardCardAndDealDamage extends AbstractEasyCard {
    public final static String ID = makeID("DiscardCardAndDealDamage");
    // intellij stuff attack, enemy, rare, 12, 3, , , , 

    public DiscardCardAndDealDamage()
    {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DiscardAction(p, p, 1, true));
        applyDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new DrawCardAction(1));
    }

    public void upp()
    {
        upgradeDamage(3);
    }
}