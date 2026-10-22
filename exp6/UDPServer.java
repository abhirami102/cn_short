import java.net.*;

class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(9876);
        System.out.println("Server Started...");

        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            ds.receive(dp);

            String msg = new String(
                dp.getData(), 0, dp.getLength());

            msg = msg.replace("tbh", "to be honest")
                     .replace("ig", "I guess")
                     .replace("lol", "laughing out loud")
                     .replace("idk", "I don't know");

            byte[] data = msg.getBytes();

            ds.send(new DatagramPacket(
                data, data.length,
                dp.getAddress(), dp.getPort()));
        }
    }
}
