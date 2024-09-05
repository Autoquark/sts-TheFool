package code.cards;

import code.actions.ConditionalAction;
import code.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

import static code.TheFoolMod.makeID;

public class BlockIfNotFrail extends AbstractEasyCard
{
    public final static String ID = makeID("BlockIfNotFrail");
    // intellij stuff attack, self, common, , , 8, 10, 2, 

    public BlockIfNotFrail()
    {
        super(ID, 1, CardType.SKILL, CardRarity./*COMMON*/SPECIAL, CardTarget.SELF);
        baseBlock = 12;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ConditionalAction(
                () -> !p.hasPower(FrailPower.POWER_ID),
                getBlockAction(),
                new ApplyPowerAction(p, p, new FrailPower(p,magicNumber, false))));
    }

    public void upp()
    {
        upgradeBlock(4);
    }
}