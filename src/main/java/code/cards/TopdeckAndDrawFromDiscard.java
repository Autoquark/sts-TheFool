package code.cards;

import static code.TheFoolMod.makeID;

import code.util.CardArtRoller;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.actions.unique.RandomCardFromDiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.green.Blur;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TopdeckAndDrawFromDiscard extends AbstractEasyCard
{
    public final static String ID = makeID("TopdeckAndDrawFromDiscard");
    // intellij stuff skill, self, UNCOMMON, , , , , , 

    public TopdeckAndDrawFromDiscard()
    {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new PutOnDeckAction(p, p, 1, false));
        addToBot(new RandomCardFromDiscardPileToHandAction());
    }

    public void upp()
    {
        upgradeBaseCost(0);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID)
    {
        return new CardArtRoller.ReskinInfo(Blur.ID, 1f, 0.5f, 0.5f, 0.5f, true);
    }
}