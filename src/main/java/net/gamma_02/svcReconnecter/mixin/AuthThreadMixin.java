package net.gamma_02.svcReconnecter.mixin;

import de.maxhenkel.voicechat.Voicechat;
import de.maxhenkel.voicechat.intercompatibility.ClientCompatibilityManager;
import de.maxhenkel.voicechat.net.ClientServerNetManager;
import de.maxhenkel.voicechat.net.RemovePlayerStatePacket;
import de.maxhenkel.voicechat.net.RequestSecretPacket;
import net.gamma_02.svcReconnecter.SvcReconnecter;
import net.gamma_02.svcReconnecter.client.WorldJoinEventRefirer;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "de/maxhenkel/voicechat/voice/client/ClientVoicechatConnection$AuthThread")
public class AuthThreadMixin {

    @Shadow
    private int authLogMessageCount;

    @Unique
    private static int reconnectionsAttempted = 0;
//    @Unique
//    private boolean authenticated = false;

//    @Definition(id = "authLogMessageCount", field = "Lde/maxhenkel/voicechat/voice/client/ClientVoicechatConnection$AuthThread;authLogMessageCount:I")
//    @Expression("this.authLogMessageCount == 10")
//    @ModifyExpressionValue(method = "run", at = @At("MIXINEXTRAS:EXPRESSION"))
//    public boolean thing(boolean original){
//
//        return this.authLogMessageCount == 1;
//    }


    //the purpose of this is to close and reopen the datagram socket in our custom client socket.
    @Inject(method = "run", at = @At(value = "INVOKE", target = "Lde/maxhenkel/voicechat/voice/client/ClientVoicechatConnection;sendToServer(Lde/maxhenkel/voicechat/voice/common/NetworkMessage;)Z", shift = At.Shift.AFTER))
    public void redirectWarn(CallbackInfo ci) {

//        if(((ClientVCConnectionAccessor)this$0).getAuthenticated() && !authenticated){
//            if(this$0.getSocket() instanceof ReconnectableSocket rs){
//                try {
//                    synchronized (ReconnectClientSocketImpl.socketLock) {
//                        rs.resetSocketTimeout();
//                    }
//                } catch (SocketException e) {
//                    //ignored
//                }
//            }
//
//            authenticated = true;
//        }

//        Voicechat.LOGGER.info(Arrays.toString(this$0.getData().getSecret().getSecret()));

//        if(authenticated)
//            return;

        if (authLogMessageCount != 10)
            return;

//
//        if( reconnectionsAttempted < 10){
//            Voicechat.LOGGER.info("Reconnecting voice chat connection");
//        } else if(reconnectionsAttempted == 10) { //todo?: config
//            Voicechat.LOGGER.warn("Trying to reconnect and authenticate voice chat connection (this message will not be logged again)");
//        }


        //note: here, we are on the client. all client methods are valid :D
        SvcReconnecter.StepWorkQueue.add(() -> {

            if (reconnectionsAttempted < 10) {

                Voicechat.LOGGER.info("Auth has failed in 10 tries, attempting voicechat reconnection");

            } else if (reconnectionsAttempted == 10) {

                Voicechat.LOGGER.warn("Auth has failed in 10 tries, attempting voicechat reconnection (This is the last time this message will be logged)");

            }

            reconnectionsAttempted += 1;

            if (ClientCompatibilityManager.INSTANCE instanceof WorldJoinEventRefirer refirer) {
                //here: fire a packet that tells the voicechat server to remove the connection
                ClientServerNetManager.sendToServer(new RemovePlayerStatePacket(Minecraft.getInstance().getUser().getProfileId()));
                refirer.svc_reconnecter$fireVoicechatJoinWorldEvent();
            }
        });


//        authLogMessageCount = -1;

//        if(this$0 instanceof ReconnectableConnection rc && this$0.getSocket() instanceof ReconnectableSocket rs){
//            synchronized (ReconnectClientSocketImpl.socketLock) {
//                try {
//                    rc.reconnect();
//                } catch (Exception e) {
//                    Voicechat.LOGGER.error("Failed to reconnect!", e);
//                }
//            }
//        }

    }
}
