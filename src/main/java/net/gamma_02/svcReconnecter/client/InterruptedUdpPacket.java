package net.gamma_02.svcReconnecter.client;

import de.maxhenkel.voicechat.api.RawUdpPacket;

import java.net.SocketAddress;

public class InterruptedUdpPacket implements RawUdpPacket {
    private final SocketAddress socketAddress;
    private final long timestamp;

    public InterruptedUdpPacket(SocketAddress socketAddress, long timestamp) {
        this.socketAddress = socketAddress;
        this.timestamp = timestamp;
    }

    @Override
    public byte[] getData() {
        return new byte[]{Byte.MAX_VALUE};
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public SocketAddress getSocketAddress() {
        return socketAddress;
    }
}
