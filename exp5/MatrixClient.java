import java.net.*;
import java.io.*;
import java.util.*;

class MatrixClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 6000);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        out.println(n);
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            String row = "";
            for (int j = 0; j < n; j++)
                row += (r.nextInt(50) + 1) + " ";
            out.println(row);
            System.out.println(row);
        }
        System.out.println(in.readLine());
        s.close();
    }
}
