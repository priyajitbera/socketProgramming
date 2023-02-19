package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;

public class Sender extends Thread {

    private DataOutputStream dout;
    private BufferedReader br;

    public Sender(DataOutputStream dout, BufferedReader br) {
        super();
        this.dout = dout;
        this.br = br;
    }

    @Override
    public void run() {
        System.out.println("<<Sender thread started>>");
        try {
            while (true) {
                System.out.println("[Type Message to Send, or type exit]: ");
                String input = br.readLine();
                dout.writeUTF(input);
                dout.flush();
                // exit condition
                if (input.equals("exit"))
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("<<<Sender thread ended>>>");
    }

}
