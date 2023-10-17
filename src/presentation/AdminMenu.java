package presentation;

import Util.Color;
import model.AccountModel;

import java.util.Scanner;

public class AdminMenu {
    public static void handleAdminMenu(Scanner scanner, AccountModel acc){
        boolean isExit = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println(Color.BACKGROUND_CYAN+"******************WAREHOUSE MANAGEMENT****************"+Color.RESET);
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Quản lý tài khoản");
            System.out.println("4. Quản lý phiếu nhập");
            System.out.println("5. Quản lý phiếu xuất");
            System.out.println("6. Quản lý báo cáo");
            System.out.println("0. Thoát");
            System.out.println();
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice){
                    case 1:
                        ProductMenu.handleProductMenu(scanner);
                        break;
                    case 2: EmpMenu.handleEmpMenu(scanner);
                        break;
                    case 3: AccMenu.handleAccMenu();
                        break;
                    case 4: ImportBillMenu.runImportBillMenu(scanner,acc);
                        break;
                    case 5: ExportBIllMenu.runExportBillMenu(scanner,acc);
                        break;
                    case 6: NotifyMenu.runNotifyMenu(scanner);
                        break;
                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                        break;
                }
            }catch (Exception e){
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            }

        }while (!isExit);
    }

}
