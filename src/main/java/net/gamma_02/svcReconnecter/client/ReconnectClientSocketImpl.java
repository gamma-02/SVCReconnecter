package net.gamma_02.svcReconnecter.client;

import com.mojang.logging.LogUtils;
import de.maxhenkel.voicechat.api.ClientVoicechatSocket;
import de.maxhenkel.voicechat.api.RawUdpPacket;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.*;
import java.util.Objects;

public class ReconnectClientSocketImpl implements ClientVoicechatSocket, ReconnectableSocket {
    private DatagramSocket socket;

    public static final Object socketLock = new Object();

    public ReconnectClientSocketImpl() {
    }

    private final byte[] BUFFER = new byte[4096]; //if we are compiling against the mod we can use VoicechatSocketBase

    @Nullable
    public RawUdpPacketImpl read(DatagramSocket socket) throws IOException {
        DatagramPacket packet = new DatagramPacket(BUFFER, BUFFER.length);

        try {
            socket.receive(packet);
        } catch (InterruptedIOException timeout){
            return null;
        }

        if (packet.getLength() >= BUFFER.length) {
            CooldownTimer.run("udp_packet_too_large", () -> {
                LogUtils.getLogger().warn("Packet from {} is too large", packet.getSocketAddress());
            });
            throw new IOException(String.format("Packet from %s is too large", packet.getSocketAddress()));
        }
        // Setting the timestamp after receiving the packet
        long timestamp = System.currentTimeMillis();
        byte[] data = new byte[packet.getLength()];
        System.arraycopy(packet.getData(), packet.getOffset(), data, 0, packet.getLength());
        return new RawUdpPacketImpl(data, packet.getSocketAddress(), timestamp);
    }

    public void open() throws Exception {

        System.out.println("Opening!");

        this.socket = new DatagramSocket();

        socket.setSoTimeout(500);

    }

    public RawUdpPacket read() throws Exception {
        System.out.println("Reading!");

        synchronized (socketLock) {
            if (this.socket == null) {
                throw new IllegalStateException("Socket not opened yet");
            }

            var packet = this.read(this.socket);

            return Objects.requireNonNullElseGet(packet, () -> new InterruptedUdpPacket(socket.getRemoteSocketAddress(), System.currentTimeMillis()));
        }
    }

    public void send(byte[] data, SocketAddress address) throws Exception {

//        synchronized (this.socketLock) {
        System.out.println("Attempting send!");

        if (this.socket != null) {
            this.socket.send(new DatagramPacket(data, data.length, address));
        }
//        }
    }

    public void close() {
        System.out.println("Attempting close!");
        if (this.socket != null) {
            this.socket.close();
        }

    }

    public boolean isClosed() {

        System.out.println("Checking close!");

        return this.socket == null;
    }

    @Override
    public void reconnectSocket() throws Exception{

        this.close();

        this.open();

    }

    @Override
    public void resetSocketTimeout() throws SocketException {
        if(!this.isClosed())
            this.socket.setSoTimeout(0);
    }

    @Override
    public Object getReconnectLock() {
        return socketLock;
    }
}
