import java.net.*;
import java.io.*;
import java.util.*;

class MatrixClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 6000);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Enter N: ");
        int n = sc.nextInt();
        out.println(n);

        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = r.nextInt(50) + 1;
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                out.print(a[i][j] + " ");
            out.println();
        }

        System.out.println("Result: " + in.readLine());
        s.close();
    }
}
