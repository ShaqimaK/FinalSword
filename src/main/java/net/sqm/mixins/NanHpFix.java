package net.sqm.mixins;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//by Mega32K
// from Entity-NaN-Health-Fix-Reborn
// https://github.com/Mega32K/-Entity-NaN-Health-Fix-Reborn/blob/main/src/main/java/com/mega/something/mixin/LivingEntityMixin.java

@Mixin(LivingEntity.class)
public abstract class NanHpFix extends Entity {
    public NanHpFix(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Shadow public abstract float getHealth();

    @Shadow public abstract void setHealth(float p_21154_);


    @Shadow @Final private static EntityDataAccessor<Float> DATA_HEALTH_ID;

    @Inject(method = "isAlive", at = @At("HEAD"), cancellable = true)
    public void isAlive(CallbackInfoReturnable<Boolean> cir) {
        if (Double.isNaN(getHealth()))
            cir.setReturnValue(false);
    }

    @Inject(method = "isDeadOrDying", at = @At("HEAD"), cancellable = true)
    public void isDeadOrDying(CallbackInfoReturnable<Boolean> cir) {
        if (Double.isNaN(getHealth()))
            cir.setReturnValue(true);
    }

    @Inject(method = "getHealth", at = @At("HEAD"), cancellable = true)
    public void GetHealth(CallbackInfoReturnable<Float> cir) {
        if (Double.isNaN(entityData.get(DATA_HEALTH_ID)))
            cir.setReturnValue(0.0F);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    public void tick(CallbackInfo ci) {
        if (Double.isNaN(getHealth()))
            setHealth(0.0F);
    }
}
