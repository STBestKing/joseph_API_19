package com.example.joseph50;

public class Game {
    public Player H;
    public Player rare;

    public Game() {

    }

    public void CreateGame(int n) {
        Player temp;
        Player end;
        H = end = new Player();
        end.next = H;
        H.no = 1;
        for (int i=2; i<=n; i++) {
            temp = new Player();
            temp.no = i;
            temp.next = H;
            end.next = temp;
            end = temp;
        }
    }

    public void SetStart(int start) {
        rare = H;
        while (rare.next != H) {
            rare = rare.next;
        }
        for (int i=1; i<start; i++) {
            H = H.next;
            rare = rare.next;
        }
    }

    public int runonce(int count) {
        if (H!=null) {
            for (int i=1; i<count; i++){
                H = H.next;
                rare = rare.next;
            }
            int x = H.no;
            rare.next = H.next;
            H = rare.next;
            return x;
        }
        else {
            return -1;
        }
    }
}
