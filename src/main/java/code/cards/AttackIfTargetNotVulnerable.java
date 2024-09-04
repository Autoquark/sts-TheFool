package code.cards;

import code.actions.ConditionalAction;
import code.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static code.TheFoolMod.makeID;

public class AttackIfTargetNotVulnerable extends AbstractEasyCard
{
    public final static String ID = makeID("AttackIfTargetNotVulnerable");
    // intellij stuff attack, enemy, common, 5, , , , , 

    public AttackIfTargetNotVulnerable()
    {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 5;
        baseSecondDamage = 5;
        baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        applyDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new ConditionalAction(
                () -> !m.hasPower(VulnerablePower.POWER_ID),
                new DamageAction(m, new DamageInfo(AbstractDungeon.player, secondDamage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY),
                new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false))));
    }

    public void upp()
    {
        upgradeSecondDamage(4);
    }
}