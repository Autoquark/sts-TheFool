package code.cards;

import static code.TheFoolMod.makeID;

import code.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DiscardAndDraw extends AbstractEasyCard
{
    public final static String ID = makeID("DiscardAndDraw");
    // intellij stuff skill, self, rare, , , , , 3, 1

    public DiscardAndDraw()
    {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.addToBottom(new SelectCardsInHandAction(magicNumber, "Choose cards to discard", list -> {
            for (AbstractCard c : list)
            {
                p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
            list.clear();
        }));
        Wiz.addToBottom(new DrawCardAction(magicNumber));
    }

    public void upp()
    {
        upgradeBaseCost(0);
    }
}