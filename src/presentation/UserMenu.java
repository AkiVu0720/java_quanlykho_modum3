package presentation;

import business.BillBusiness;
import model.AccountModel;

import java.util.Scanner;

public class UserMenu {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";
    public static final boolean BILL_TYPE_IMPORT  = true;
    public static final boolean BILL_TYPE_EXPORT  = false;

    public  static void RunUserMenu(Scanner scanner, AccountModel acc){
        boolean isExit = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println(BACKGROUND_CYAN+"******************WAREHOUSE MANAGEMENT****************"+RESET);
            System.out.println("1. Danh sách phiếu nhập theo trạng thái");
            System.out.println("2. Tạo phiếu nhập");
            System.out.println("3. Cập nhật phiếu nhập");
            System.out.println("4. Tìm kiếm phiếu nhập");
            System.out.println("5. Danh sách phiếu xuất theo trạng thái");
            System.out.println("6. Tạo phiếu xuất");
            System.out.println("7. Cập nhật phiếu xuất");
            System.out.println("8. Tìm kiếm phiếu xuất");
            System.out.println("0. Thoát");
            System.out.println();
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice){
                    case 1:
                        ImportBillMenu.BillDetailMenu(BILL_TYPE_IMPORT, acc);
                        break;
                    case 2:
                        ImportBillMenu.creatInBill(scanner,BILL_TYPE_IMPORT,acc);
                        break;
                    case 3:
                        ImportBillMenu.runUpdateImportBill(scanner,BILL_TYPE_IMPORT,acc);
                        break;
                    case 4:
                        ImportBillMenu.searchBillByCode(scanner,BILL_TYPE_IMPORT,acc);
                        break;
                    case 5:
                        ImportBillMenu.BillDetailMenu(BILL_TYPE_EXPORT,acc);
                        break;
                    case 6:
                        ImportBillMenu.creatInBill(scanner,BILL_TYPE_EXPORT,acc);
                        break;
                    case 7:
                        ImportBillMenu.runUpdateImportBill(scanner,BILL_TYPE_EXPORT,acc);
                        break;
                    case 8:
                        ImportBillMenu.searchBillByCode(scanner,BILL_TYPE_EXPORT,acc);
                        break;
                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                        break;
                }
            } catch (Exception e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }

        }while (!isExit);
    }
}
