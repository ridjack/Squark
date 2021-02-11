package ridjack.squark.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.StairsBlock;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.block.ICharmBlock;
import ridjack.squark.module.Slate;

public class SmoothSlateStairsBlock extends StairsBlock implements ICharmBlock {
    private CharmModule module;

    public SmoothSlateStairsBlock(CharmModule module) {
        super(Slate.SMOOTH_SLATE_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Slate.SMOOTH_SLATE_BLOCK));
        this.register(module, "smooth_slate_stairs");
        this.module = module;
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }
}
