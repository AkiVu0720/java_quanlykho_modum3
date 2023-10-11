package presentation;

import business.BillBusiness;
import model.AccountModel;
import model.BillDetailModel;
import model.BillModel;

import java.util.List;
import java.util.Scanner;

public class ExportBIllMenu {

    public static final boolean BILL_TYPE_IMPORT  = true;
    public static final boolean BILL_TYPE_EXPORT  = false;

    public static void runExportBillMenu(Scanner scanner, AccountModel acc) {
        boolean isExit = false;
        do {
            System.out.println("******************RECEIPT MANAGEMENT****************");
            System.out.println("1. Danh sách phiếu xuất");
            System.out.println("2. Tạo phiếu xuất");
            System.out.println("3. Cập nhật thông tin phiếu xuất");
            System.out.println("4. Chi tiết phiếu xuất");
            System.out.println("5. Duyệt phiếu xuất");
            System.out.println("6. Tìm kiếm phiếu xuất");
            System.out.println("0. Thoát");
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
                        ImportBillMenu.BillDetailMenu(ImportBillMenu.BILL_TYPE_EXPORT);
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
                        System.out.println("Lựa chọn của bạn không hợp lệ");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Lựa chọn của bạn không hợp lệ");
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
/*
    public static void runUpdateImportBill(Scanner scanner) {
        do {
            System.out.println("Nhập Bill Id: ");
            try {
                int bill = Integer.parseInt(scanner.nextLine());
                updateBill(bill, BILL_TYPE_IMPORT);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Lựa chọn của bạn không phù hợp");
            }
        } while (true);
    }

    public static void updateBill(int billId, boolean billType) {
        Scanner scanner = new Scanner(System.in);
        BillDetailModel billModel = BillBusiness.getBillById(billId,billType);
        List<BillDetailModel>modelList = BillBusiness.getListBillById(billId,billType);
        if (billModel != null) {
            styleOuput();
            styleOutputBillDetail();
            billModel.outputBillDetail();
            boolean isExit = false;
            do {
                System.out.println("Menu cập nhật");
                System.out.println("1. Cập nhật Mã code");
                System.out.println("2. Cập nhật người tạo.");
                System.out.println("3. Cập nhật ngày tạo.");
                System.out.println("4. Cập nhật trạng thái.");
                System.out.println("5. Cập nhật chi tiết phiếu");
                System.out.println("0. Thoát");
                try {
                    byte choice = Byte.parseByte(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            billModel.setBill_Code(BillModel.validateBillCode(scanner,billType));
                            if (BillBusiness.updateBillCode(billModel)) {
                                System.out.println("Cập nhật thành công");
                            }
                            break;
                        case 2:
                            billModel.setEmp_id_created(BillModel.validteEmpId(scanner));
                            break;
                        case 3:
                            billModel.setDayCreate(BillModel.validateDay(scanner));
                            break;
                        case 4:
                            billModel.setBill_Status(BillModel.validateBill_Status(scanner));
                            break;
                        case 5:
                            updateBillDetail(scanner,modelList,billType);
                            break;
                        case 0:
                            isExit = true;
                            break;
                    }
                    if (BillBusiness.updateBill(billModel, billType)) {
                        System.out.println(" Cập nhật thành công");
                    } else {
                        System.out.println("Cập nhật bill thất bại");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Lựa chọn của bạn không phù hợp");
                }
            } while (!isExit);
        } else {
            System.out.println("Mã bill không tồn tại");
        }

    }
    public static void updateBillDetail(Scanner scanner, List<BillDetailModel>modelList,  boolean billType ){
        System.out.println("Nhập mã BillDetail Id:");{
            try {
                int billId = Integer.parseInt(scanner.nextLine());
                boolean test = modelList.stream().anyMatch(billDetailModel -> billDetailModel.getBillDetailId()==billId);
                if (test){
                    boolean isExit = false;
                    BillDetailModel billDetailModel = BillBusiness.getBillDetailById(billId);
                    do {
                        System.out.println("1. Cập nhật Mã Sp");
                        System.out.println("2. Cập nhật Số lượng");
                        System.out.println("3. Cập nhật Giá");
                        System.out.println("0. Thoát");

                        byte choice = Byte.parseByte(scanner.nextLine());
                        switch (choice){
                            case 1:billDetailModel.setProductId(BillDetailModel.validateProductId(scanner));
                                break;
                            case 2:
                                billDetailModel.setQuantity(BillDetailModel.validateQuantity(scanner));
                                break;
                            case 3:
                                billDetailModel.setPrice(BillDetailModel.validatePrice(scanner));
                                break;
                            case 0:
                                isExit = true;
                                break;
                        }
                    }while (isExit);
                    if (BillBusiness.updateBill(billDetailModel, billType)) {
                        System.out.println(" Cập nhật thành công");
                    } else {
                        System.out.println("Cập nhật billDetail  thất bại");
                    }
                }else {
                    System.out.println("Mã billDetail không có");
                }
            } catch (Exception e){
                System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

 */

    public static void searchBillByCode(Scanner scanner, AccountModel acc) {
        styleOuput();
        styleOutputBillDetail();
        System.out.println("Nhập Bill code cần tìm: ");
        String billCode = scanner.nextLine();
        List<BillModel> modelList = BillBusiness.searchlistBillDetail(billCode,BILL_TYPE_IMPORT,acc);
        if (modelList.size() > 0) {
            modelList.stream().forEach(billDetailModel -> billDetailModel.output());
        } else {
            System.out.println("Mã Bill không tồn tại");
        }
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

    public static void BillDetailMenu() {
        styleOuput();
        styleOutputBillDetail();
        System.out.println();
        getListImportBillDetail();
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
    public static void getListImportBillDetail(){
        List<BillDetailModel> modelList = BillBusiness.getlistBillDetail2(BILL_TYPE_IMPORT);
        for (BillDetailModel billDetailModel : modelList) {
            billDetailModel.outputBillDetail();
        }
    }


}
