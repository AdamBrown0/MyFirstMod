package io.github.spaghettifying.myfirstmod.entity.animal;

import io.github.spaghettifying.myfirstmod.Myfirstmod;
import io.github.spaghettifying.myfirstmod.annotations.ModdedMob;
import io.github.spaghettifying.myfirstmod.initializers.ModEntities;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

@ModdedMob(name = "tiger", width = 1.5f, height = 1.75f)
public class TigerEntity extends AnimalEntity implements GeoEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private final ServerBossBar bossBar;

    public TigerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        bossBar = new ServerBossBar(Text.of("TIGGER") , BossBar.Color.PINK, BossBar.Style.PROGRESS);
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
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 69.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 50.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 10.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 20.0f);
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

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return (PassiveEntity) ModEntities.get(this.getClass()).create(world);
    }

    @Override
    public void registerControllers(AnimatableManager.@NotNull ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "contoller", 0, this::predicate));
    }
    
    private <T extends GeoAnimatable> PlayState predicate(@NotNull AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.tiger.walk", Animation.LoopType.LOOP));
        }

        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.tiger.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }
    
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    /**
     * Stores the information about the Tiger's model
     */
    public static class Model extends GeoModel<TigerEntity> {

        @Override
        public Identifier getModelResource(TigerEntity animatable) {
            return new Identifier(Myfirstmod.ModIdentifier, "geo/tiger.geo.json");
        }

        @Override
        public Identifier getTextureResource(TigerEntity animatable) {
            return new Identifier(Myfirstmod.ModIdentifier, "textures/entity/tiger.png");
        }

        @Override
        public Identifier getAnimationResource(TigerEntity animatable) {
            return new Identifier(Myfirstmod.ModIdentifier, "animations/tiger.animation.json");
        }

        @Override
        public void setCustomAnimations(TigerEntity animatable, long instanceId, AnimationState<TigerEntity> animationState) {
            CoreGeoBone head = getAnimationProcessor().getBone("head");

            if (head != null) {
                EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
                head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
                head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
            }
        }

    }

    /**
     * Handles the rendering of the model
     */
    public static class Renderer extends GeoEntityRenderer<TigerEntity> {

        public Renderer(EntityRendererFactory.Context renderManager) {
            super(renderManager, new Model());
        }

        @Override
        public Identifier getTextureLocation(TigerEntity animatable) {
            return new Identifier(Myfirstmod.ModIdentifier, "textures/entity/tiger.png");
        }

        @Override
        public void render(@NotNull TigerEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                           VertexConsumerProvider bufferSource, int packedLight) {
            if(entity.isBaby()) {
                poseStack.scale(0.4f, 0.4f, 0.4f);
            }

            super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        }

    }

}
