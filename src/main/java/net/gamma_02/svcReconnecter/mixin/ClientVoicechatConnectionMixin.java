package net.gamma_02.svcReconnecter.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.maxhenkel.voicechat.Voicechat;
import de.maxhenkel.voicechat.api.ClientVoicechatSocket;
import de.maxhenkel.voicechat.voice.client.ClientVoicechatConnection;
import net.gamma_02.svcReconnecter.client.ReconnectClientSocketImpl;
import net.gamma_02.svcReconnecter.client.ReconnectableConnection;
import net.gamma_02.svcReconnecter.client.ReconnectableSocket;
import net.gamma_02.svcReconnecter.client.SvcReconnecterClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Mixin(ClientVoicechatConnection.class)
public class ClientVoicechatConnectionMixin implements ReconnectableConnection {


    @Shadow
    @Final
    private ClientVoicechatSocket socket;

    @Override
    public void reconnect() throws Exception {
        if(this.socket instanceof ReconnectableSocket rs){
            rs.reconnectSocket();
        }
    }

//    @WrapOperation(method = "sendToServer", at = @At(value = "INVOKE", target = "Lde/maxhenkel/voicechat/api/ClientVoicechatSocket;send([BLjava/net/SocketAddress;)V"))
//    public void wrapSendToServer(ClientVoicechatSocket instance, byte[] bytes, SocketAddress socketAddress, Operation<Void> original){
////        Voicechat.LOGGER.info("Socket Address is: " + ((InetSocketAddress)socketAddress).toString());
//
//        if(this.socket instanceof ReconnectableSocket) {
////            synchronized (ReconnectClientSocketImpl.socketLock) {
//                original.call(instance, bytes, socketAddress);
////            }
//        } else {
//            original.call(instance, bytes, socketAddress);
//        }
//    }
}
