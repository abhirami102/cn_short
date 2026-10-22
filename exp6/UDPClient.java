import java.net.*;
import java.util.*;

class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        InetAddress ip = InetAddress.getByName("localhost");
        byte[] data = sc.nextLine().getBytes();
        ds.send(new DatagramPacket(data, data.length, ip, 9876));
        data = new byte[1024];
        DatagramPacket dp = new DatagramPacket(data, data.length);
        ds.receive(dp);
        System.out.println(new String(dp.getData(), 0, dp.getLength()));
        ds.close();
    }
}
