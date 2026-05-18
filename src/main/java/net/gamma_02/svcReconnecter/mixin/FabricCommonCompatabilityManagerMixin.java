package net.gamma_02.svcReconnecter.mixin;

import net.minecraft.server.level.ServerPlayer;
import de.maxhenkel.voicechat.intercompatibility.FabricCommonCompatibilityManager;
import net.gamma_02.svcReconnecter.client.PlayerDisconnectEventRefirer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(FabricCommonCompatibilityManager.class)
public class FabricCommonCompatabilityManagerMixin implements PlayerDisconnectEventRefirer {

    @Unique
    private Consumer<ServerPlayer> svc_reconnecter$disconnectListener;

    @Inject(method = "onPlayerLoggedOut", at = @At("HEAD"))
    public void capturePlayerLoggedOutListener(Consumer<ServerPlayer> onPlayerLoggedOut, CallbackInfo ci){
        svc_reconnecter$disconnectListener = onPlayerLoggedOut;
    }


    @Override
    public void svc_reconnecter$fireDisconnectEvent(ServerPlayer player) {
        svc_reconnecter$disconnectListener.accept(player);
    }
}
