package com.napdot.gameoflife;
import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        PrintWriter pw = new PrintWriter(System.out, true);
        Scanner in = new Scanner(System.in);

        int N, M, alive;

        pw.println("Conway's Game of Life");
        try {
            pw.println("Enter grid size M:");
            M = in.nextInt();
        }
        catch(Exception e){
            pw.println("Not valid integer... Using 10");
            M = 10;
        }
        try {
            pw.println("Enter grid size N:");
            N = in.nextInt();
        }
        catch(Exception e){
            pw.println("Not valid integer... Using 10");
            N = 10;
        }
        try {
            pw.print("Enter initial alive count in range 0-");
            int maxAlive = (int) (M * N);
            pw.println(maxAlive);
            alive = in.nextInt();
            if (alive > maxAlive){
                throw new Exception("alivecount > maxAlive");
            }
        }
        catch(Exception e){
            pw.print("Not valid integer... Using ");
            alive = (int) (M * N * 0.2);
            pw.println(alive);
        }

        pw.println("Inititating new grid...");
        Grid myGrid = new Grid(M, N, alive);
        pw.println(myGrid);
        int currentlyAlive = myGrid.numberOfAlive();
        int turn = 1;
        int maxTurns = 100;
        int maxAliveTurn [] = {alive, turn};
        while ((currentlyAlive > 0) & (turn <= maxTurns)){
            myGrid.nextGen();
            pw.print("Current turn -----  ");
            pw.println(turn);
            pw.println(myGrid);
            currentlyAlive = myGrid.numberOfAlive();
            if (currentlyAlive > maxAliveTurn[0]){
                maxAliveTurn[0] = currentlyAlive;
                maxAliveTurn[1] = turn;
            }
            turn++;
        }
    }
}