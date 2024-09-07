package code.cards;

import code.powers.DrawReductionSelfAppliedPower;
import code.util.Wiz;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.TheFoolMod.makeID;

public class DrawAndGainDazed extends AbstractEasyCard
{
    public final static String ID = makeID("DrawAndGainDazed");
    // intellij stuff skill, self, common, , , , , 3, 1

    public DrawAndGainDazed()
    {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new MakeTempCardInDiscardAction(new Dazed(), 1));
    }

    public void upp()
    {
        upgradeMagicNumber(1);
    }
}