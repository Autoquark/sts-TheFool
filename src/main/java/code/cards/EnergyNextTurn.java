package code.cards;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.ImpulsiveCardModifier;
import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.util.Wiz;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;

public class EnergyNextTurn extends AbstractEasyCard
{
    public final static String ID = makeID("EnergyNextTurn");
    // intellij stuff skill, self, UNCOMMON, , , , , 1, 1

    public EnergyNextTurn()
    {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;

        CardModifierManager.addModifier(this, new ImpulsiveCardModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.applyToSelf(new EnergizedPower(p, magicNumber));
    }

    public void upp()
    {
        upgradeMagicNumber(1);
    }
}