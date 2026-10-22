import java.net.*;
import java.io.*;

class MatrixServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6000);
        while (true) {
            Socket s = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            int n = Integer.parseInt(in.readLine());
            int[][] a = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] row = in.readLine().trim().split("\\s+");
                for (int j = 0; j < n; j++)
                    a[i][j] = Integer.parseInt(row[j]);
            }
            boolean up = true, low = true, dia = true;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (i > j && a[i][j] != 0) up = false;
                    if (i < j && a[i][j] != 0) low = false;
                    if (i != j && a[i][j] != 0) dia = false;
                }
            out.println(dia ? "Diagonal Matrix" : up ? "Upper Triangular Matrix" :
                low ? "Lower Triangular Matrix" : "Normal Matrix");
            s.close();
        }
    }
}
