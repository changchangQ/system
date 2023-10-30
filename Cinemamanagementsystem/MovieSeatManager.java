
package Cinemamanagementsystem;

import java.util.Arrays;

public class MovieSeatManager {
    private final boolean[][] seats;

    public MovieSeatManager(int numRows, int numCols) {
        this.seats = new boolean[numRows][numCols];
        this.initializeSeats();
    }

    private void initializeSeats() {
        boolean[][] var1 = this.seats;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            boolean[] row = var1[var3];
            Arrays.fill(row, true);
        }

    }

    public void bookSeat(int row, int col) {
        if (row >= 1 && row <= this.seats.length && col >= 1 && col <= this.seats[0].length) {
            if (this.seats[row - 1][col - 1]) {
                this.seats[row - 1][col - 1] = false;
                System.out.println("成功预定座位：" + row + "-" + col);
            } else {
                System.out.println("该座位已被预定！");
            }
        } else {
            System.out.println("无效的座位位置！");
        }

    }

    public void printSeatMap() {
        System.out.println("\n座位信息：");

        for(int i = 0; i < this.seats.length; ++i) {
            System.out.print(i + 1 + " ");

            for(int j = 0; j < this.seats[i].length; ++j) {
                System.out.print(this.seats[i][j] ? "O " : "X ");
            }

            System.out.println();
        }

    }

    public int getTotalSeats() {
        return this.seats.length * this.seats[0].length;
    }

    public int getAvailableSeats() {
        int count = 0;
        boolean[][] var2 = this.seats;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            boolean[] row = var2[var4];
            boolean[] var6 = row;
            int var7 = row.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                boolean seat = var6[var8];
                if (seat) {
                    ++count;
                }
            }
        }

        return count;
    }
}
