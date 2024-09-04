package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class MultiHitDamageAction extends AbstractGameAction
{
    private final int strikes;
    private final DamageInfo damageInfo;
    //private int strikesDone = 0;

    public MultiHitDamageAction(AbstractCreature target, DamageInfo damageInfo, int strikes, AttackEffect attackEffect)
    {
        this.damageInfo = damageInfo;
        this.attackEffect = attackEffect;
        this.damageType = damageInfo.type;
        this.target = target;
        this.strikes = strikes;

        this.setValues(target, damageInfo);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.BLUNT_LIGHT;
        this.duration = 0.01F;
    }

    @Override
    public void update()
    {
        //AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX + MathUtils.random(-100.0F * Settings.xScale, 100.0F * Settings.xScale), this.target.hb.cY + MathUtils.random(-100.0F * Settings.scale, 100.0F * Settings.scale), this.attackEffect));
        for(int i = 0; i < strikes; i++)
        {
            addToTop(new DamageAction(target, damageInfo, attackEffect));
        }
        this.isDone = true;
    }
}
