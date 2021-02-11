package ridjack.squark.module;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import ridjack.squark.Squark;
import ridjack.squark.block.SlateBlock;
import ridjack.squark.block.SmoothSlateBlock;
import ridjack.squark.block.SmoothSlateSlabBlock;
import ridjack.squark.block.SmoothSlateStairsBlock;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.iface.Module;

@Module(mod = Squark.MOD_ID, description = "New stone added to worldgen.")
public class Slate extends CharmModule {
    public static Identifier ID = new Identifier(Squark.MOD_ID, "slate");
    public static SlateBlock SLATE_BLOCK;
    public static SmoothSlateBlock SMOOTH_SLATE_BLOCK;
    public static SmoothSlateStairsBlock SMOOTH_SLATE_STAIRS;
    public static SmoothSlateSlabBlock SMOOTH_SLATE_SLAB;
    public static ConfiguredFeature<?, ?> FEATURE;

    @Override
    public void register() {
        SLATE_BLOCK = new SlateBlock(this);
        SMOOTH_SLATE_BLOCK = new SmoothSlateBlock(this);
        SMOOTH_SLATE_STAIRS = new SmoothSlateStairsBlock(this);
        SMOOTH_SLATE_SLAB = new SmoothSlateSlabBlock(this);

    

        FEATURE = Feature.ORE
            .configure(new OreFeatureConfig(
                OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                SLATE_BLOCK.getDefaultState(),
                33)) // vein size
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                0, // bottom offset
                0, // min y level
                79))) // max y level
            .spreadHorizontally()
            .repeat(10); // number of veins per chunk

        RegistryKey<ConfiguredFeature<?, ?>> key = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, ID);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key.getValue(), FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, key);
    }

}
