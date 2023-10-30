//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Cinemamanagementsystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Manager {
    private static final String MANAGER_USERNAME = "manager";
    private static final String MANAGER_PASSWORD = "manager666";
    private static final List<Movie> movieList = new ArrayList();
    private static final List<Screening> screeningList = new ArrayList();

    public Manager() {
    }

    public void initialize() {
        movieList.add(new Movie("Title1", "Director1", "Actors1", "PlotSummary1", 120.0));
        movieList.add(new Movie("Title2", "Director2", "Actors2", "PlotSummary2", 180.0));
        screeningList.add(new Screening("Movie1", "Theater1", "10:00", 50.0, LocalDate.now().minusDays(1L)));
        screeningList.add(new Screening("Movie2", "Theater2", "12:00", 60.0, LocalDate.now().plusDays(1L)));
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
        if (username.equals("ynuinfo#777") && password.equals("manager666")) {
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
        System.out.println("1. 影片管理");
        System.out.println("2. 排片管理");
        System.out.println("3. 注销");
        System.out.println("==========================");
        System.out.println("请输入你的选择： ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        Manager manager = new Manager();
        manager.initialize();
        switch (choice) {
            case 1:
                movieManagement();
                break;
            case 2:
                screeningManagement();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("无效的选择，请重试。");
                showMenu();
        }

    }

    private static void movieManagement() {
        System.out.println("========== 影片管理 ==========");
        System.out.println("1. 列出所有正在上映影片");
        System.out.println("2. 添加影片");
        System.out.println("3. 修改影片");
        System.out.println("4. 删除影片");
        System.out.println("5. 查询影片");
        System.out.println("6. 返回主菜单");
        System.out.println("======================================");
        System.out.println("请输入你的选择");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                listAllMovies();
                break;
            case 2:
                addMovie();
                break;
            case 3:
                updateMovie();
                break;
            case 4:
                deleteMovie();
                break;
            case 5:
                searchMovies();
                break;
            case 6:
                showMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                movieManagement();
        }

    }

    private static void listAllMovies() {
        if (movieList.isEmpty()) {
            System.out.println("未找到该电影！");
        } else {
            System.out.println("当前正在播放的电影：");
            Iterator var0 = movieList.iterator();

            while(var0.hasNext()) {
                Movie movie = (Movie)var0.next();
                System.out.println(movie.getTitle());
            }
        }

        movieManagement();
    }

    private static void addMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入影片标题：");
        String title = sc.nextLine();
        System.out.println("请输入导演： ");
        String director = sc.nextLine();
        System.out.println("请输入主要演员：");
        String actors = sc.nextLine();
        System.out.println("请输入主要情节梗概");
        String plotSummary = sc.nextLine();
        System.out.println("请输入持续时间（以分钟为单位）：\n ");
        int duration = sc.nextInt();
        Movie newMovie = new Movie(title, director, actors, plotSummary, duration);
        movieList.add(newMovie);
        System.out.println("电影添加成功！");
        movieManagement();
    }

    private static void updateMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要更新的影片标题： ");
        String title = sc.nextLine();
        Movie movieToUpdate = findMovieByTitle(title);
        if (movieToUpdate != null) {
            System.out.println("请输入新的导演(或按Enter键保留当前导演): ");
            String director = sc.nextLine();
            if (!director.isEmpty()) {
                movieToUpdate.setDirector(director);
            }

            System.out.println("请输入新的演员(或按Enter键保留当前演员):  ");
            String actors = sc.nextLine();
            if (!actors.isEmpty()) {
                movieToUpdate.setActors(actors);
            }

            System.out.println("请输入新的情节梗概(或按Enter键保留当前情节梗概): ");
            String plotSummary = sc.nextLine();
            if (!plotSummary.isEmpty()) {
                movieToUpdate.setPlotSummary(plotSummary);
            }

            System.out.println("请输入新的电影时长(或按Enter键保留当前电影时长):  ");
            int duration = sc.nextInt();
            if (duration > 0) {
                movieToUpdate.setDuration(duration);
            }

            System.out.println("电影已成功更新！");
        } else {
            System.out.println("未找到该电影");
        }

        movieManagement();
    }

    private static void deleteMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入要删除的影片的标题：");
        String title = scanner.nextLine();
        Movie movieToDelete = findMovieByTitle(title);
        if (movieToDelete != null) {
            System.out.println("是否确实要删除电影 " + title + "? (是/否)");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("Y")) {
                movieList.remove(movieToDelete);
                System.out.println("电影 " + title + " 删除成功!");
            } else {
                System.out.println("取消删除");
            }
        } else {
            System.out.println("未找到该电影！");
        }

        movieManagement();
    }

    private static void searchMovies() {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入搜索查询（标题、导演或演员）：");
        String searchQuery = sc.nextLine();
        List<Movie> searchResults = new ArrayList();
        Iterator var3 = movieList.iterator();

        while(true) {
            Movie movie;
            do {
                if (!var3.hasNext()) {
                    if (searchResults.isEmpty()) {
                        System.out.println("未找到与搜索查询匹配的电影： " + searchQuery);
                    } else {
                        System.out.println("查找结果");
                        var3 = searchResults.iterator();

                        while(var3.hasNext()) {
                            movie = (Movie)var3.next();
                            System.out.println("电影名：" + movie.getTitle());
                            System.out.println("导演：" + movie.getDirector());
                            System.out.println("剧情梗概：" + movie.getPlotSummary());
                            System.out.println("影片时长" + movie.getDuration());
                        }
                    }

                    movieManagement();
                    return;
                }

                movie = (Movie)var3.next();
            } while(!movie.getTitle().equalsIgnoreCase(searchQuery) && !movie.getDirector().equalsIgnoreCase(searchQuery) && !movie.getActors().contains(searchQuery));

            searchResults.add(movie);
        }
    }

    private static void screeningManagement() {
        System.out.println("========== 排片管理 ==========");
        System.out.println("1. 增加场次");
        System.out.println("2. 修改场次");
        System.out.println("3. 删除场次");
        System.out.println("4. 列出所有场次");
        System.out.println("5. 返回主菜单");
        System.out.println("==========================================");
        System.out.println("请输入你的选择");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addScreening();
                break;
            case 2:
                updateScreening();
                break;
            case 3:
                deleteScreening();
                break;
            case 4:
                listAllScreenings();
                break;
            case 5:
                showMenu();
                break;
            default:
                System.out.println("无效的选择，请重试。");
                screeningManagement();
        }

    }

    private static void addScreening() {
        System.out.println("请输入该电影名称 ");
        Scanner sc = new Scanner(System.in);
        String movieTitle = sc.nextLine();
        Movie movie = findMovieByTitle(movieTitle);
        if (movie == null) {
            System.out.println("未找到该电影");
            screeningManagement();
        }

        System.out.println("请输入该放映厅");
        String theater = sc.nextLine();
        System.out.println("请输入该日期 (年-月-日): ");
        String dateStr = sc.nextLine();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        System.out.println("请输入时间 (时:分): ");
        String time = sc.nextLine();
        System.out.println("请输入票价: ");
        double price = sc.nextDouble();
        sc.nextLine();
        Screening newScreening = new Screening(movie.getTitle(), theater, time, price, date);
        screeningList.add(newScreening);
        LocalDate oneWeekAhead = LocalDate.now().plusWeeks(1L);
        if (date.isAfter(oneWeekAhead)) {
            throw new IllegalArgumentException("仅允许一周内排片");
        } else {
            screeningList.add(newScreening);
            System.out.println("场次添加成功");
            screeningManagement();
        }
    }

    private static void updateScreening() {
        System.out.println("请输入要修改电影场次的名称 ");
        Scanner sc = new Scanner(System.in);
        String movieTitle = sc.nextLine();
        Screening screeningToUpdate = findScreeningByMovieTitle(movieTitle);
        if (screeningToUpdate == null) {
            System.out.println("未找到该场次.");
            screeningManagement();
        }

        System.out.println("请输入电影名称 ");
        String newMovieTitle = sc.nextLine();
        Movie newMovie = findMovieByTitle(newMovieTitle);
        if (newMovie == null) {
            System.out.println("未找到该电影");
            screeningManagement();
        }

        System.out.println("请输入该放映厅");
        String newTheater = sc.nextLine();
        System.out.println("请输入该日期 (年-月-日): ");
        String newDateStr = sc.nextLine();
        LocalDate newDate = LocalDate.parse(newDateStr, DateTimeFormatter.ISO_DATE);
        System.out.println("请输入时间 (时:分): ");
        String newTime = sc.nextLine();
        System.out.println("请输入票价: ");
        double newPrice = sc.nextDouble();
        sc.nextLine();
        screeningToUpdate.setMovie(newMovie);
        screeningToUpdate.setTheater(newTheater);
        screeningToUpdate.setDate(newDate);
        screeningToUpdate.setTime(newTime);
        screeningToUpdate.setPrice(newPrice);
        System.out.println("场次修改成功");
        screeningManagement();
    }

    private static void deleteScreening() {
        System.out.println("请输入要删除的电影场次 ");
        Scanner sc = new Scanner(System.in);
        String movieTitle = sc.nextLine();
        Screening screeningToDelete = findScreeningByMovieTitle(movieTitle);
        if (screeningToDelete == null) {
            System.out.println("未找到该场次");
            screeningManagement();
        }

        screeningList.remove(screeningToDelete);
        System.out.println("该场次删除成功");
        screeningManagement();
    }

    private static void listAllScreenings() {
        if (screeningList.isEmpty()) {
            System.out.println("未找到场次");
        } else {
            System.out.println("电影场次:");
            Iterator var0 = screeningList.iterator();

            while(var0.hasNext()) {
                Screening screening = (Screening)var0.next();
                System.out.println(screening.getTheater());
                System.out.println(screening.getDate());
                System.out.println(screening.getTime());
                System.out.println(screening.getPrice());
            }
        }

        screeningManagement();
    }

    private static Movie findMovieByTitle(String title) {
        Iterator var1 = movieList.iterator();

        Movie movie;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            movie = (Movie)var1.next();
        } while(!movie.getTitle().equalsIgnoreCase(title));

        return movie;
    }

    private static Screening findScreeningByMovieTitle(String movieTitle) {
        Iterator var1 = screeningList.iterator();

        Screening screening;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            screening = (Screening)var1.next();
        } while(!screening.getMovie().equalsIgnoreCase(movieTitle));

        return screening;
    }

    private static void logout() {
        System.out.println("已成功注销！");
        CinemaManagementSystem cinemaManagementSystem = new CinemaManagementSystem();
        CinemaManagementSystem.start();
    }
}
