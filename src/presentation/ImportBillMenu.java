package presentation;

import Controller.BillController;
import Util.BillType;
import Util.Color;
import repository.BillRepository;
import model.AccountModel;
import model.BillDetailModel;
import model.BillModel;

import java.util.List;
import java.util.Scanner;

public class ImportBillMenu extends BillController {

    public static void runImportBillMenu(Scanner scanner, AccountModel acc) {
        boolean isExit = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println(Color.BACKGROUND_CYAN+"******************RECEIPT MANAGEMENT****************"+Color.RESET);
            System.out.println("1. Danh sách phiếu nhập");
            System.out.println("2. Tạo phiếu nhập");
            System.out.println("3. Cập nhật thông tin phiếu nhập");
            System.out.println("4. Chi tiết phiếu nhập");
            System.out.println("5. Duyệt phiếu nhập");
            System.out.println("6. Tìm kiếm phiếu nhập");
            System.out.println("0. Thoát");
            System.out.println();
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice) {
                    case 1:
                        getListBill(BillType.BILL_TYPE_IMPORT,acc);
                        break;
                    case 2:
                        creatBill(BillType.BILL_TYPE_IMPORT,acc);
                        break;
                    case 3:
                        updateBill(BillType.BILL_TYPE_IMPORT,acc);
                        break;
                    case 4:
                        displayBillDetailMenu(BillType.BILL_TYPE_IMPORT, acc);
                        break;
                    case 5:
                        checkBill(acc,BillType.BILL_TYPE_IMPORT);
                        break;
                    case 6:
                        findBillByCode(BillType.BILL_TYPE_IMPORT,acc);
                        break;
                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                        break;
                }
            } catch (Exception e) {
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            }
        } while (!isExit);
    }
}