package io.github.adambrown0.myfirstmod;

import io.github.adambrown0.myfirstmod.initializers.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registry;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

public class Myfirstmod implements ModInitializer {

    public static final String ModIdentifier = "myfirstmod";

    @Override
    public void onInitialize() {
        // Delegates the task of initializing major aspects of the mod to the initializers
        Set<Class<? extends Initializer>> initializers = new Reflections("io.github.adambrown0.myfirstmod").getSubTypesOf(Initializer.class);
        for (Class<? extends Initializer> initializer: initializers) {
            try {
                initializer.getConstructor().newInstance().init();
            } catch (Exception e) {
                throw new RuntimeException(initializer.getName() + " failed to initialize. Stack trace:\n" + Arrays.toString(e.getStackTrace()));
            }
        }
    }

}
