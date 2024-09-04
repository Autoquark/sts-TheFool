package code.cards;

import code.actions.ConditionalAction;
import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class GainStrengthDexIfNegative extends AbstractEasyCard
{
    public final static String ID = makeID("GainStrengthDexIfNegative");
    // intellij stuff skill, self, rare, , , , , , 

    public GainStrengthDexIfNegative()
    {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ConditionalAction(
                () -> p.hasPower(StrengthPower.POWER_ID) && p.getPower(StrengthPower.POWER_ID).amount < 0,
                new RemoveSpecificPowerAction(p, p, StrengthPower.POWER_ID),
                new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber))
        ));

        addToBot(new ConditionalAction(
                () -> p.hasPower(DexterityPower.POWER_ID) && p.getPower(DexterityPower.POWER_ID).amount < 0,
                new RemoveSpecificPowerAction(p, p, DexterityPower.POWER_ID),
                new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber))
        ));
    }

    public void upp()
    {
        upgradeMagicNumber(1);
    }
}