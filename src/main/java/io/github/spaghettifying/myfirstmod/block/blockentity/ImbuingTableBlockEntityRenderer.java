package io.github.spaghettifying.myfirstmod.block.blockentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class ImbuingTableBlockEntityRenderer implements BlockEntityRenderer<ImbuingTableBlockEntity> {

    public static final SpriteIdentifier POTION_TEXTURE;
    private final PotionModel potion;

    public ImbuingTableBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.potion = new PotionModel(ctx.getLayerModelPart(EntityModelLayers.BOOK));
    }

    public void render(ImbuingTableBlockEntity imbuingTableBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        matrixStack.push();
        matrixStack.translate(0.5f, 0.75F, 0.5f);
        float g = (float)imbuingTableBlockEntity.ticks + f;
        matrixStack.translate(0.0f, 0.1f + MathHelper.sin(g * 0.1f) * 0.01f, 0.0f);

        float h;
        for (h = imbuingTableBlockEntity.bookRotation - imbuingTableBlockEntity.lastBookRotation; h>= 3.1415927F; h -= 6.2831855F) {
        }

        while(h < -3.1415927F) {
            h+= 6.2831855F;
        }

        float k = imbuingTableBlockEntity.lastBookRotation + h * f;
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotation(-k));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(80.0F));
        float l = MathHelper.lerp(f, imbuingTableBlockEntity.pageAngle, imbuingTableBlockEntity.nextPageAngle);
        float m = MathHelper.fractionalPart(l + 0.25F) * 1.6F - 0.3F;
        float n = MathHelper.fractionalPart(l + 0.75F) * 1.6F - 0.3F;
        float o = MathHelper.lerp(f, imbuingTableBlockEntity.pageTurningSpeed, imbuingTableBlockEntity.nextPageTurningSpeed);
        this.potion.setPageAngles(g, MathHelper.clamp(m, 0.0F, 1.0F), MathHelper.clamp(n, 0.0F, 1.0F), o);
        VertexConsumer vertexConsumer = POTION_TEXTURE.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntitySolid);
        this.potion.renderBook(matrixStack, vertexConsumer, i, j, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
    }

    static {
        POTION_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("entity/enchanting_table_book"));
    }
}
