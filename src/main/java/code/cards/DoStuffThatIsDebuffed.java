package code.cards;

import static code.TheFoolMod.makeID;

import code.actions.ConditionalAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class DoStuffThatIsDebuffed extends AbstractEasyCard
{
    public final static String ID = makeID("DoStuffThatIsDebuffed");
    // intellij stuff attack, enemy, rare, 10, 2, 10, 2, , 

    public DoStuffThatIsDebuffed()
    {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 10;
        baseBlock = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ConditionalAction(() -> p.hasPower(WeakPower.POWER_ID), getDamageAction(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY)));
        addToBot(new ConditionalAction(() -> p.hasPower(FrailPower.POWER_ID), getBlockAction()));
        addToBot(new ConditionalAction(() -> p.hasPower(VulnerablePower.POWER_ID), getBlockAction()));
    }

    public void upp()
    {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}