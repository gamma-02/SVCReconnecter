package net.gamma_02.svcReconnecter.mixin;

import de.maxhenkel.voicechat.voice.client.ClientVoicechatConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientVoicechatConnection.class)
public interface ClientVCConnectionAccessor {

    @Accessor
    boolean getAuthenticated();
}
