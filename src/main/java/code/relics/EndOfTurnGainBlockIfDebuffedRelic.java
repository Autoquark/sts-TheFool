package code.relics;

import code.TheFool;
import code.actions.ConditionalAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.TheFoolMod.makeID;

public class EndOfTurnGainBlockIfDebuffedRelic extends AbstractEasyRelic
{
    public static final String ID = makeID("EndOfTurnGainBlockIfDebuffedRelic");

    public EndOfTurnGainBlockIfDebuffedRelic()
    {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, TheFool.Enums.COLOR_YELLOW);
    }

    @Override
    public void onPlayerEndTurn()
    {
        super.onPlayerEndTurn();

        addToTop(new ConditionalAction(
                () -> AbstractDungeon.player.powers.stream().anyMatch(x -> x.type == AbstractPower.PowerType.DEBUFF),
                new GainBlockAction(AbstractDungeon.player, 2),
                new RelicAboveCreatureAction(AbstractDungeon.player, this)
        ));
    }
}
