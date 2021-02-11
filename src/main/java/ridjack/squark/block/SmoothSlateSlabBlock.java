package ridjack.squark.block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SlabBlock;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.block.ICharmBlock;
import ridjack.squark.module.Slate;

public class SmoothSlateSlabBlock extends SlabBlock implements ICharmBlock {
    private CharmModule module;

    public SmoothSlateSlabBlock(CharmModule module) {
        super(AbstractBlock.Settings.copy(Slate.SLATE_BLOCK));
        this.register(module, "smooth_slate_slab");
        this.module = module;
    }

    @Override
    public boolean enabled() { return module.enabled;
    }
}
