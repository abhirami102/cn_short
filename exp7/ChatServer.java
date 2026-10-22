import java.net.*;
import java.io.*;
import java.util.*;

class ChatServer {
    static Vector<PrintWriter> clients = new Vector<>();
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        while (true) {
            Socket s = ss.accept();
            new Thread(() -> {
                PrintWriter out = null;
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    out = new PrintWriter(s.getOutputStream(), true);
                    PrintWriter w = out;
                    String name = in.readLine();
                    clients.add(w);
                    String m;
                    while ((m = in.readLine()) != null && !m.equals("/exit"))
                        for (PrintWriter p : clients)
                            if (p != w) p.println(name + ": " + m);
                } catch (Exception e) {
                } finally {
                    clients.remove(out);
                    try { s.close(); } catch (Exception e) {}
                }
            }).start();
        }
    }
}
