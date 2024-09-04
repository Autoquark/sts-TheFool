package code.relics;

import code.TheFool;
import code.actions.ConditionalAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static code.TheFoolMod.makeID;

public class DrawCardAtStartOfTurnIfDebuffedRelic extends AbstractEasyRelic
{
    public static final String ID = makeID("DrawCardAtStartOfTurnIfDebuffedRelic");

    public DrawCardAtStartOfTurnIfDebuffedRelic()
    {
        super(ID, AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.CLINK, TheFool.Enums.COLOR_YELLOW);
    }

    @Override
    public void atTurnStart()
    {
        super.atTurnStart();
        addToBot(new ConditionalAction(
                () -> AbstractDungeon.player.powers.stream().anyMatch(x -> x.type == AbstractPower.PowerType.DEBUFF),
                new DrawCardAction(1)
        ));
    }
}
