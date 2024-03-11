package io.github.adambrown0.myfirstmod.entity.watercreature;

import io.github.adambrown0.myfirstmod.Myfirstmod;
import io.github.adambrown0.myfirstmod.annotations.ModdedMob;
import io.github.adambrown0.myfirstmod.annotations.SpawnEgg;
import io.github.adambrown0.myfirstmod.entity.animal.TigerEntity;
import io.github.adambrown0.myfirstmod.initializers.ModEntities;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@ModdedMob(name = "leviathan", height = 1, width = 2.789f)
@SpawnEgg(eggPrimary = 0x0d0d0d, eggSecondary = 0x1f9de0)
public class Leviathan extends WaterCreatureEntity implements GeoEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private final ServerBossBar bossBar;

    public Leviathan(EntityType<? extends WaterCreatureEntity> entityType, World world) {
        super(entityType, world);
        bossBar = new ServerBossBar(Text.of("LEVIATHAN") , BossBar.Color.BLUE, BossBar.Style.PROGRESS);
        bossBar.setPercent(1.0f);
        bossBar.setVisible(true);
    }

    /**
     * Defines what happens whenever the mob is ticked by the server
     */
    @Override
    protected void mobTick() {
        super.mobTick();
        float healthPercentage = getHealth() / getMaxHealth();
        bossBar.setPercent(MathHelper.clamp(healthPercentage, 0.0f, 1.0f)); // Sets the boss bar of the mob to its current health
    }

    /**
     * Defines what happens when the player gets within range of the mob
     * @param player The player that gets within range
     */
    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        bossBar.addPlayer(player);
    }

    /**
     * Defines what happens when the player leaves the range of the mob
     * @param player The player that leaves the range
     */
    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        bossBar.removePlayer(player);
    }

    /**
     * Sets the custom attributes of the mob
     * @return A set of customized attributes
     */
    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 150.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 5.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 2.0f);
    }

    /**
     * Initializes the path finding and attacking goals of the mob
     */
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75f, 1));

        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true)); // Attacks players
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, MerchantEntity.class, true)); // Attacks merchant
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, ChickenEntity.class, true)); // Attacks chicken
    }

    @Override
    public void registerControllers(AnimatableManager.@NotNull ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(@NotNull AnimationState<T> tAnimationState) {
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.leviathan.swim", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public static class Model extends GeoModel<Leviathan> {

        @Override
        public Identifier getModelResource(Leviathan animatable) {
            return new Identifier(Myfirstmod.ModIdentifier, "geo/leviathan.geo.json");
        }

        @Override
        public Identifier getTextureResource(Leviathan animatable) {
            return new Identifier(Myfirstmod.ModIdentifier, "textures/entity/leviathan.png");
        }

        @Override
        public Identifier getAnimationResource(Leviathan animatable) {
            return new Identifier(Myfirstmod.ModIdentifier, "animations/leviathan.animation.json");
        }

        @Override
        public void setCustomAnimations(Leviathan animatable, long instanceId, AnimationState<Leviathan> animationState) {
            CoreGeoBone head = getAnimationProcessor().getBone("head");

            if (head != null) {
                EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
                head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
                head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
            }
        }

    }

    public static class Renderer extends GeoEntityRenderer<Leviathan> {

        public Renderer(EntityRendererFactory.Context renderManager) {
            super(renderManager, new Leviathan.Model());
        }

        @Override
        public Identifier getTextureLocation(Leviathan animatable) {
            return new Identifier(Myfirstmod.ModIdentifier, "textures/entity/leviathan.png");
        }

        @Override
        public void render(@NotNull Leviathan entity, float entityYaw, float partialTick, MatrixStack poseStack,
                           VertexConsumerProvider bufferSource, int packedLight) {
            if(entity.isBaby()) {
                poseStack.scale(0.4f, 0.4f, 0.4f);
            }

            super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        }


    }

}
