package code.cards;

import static code.TheFoolMod.makeID;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.ImpulsiveCardModifier;
import code.util.CardArtRoller;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.colorless.Trip;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FallOver extends AbstractEasyCard
{
    public final static String ID = makeID("FallOver");
    // intellij stuff skill, self, rare, , , 10, 4, , 

    public FallOver()
    {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = 13;
        CardModifierManager.addModifier(this, new ImpulsiveCardModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        applyBlock();
        addToBot(new PressEndTurnButtonAction());
    }

    public void upp()
    {
        upgradeBlock(5);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID)
    {
        return new CardArtRoller.ReskinInfo(Trip.ID, 1f, 0.5f, 0.5f, 0.5f, true);
    }
}