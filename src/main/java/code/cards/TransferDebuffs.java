package code.cards;

import code.actions.CopyPowersAction;
import code.actions.PowerTypeFilter;
import code.cards.AbstractEasyCard;

import static code.TheFoolMod.makeID;

import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.ReApplyPowersAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TransferDebuffs extends AbstractEasyCard
{
    public final static String ID = makeID("TransferDebuffs");
    // intellij stuff skill, enemy, rare, , , , , , 

    public TransferDebuffs()
    {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new CopyPowersAction(p, m, PowerTypeFilter.DebuffsOnly));
        if(upgraded)
        {
            addToBot(new RemoveDebuffsAction(p));
        }
    }

    public void upp()
    {
        // This space deliberately left blank
    }
}