package code.cards;

import static code.TheFoolMod.makeID;

import basemod.helpers.CardModifierManager;
import code.cardmodifiers.ImpulsiveCardModifier;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlayTopCardOfDeck extends AbstractEasyCard
{
    public final static String ID = makeID("PlayTopCardOfDeck");
    // intellij stuff skill, self, Uncommon, , , , , , 

    public PlayTopCardOfDeck()
    {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        CardModifierManager.addModifier(this, new ImpulsiveCardModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractMonster target = AbstractDungeon.getRandomMonster();
        addToBot(new PlayTopCardAction(target, false));
    }

    public void upp()
    {
        upgradeBaseCost(0);
    }
}