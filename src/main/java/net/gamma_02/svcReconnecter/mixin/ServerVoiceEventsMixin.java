package net.gamma_02.svcReconnecter.mixin;

import de.maxhenkel.voicechat.Voicechat;
import de.maxhenkel.voicechat.intercompatibility.CommonCompatibilityManager;
import de.maxhenkel.voicechat.voice.server.ServerVoiceEvents;
import net.gamma_02.svcReconnecter.client.PlayerDisconnectEventRefirer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerVoiceEvents.class)
public class ServerVoiceEventsMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    public void registerServerStateRemoveListener(CallbackInfo ci){
        CommonCompatibilityManager.INSTANCE
                .getNetManager()
                .removePlayerStateChannel
                .setServerListener(
                        (player, packet) -> {
                            Voicechat.LOGGER.info("Removing server-side player connection and secret for: {}", player.getName().getString());

                            if(CommonCompatibilityManager.INSTANCE instanceof PlayerDisconnectEventRefirer refirer){
                                refirer.svc_reconnecter$fireDisconnectEvent(player);
                            }
                        }
                );
    }

}
