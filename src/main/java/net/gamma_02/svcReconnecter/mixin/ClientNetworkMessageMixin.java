package net.gamma_02.svcReconnecter.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.maxhenkel.voicechat.Voicechat;
import de.maxhenkel.voicechat.api.RawUdpPacket;
import de.maxhenkel.voicechat.logging.VoicechatLogger;
import de.maxhenkel.voicechat.voice.client.ClientNetworkMessage;
import net.gamma_02.svcReconnecter.client.InterruptedUdpPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientNetworkMessage.class)
public class ClientNetworkMessageMixin {

//    @WrapOperation(method = "readPacketClient", at = @At(value = "INVOKE", target = "Lde/maxhenkel/voicechat/logging/VoicechatLogger;debug(Ljava/lang/String;[Ljava/lang/Object;)V"))
//    private static void thing(VoicechatLogger instance, String message, Object[] args, Operation<Void> original, RawUdpPacket packet){
//        if(packet instanceof InterruptedUdpPacket){
//            Voicechat.LOGGER.warn("Packet reading was interupted!");
//            return;
//        }
//
//        original.call(instance, message, args);
//    }
}
