package net.gamma_02.svcReconnecter.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.maxhenkel.voicechat.logging.VoicechatLogger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(targets = "de.maxhenkel.voicechat.voice.client.ClientVoicechatConnection.AuthThread")
public class ClientVoicechatConnectionMixin {

    @Shadow
    private int authLogMessageCount;
    @Unique
    private int reconnectionsAttempted = 0;

    //the purpose of this is to close and reopen the datagram socket in our custom client socket.
    @WrapOperation(method = "run", at = @At(value = "INVOKE", target = "Lde/maxhenkel/voicechat/logging/VoicechatLogger;warn(Ljava/lang/String;[Ljava/lang/Object;)V"))
    public void redirectWarn(VoicechatLogger instance, String message, Object[] args, Operation<Void> original){


        if(reconnectionsAttempted >= 10) { //todo?: config
            original.call(instance, "")
        }

        authLogMessageCount = -1;

    }
}
