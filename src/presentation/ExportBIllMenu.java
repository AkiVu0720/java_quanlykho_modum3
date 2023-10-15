package presentation;

import business.BillBusiness;
import model.AccountModel;
import model.BillDetailModel;
import model.BillModel;

import java.util.List;
import java.util.Scanner;

public class ExportBIllMenu {
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
                        ImportBillMenu.getListBill(BILL_TYPE_EXPORT, acc);
                        break;
                    case 2:
                        ImportBillMenu.creatInBill(scanner,BILL_TYPE_EXPORT,acc);
                        break;
                    case 3:
                        ImportBillMenu.runUpdateImportBill(scanner,ImportBillMenu.BILL_TYPE_EXPORT,acc);
                        break;
                    case 4:
                        ImportBillMenu.BillDetailMenu(ImportBillMenu.BILL_TYPE_EXPORT,acc);
                        //BillDetailMenu();
                        break;
                    case 5:ImportBillMenu.runcheckBill2(scanner,acc,BILL_TYPE_EXPORT);
                        //runcheckBill(scanner,acc);
                        break;
                    case 6:
                        ImportBillMenu.searchBillByCode(scanner,BILL_TYPE_EXPORT,acc);
                        //searchBillByCode(scanner);
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



    public static void creatInBill(Scanner scanner,boolean billType) {
        boolean iExits = false;
        BillDetailModel billDetail = new BillDetailModel();
        billDetail.inputBillDetail2(scanner,billType);
        billDetail.setBill_Type(billType);
        if (BillBusiness.createBill(billDetail) ){
            System.out.println("Tạo bill thành công");
        }else {
            System.out.println("Tạo mới thất bại");
        }
        BillModel billModel1 = BillBusiness.getBillByICode(billDetail.getBill_Code(),billType);
        billDetail.setBillId(billModel1.getBill_id());

        if (BillBusiness.createBillDetail(billDetail)){
            System.out.println("Tạo chi tiết phiếu nhập thành công");
        }else {

            System.out.println("Tạo mới chi tiết phiếu nhập thất bại");
        }
        do {
            System.out.println("1. Tạo Thêm");
            System.out.println("2. Thoát");

            byte choice = Byte.parseByte(scanner.nextLine());
            switch (choice){
                case 1:BillDetailModel billDetail2 = new BillDetailModel();
                    billDetail2.inputBillDetail(scanner);
                    billDetail2.setBillId(billModel1.getBill_id());
                    if (BillBusiness.createBillDetail(billDetail2)){
                        System.out.println("Tạo chi tiết phiếu nhập thành công");
                    }else {
                        System.out.println("Tạo chi tiết phiếu nhập Thất bại");
                    }
                    break;
                case 2:
                    iExits = true;
            }

        }while (!iExits);
    }


    public static boolean checkBill(String billCode, AccountModel acc, boolean billType) {
        Scanner scanner = new Scanner(System.in);
        BillModel billModel = BillBusiness.getBillByICode(billCode,billType);
        boolean result = false;
        if (billModel != null) {
            styleOuput();
            System.out.println();
            billModel.output();
            System.out.println();
            billModel.setEmp_id_auth(acc.getEmp_id());
            billModel.setBill_Status(BillModel.validateBill_Status(scanner));
            if (BillBusiness.updateBillStatus(billModel)) {
                result = true;
            }
        }
        return result;
    }

    public static void runcheckBill(Scanner scanner, AccountModel acc, boolean billType) {
        do {
            System.out.println("Nhập mã bill Code: ");
            String billCode = scanner.nextLine();
            if (checkBill(billCode,acc,billType)) {
                System.out.println("Cập nhật thành công");
                return;
            } else {
                System.out.println("Cập nhật thất bại:");
            }
        } while (true);
    }

    public static void styleOuput() {
        System.out.printf("| %-4s | %-7.20s | %-7.10s | %-9.30s | %-9.10s | %-9.10s | %-9.10s | %-7.10s",
                "BillId", "BillCode", "T.loại", "NV.tạo",
                "Ngày tạo", "NV.Duyệt", "Ngày duyệt", "T.thái"
        );

    }
    public static void styleOutputBillDetail(){
        System.out.printf("| %-7s | %-7s |%-4s |%-5s |\n",
                "billDetailId", "Mã Sp","S.Lg" , "Giá");
    }

    public static void BillDetailMenu(AccountModel acc) {
        styleOuput();
        styleOutputBillDetail();
        System.out.println();
        getListImportBillDetail(acc);
        System.out.println();
    }
    public static void createBillDetail(Scanner scanner) {
        BillDetailModel billDe = new BillDetailModel();
        billDe.inputBillDetail(scanner);
        if (BillBusiness.createBillDetail(billDe)) {
            System.out.println("Tạo mới thành công");
        } else {
            System.out.println("Tạo mới thất bại");
        }
    }
    public static void getListImportBillDetail( AccountModel acc){
        List<BillDetailModel> modelList = BillBusiness.getlistBillDetail2(BILL_TYPE_IMPORT,acc);
        for (BillDetailModel billDetailModel : modelList) {
            billDetailModel.outputBillDetail();
        }
    }


}
