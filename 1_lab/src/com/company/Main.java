package com.company;

import java.net.InetAddress;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        try
        {
            // устанавливаем сокет на localhost:8080
            ServerSocket server = new ServerSocket(8080, 0,
                    InetAddress.getByName("localhost"));

            System.out.println("server is started");

            while(true)
            {
                new WebServer(server.accept());
            }
        }
        catch(Exception e)
        {System.out.println("init error: "+e);}
    }
}
