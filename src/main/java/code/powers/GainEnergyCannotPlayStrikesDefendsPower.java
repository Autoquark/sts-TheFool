package code.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.TheFoolMod.makeID;

public class GainEnergyCannotPlayStrikesDefendsPower extends AbstractEasyPower
{
    public final static String POWER_ID = makeID("EnergyCannotPlayStrikesDefendsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public GainEnergyCannotPlayStrikesDefendsPower(AbstractCreature owner, int amount)
    {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public boolean canPlayCard(AbstractCard card)
    {
        return super.canPlayCard(card) && !card.isStarterStrike() && !card.isStarterDefend();
    }
}
