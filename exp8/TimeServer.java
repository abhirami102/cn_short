import java.net.*;
import java.util.*;

class TimeServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(5000);
        System.out.println("Time Server Started");

        while (true) {
            byte[] buf = new byte[100];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            ds.receive(dp);

            new Thread(() -> {
                try {
                    byte[] data = new Date().toString().getBytes();

                    DatagramPacket reply = new DatagramPacket(
                        data, data.length,
                        dp.getAddress(), dp.getPort());

                    ds.send(reply);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
