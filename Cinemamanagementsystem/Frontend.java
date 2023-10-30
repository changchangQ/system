//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Cinemamanagementsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Frontend {
    private static final String FRONTEND_USERNAME = "frontend";
    private static final String FRONTEND_PASSWORD = "frontend666";
    private static final List<Movie> movieList = new ArrayList();
    private static final List<Screening> screeningList = new ArrayList();
    private static final List<Ticket> TicketList = new ArrayList();
    static MovieSeatManager seatManager = new MovieSeatManager(7, 11);

    public Frontend() {
        seatManager = new MovieSeatManager(7, 11);
    }

    public void createTicket(String movieTitle, String showtime, String customerName, String phoneNumber, double paymentAmount) {
        Ticket ticket = new Ticket(movieTitle, showtime, customerName, phoneNumber, paymentAmount);
        TicketList.add(ticket);
    }

    public static void main(String[] args) {
        login();
    }

    static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎来到影院管理系统！");
        System.out.println("请输入您的用户名： ");
        String username = sc.nextLine();
        System.out.println("请输入您的密码：");
        String password = sc.nextLine();
        if (username.equals("frontend") && password.equals("frontend666")) {
            System.out.println("登陆成功！");
            showMenu();
        } else {
            System.out.println("用户名或密码无效，请重试。");
            login();
        }

    }

    private static void showMenu() {
        System.out.println();
        System.out.println("========== 菜单 ==========");
        System.out.println("1. 列出所有正在上映影片信息");
        System.out.println("2. 列出未来一周内所有上映影片场次计划");
        System.out.println("3. 列出指定电影场次信息");
        System.out.println("4. 售票");
        System.out.println("5. 注销");
        System.out.println("==========================");
        System.out.println("请输入你的选择： ");
        Manager manager = new Manager();
        manager.initialize();
        Frontend frontend = new Frontend();
        frontend.createTicket("Movie Title", "2023-07-29 15:00", "John Doe", "1234567890", 10.0);
        Admin admin = new Admin("丁真", "admin", "ynuinfo#777", "high", "2001.5.7", 0.0, "202011", "dingzhenzhenzhu.com");
        admin.initialize();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                listAllCurrentMovies();
                break;
            case 2:
                listAllMovieShowsScheduled();
                break;
            case 3:
                listsSpecifiedSMovieInformationt();
                break;
            case 4:
                saleTickets();
                break;
            case 5:
                logout();
                break;
            default:
                System.out.println("无效的选择，请重试。");
                showMenu();
        }

    }

    private static void listAllCurrentMovies() {
        LocalDate today = LocalDate.now();
        movieList.stream().filter((movie) -> {
            return movie.getReleaseDate().isBefore(today);
        }).forEach((movie) -> {
            System.out.println("电影名称： " + movie.getTitle());
            System.out.println("导演： " + movie.getDirector());
            System.out.println("主演： " + movie.getActors());
            System.out.println("电影名称： " + movie.getTitle());
            System.out.println("剧情简介： " + movie.getPlotSummary());
            System.out.println("影片时长: " + movie.getDuration());
        });
        showMenu();
    }

    private static void listAllMovieShowsScheduled() {
        LocalDate today = LocalDate.now();
        LocalDate oneweekFromNow = today.plusWeeks(1L);
        movieList.stream().filter((movie) -> {
            return movie.getReleaseDate().isBefore(oneweekFromNow);
        }).forEach((movie) -> {
            System.out.println("电影名称： " + movie.getTitle());
            System.out.println("导演： " + movie.getDirector());
            System.out.println("主演： " + movie.getActors());
            System.out.println("电影名称： " + movie.getTitle());
            System.out.println("剧情简介： " + movie.getPlotSummary());
            System.out.println("影片时长: " + movie.getDuration());
        });
        showMenu();
    }

    private static void listsSpecifiedSMovieInformationt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入搜索查询（标题、导演或演员）：");
        String searchQuery = sc.nextLine();
        System.out.println("请输入第几排：");
        int row = sc.nextInt();
        System.out.println("请输入第几列");
        int col = sc.nextInt();
        MovieSeatManager movieSeatManager = new MovieSeatManager(7, 11);
        List<Movie> searchResults = new ArrayList();
        Iterator var6 = movieList.iterator();

        while(true) {
            Movie movie;
            do {
                if (!var6.hasNext()) {
                    if (searchResults.isEmpty()) {
                        System.out.println("未找到与搜索查询匹配的电影： " + searchQuery);
                    } else {
                        System.out.println("查找结果");
                        var6 = searchResults.iterator();

                        while(var6.hasNext()) {
                            movie = (Movie)var6.next();
                            System.out.println("电影名称： " + movie.getTitle());
                            System.out.println("导演： " + movie.getDirector());
                            System.out.println("主演： " + movie.getActors());
                            System.out.println("电影名称： " + movie.getTitle());
                            System.out.println("剧情简介： " + movie.getPlotSummary());
                            System.out.println("影片时长: " + movie.getDuration());
                            movieSeatManager.getTotalSeats();
                            movieSeatManager.bookSeat(row, col);
                            movieSeatManager.printSeatMap();
                            movieSeatManager.getAvailableSeats();
                        }
                    }

                    showMenu();
                    return;
                }

                movie = (Movie)var6.next();
            } while(!movie.getTitle().equalsIgnoreCase(searchQuery) && !movie.getDirector().equalsIgnoreCase(searchQuery) && !movie.getActors().contains(searchQuery));

            searchResults.add(movie);
        }
    }

    private static void saleTickets() {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入搜索查询（标题、用户名或电话号码）：");
        String searchQuery = sc.nextLine();
        List<Ticket> searchResults = new ArrayList();
        Iterator var3 = TicketList.iterator();

        while(true) {
            Ticket ticket;
            do {
                if (!var3.hasNext()) {
                    if (searchResults.isEmpty()) {
                        System.out.println("未找到与搜索查询匹配的电影： " + searchQuery);
                    } else {
                        System.out.println("查找结果");
                        var3 = searchResults.iterator();

                        while(var3.hasNext()) {
                            ticket = (Ticket)var3.next();
                            System.out.println("电影名称： " + ticket.getMovieTitle());
                            System.out.println("演出时间： " + ticket.getShowtime());
                            System.out.println("用户名  " + ticket.getCustomerName());
                            System.out.println("电话号码： " + ticket.getPhoneNumber());
                            System.out.println("支付金额： " + ticket.getPaymentAmount());
                        }
                    }

                    showMenu();
                    return;
                }

                ticket = (Ticket)var3.next();
            } while(!ticket.getMovieTitle().equalsIgnoreCase(searchQuery) && !ticket.getCustomerName().equalsIgnoreCase(searchQuery) && !ticket.getPhoneNumber().contains(searchQuery));

            searchResults.add(ticket);
        }
    }

    private static void logout() {
        System.out.println("已成功注销！");
        CinemaManagementSystem cinemaManagementSystem = new CinemaManagementSystem();
        CinemaManagementSystem.start();
    }
}