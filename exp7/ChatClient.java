import java.net.*;
import java.io.*;
import java.util.*;

class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");
        out.println(sc.nextLine());

        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null)
                    System.out.println(msg);
            } catch (Exception e) {}
        }).start();

        while (true) {
            String msg = sc.nextLine();
            out.println(msg);

            if (msg.equals("/exit"))
                break;
        }

        s.close();
    }
}
