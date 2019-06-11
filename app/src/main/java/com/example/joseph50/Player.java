package com.example.joseph50;

public class Player {
    public int no;
    public Player next;

    public Player() {
    }

    public Player(int no, Player next) {
        this.no = no;
        this.next = next;
    }
}
