package org.example;

import java.io.BufferedReader;
import java.io.DataInputStream;

public class Receiver extends Thread {

    private DataInputStream din;

    public Receiver(DataInputStream din) {
        super();
        this.din = din;
    }

    @Override
    public void run() {
        System.out.println("<<<Receiver thread started>>>");
        try {
            while (true) {
                String received = din.readUTF();
                if(received.equals("exit")) break;
                System.out.println("[Received]: " + received);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("<<<Receiver thread ended>>>");
    }

}
