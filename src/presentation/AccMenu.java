package presentation;

import Controller.AccountController;
import Util.Color;
import repository.AccRepository;
import model.AccountModel;

import java.util.List;
import java.util.Scanner;

public class AccMenu extends AccountController {
    public static void handleAccMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println(Color.BACKGROUND_WHITE+"******************ACCOUNT MANAGEMENT****************"+Color.RESET);
            System.out.println("1. Danh sách tài khoản");
            System.out.println("2. Tạo tài khoản mới");
            System.out.println("3. Cập nhật trạng thái tài khoản");
            System.out.println("4. Tìm kiếm tài khoản");
            System.out.println("0. Thoát");
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice) {
                    case 1:
                        getListAcc();
                        break;
                    case 2:
                        createAccount();
                        break;
                    case 3:
                        handleUpdateAccount();
                        break;
                    case 4:
                        findAccByName();
                        break;
                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                        break;
                }
            } catch(Exception e){
                e.printStackTrace();
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            }
        }while (!isExit) ;
    }

}
