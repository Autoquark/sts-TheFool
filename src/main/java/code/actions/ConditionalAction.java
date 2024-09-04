package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import org.apache.commons.lang3.ArrayUtils;

import java.util.function.Supplier;

public class ConditionalAction extends AbstractGameAction
{
    private final Supplier<Boolean> condition;
    private final AbstractGameAction[] actions;

    public ConditionalAction(Supplier<Boolean> condition, AbstractGameAction... actions)
    {
        this.condition = condition;
        this.actions = actions;
    }

    @Override
    public void update()
    {
        if(condition.get())
        {
            // Since we are adding to top we must go in reverse order
            for(int i = actions.length - 1; i >= 0; i--)
            {
                addToTop(actions[i]);
            }
        }

        this.isDone = true;
    }
}
