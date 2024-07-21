package io.github.spaghettifying.myfirstmod.block.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.spaghettifying.myfirstmod.userinterface.BetterButtonComponent;
import io.wispforest.owo.ui.base.BaseOwoHandledScreen;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ImbuementScreen extends BaseOwoHandledScreen<FlowLayout, ImbuementScreenHandler> {

    public ImbuementScreen(ImbuementScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, new Identifier("minecraft", "textures/gui/container/dispenser.png"));
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(new Identifier("minecraft", "textures/gui/container/dispenser.png"), x, y, 0, 0, backgroundWidth, backgroundHeight);
    }


    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(@NotNull FlowLayout rootComponent) {
        rootComponent
                .surface(Surface.VANILLA_TRANSLUCENT)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER);

        rootComponent.child(Containers.verticalFlow(Sizing.content(), Sizing.content())
                .child(createImbuementButton(0))
                .child(createImbuementButton(1))
                .child(createImbuementButton(2))
                .padding(Insets.of(10))
                .surface(Surface.PANEL)
                .verticalAlignment(VerticalAlignment.CENTER)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .sizing(Sizing.fixed(179), Sizing.fixed(173)));
    }


    private void imbue(int imbuementNumber) {

    }

    private Component createImbuementButton(int index) {
        return new BetterButtonComponent(
                Text.literal("Button #" + index),
                button -> imbue(index),
                "imbuementbutton"
        ).horizontalSizing(Sizing.fill(70));
    }

}
