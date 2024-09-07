package code.cards;

import code.actions.ModifyCostForCombatAction;
import code.actions.ReduceCostRandomCardAction;
import code.actions.SelectCardsAndDoThingAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.TheFoolMod.makeID;

public class IncreaseCostReduceOtherCardCost extends AbstractEasyCard
{
    public final static String ID = makeID("IncreaseCostReduceOtherCardCost");

    public IncreaseCostReduceOtherCardCost()
    {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 6;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster target)
    {
        applyBlock();
        addToBot(new ReduceCostRandomCardAction(1, false));
        addToBot(new ModifyCostForCombatAction(this, 1));
    }

    @Override
    public void upp()
    {
        upgradeBaseCost(0);
    }
}
