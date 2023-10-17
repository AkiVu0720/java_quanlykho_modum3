package presentation;

import Controller.BillController;
import Util.BillType;
import repository.BillRepository;
import model.AccountModel;
import model.BillDetailModel;
import model.BillModel;

import java.util.List;
import java.util.Scanner;

public class ExportBIllMenu extends BillController {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";
    public static final boolean BILL_TYPE_IMPORT  = true;
    public static final boolean BILL_TYPE_EXPORT  = false;

    public static void runExportBillMenu(Scanner scanner, AccountModel acc) {
        boolean isExit = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println(BACKGROUND_CYAN+"******************RECEIPT MANAGEMENT****************"+RESET);
            System.out.println("1. Danh sách phiếu xuất");
            System.out.println("2. Tạo phiếu xuất");
            System.out.println("3. Cập nhật thông tin phiếu xuất");
            System.out.println("4. Chi tiết phiếu xuất");
            System.out.println("5. Duyệt phiếu xuất");
            System.out.println("6. Tìm kiếm phiếu xuất");
            System.out.println("0. Thoát");
            System.out.println();
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice) {
                    case 1:
                        getListBill(BillType.BILL_TYPE_EXPORT,acc);
                        break;
                    case 2:
                        creatBill(BillType.BILL_TYPE_EXPORT,acc);
                        break;
                    case 3:
                        updateBill(BillType.BILL_TYPE_EXPORT,acc);
                        break;
                    case 4:
                        displayBillDetailMenu(BillType.BILL_TYPE_EXPORT, acc);
                        break;
                    case 5:
                        checkBill(acc,BillType.BILL_TYPE_EXPORT);
                        break;
                    case 6:
                        findBillByCode(BillType.BILL_TYPE_EXPORT,acc);
                        break;
                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                        break;
                }
            } catch (Exception e) {
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }
        } while (!isExit);

    }
}
