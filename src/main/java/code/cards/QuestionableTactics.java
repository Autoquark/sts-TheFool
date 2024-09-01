package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Arrays;

import static code.TheFoolMod.makeID;

public class QuestionableTactics extends AbstractEasyCard
{
    public final static String ID = makeID("QuestionableTactics");
    // intellij stuff attack, enemy, common, 8, 10, 8, 10, , 

    public QuestionableTactics()
    {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(m.currentBlock > 0)
        {
            applyDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }

        if(!Arrays.stream(AttackIntents).anyMatch(m.intent::equals))
        {
            applyBlock();
        }
    }

    public void upp()
    {
        upgradeDamage(2);
        upgradeBlock(2);

    }

    private static AbstractMonster.Intent[] AttackIntents = new AbstractMonster.Intent[]
            {AbstractMonster.Intent.ATTACK, AbstractMonster.Intent.ATTACK_BUFF, AbstractMonster.Intent.ATTACK_DEBUFF, AbstractMonster.Intent.ATTACK_DEFEND};
}