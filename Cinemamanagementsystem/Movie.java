//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Cinemamanagementsystem;

import java.time.LocalDate;
import java.util.Arrays;

public class Movie extends Screening.Movie {
    private String title;
    private String director;
    private String actors;
    private String plotSummary;
    private double duration;
    private LocalDate releaseDate;
    private int numRows;
    private int numCols;
    private final boolean[][] seats;

    public Movie(String title, String director, String actors, String plotSummary, double duration) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.plotSummary = plotSummary;
        this.duration = duration;
        this.numCols = this.numCols;
        this.seats = new boolean[this.numRows][this.numCols];
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
        if (row >= 1 && row <= this.numRows && col >= 1 && col <= this.numCols) {
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

        for(int i = 0; i < this.numRows; ++i) {
            System.out.print(i + 1 + " ");

            for(int j = 0; j < this.numCols; ++j) {
                System.out.print(this.seats[i][j] ? "O " : "X ");
            }

            System.out.println();
        }

    }

    public int getTotalSeats() {
        return this.numRows * this.numCols;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return this.actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlotSummary() {
        return this.plotSummary;
    }

    public void setPlotSummary(String plotSummary) {
        this.plotSummary = plotSummary;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
