package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.TheFoolMod.makeID;

public class AttackCheaperPerDebuff extends AbstractEasyCard
{
    public final static String ID = makeID("AttackCheaperPerDebuff");
    public final static int baseCost = 3;
    // Track any externally applied cost override for this turn
    private int outsideCostForTurn = -1;
    // Track any externally applied cost modifications for this combat
    private int outsideCostForCombatModification = 0;

    public AttackCheaperPerDebuff()
    {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 24;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        applyDamage(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    public void upp()
    {
        upgradeDamage(6);
    }

    @Override
    public void setCostForTurn(int amt)
    {
        super.setCostForTurn(amt);
        outsideCostForTurn = costForTurn;
    }

    @Override
    public void modifyCostForCombat(int amt)
    {
        super.modifyCostForCombat(amt);
        outsideCostForCombatModification += amt;
    }

    @Override
    public void resetAttributes()
    {
        super.resetAttributes();
        outsideCostForTurn = -1;
    }

    @Override
    public void applyPowers()
    {
        super.applyPowers();
        int debuffCount = (int)AbstractDungeon.player.powers.stream().filter(x -> x.type == AbstractPower.PowerType.DEBUFF).count();

        int base = outsideCostForTurn != -1 ? outsideCostForTurn : baseCost;

        cost = base - debuffCount + outsideCostForCombatModification;
        costForTurn = cost;
        isCostModified = baseCost != costForTurn;
    }
}