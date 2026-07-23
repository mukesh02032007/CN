import java.io.*;
import java.net.*;
class Server {
    public static void main(String[] args) {
        try {
            // Create a server socket on port 3000
            ServerSocket obj = new ServerSocket(3000);
            System.out.println("Server started, waiting for client...");
            // Accept the incoming client connection
            Socket obj1 = obj.accept();
            System.out.println("Client connected.");
            // BufferedReader to read from client input stream
            BufferedReader din = new BufferedReader(new InputStreamReader(obj1.getInputStream()));
            // DataOutputStream to write to client output stream
            DataOutputStream dout = new DataOutputStream(obj1.getOutputStream());
            // Define IP and MAC address pairs
            String[] ip = {"165.165.80.80", "165.165.79.1"};
            String[] mac = {"6A:08:AA:C2", "8A:BC:E3:FA"};
            while (true) {
                // Read the MAC address from the client
                String str = din.readLine();
                if (str == null) {
                    break; // Exit the loop if client disconnects
                }
                boolean found = false;
                // Look for the corresponding IP address for the given MAC
                for (int i = 0; i < mac.length; i++) {
                    if (str.equals(mac[i])) {
                        dout.writeBytes(ip[i] + '\n');
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    dout.writeBytes("IP not found\n");
                }
            }
            // Close the connection
            obj1.close();
            obj.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

import java.io.*;
import java.net.*;
class Csix {
    public static void main(String[] args) {
        try {
            // BufferedReader for user input from the console
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            // Create a socket to connect to the server on localhost:3000
            Socket clsct = new Socket("127.0.0.1", 3000);
            // BufferedReader to read from the server's input stream
            BufferedReader din = new BufferedReader(new InputStreamReader(clsct.getInputStream()));
            // DataOutputStream to write to the server's output stream
            DataOutputStream dout = new DataOutputStream(clsct.getOutputStream());
            // Prompt user to enter the MAC address
            System.out.println("Enter the Physical Address (MAC):");
            String str1 = in.readLine(); // Read user input
            // Send the MAC address to the server
            dout.writeBytes(str1 + '\n');
            // Read the server's response
            String str = din.readLine();
            System.out.println("The Logical address is (IP): " + str);
            // Close streams and connection
            in.close();
            din.close();
            dout.close();
            clsct.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
