package io.github.spaghettifying.myfirstmod.item.QuestBook.ui;


import io.github.spaghettifying.myfirstmod.userinterface.BetterButtonComponent;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class QuestBookScreen extends BaseOwoScreen<FlowLayout> {

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

        rootComponent.child(Containers.verticalFlow(Sizing.content(), Sizing.content()))
                .child(createBackButton())
                .padding(Insets.of(5))
                .surface(Surface.DARK_PANEL)
                .verticalAlignment(VerticalAlignment.CENTER)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .sizing(Sizing.fixed(179), Sizing.fixed(173));
    }

    private Component createForwardButtom() {
        return null;
    }

    private void backButton() {
        System.out.println("quest book back pressed");
    }
    private Component createBackButton() {
        return new BetterButtonComponent(
                Text.literal(""),
                button -> backButton(),
                "questbookbutton/back"
        );
    }
}
