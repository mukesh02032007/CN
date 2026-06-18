import java.io.*;
import java.net.*;
public class webc {
    public static void main(String[] args) {
        try {
            Socket cs = new Socket("example.com", 80);
            PrintWriter w = new PrintWriter(cs.getOutputStream(), true);
            // Proper HTTP request
            w.println("GET / HTTP/1.1");
            w.println("Host: example.com");
            w.println("Connection: close");
            w.println();
            BufferedReader r = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            String l;
            while ((l = r.readLine()) != null) {
                System.out.println(l);
            }
            r.close();
            w.close();
            cs.close();
        } catch (IOException e) {
            System.out.println("Error in connecting to server");
            System.out.println(e.getMessage());
        }
    }
}



