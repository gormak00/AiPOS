package com.company;

import java.net.Socket;

public class WebServer {
    private Socket s;

    public WebServer(Socket s) {
        this.s = s;
    }
}
