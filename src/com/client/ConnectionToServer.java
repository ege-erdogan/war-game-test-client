package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ConnectionToServer {
  public static final String DEFAULT_SERVER_ADDRESS = "localhost";
  public static final int DEFAULT_SERVER_PORT = 4242;
  private Socket s;
  //private BufferedReader br;
  protected BufferedReader in;
  protected PrintWriter out;

  protected String serverAddress;
  protected int serverPort;

  /**
   * @param address IP address of the server, if you are running the server on the same computer as client, put the address as "localhost"
   * @param port    port number of the server
   */
  public ConnectionToServer(String address, int port) {
    serverAddress = address;
    serverPort = port;
  }

  /**
   * Establishes a socket connection to the server that is identified by the serverAddress and the serverPort
   */
  public void Connect() {
    try {
      s = new Socket(serverAddress, serverPort);
      //br= new BufferedReader(new InputStreamReader(System.in));
            /*
            Read and write buffers on the socket
             */
      in = new BufferedReader(new InputStreamReader(s.getInputStream()));
      out = new PrintWriter(s.getOutputStream());

      System.out.println("Successfully connected to " + serverAddress + " on port " + serverPort);

    } catch (IOException e) {
      //e.printStackTrace();
      System.err.println("Error: no server has been found on " + serverAddress + "/" + serverPort);
    }
  }

  /**
   * sends the message String to the server and retrives the answer
   *
   * @param message input message string to the server
   * @return the received server answer
   */
  public String SendForAnswer(String message) {
    String response = new String();
    try {
            /*
            Sends the message to the server via PrintWriter
             */
      out.println(message);
      out.flush();
            /*
            Reads a line from the server via Buffer Reader
             */
      response = in.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("ConnectionToServer. SendForAnswer. Socket read Error");
    }
    return response;
  }


  /**
   * Disconnects the socket and closes the buffers
   */
  public void Disconnect() {
    try {
      in.close();
      out.close();
      //br.close();
      s.close();
      System.out.println("ConnectionToServer. SendForAnswer. Connection Closed");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void sendMessage(String message) {
    out.println(message);
    out.flush();
  }

  public String getResponse() {
    try {
      return in.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
