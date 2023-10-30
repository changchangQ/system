//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Cinemamanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaManagementSystem {
    private final List<User> userList = new ArrayList();

    public CinemaManagementSystem() {
    }

    static void start() {
        Admin admin = new Admin("丁真", "admin", "ynuinfo#777", "high", "2001.5.7", 0.0, "202011", "dingzhenzhenzhu.com");
        Manager manager = new Manager();
        Frontend frontend = new Frontend();
        Customer customer = new Customer();
        mainShowMenu();
        Scanner sc = new Scanner(System.in);
        int identity = sc.nextInt();
        switch (identity) {
            case 1:
                Admin.login();
                break;
            case 2:
                Manager.login();
                break;
            case 3:
                Frontend.login();
                break;
            case 4:
                Customer.loginIn();
                break;
            default:
                System.out.println("无效的选择，请重试。");
                mainShowMenu();
        }

    }

    static void mainShowMenu() {
        System.out.println("==========================");
        System.out.println("请选择你的身份： ");
        System.out.println("1. 管理员");
        System.out.println("2. 经理");
        System.out.println("3. 前台");
        System.out.println("4. 客户");
        System.out.println("==========================");
    }
}
