package code.potions;

import basemod.BaseMod;
import code.TheFool;
import code.TheFoolMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static code.TheFoolMod.makeID;
import static code.util.Wiz.*;

public class DemoPotion extends AbstractEasyPotion {
    public static String ID = makeID("DemoPotion");

    public DemoPotion() {
        super(ID, PotionRarity.PLACEHOLDER, PotionSize.ANVIL, new Color(0.2f, 0.4f, 0.9f, 1f), new Color(0.6f, 0.8f, 1.0f, 1f), null, TheFool.Enums.THE_FOOL, TheFoolMod.characterColor);
    }

    public int getPotency(int ascensionlevel) {
        return 10;
    }

    public void use(AbstractCreature creature) {
        applyToSelf(new StrengthPower(adp(), potency));
    }

    public String getDescription() {
        return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[1];
    }

    public void addAdditionalTips() {
        //tips.add(new PowerTip(BaseMod.getKeywordTitle(makeID("todo")), BaseMod.getKeywordDescription(makeID("todo"))));
    }
}