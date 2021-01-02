package ridjack.squark;

import ridjack.squark.module.Limestone;
import ridjack.squark.module.Slate;
import net.fabricmc.api.ModInitializer;
import svenhjol.charm.Charm;
import svenhjol.charm.base.CharmLoader;

import java.util.Arrays;

public class Squark implements ModInitializer {
    public static final String MOD_ID = "squark";

    @Override
    public void onInitialize() {
        // always ensure Charm runs first!
        Charm.runFirst();

        new CharmLoader(Squark.MOD_ID, Arrays.asList(
            Slate.class,
            Limestone.class
        ));
    }
}
