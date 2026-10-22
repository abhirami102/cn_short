import java.net.*;
import java.io.*;

class ConcurrentFileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        long pid = ProcessHandle.current().pid();

        System.out.println("File Server Started...");

        while (true) {
            Socket s = ss.accept();

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));

                    PrintWriter out = new PrintWriter(
                        s.getOutputStream(), true);

                    String name = in.readLine();
                    out.println("Server PID: " + pid);

                    File f = new File(name);

                    if (f.exists() && f.isFile()) {
                        out.println("File Found");

                        BufferedReader fr = new BufferedReader(
                            new FileReader(f));

                        String line;
                        while ((line = fr.readLine()) != null)
                            out.println(line);

                        fr.close();
                    } else {
                        out.println("File Not Found");
                    }

                    out.println("EOF");
                    s.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
