package code.cards;

import static code.TheFoolMod.makeID;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import code.actions.ConditionalAction;
import code.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

@NoPools
@NoCompendium
public class AttackIfNotWeak extends AbstractEasyCard
{
    public final static String ID = makeID("AttackIfNotWeak");
    // intellij stuff attack, enemy, common, 12, 15, , , 2, 

    public AttackIfNotWeak()
    {
        super(ID, 1, CardType.ATTACK, CardRarity./*COMMON*/ SPECIAL, CardTarget.ENEMY);
        baseDamage = 12;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ConditionalAction(
                () -> !p.hasPower(WeakPower.POWER_ID),
                getDamageAction(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY),
                new ApplyPowerAction(p, p, new WeakPower(p, magicNumber, false))));
    }

    public void upp()
    {
        upgradeDamage(4);
    }
}