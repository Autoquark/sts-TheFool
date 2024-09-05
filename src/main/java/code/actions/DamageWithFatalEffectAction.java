package code.actions;

import code.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DamageWithFatalEffectAction extends DamageAction
{
    private final DamageInfo info;
    private final AbstractGameAction[] actionsIfFatal;

    public DamageWithFatalEffectAction(AbstractCreature target, DamageInfo info, AbstractGameAction... actionsIfFatal)
    {
        super(target, info);
        this.info = info;
        this.actionsIfFatal = actionsIfFatal;
        actionType = ActionType.DAMAGE;
    }

    @Override
    public void update()
    {
        boolean doCheck = !this.shouldCancelAction() || this.info.type == DamageInfo.DamageType.THORNS;
        super.update();
        if(!doCheck)
        {
            return;
        }

        if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion"))
        {
            for(int i = actionsIfFatal.length - 1; i >= 0; i--)
            {
                addToTop(actionsIfFatal[i]);
            }
        }

        isDone = true;
    }
}
