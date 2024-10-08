package code.cards;

import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.powers.TrackDamageAndHealAtEndOfCombatPower;
import code.util.Wiz;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TrackDamageAndHealAtEndOfCombat extends AbstractEasyCard
{
    public final static String ID = makeID("TrackDamageAndHealAtEndOfCombat");
    // intellij stuff POWER, SELF, RARE, , , , , , 

    public TrackDamageAndHealAtEndOfCombat()
    {
        super(ID, 2, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);

        // Multiple stacks means you heal more damage than you took, so players could be incentivised to stall for this
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.applyToSelf(new TrackDamageAndHealAtEndOfCombatPower(p, 1));
    }

    public void upp()
    {
        upgradeBaseCost(1);
    }
}