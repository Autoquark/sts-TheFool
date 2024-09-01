package code.cards;

import static code.TheFoolMod.makeID;

import code.actions.ApplyPowerWithDynamicAmountAction;
import code.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class GainStrengthFromWeak extends AbstractEasyCard
{
    public final static String ID = makeID("GainStrengthFromWeak");
    // intellij stuff skill, self, rare, , , , , 1, 

    public GainStrengthFromWeak()
    {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.applyToSelf(new WeakPower(p, magicNumber, false));

        addToBot(new ApplyPowerWithDynamicAmountAction(p, p, StrengthPower.POWER_ID, () -> new StrengthPower(p, p.getPower(WeakPower.POWER_ID).amount), false, AbstractGameAction.AttackEffect.LIGHTNING));
    }

    public void upp()
    {
        upgradeMagicNumber(1);
    }
}