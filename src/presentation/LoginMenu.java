package presentation;

import Controller.LoginController;
import Util.Color;

import java.util.Scanner;

public class LoginMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login(scanner);
    }

    public static void Login(Scanner scanner) {
        do {
            try {
                System.out.println(Color.BACKGROUND_CYAN + "\t\t\t\t\tĐĂNG NHẬP" + Color.RESET);
                System.out.println();
                System.out.println(Color.BACKGROUND_CYAN + "\t\t\tTHOÁT" + Color.RESET);
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println(Color.BACKGROUND_WHITE + "Tài khoản: " + Color.RESET);
                        String userName = scanner.nextLine();
                        System.out.println(Color.BACKGROUND_WHITE + "Mật khẩu: " + Color.RESET);
                        String passWord = scanner.nextLine();
                        LoginController.Login(userName, passWord);
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println(Color.RED + "Lựa chọn của bạn không hợp lệ" + Color.RESET);
                        break;
                }
            } catch (Exception e) {
                System.out.println(Color.RED + "Lựa chọn của bạn không hợp lệ" + Color.RESET);
            }

        } while (true);

    }


}
