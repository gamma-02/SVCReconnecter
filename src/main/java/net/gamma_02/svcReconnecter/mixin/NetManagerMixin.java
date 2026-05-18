package net.gamma_02.svcReconnecter.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.maxhenkel.voicechat.net.Channel;
import de.maxhenkel.voicechat.net.NetManager;
import de.maxhenkel.voicechat.net.RemovePlayerStatePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NetManager.class)
public class NetManagerMixin {

    @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lde/maxhenkel/voicechat/net/NetManager;registerReceiver(Ljava/lang/Class;ZZ)Lde/maxhenkel/voicechat/net/Channel;", ordinal = 3))
    public Channel<RemovePlayerStatePacket> modifyRemovePlayerStateChannel(NetManager instance, Class<RemovePlayerStatePacket> tClass, boolean toClient, boolean toServer, Operation<Channel<RemovePlayerStatePacket>> original){
        return original.call(instance, tClass, true, true);
    }
}
