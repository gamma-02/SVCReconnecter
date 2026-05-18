package net.gamma_02.svcReconnecter.mixin;


import net.gamma_02.svcReconnecter.SvcReconnecter;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class ClientWorkExecutor {

    @Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;handleDelayedCrash()V", shift = At.Shift.AFTER))
    public void invokeWork(CallbackInfo ci){
        SvcReconnecter.onStep();
    }
}
