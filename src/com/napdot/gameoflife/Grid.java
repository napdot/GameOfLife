package com.napdot.gameoflife;
import java.util.Random;

public class Grid {
    static private int M;
    static private int N;
    static int myGrid[][];

    Grid(int a, int b, int c){
        M = a;
        N = b;
        myGrid = new int[M][N];
        gridToZero();
        gridAddAlive(c);
    }

    Grid(int a, int b, float c){
        M = a;
        N = b;
        myGrid = new int[M][N];
        gridToZero();
        int initialAlive = (int) (M * N * c);
        gridAddAlive(initialAlive);
    }


    Grid(int a){
        M = a;
        N = a;
        myGrid = new int[M][N];;
        int initialAlive = (int) (a * a * 0.2f);
        gridToZero();
        gridAddAlive(initialAlive);
    }

    Grid(){
        M = 10;
        N = 10;
        myGrid = new int[M][N];
        gridToZero();
        gridAddAlive(20);
    }

    private void gridAddAlive(int count) {
        Random r = new Random();
        for (int i= 0; i < count; i++){
            int loc = r.nextInt(M*N);
            myGrid[(loc%M)][(int) (loc/M)] = 1;
        }
    }

    private void gridToZero() {
        for (int row = 0; row < myGrid.length; row++) {
            for (int col = 0; col < myGrid[row].length; col++) {
                myGrid[row][col] = 0;
            }
        }
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int row = 0; row < myGrid.length; row++) {
            for (int col = 0; col < myGrid[row].length; col++) {
                if (myGrid[row][col] == 0) s.append(".");
                else s.append("*");
            }
            s.append("\n");
        }
        String out = s.toString();
        return out;
    };

    public int numberOfAlive(){
        int aliveCount = 0;
        for (int row = 0; row < myGrid.length; row++) {
            for (int col = 0; col < myGrid[row].length; col++) {
                if (myGrid[row][col] == 1) aliveCount++;
            }
        }
        return aliveCount;
    };

    public void nextGen() {
        // Create a copy of the current array
        int[][] tempArr = new int[myGrid.length][];
        for (int i = 0; i < myGrid.length; i++) {
            tempArr[i] = new int[myGrid[1].length];
            System.arraycopy(myGrid[i], 0, tempArr[i], 0, tempArr[i].length);
        }
        // Do the next generation (middle)
        // Alive neighbors
        for (int row = 1; row < tempArr.length - 1; row++) {
            for (int col = 1; col < tempArr[row].length - 1; col++) {
                int alive = 0;
                // Need to subtract the value of grid[row][col] or do i and j with step 2
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        alive += tempArr[row + i][col + j];
                    }
                }
                // If now using step 3, then subtract
                alive -= tempArr[row][col];
                // For new cell
                if ((tempArr[row][col] == 0) && (alive == 3)) {
                    myGrid[row][col] = 1;
                }
                // Neighbor minimum restriction
                if ((tempArr[row][col] == 1) && (alive < 2)) {
                    myGrid[row][col] = 0;
                }
                // Neighbor maximum restriction
                if ((tempArr[row][col] == 1) && (alive > 3)) {
                    myGrid[row][col] = 0;
                }
            }
        }
    }
}
