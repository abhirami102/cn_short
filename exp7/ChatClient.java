import java.net.*;
import java.io.*;
import java.util.*;

class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);
        out.println(sc.nextLine());
        new Thread(() -> {
            try {
                String m;
                while ((m = in.readLine()) != null) System.out.println(m);
            } catch (Exception e) {}
        }).start();
        while (true) {
            String m = sc.nextLine();
            out.println(m);
            if (m.equals("/exit")) break;
        }
        s.close();
    }
}
