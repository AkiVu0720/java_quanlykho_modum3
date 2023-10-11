package presentation;

import model.AccountModel;

import java.util.Scanner;

public class AdminMenu {
    public static void RunAdminMenu(Scanner scanner, AccountModel acc){
        boolean isExit = false;
        do {
            System.out.println("******************WAREHOUSE MANAGEMENT****************");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Quản lý tài khoản");
            System.out.println("4. Quản lý phiếu nhập");
            System.out.println("5. Quản lý phiếu xuất");
            System.out.println("6. Quản lý báo cáo");
            System.out.println("0. Thoát");
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice){
                    case 1:
                        ProductMenu.runProductMenu(scanner);
                        break;
                    case 2: EmpMenu.runEmpMenu(scanner);
                        break;
                    case 3: AccMenu.runAccMenu(scanner);
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
                        System.out.println("Lựa chọn của bạn không hợp lệ");
                        break;
                }
            }catch (Exception e){
                System.out.println("Lựa chọn của bạn không hợp lệ");
            }

        }while (!isExit);
    }

}
