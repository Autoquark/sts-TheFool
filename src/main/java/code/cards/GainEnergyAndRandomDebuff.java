package code.cards;

import code.actions.RandomActionFromList;

import static code.TheFoolMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class GainEnergyAndRandomDebuff extends AbstractEasyCard
{
    public final static String ID = makeID("GainEnergyAndRandomDebuff");
    // intellij stuff skill, , , , , , , 1, 1

    public GainEnergyAndRandomDebuff()
    {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new RandomActionFromList(
                new ApplyPowerAction(p, p, new WeakPower(p, 1, false)),
                new ApplyPowerAction(p, p, new FrailPower(p, 1, false)),
                new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false))
        ));
    }

    public void upp()
    {
        upgradeMagicNumber(1);
    }
}