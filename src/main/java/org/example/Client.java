package org.example;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

// Start the server then the client
public class Client {
    public static void main(String[] args) {
        String SERVER_IP = "localhost";
        int PORT_TO_CONNECT = 6666;
        try {
            Socket socket = new Socket(SERVER_IP, PORT_TO_CONNECT);
            System.out.println("Connection established with:" + SERVER_IP + ":" + PORT_TO_CONNECT);
            System.out.println("Local Port: " + socket.getLocalPort());

            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // create threads and start
            Thread receiverThread = new Receiver(din);
            Thread senderThread = new Sender(dout, br);
            receiverThread.start();
            senderThread.start();
            receiverThread.join();
            senderThread.join();

            // close connection
            dout.close();
            socket.close();
            System.out.println("<<<Client Closed>>>");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}