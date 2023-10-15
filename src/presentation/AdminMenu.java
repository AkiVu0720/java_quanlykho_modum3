package presentation;

import model.AccountModel;

import java.util.Scanner;

public class AdminMenu {
    public static final String	BLACK= "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    public static final String	BACKGROUND_WHITE= "\u001B[47m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";
    public static void RunAdminMenu(Scanner scanner, AccountModel acc){
        boolean isExit = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println(BACKGROUND_CYAN+"******************WAREHOUSE MANAGEMENT****************"+RESET);
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
                        System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                        break;
                }
            }catch (Exception e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }

        }while (!isExit);
    }

}
