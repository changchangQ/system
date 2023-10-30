package Cinemamanagementsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Customer {
    private static final int MAX_LOGIN_ATTEMPTS = 5;
    private static final int LOCK_TIME_SECONDS = 60;

    private static int loginAttempts = 0;
    private static boolean isAccountLocked = false;
    private static List<Movie> movieList = new ArrayList<>();
    private static List<Screening> screeningList = new ArrayList<>();
    private static List<Ticket> TicketList = new ArrayList<>();
    private static final int LOCK_TIMEOUT = 2 * 60 * 1000; // 2 minutes in milliseconds

    private static Map<String, Boolean> seatMap = new HashMap<>();
    private static Map<String, Integer> userLevelDiscount = new HashMap<>();

    static {
        // 初始化座位信息
        seatMap.put("A1", true);
        seatMap.put("A2", true);
        seatMap.put("A3", true);
        seatMap.put("B1", true);
        seatMap.put("B2", true);
        seatMap.put("B3", true);
    }

    public static void displaySeatMap() {
        for (Map.Entry<String, Boolean> entry : seatMap.entrySet()) {
            String seat = entry.getKey();
            boolean isOccupied = !entry.getValue();
            char status = isOccupied ? 'X' : 'O';
            System.out.println(seat + ": " + status);
        }
    }

    public static void main(String[] args) {


        loginIn();
    }

    static void loginIn() {

        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;
        String loggedInUsername = "";

        while (true) {
            System.out.println("--- 欢迎来到用户管理系统 ---");

            System.out.println("1. 注册");
            System.out.println("2. 登录");

            Manager manager = new Manager();
            manager.initialize();

            Frontend frontend = new Frontend(); // 创建一个Frontend对象
            frontend.createTicket("Movie Title", "2023-07-29 15:00", "John Doe", "1234567890", 10.00);

            Admin admin = new Admin("丁真", "admin", "ynuinfo#777", "high", "2001.5.7", 0, "202011", "dingzhenzhenzhu.com");
            admin.initialize();


            System.out.print("请输入你的选择: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    if (!isAccountLocked) {
                        isLoggedIn = login();
                        if (isLoggedIn) {
                        }
                        if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                            System.out.println("您已达到最大登录尝试次数。您的帐户已被锁定。");
                            isAccountLocked = true;
                        }
                    } else {
                        System.out.println("您的帐户已被锁定，请稍后重试。");
                    }
                    break;
                default:
                    System.out.println("无效的选择，请重试。");
                    break;
            }
        }
    }


    private static void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入用户名： ");
        String username = scanner.nextLine();

        if (username.length() < 5) {
            System.out.println("用户名长度应至少为 5 个字符。");
            return;
        }

        System.out.print("请输入密码： ");
        String password = scanner.nextLine();

        if (!isValidPassword(password)) {
            System.out.println("密码长度应至少为 8 个字符，并且必须包含大写和小写字母、数字和符号的组合。");
            return;
        }

        System.out.println("注册成功！");
    }

    static boolean login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入用户名 ");
        String username = scanner.nextLine();

        System.out.print("请输入密码 ");
        String password = scanner.nextLine();

        if (username.equals(username) && password.equals(password)) {
            System.out.println("已成功登录！");
            loginAttempts = 0;
            showMenu();
            return true;
        } else {
            System.out.println("用户名或密码无效。");
            loginAttempts++;
            return false;
        }
    }

    private static boolean isValidPassword(String password) {

        String pattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@#$%^&+=])[A-Za-z0-9@#$%^&+=]{8,}$";

        return password.matches(pattern);

    }


    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@#$%^&+=])[A-Za-z0-9@#$%^&+=]{8,}$";


    private static String loggedInUser;
    private static String loggedInPassword;

    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--- 欢迎来到电影票系统 ---");
            System.out.println("1. 修改密码");
            System.out.println("2. 忘记密码");
            System.out.println("3. 买电影票");
            System.out.println("4. 注销");

            Manager manager = new Manager();
            manager.initialize();

            Frontend frontend = new Frontend(); // 创建一个Frontend对象
            frontend.createTicket("Movie Title", "2023-07-29 15:00", "John Doe", "1234567890", 10.00);

            Admin admin = new Admin("丁真", "admin", "ynuinfo#777", "high", "2001.5.7", 0, "202011", "dingzhenzhenzhu.com");
            admin.initialize();


            System.out.print("请输入你的选择： ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    modifyPassword();

                    break;
                case 2:
                    forgotPassword();
                    break;
                case 3:
                    buyTickets();

                    break;
                case 4:
                    System.out.println("注销成功");
                    CinemaManagementSystem cinemaManagementSystem = new CinemaManagementSystem();
                    cinemaManagementSystem.start();
                    break;
                default:
                    System.out.println("无效的选择，请重试。");
                    break;
            }
        }
    }

    private static boolean isLoggedIn() {
        return loggedInUser != null && loggedInPassword != null;
    }

    private static void modifyPassword() {

        //修改密码

        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入当前密码： ");
        String currentPassword = scanner.nextLine();

        if (!currentPassword.equals(loggedInPassword)) {
            System.out.println("密码无效。");
            return;
        }

        System.out.print("请输入新的密码： ");
        String newPassword = scanner.nextLine();

        if (!newPassword.matches(PASSWORD_PATTERN)) {
            System.out.println("密码长度应至少为 8 个字符，并且必须包含大写和小写字母、数字和符号的组合。");
            return;
        }

        loggedInPassword = newPassword;
        System.out.println("密码修改成功");

        login();
    }

    private static void forgotPassword() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入你的邮箱 ");
        String email = scanner.nextLine();

