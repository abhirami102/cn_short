import java.net.*;
import java.io.*;
import java.util.*;

class ChatServer {
    static Vector<PrintWriter> clients = new Vector<>();

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Chat Server Started...");

        while (true) {
            Socket s = ss.accept();

            new Thread(() -> {
                PrintWriter out = null;

                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));

                    out = new PrintWriter(
                        s.getOutputStream(), true);

                    String name = in.readLine();
                    clients.add(out);

                    for (PrintWriter p : clients)
                        if (p != out)
                            p.println(name + " joined the chat");

                    String msg;

                    while ((msg = in.readLine()) != null
                            && !msg.equals("/exit")) {

                        for (PrintWriter p : clients)
                            if (p != out)
                                p.println(name + ": " + msg);
                    }
                } catch (Exception e) {
                    System.out.println("Client disconnected");
                } finally {
                    if (out != null) {
                        clients.remove(out);
                        for (PrintWriter p : clients)
                            p.println("A user left the chat");
                    }

                    try {
                        s.close();
                    } catch (Exception e) {}
                }
            }).start();
        }
    }
}
