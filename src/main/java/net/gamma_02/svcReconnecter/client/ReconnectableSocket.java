package net.gamma_02.svcReconnecter.client;

import java.net.SocketException;

public interface ReconnectableSocket {

    void reconnectSocket() throws Exception;

    void resetSocketTimeout() throws SocketException;


    Object getReconnectLock();
}
