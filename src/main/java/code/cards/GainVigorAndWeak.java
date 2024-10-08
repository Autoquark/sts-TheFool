package code.cards;

import static code.TheFoolMod.makeID;

import code.util.CardArtRoller;
import code.util.Wiz;
import com.megacrit.cardcrawl.cards.red.WildStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class GainVigorAndWeak extends AbstractEasyCard
{
    public final static String ID = makeID("GainVigorAndWeak");
    // intellij stuff skill, self, common, , , , , 10, 3

    public GainVigorAndWeak()
    {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 12;
        baseSecondMagic = secondMagic = 1;

        //portrait = CardArtRoller.getModifiedPortrait(new WildStrike(), this, 0.5f, 0.5f, 0.5f, 0.5f, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.applyToSelf(new WeakPower(p, secondMagic, false));
        Wiz.applyToSelf(new VigorPower(p, magicNumber));
    }

    public void upp()
    {
        upgradeMagicNumber(4);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID)
    {
        return new CardArtRoller.ReskinInfo(WildStrike.ID, 1f, 0.5f, 0.5f, 0.5f, true);
    }
}