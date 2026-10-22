import java.net.*;
import java.util.*;

class TimeServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(5000);
        while (true) {
            byte[] buf = new byte[100];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            ds.receive(dp);
            new Thread(() -> {
                try {
                    byte[] data = new Date().toString().getBytes();
                    ds.send(new DatagramPacket(data, data.length,
                        dp.getAddress(), dp.getPort()));
                } catch (Exception e) {}
            }).start();
        }
    }
}
