package presentation;

import business.AccBusiness;
import model.AccountModel;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class LoginMenu {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    public static final String	BACKGROUND_GREEN	= "\u001B[42m";
    public static final String	BACKGROUND_WHITE= "\u001B[47m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RunLoginMenu(scanner);
    }
    public static void RunLoginMenu(Scanner scanner){

        do {
            try {
                System.out.println();
                System.out.println();
            System.out.println(BACKGROUND_CYAN+"\t\t\t\t\tĐĂNG NHẬP"+RESET);
                System.out.println();
            System.out.println(BACKGROUND_CYAN+"\t\t\tTHOÁT"+RESET);

            byte choice = Byte.parseByte(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println(BACKGROUND_WHITE+"Tài khoản: "+RESET);
                    String userName = scanner.nextLine();
                    System.out.println(BACKGROUND_WHITE+"Mật khẩu: "+RESET);
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
                            System.out.println(RED+"Tài khoản bạn đang bị khoá"+RESET);
                        }

                    }else {
                        System.out.println(RED+"Tài khoản không hợp lệ."+RESET);
                    }
                    break;
                case 0: System.exit(0);
                    break;
                default:
                    System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                    break;
            }

        }catch (Exception e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
        }

        }while (true);

    }


}
