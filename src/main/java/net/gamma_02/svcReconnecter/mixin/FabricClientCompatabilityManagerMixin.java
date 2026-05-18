package net.gamma_02.svcReconnecter.mixin;

import de.maxhenkel.voicechat.intercompatibility.FabricClientCompatibilityManager;
import net.gamma_02.svcReconnecter.client.WorldJoinEventRefirer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FabricClientCompatibilityManager.class)
public class FabricClientCompatabilityManagerMixin implements WorldJoinEventRefirer {

    @Unique
    Runnable reconnector$onJoinRunnable = null;

    @Inject(method = "onJoinWorld", at = @At("HEAD"))
    void storeJoinWorldRunnable(Runnable onJoinWorld, CallbackInfo ci){
        reconnector$onJoinRunnable = onJoinWorld;
    }

    @Override
    @Unique
    public void svc_reconnecter$fireVoicechatJoinWorldEvent() {
        reconnector$onJoinRunnable.run();
    }
}
