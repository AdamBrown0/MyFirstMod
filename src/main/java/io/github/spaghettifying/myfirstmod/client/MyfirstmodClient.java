package io.github.spaghettifying.myfirstmod.client;

import io.github.spaghettifying.myfirstmod.Myfirstmod;
import io.github.spaghettifying.myfirstmod.block.blockentity.ImbuingTableBlockEntityRenderer;
import io.github.spaghettifying.myfirstmod.block.ui.ImbuementScreen;
import io.github.spaghettifying.myfirstmod.block.ui.ImbuementScreenHandler;
import io.github.spaghettifying.myfirstmod.initializers.ModBlocks;
import io.github.spaghettifying.myfirstmod.initializers.ModEntities;
import io.github.spaghettifying.myfirstmod.item.QuestBook.ui.QuestBookScreen;
import io.github.spaghettifying.myfirstmod.item.QuestBook.ui.QuestBookScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@SuppressWarnings({"deprecation", "unchecked"})
public class MyfirstmodClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(ModBlocks.getImbuementTableEntity(), ImbuingTableBlockEntityRenderer::new);
        HandledScreens.register(ImbuementScreenHandler.getScreenHandlerType(), ImbuementScreen::new);
//        HandledScreens.register(QuestBookScreenHandler.getScreenHandlerType(), QuestBookScreen::new);

        /**
         * Handles the client side initialization of all mobs
         * @see ModEntities#init() for the server side initialization
         */
        Set<Class<?>> classes = ModEntities.getKeySet();
        for (Class<?> clazz : classes) {
            EntityRendererRegistry.register(
                    (EntityType<? extends LivingEntity>) ModEntities.get(clazz),
                    (ctx -> {
                        try {
                            // Constructs the entity's Renderer inner class
                            return (EntityRenderer<LivingEntity>) Class.forName(clazz.getName() + "$Renderer")
                                    .getDeclaredConstructor(ctx.getClass())
                                    .newInstance(ctx);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
                            throw new RuntimeException(exception);
                        }
                    }
                    ));
        }
    }

}
