package com.bswanepo;

public class App {

    public static void main(String[] args) {
        
        LobbyController lobbyController = new LobbyController();
        lobbyController.startScreen();
        lobbyController.startOrEndGame();
    }
}