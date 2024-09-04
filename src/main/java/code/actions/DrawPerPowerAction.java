package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DrawPerPowerAction extends AbstractGameAction
{
    private final PowerTypeFilter filter;
    private final int multiplier;

    public DrawPerPowerAction(AbstractCreature source, PowerTypeFilter filter, int multiplier)
    {
        this.multiplier = multiplier;
        this.source = source;
        this.filter = filter;
    }

    @Override
    public void update()
    {
        addToBot(new DrawCardAction(source, (int)source.powers.stream().filter(filter::powerMatchesFilter).count() * multiplier));
        this.isDone = true;
    }
}
