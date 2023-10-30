package Cinemamanagementsystem;


class Ticket {
    private String movieTitle;
    private String showtime;
    private String customerName;
    private String phoneNumber;
    private double paymentAmount;

    public Ticket(String movieTitle, String showtime, String customerName, String phoneNumber, double paymentAmount) {
        this.movieTitle = movieTitle;
        this.showtime = showtime;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.paymentAmount = paymentAmount;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}