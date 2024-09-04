package code.cards;

import code.actions.ConditionalAction;
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
        baseDamage = 12;
        baseBlock = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(m.currentBlock > 0)
        {
            applyDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }

        addToBot(new ConditionalAction(() -> Arrays.stream(AttackIntents).noneMatch(m.intent::equals), getBlockAction()));
    }

    public void upp()
    {
        upgradeDamage(4);
        upgradeBlock(4);
    }

    private static final AbstractMonster.Intent[] AttackIntents = new AbstractMonster.Intent[]
            {AbstractMonster.Intent.ATTACK, AbstractMonster.Intent.ATTACK_BUFF, AbstractMonster.Intent.ATTACK_DEBUFF, AbstractMonster.Intent.ATTACK_DEFEND};
}