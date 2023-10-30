package Cinemamanagementsystem;

import java.time.LocalDate;

public class Screening {
    String movie;
    String theater;
    String time;
    double price;
    LocalDate date;

    public Screening(String movie, String theater, String time, double price, LocalDate date) {
        this.movie = movie;
        this.theater = theater;
        this.time = time;
        this.price = price;
        this.date = date;
    }

    public String getMovie() {
        return this.movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getTheater() {
        return this.theater;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMovie(Cinemamanagementsystem.Movie newMovie) {
    }

    static class Movie {
        private String title;

        public Movie() {
            this.title = this.title;
        }

        public String getTitle() {
            return this.title;
        }
    }
}