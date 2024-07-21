package io.github.spaghettifying.myfirstmod.initializers;

import io.github.spaghettifying.myfirstmod.Myfirstmod;
import io.github.spaghettifying.myfirstmod.block.ui.ImbuementScreen;
import io.github.spaghettifying.myfirstmod.block.ui.ImbuementScreenHandler;
import lombok.Getter;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers implements Initializer {

    public void init() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(Myfirstmod.ModIdentifier, "imbuement_screen_handler"), ImbuementScreenHandler.getScreenHandlerType());
    }

}
