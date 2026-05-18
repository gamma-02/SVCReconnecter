package net.gamma_02.svcReconnecter.client;

import net.minecraft.server.level.ServerPlayer;

public interface PlayerDisconnectEventRefirer {

    void svc_reconnecter$fireDisconnectEvent(ServerPlayer player);
}
