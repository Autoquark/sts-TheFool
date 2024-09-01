package code.actions;

import code.TheFoolMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class SetIsInStartOfTurnDrawAction extends AbstractGameAction
{
    private boolean value;

    public SetIsInStartOfTurnDrawAction(boolean value)
    {
        this.value = value;
    }

    @Override
    public void update()
    {
        TheFoolMod.setIsInStartOfTurnDraw(value);
        this.isDone = true;
    }
}
