package io.github.adambrown0.myfirstmod.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation which allows for automatic creation and registration of spawn eggs for a given entity
 * <br><br>Note: all classes using this annotation must already be using the {@link ModdedMob} annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SpawnEgg {
    int eggPrimary() default 0x000000;
    int eggSecondary() default 0x000000;
}
