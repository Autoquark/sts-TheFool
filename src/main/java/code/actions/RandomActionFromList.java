package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RandomActionFromList extends AbstractGameAction
{
    private final AbstractGameAction[] list;

    public RandomActionFromList(AbstractGameAction... list)
    {
        this.list = list;
    }

    @Override
    public void update()
    {
        addToBot(list[AbstractDungeon.cardRandomRng.random(list.length - 1)]);
        this.isDone = true;
    }
}
