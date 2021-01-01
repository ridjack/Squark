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
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.iface.Module;

@Module(mod = Squark.MOD_ID, description = "New stone added to worldgen.")
public class Slate extends CharmModule {
    public static Identifier ID = new Identifier(Squark.MOD_ID, "slate");
    public static SlateBlock SLATE_BLOCK;

    public static ConfiguredFeature<?, ?> FEATURE;

    @Override
    public void register() {
        SLATE_BLOCK = new SlateBlock(this);

//        SLATE_BLOCK_GEN = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, SLATE_BLOCK.getDefaultState(), 33)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(25, 0, 120)).spreadHorizontally().repeat(8));

        FEATURE = Feature.ORE
            .configure(new OreFeatureConfig(
                OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                SLATE_BLOCK.getDefaultState(),
                9)) // vein size
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                0, // bottom offset
                0, // min y level
                100))) // max y level
            .spreadHorizontally()
            .repeat(100); // number of veins per chunk

        RegistryKey<ConfiguredFeature<?, ?>> key = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, ID);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key.getValue(), FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, key);
    }



//    @Config(name = "Chorus teleport range", description = "A chorus fruit will teleport you to an ender pearl block within this range (in blocks).")
//    public static int teleportRange = 8;
//
//    @Config(name = "Chorus fruit teleport", description = "If true, eating a chorus fruit while in range of an ender pearl block will teleport you to it.")
//    public static boolean chorusTeleport = true;
//
//    @Config(name = "Convert silverfish to endermite", description = "If true, a silverfish has a chance to be converted into an endermite when it is next to an ender pearl block.")
//    public static boolean convertSilverfish = true;
//

//
//    @Override
//    public void init() {
//        AddEntityCallback.EVENT.register(this::addGoalToSilverfish);
//    }
//
//    public static boolean tryChorusTeleport(LivingEntity entity, ItemStack stack) {
//        if (!ModuleHandler.enabled("charm:block_of_ender_pearls") || !chorusTeleport)
//            return false;
//
//        if (!(entity instanceof PlayerEntity))
//            return false; // don't want non-players teleporting!
//
//        if (entity.world.isClient)
//            return false; // must be on server side
//
//        ServerPlayerEntity player = (ServerPlayerEntity)entity;
//        BlockPos playerPos = player.getBlockPos();
//        World world = player.world;
//        Map<Double, BlockPos> foundPositions = new HashMap<>();
//
//        // find blocks around player
//        BlockPos.stream(
//                playerPos.add(-teleportRange, -teleportRange, -teleportRange),
//                playerPos.add(teleportRange, teleportRange, teleportRange)
//        ).forEach(blockPos -> {
//            BlockPos abovePos = blockPos.up(1);
//            BlockState stateAtPos = world.getBlockState(blockPos);
//
//            if (stateAtPos.getBlock() != ENDER_PEARL_BLOCK)
//                return; // must be an ender pearl block
//
//            if (abovePos.equals(playerPos))
//                return; // can't teleport the player back to their current spot
//
//            BlockState state1 = world.getBlockState(blockPos.up(1));
//            BlockState state2 = world.getBlockState(blockPos.up(2));
//            if (state1.getMaterial() != Material.AIR || state2.getMaterial() != Material.AIR)
//                return; // don't suffocate the player
//
//            double dist = PosHelper.getDistanceSquared(playerPos, abovePos);
//            foundPositions.put(dist, abovePos);
//        });
//
//        if (foundPositions.isEmpty())
//            return false; // didn't find any ender pearl block locations
//
//        BlockPos targetPos = foundPositions.get(Collections.min(foundPositions.keySet()));
//        if (targetPos == null)
//            return false; // didn't find the closest ender pearl?
//
//        double x = targetPos.getX() + 0.5D;
//        double y = targetPos.getY();
//        double z = targetPos.getZ() + 0.5D;
//
//        // final parameter is whether to display particle effects after teleport
//        boolean didTeleport = player.teleport(x, y, z, true);
//        if (!didTeleport)
//            return false; // I guess the player didn't make it?
//
//        SoundEvent teleportSound = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
//        world.playSound(null, x, y, z, teleportSound, SoundCategory.PLAYERS, 1.0F, 1.0F); // at old location
//        player.playSound(teleportSound, 1.0F, 1.0F); // at new location
//
//        player.getItemCooldownManager().set(Items.CHORUS_FRUIT, 20);
//        if (!player.isCreative())
//            stack.decrement(1);
//
//        return true;
//    }
//
//    private ActionResult addGoalToSilverfish(Entity entity) {
//        if (!convertSilverfish)
//            return ActionResult.PASS;
//
//        if (!(entity instanceof SilverfishEntity))
//            return ActionResult.PASS; // must be a silverfish to process it
//
//        SilverfishEntity silverfish = (SilverfishEntity)entity;
//        GoalSelector goalSelector = MobHelper.getGoalSelector(silverfish);
//
//        if (goalSelector.getRunningGoals().noneMatch(g -> g.getGoal() instanceof FormEndermiteGoal))
//            goalSelector.add(2, new FormEndermiteGoal(silverfish));
//
//        return ActionResult.SUCCESS;
//    }
}
