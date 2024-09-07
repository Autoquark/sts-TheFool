package code.cards;

import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.powers.DrawOnPlayPower;
import code.util.Wiz;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DrawOnPlay extends AbstractEasyCard
{
    public final static String ID = makeID("DrawOnPlay");
    // intellij stuff skill, self, rare, , , , , , 

    public DrawOnPlay()
    {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.applyToSelf(new DrawOnPlayPower(p, 1));
    }

    public void upp()
    {
        upgradeBaseCost(1);
    }
}