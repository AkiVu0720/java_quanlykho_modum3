package presentation;

import business.AccBusiness;
import model.AccountModel;

import java.util.List;
import java.util.Scanner;

public class LoginMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RunLoginMenu(scanner);
    }
    public static void RunLoginMenu(Scanner scanner){

        do {
            try {
            System.out.println("1.Đăng nhập");
            System.out.println("0.Thoát");
            byte choice = Byte.parseByte(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Tài khoản: ");
                    String userName = scanner.nextLine();
                    System.out.println("Mật khẩu: ");
                    String passWord = scanner.nextLine();
                    AccountModel acc = AccBusiness.checkAcc(userName,passWord);
                    if (acc != null){
                        if (acc.isAcc_Status()){
                            if(!acc.isRole_acc()){
                                AdminMenu.RunAdminMenu(scanner, acc);
                            }else {
                                UserMenu.RunUserMenu(scanner,acc);
                            };
                        }else {
                            System.out.println("Tài khoản bạn đang bị khoá");
                        }

                    }else {
                        System.out.println("Tài khoản không hợp lệ.");
                    }
                    break;
                case 0: System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn của bạn không hợp lệ");
                    break;
            }

        }catch (Exception e){
            System.out.println("Lựa chọn không hợp lệ");
        }

        }while (true);

    }


}
