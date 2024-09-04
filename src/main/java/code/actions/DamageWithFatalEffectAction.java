package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DamageWithFatalEffectAction extends DamageAction
{
    private final DamageInfo info;
    private final AbstractGameAction actionIfFatal;

    public DamageWithFatalEffectAction(AbstractCreature target, DamageInfo info, AbstractGameAction actionIfFatal)
    {
        super(target, info);
        this.info = info;
        this.actionIfFatal = actionIfFatal;
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
            addToTop(actionIfFatal);
        }

    }
}
