package presentation;

import business.BillBusiness;
import business.ProductBusiness;
import model.AccountModel;
import model.BillDetailModel;
import model.BillModel;
import model.ProductModel;

import java.beans.beancontext.BeanContext;
import java.util.List;
import java.util.Scanner;

public class ImportBillMenu {
    public static final boolean BILL_TYPE_IMPORT  = true;
    public static final boolean BILL_TYPE_EXPORT  = false;

    public static void runImportBillMenu(Scanner scanner, AccountModel acc) {
        boolean isExit = false;
        do {
            System.out.println("******************RECEIPT MANAGEMENT****************");
            System.out.println("1. Danh sách phiếu nhập");
            System.out.println("2. Tạo phiếu nhập");
            System.out.println("3. Cập nhật thông tin phiếu nhập");
            System.out.println("4. Chi tiết phiếu nhập");
            System.out.println("5. Duyệt phiếu nhập");
            System.out.println("6. Tìm kiếm phiếu nhập");
            System.out.println("0. Thoát");
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice) {
                    case 1:
                        getListBill(BILL_TYPE_IMPORT,acc);
                        break;
                    case 2:
                        creatInBill(scanner,BILL_TYPE_IMPORT,acc);
                        break;
                    case 3:
                        runUpdateImportBill(scanner,BILL_TYPE_IMPORT,acc);
                        break;
                    case 4:
                        BillDetailMenu(BILL_TYPE_IMPORT);
                        break;
                    case 5:
                        runcheckBill2(scanner,acc,BILL_TYPE_IMPORT);
                        //runcheckBill(scanner,acc,BILL_TYPE_IMPORT);
                        break;
                    case 6:
                        searchBillByCode(scanner,BILL_TYPE_IMPORT,acc);
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

    public static void getListBill(boolean typeBill,AccountModel acc ) {
        Scanner scanner = new Scanner(System.in);
        styleOuput();
        System.out.println();
        List<BillModel> modelList = BillBusiness.getlistBill(typeBill,acc,7,0 );
        modelList.stream().forEach(billModel -> {
            billModel.output();
            System.out.println();
        });
        do {
            try {

                System.out.println("1.Trang 1");
                System.out.println("2.Trang 2");
                System.out.println("3.Trang 3");
                System.out.println("0. Thoát");
                int chon = Integer.parseInt(scanner.nextLine());
                if ( chon == 2){
                    styleOuput();
                    System.out.println();
                    modelList = BillBusiness.getlistBill(typeBill,acc,7,6 );
                    modelList.stream().forEach(billModel -> {
                        billModel.output();
                        System.out.println();
                    });
                }else if (chon==1){
                    styleOuput();
                    System.out.println();
                    modelList = BillBusiness.getlistBill(typeBill,acc,7,0 );
                    modelList.stream().forEach(billModel -> {
                        billModel.output();
                        System.out.println();
                    });
                }else if (chon==3){
                    styleOuput();
                    System.out.println();
                    modelList = BillBusiness.getlistBill(typeBill,acc,7,11 );
                    modelList.stream().forEach(billModel -> {
                        billModel.output();
                        System.out.println();
                    });
                }else
                {
                    return;
                }
            } catch (NumberFormatException e){
                System.out.println("Lựa chọn của bạn ko có");
            } catch (Exception e){
                System.out.println("Lựa chọn của bạn ko có");
            }
        }while (true);
    }

    public static void creatInBill(Scanner scanner,boolean billType, AccountModel acc) {
        boolean iExits = false;
        BillDetailModel billDetail = new BillDetailModel();
        billDetail.inputBillDetail2(scanner,billType);
        billDetail.setBill_Type(billType);
        billDetail.setEmp_id_created(acc.getEmp_id());
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

    public static void runUpdateImportBill(Scanner scanner, boolean billType, AccountModel acc) {
        do {
            System.out.println("Nhập Bill Id: ");
            try {
                int bill = Integer.parseInt(scanner.nextLine());
                updateBill(bill, billType, acc);
                return;
            } catch (NumberFormatException numberFormatException){
                System.out.println("Mã Id là kiểu số.");
            }
             catch (Exception e) {
                e.printStackTrace();
                System.out.println("Lựa chọn của bạn không phù hợp");
            }
        } while (true);
    }

    public static void updateBill(int billId, boolean billType, AccountModel acc) {
        Scanner scanner = new Scanner(System.in);
        BillDetailModel billModel = BillBusiness.getBillById(billId,billType,acc);
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
                System.out.println("4. Cập nhật chi tiết phiếu");
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

    public static void searchBillByCode(Scanner scanner, boolean billType,AccountModel acc) {

        System.out.println("Nhập Bill code cần tìm: ");
        String billCode = scanner.nextLine();
        List<BillModel> modelList = BillBusiness.searchlistBillDetail(billCode,billType,acc);
        if (modelList.size() > 0) {
            styleOuput();
            System.out.println();
            modelList.stream().forEach(billDetailModel -> {
                billDetailModel.output();
                System.out.println();
            });
        } else {
            System.out.println("Mã Bill không tồn tại");
        }
    }

    public static boolean checkBill(String billCode, AccountModel acc, boolean billType) {
        Scanner scanner = new Scanner(System.in);
        BillModel billModel = BillBusiness.getBillByICode(billCode, billType);
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
            if (checkBill(billCode,acc, billType)) {
                System.out.println("Cập nhật thành công.");
                return;
            } else {
                System.out.println("Mã nhập không chính xác.");
            }
        } while (true);
    }
    public static void runcheckBill2(Scanner scanner, AccountModel acc, boolean billType) {
        do {
            System.out.println("Nhập mã bill Code: ");
            String billCode = scanner.nextLine();
            BillModel billModel = BillBusiness.getBillByICode(billCode, billType);
            if (billModel != null) {
                styleOuput();
                System.out.println();
                billModel.output();
                System.out.println();
                billModel.setEmp_id_auth(acc.getEmp_id());
                billModel.setBill_Status( BillModel.validateBill_Status(scanner));
                if (BillBusiness.updateBillDetail(billModel,billType)==0) {
                    System.out.println("Cập nhật thành công. ");
                    return;
                }else {
                    System.out.println("Số lượng tồn kho không đủ để xuất.");;
                    return;
                }
            }else {
                System.out.println("Mã bill không tồn tại");
            }
        } while (true);
    }

    public static void styleOuput() {
        System.out.printf("| %-4s | %-7.20s | %-7.10s | %-14.30s | %-9.10s | %-9.10s | %-9.10s | %-7.10s",
                "BillId", "BillCode", "T.loại", "NV.tạo",
                "Ngày tạo", "NV.Duyệt", "Ngày duyệt", "T.thái"
        );

    }
    public static void styleOutputBillDetail(){
        System.out.printf("| %-7s | %-7s |%-4s |%-5s |\n",
                "billDetailId", "Mã Sp","S.Lg" , "Giá");
    }

    public static void BillDetailMenu(boolean billType) {
        styleOuput();
        styleOutputBillDetail();
        System.out.println();
        getListImportBillDetail(billType);
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
    public static void getListImportBillDetail(boolean billType){
        List<BillDetailModel> modelList = BillBusiness.getlistBillDetail2(billType);
        for (BillDetailModel billDetailModel : modelList) {
            billDetailModel.outputBillDetail();
        }
    }

}