package io.github.adambrown0.myfirstmod.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation which allows for automatic registration of mobs on both client and server
 * <br><br>Note: all classes using this interface <b>MUST</b> extend {@link net.minecraft.entity.Entity},
 * implement {@link software.bernie.geckolib.animatable.GeoEntity} and contain the inner public static classes
 * {@code Model extends GeoModel<M>} and {@code Renderer extends GeoEntityRenderer<M>}
 * where M is the outer class @ModdedMob is applied to
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModdedMob {
    String name();
    float width();
    float height();
}
