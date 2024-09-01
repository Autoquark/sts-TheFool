package code.relics;

import code.TheFool;

import static code.TheFoolMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheFool.Enums.COLOR_YELLOW);
    }
}
