package code.relics;

import code.TheFool;
import code.actions.ConditionalAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static code.TheFoolMod.characterColor;
import static code.TheFoolMod.makeID;

public class GainEnergyIfDebuffedRelic extends AbstractEasyRelic
{
    public static final String ID = makeID("GainEnergyIfDebuffedRelic");

    public GainEnergyIfDebuffedRelic()
    {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheFool.Enums.COLOR_YELLOW);
    }

    @Override
    public void atTurnStart()
    {
        super.atTurnStart();
        addToBot(new ConditionalAction(
                () -> AbstractDungeon.player.powers.stream().anyMatch(x -> x.type == AbstractPower.PowerType.DEBUFF),
                new GainEnergyAction(1)));
    }
}
