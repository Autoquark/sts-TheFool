package code.cards;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.ImpulsiveCardModifier;
import code.util.Wiz;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static code.TheFoolMod.makeID;

public class ImpulsiveEnergy extends AbstractEasyCard
{
    public final static String ID = makeID("ImpulsiveEnergy");
    // intellij stuff skill, self, UNCOMMON, , , , , 1, 1

    public ImpulsiveEnergy()
    {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;

        CardModifierManager.addModifier(this, new ImpulsiveCardModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new DrawCardAction(1));
    }

    @Override
    public void upp()
    {
        exhaust = false;
    }
}