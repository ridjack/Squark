package ridjack.squark;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charm.base.CharmClientLoader;

public class SquarkClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        new CharmClientLoader(Squark.MOD_ID);
    }
}
