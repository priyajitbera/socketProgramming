package org.example;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// Start the server first then the client
public class Server {
    public static void main(String[] args) {
        int PORT_TO_LISTEN = 6666;
        try {
            ServerSocket serverSocket = new ServerSocket(PORT_TO_LISTEN);
            Socket socket = serverSocket.accept();

            System.out.println("Listening to PORT:" + PORT_TO_LISTEN);
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
            din.close();
            socket.close();
            serverSocket.close();
            System.out.println("<<<Server Closed>>>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
