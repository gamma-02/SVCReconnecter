package net.gamma_02.svcReconnecter;

import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EventRegistration;

public class VCPlugin implements VoicechatPlugin {
    @Override
    public String getPluginId() {
        return SvcReconnecter.MODID;
    }

    @Override
    public void registerEvents(EventRegistration registration) {
//        registration.registerEvent(ClientVoicechatInitializationEvent.class, this::onClientInit);
    }

//    private void onClientInit(ClientVoicechatInitializationEvent clientVoicechatInitializationEvent) {
//        clientVoicechatInitializationEvent.setSocketImplementation(new ReconnectClientSocketImpl());
//    }
}
