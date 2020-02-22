package com.client;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        ConnectionToServer connectionToServer = new ConnectionToServer("localhost", 4242);
        connectionToServer.Connect();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> deck = null;
        String message = "";
        while (!message.equals("5")) {
            String prompt = connectionToServer.getResponse();

            System.out.println(prompt);
            if (prompt.equals("1") && deck == null) {
                deck = new ArrayList<>();
                for (int i = 0; i < 26; i++) {
                    Integer card = Integer.parseInt(connectionToServer.getResponse());
                    deck.add(card);
                }
                System.out.println(deck);
            } else {
                if (prompt.contains("Enter")) {
                    message = scanner.nextLine();
                    connectionToServer.sendMessage(message);
                }
            }
        }

        connectionToServer.Disconnect();
    }

}

