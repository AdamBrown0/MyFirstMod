package io.github.spaghettifying.myfirstmod.initializers;

import io.github.spaghettifying.myfirstmod.Myfirstmod;
import io.github.spaghettifying.myfirstmod.annotations.ModdedMob;
import io.github.spaghettifying.myfirstmod.annotations.SpawnEgg;
import io.github.spaghettifying.myfirstmod.client.MyfirstmodClient;
import io.github.spaghettifying.myfirstmod.item.AbstractItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ModEntities implements Initializer {

    private static final HashMap<Class<?>, EntityType<?>> entities = new HashMap<>();

    /**
     * Handles the server-side initialization of all mobs via the @ModdedMob annotation
     * @throws Exception If an exception is called, there was an error with processing the registration of the mob
     * @see MyfirstmodClient#onInitializeClient() for the client side initialization
     */
    public void init() throws Exception {
        // Get an array of entity classes
        Set<Class<?>> classesPreCheck = new Reflections("io.github.spaghettifying.myfirstmod").getTypesAnnotatedWith(ModdedMob.class);
        Set<Class<? extends MobEntity>> classes = new HashSet<>();
        for (Class<?> clazz : classesPreCheck) {
            if (MobEntity.class.isAssignableFrom(clazz)) {
                //noinspection unchecked
                classes.add((Class<? extends MobEntity>) clazz);
            }
        }

        for (Class<? extends MobEntity> clazz : classes) {
            String name = clazz.getAnnotation(ModdedMob.class).name();

            // Place the mobs into a HashMap for access by other areas of the program
            EntityType<? extends MobEntity> entity = Registry.register(
                    Registries.ENTITY_TYPE, new Identifier(Myfirstmod.ModIdentifier, name),
                    FabricEntityTypeBuilder.create(
                            SpawnGroup.CREATURE,
                            (EntityType<MobEntity> entityType, World world) -> {
                                try {
                                    // Create a new instance of the class
                                    return clazz.getDeclaredConstructor(EntityType.class, World.class).newInstance(entityType, world);
                                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                                         NoSuchMethodException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    ).dimensions(EntityDimensions.fixed(clazz.getAnnotation(ModdedMob.class).width(), clazz.getAnnotation(ModdedMob.class).height())).build()
            );
            entities.put(clazz, entity);

            if(!clazz.isAnnotationPresent(SpawnEgg.class)) break;
            AbstractItem.register(
                    name + "_spawn_egg",
                    new SpawnEggItem(
                            entity,
                            clazz.getAnnotation(SpawnEgg.class).eggPrimary(),
                            clazz.getAnnotation(SpawnEgg.class).eggSecondary(),
                            new FabricItemSettings()
                    ),
                    ItemGroups.SPAWN_EGGS
            );
        }

        // Register each of the mobs on the server side
        for (Class<?> clazz : entities.keySet()) {
            //noinspection unchecked
            FabricDefaultAttributeRegistry.register((EntityType<? extends LivingEntity>) entities.get(clazz), (DefaultAttributeContainer.Builder) clazz.getMethod("setAttributes").invoke(null));
        }
    }

    @Contract(pure = true)
    public static @NotNull Set<Class<?>> getKeySet() {
        return entities.keySet();
    }

    public static EntityType<?> get(Class<?> clazz) {
        return entities.get(clazz);
    }

}
