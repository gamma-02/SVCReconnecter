package net.gamma_02.svcReconnecter.client;

import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.ClientVoicechatInitializationEvent;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.plugins.impl.events.ClientVoicechatInitializationEventImpl;
import net.gamma_02.svcReconnecter.SvcReconnecter;

public class ClientVCPlugin implements VoicechatPlugin {
    @Override
    public String getPluginId() {
        return SvcReconnecter.MODID + ":client-plugin";
    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(ClientVoicechatInitializationEventImpl.class, this::onClientInit);
    }

    private void onClientInit(ClientVoicechatInitializationEventImpl clientVoicechatInitializationEvent) {
        clientVoicechatInitializationEvent.setSocketImplementation(new ReconnectClientSocketImpl());
    }
}
