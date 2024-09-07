package code.cards;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import code.powers.DrawReductionSelfAppliedPower;
import code.util.Wiz;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

@NoPools
@NoCompendium
public class DrawAndGainMinusDraw extends AbstractEasyCard
{
    public final static String ID = makeID("DrawAndGainMinusDraw");
    // intellij stuff skill, self, common, , , , , 3, 1

    public DrawAndGainMinusDraw()
    {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DrawCardAction(magicNumber));
        Wiz.applyToSelf(new DrawReductionSelfAppliedPower(p, 1));
    }

    public void upp()
    {
        upgradeMagicNumber(1);
    }
}