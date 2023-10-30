package Cinemamanagementsystem;

public class User {
    private String id;
    private String username;
    private String password;
    private String level;
    private String registrationTime;
    private double totalSpending;
    private String phoneNumber;
    private String email;

    public User(String id, String username, String password, String level, String registrationTime, double totalSpending, String phoneNumber, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.level = level;
        this.registrationTime = registrationTime;
        this.totalSpending = totalSpending;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRegistrationTime() {
        return this.registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public double getTotalSpending() {
        return this.totalSpending;
    }

    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