//            生成随机密码重置代码并将其发送到电子邮件
        String resetCode = generateResetCode();
        sendResetEmail(email, resetCode);
        System.out.println("密码重置代码已发送到您的电子邮件。");
    }

    private static String generateResetCode() {

        final int CODE_LENGTH = 10;
        final String CODE_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CODE_CHARS.length());
            char randomChar = CODE_CHARS.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }


    private static void sendResetEmail(String email, String resetCode) {
        System.out.println("发送重置代码 " + resetCode + " 到电子邮件: " + email);
        // 将重置代码发送到提供的电子邮件地址
    }


    private static void buyTickets() {
//            // 显示电影放映时间、座位可用性、选择座位和付款的逻辑
//            此代码片段只是一个占位符，需要根据需求实现

        Scanner sc = new Scanner(System.in);

        System.out.println("输入搜索查询（标题、用户名或电话号码）：");
        String searchQuery = sc.nextLine();

        System.out.print("请输入座位序号 ");
        String seatNumber = sc.next();

        List<Ticket> searchResults = new ArrayList<>();

        for (Ticket ticket : TicketList) {
            if (ticket.getMovieTitle().equalsIgnoreCase(searchQuery)
                    || ticket.getCustomerName().equalsIgnoreCase(searchQuery)
                    || ticket.getPhoneNumber().contains(searchQuery)) {
                searchResults.add(ticket);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("未找到与搜索查询匹配的电影： " + searchQuery);
        } else {
            System.out.println("查找结果");
            for (Ticket ticket : searchResults) {
                System.out.println("电影名称： " + ticket.getMovieTitle());
                System.out.println("演出时间： " + ticket.getShowtime());
                System.out.println("用户名  " + ticket.getCustomerName());
                System.out.println("电话号码： " + ticket.getPhoneNumber());
                System.out.println("应支付金额： " + ticket.getPaymentAmount());

            }
        }

        for (Map.Entry<String, Boolean> entry : seatMap.entrySet()) {
            String seat = entry.getKey();
            boolean isOccupied = !entry.getValue();
            char status = isOccupied ? 'X' : 'O';
            System.out.println(seat + ": " + status);
        }
    }

    public static void selectSeats(int numOfSeats) {
        Scanner scanner = new Scanner(System.in);
        List<String> selectedSeats = new ArrayList<>();
        for (int i = 0; i < numOfSeats; i++) {
            System.out.print("请输入座位号: ");
            String seat = scanner.nextLine();
            if (!seatMap.containsKey(seat) || seatMap.get(seat)) {
                System.out.println("座位 " + seat + " 不可用，请重试。");
                i--;
                continue;
            }
            selectedSeats.add(seat);
            seatMap.put(seat, false);

        }
        showMenu();
    }

}
