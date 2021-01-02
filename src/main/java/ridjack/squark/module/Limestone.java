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
import ridjack.squark.block.LimestoneBlock;
import ridjack.squark.block.SmoothLimestoneBlock;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.iface.Module;

@Module(mod = Squark.MOD_ID, description = "New stone added to worldgen.")
public class Limestone extends CharmModule {
    public static Identifier ID = new Identifier(Squark.MOD_ID, "limestone");
    public static LimestoneBlock LIMESTONE_BLOCK;
    public static SmoothLimestoneBlock SMOOTH_LIMESTONE_BLOCK;
    public static ConfiguredFeature<?, ?> FEATURE;

    @Override
    public void register() {
        LIMESTONE_BLOCK = new LimestoneBlock(this);
        SMOOTH_LIMESTONE_BLOCK = new SmoothLimestoneBlock(this);

        FEATURE = Feature.ORE
            .configure(new OreFeatureConfig(
                OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                LIMESTONE_BLOCK.getDefaultState(),
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
