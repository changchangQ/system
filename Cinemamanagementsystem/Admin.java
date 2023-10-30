package Cinemamanagementsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 定义管理员类
public class Admin extends User {

    //初始管理员用户名密码
    private static  String username = "admin";
    private static String storePassword = "ynuinfo#777";
    private static List<User> userList = new ArrayList<>();





    public Admin(String id, String username, String password, String level, String registrationTime, double totalSpending, String phoneNumber, String email) {
        super(id, username, password, level, registrationTime, totalSpending, phoneNumber, email);
    }
    public void initialize() {
        userList.add(new User("1", "user1", "password1", "level1", "2023-01-01", 100.0, "phone1", "email1"));
        userList.add(new User("2", "user2", "password2", "level2", "2023-01-02", 200.0, "phone2", "email2"));
    }


    //登录
    public static void main(String[] args) {


        CinemaManagementSystem cinemaManagementSystem = new CinemaManagementSystem();
        login();
    }

    static void login() {
        //管理员登录入口
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名: ");
        String username = scanner.nextLine();

        System.out.println("请输入密码: ");
        String password = scanner.nextLine();

        if (username.equals(username) && password.equals(storePassword)) {
            System.out.println("登陆成功！");
            showMenu();
        } else {
            System.out.println("用户名或密码无效，请重试。");
            login();
        }
    }

    private static void showMenu() {

        //管理员选择功能

        System.out.println("1. 更改密码");
        System.out.println("2. 重置用户密码");
        System.out.println("3. 列出用户信息");
        System.out.println("4. 删除用户");
        System.out.println("5. 搜索用户");
        System.out.println("6. 注销");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        Admin admin = new Admin("丁真", "admin", "ynuinfo#777", "high", "2001.5.7", 0, "202011", "dingzhenzhenzhu.com");
        admin.initialize();

        switch (choice) {
            case 1:
                changePassword();
                break;
            case 2:
                resetUserPassword();
                break;
            case 3:
                listUserInformation();
                break;
            case 4:
                deleteUser();
                break;
            case 5:
                Scanner sc = new Scanner(System.in);
                System.out.println("请输入要搜索的用户的用户名或id： ");
                String searchQuery = sc.nextLine();
                searchUser(searchQuery);
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("无效选择，请重试！");
                showMenu();
                break;
        }
    }

    private static void changePassword() {

        //改密码
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入旧密码：");
        String oldPassword = sc.nextLine();


        if (isValidPassword(oldPassword)&& oldPassword.equals(storePassword)) {
            storePassword = oldPassword; // 存储旧密码

            System.out.println("请输入新密码：");
            String newPassword = sc.nextLine();

            storePassword = newPassword;
            System.out.println("密码更改成功！");

            login();
        } else {
            System.out.println("旧密码错误，请重新输入！");
            showMenu();
        }

    }

    private static boolean isValidPassword(String storepassword) {

        String pattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@#$%^&+=])[A-Za-z0-9@#$%^&+=]{8,}$";

        return storepassword.matches(pattern);

    }


    private static void resetUserPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入新密码: ");
        String newPassword = scanner.nextLine();  // 这里我们只是简单地将新密码设置为输入的新密码，实际应用中可能需要更复杂的逻辑，比如将新密码发送到用户的电子邮件等。
        boolean found = false;
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);  // 这里我们假设有一个setPassword方法来设置用户的密码。这只是一个例子，实际应用中可能需要更复杂的逻辑。
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("密码已成功重置！");
        } else {
            System.out.println("未找到该用户！");
        }
    }


    private static void listUserInformation() {
        // 列出用户信息

        if (userList.isEmpty()) {
            System.out.println("未找到用户。");
        } else {
            System.out.println("请输入要查找的用户名：");
            Scanner scanner = new Scanner(System.in);
            String searchUsername = scanner.nextLine();

            User foundUser = null;
            for (User user : userList) {
                if (user.getUsername().equals(searchUsername)) {
                    foundUser = user;
                    break;
                }
            }

            if (foundUser != null) {
                System.out.println(foundUser.toString());
            } else {
                System.out.println("未找到该用户。");
            }
        }
        showMenu();
    }

    private static void deleteUser() {

        //删除用户

        Scanner sc = new Scanner(System.in);
        System.out.println("输入要删除的用户的用户名：");
        String username = sc.nextLine();
        System.out.println("是否确实要删除用户：" + username + "? (是/否)");
        String confirmation = sc.nextLine();
        if (confirmation.equals("是")) {
            String usernameDelete = sc.nextLine();
            User userToDelete = null;
            for (User user : userList) {
                if (user.getUsername().equals((userToDelete)))
                    userToDelete = user;
                break;
            }
            if (userToDelete != null) {
                userList.remove(userToDelete);
                System.out.println("该用户已成功删除！");
            } else {
                System.out.println("未找到该用户：" + usernameDelete);
            }
        }
        showMenu();
    }

    private static void searchUser(String searchQuery) {

        //搜索用户

        List<User> searchResults = new ArrayList<>();
        for (User user : userList) {
            if (user.getUsername().equals(searchQuery) || user.getId().equals(searchQuery)) {
                searchResults.add(user);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("未找到与搜索查询匹配的用户：" + searchQuery);
        } else {
            System.out.println("搜索结果：");
            for (User user : searchResults) {
                System.out.println(user);
            }
        }

        showMenu();
    }

    private static void logout() {

        //注销

        System.out.println("已成功注销。");


        CinemaManagementSystem cinemaManagementSystem = new CinemaManagementSystem();
        cinemaManagementSystem.start();
    }
}
