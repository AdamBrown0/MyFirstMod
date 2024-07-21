package io.github.spaghettifying.myfirstmod.userinterface;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.spaghettifying.myfirstmod.Myfirstmod;
import io.wispforest.owo.mixin.ui.access.ButtonWidgetAccessor;
import io.wispforest.owo.mixin.ui.access.ClickableWidgetAccessor;
import io.wispforest.owo.ui.core.CursorStyle;
import io.wispforest.owo.ui.core.OwoUIDrawContext;
import io.wispforest.owo.ui.core.Sizing;
import io.wispforest.owo.ui.parsing.UIModel;
import io.wispforest.owo.ui.parsing.UIParsing;
import io.wispforest.owo.ui.util.NinePatchTexture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.HoveredTooltipPositioner;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.w3c.dom.Element;

import java.util.Map;
import java.util.function.Consumer;

public class BetterButtonComponent extends ButtonWidget {

    protected BetterButtonComponent.Renderer renderer;
    protected boolean textShadow = true;

    public BetterButtonComponent(Text message, Consumer<BetterButtonComponent> onPress, String buttonType) {
        super(0, 0, 0, 0, message, button -> onPress.accept((BetterButtonComponent) button), ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        this.sizing(Sizing.content());

        renderer = Renderer.create(buttonType);
    }

    @Override
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderer.draw((OwoUIDrawContext) context, this, delta);

        var textRenderer = MinecraftClient.getInstance().textRenderer;
        int color = this.active ? 0xffffff : 0xa0a0a0;

        if (this.textShadow) {
            context.drawCenteredTextWithShadow(textRenderer, this.getMessage(), this.getX() + this.width / 2, this.getY() + (this.height - 8) / 2, color);
        } else {
            context.drawText(textRenderer, this.getMessage(), (int) (this.getX() + this.width / 2f - textRenderer.getWidth(this.getMessage()) / 2f), (int) (this.getY() + (this.height - 8) / 2f), color, false);
        }

        var tooltip = ((ClickableWidgetAccessor) this).owo$getTooltip();
        if (this.hovered && tooltip != null)
            context.drawTooltip(textRenderer, tooltip.getLines(MinecraftClient.getInstance()), HoveredTooltipPositioner.INSTANCE, mouseX, mouseY);
    }

    public BetterButtonComponent onPress(Consumer<BetterButtonComponent> onPress) {
        ((ButtonWidgetAccessor) this).owo$setOnPress(button -> onPress.accept((BetterButtonComponent) button));
        return this;
    }

    public BetterButtonComponent renderer(BetterButtonComponent.Renderer renderer) {
        this.renderer = renderer;
        return this;
    }

    public BetterButtonComponent.Renderer renderer() {
        return this.renderer;
    }

    public BetterButtonComponent textShadow(boolean textShadow) {
        this.textShadow = textShadow;
        return this;
    }

    public boolean textShadow() {
        return this.textShadow;
    }

    public BetterButtonComponent active(boolean active) {
        this.active = active;
        return this;
    }

    public boolean active() {
        return this.active;
    }

    @Override
    public void parseProperties(UIModel model, Element element, Map<String, Element> children) {
        super.parseProperties(model, element, children);
        UIParsing.apply(children, "text", UIParsing::parseText, this::setMessage);
        UIParsing.apply(children, "text-shadow", UIParsing::parseBool, this::textShadow);
    }

    protected CursorStyle owo$preferredCursorStyle() {
        return CursorStyle.HAND;
    }

    @FunctionalInterface
    public interface Renderer {

        void draw(OwoUIDrawContext context, BetterButtonComponent button, float delta);

        static BetterButtonComponent.Renderer create(String buttonType) {
            Identifier ACTIVE_TEXTURE = new Identifier(Myfirstmod.ModIdentifier, buttonType + "/active");
            Identifier HOVERED_TEXTURE = new Identifier(Myfirstmod.ModIdentifier, buttonType + "/hovered");
            Identifier DISABLED_TEXTURE = new Identifier(Myfirstmod.ModIdentifier, buttonType + "/disabled");

            return (matrices, button, delta) -> {
                RenderSystem.enableDepthTest();

                var texture = button.active
                        ? button.hovered ? HOVERED_TEXTURE : ACTIVE_TEXTURE
                        : DISABLED_TEXTURE;
                NinePatchTexture.draw(texture, matrices, button.getX(), button.getY(), button.width, button.height);
            };
        }

    }

}
