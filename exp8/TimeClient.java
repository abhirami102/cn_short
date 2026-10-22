import java.net.*;

class TimeClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();

        byte[] data = "TIME".getBytes();
        InetAddress ip = InetAddress.getByName("localhost");

        ds.send(new DatagramPacket(
            data, data.length, ip, 5000));

        data = new byte[100];
        DatagramPacket dp = new DatagramPacket(data, data.length);
        ds.receive(dp);

        System.out.println("Server Time: " +
            new String(dp.getData(), 0, dp.getLength()));

        ds.close();
    }
}
