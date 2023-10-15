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
    public static final String	BLACK= "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    public static final String	BACKGROUND_WHITE= "\u001B[47m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";
    public static final boolean BILL_TYPE_IMPORT  = true;
    public static final boolean BILL_TYPE_EXPORT  = false;


    public static void runImportBillMenu(Scanner scanner, AccountModel acc) {
        boolean isExit = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println(BACKGROUND_CYAN+"******************RECEIPT MANAGEMENT****************"+RESET);
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
                        getListBill(BILL_TYPE_IMPORT,acc);
                        break;
                    case 2:
                        creatInBill(scanner,BILL_TYPE_IMPORT,acc);
                        break;
                    case 3:
                        runUpdateImportBill(scanner,BILL_TYPE_IMPORT,acc);
                        break;
                    case 4:
                        BillDetailMenu(BILL_TYPE_IMPORT, acc);
                        break;
                    case 5:
                        runcheckBill2(scanner,acc,BILL_TYPE_IMPORT);
                        break;
                    case 6:
                        searchBillByCode(scanner,BILL_TYPE_IMPORT,acc);
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

    public static void getListBill(boolean typeBill,AccountModel acc ) {
        Scanner scanner = new Scanner(System.in);
List<BillModel>modelListsize = BillBusiness.getSizeBill(typeBill,acc);
        int pageNumber = modelListsize.size();
        int indexPage = 0;
        do {
            try {
                if (pageNumber == 0){
                    System.out.println( RED+"Chưa có phiếu xuất nào"+RESET);
                    System.out.println();
                    return;
                }else {
                    styleOuput();
                    System.out.println();
                    List<BillModel> modelList = BillBusiness.getlistBill(typeBill, acc, 7, indexPage);
                    modelList.stream().forEach(billModel -> {
                        billModel.output();
                        System.out.println();
                    });
                    if (indexPage == 0) {
                        System.out.println(GREEN2 + "\t\t\t2.Trang tiếp" + RESET);
                        System.out.println("0. Thoát");
                    } else if (indexPage + 5 >= pageNumber) {
                        System.out.print(GREEN2 + "\"\t\t\t\"1.Trang sau" + RESET);
                        System.out.println("0. Thoát");
                    } else {
                        System.out.print(GREEN2 + "\"\t\t\t\"1.Trang sau" + RESET);
                        System.out.println(GREEN2 + "\t\t\t2.Trang tiếp" + RESET);
                        System.out.println("0. Thoát");
                    }
                    int chon = Integer.parseInt(scanner.nextLine());
                    if (chon == 2) {
                        indexPage += 5;
                    } else if (indexPage > 0 && chon == 1) {
                        indexPage -= 5;
                    } else {
                        return;
                    }
                }
            } catch (NumberFormatException e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            } catch (Exception e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
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
            System.out.println(GREEN2+"Tạo bill thành công"+RESET);
        }else {
            System.out.println(RED+"Tạo mới thất bại"+RESET);
        }
        BillModel billModel1 = BillBusiness.getBillByICode(billDetail.getBill_Code(),billType);
        billDetail.setBillId(billModel1.getBill_id());

            if (BillBusiness.createBillDetail(billDetail)){
                System.out.println(GREEN2+"Tạo chi tiết phiếu nhập thành công"+RESET);
        }else {

            System.out.println(RED+"Tạo mới chi tiết phiếu nhập thất bại"+RESET);
        }
        do {
            System.out.println(YELLOW+"1. Tạo Thêm"+RESET);
            System.out.println(YELLOW+"2. Thoát"+RESET);

            byte choice = Byte.parseByte(scanner.nextLine());
            switch (choice){
                case 1:BillDetailModel billDetail2 = new BillDetailModel();
                    billDetail2.inputBillDetail(scanner);
                    billDetail2.setBillId(billModel1.getBill_id());
                    if (BillBusiness.createBillDetail(billDetail2)){
                        System.out.println(GREEN2+"Done!"+RESET);
                    }else {
                        System.out.println(RED+"Tạo chi tiết phiếu nhập Thất bại"+RESET);
                    }
                    break;
                case 2:
                    iExits = true;
            }

        }while (!iExits);
    }

    public static void runUpdateImportBill(Scanner scanner, boolean billType, AccountModel acc) {
        do {
            System.out.println(YELLOW+"Nhập Bill Id: "+RESET);
            try {
                int bill = Integer.parseInt(scanner.nextLine());
                updateBill(bill, billType, acc);
                return;
            } catch (NumberFormatException numberFormatException){
                System.out.println(RED+"Mã Id là kiểu số."+RESET);
            }
             catch (Exception e) {
                e.printStackTrace();
                 System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }
        } while (true);
    }

    public static void updateBill(int billId, boolean billType, AccountModel acc) {
        Scanner scanner = new Scanner(System.in);
        BillDetailModel billModel = BillBusiness.getBillById(billId,billType,acc);
        List<BillDetailModel>modelList = BillBusiness.getListBillById(billId,billType);
        int error = 0;
        if (billModel == null){
            System.out.println(RED+"Mã bill không tồn tại"+RESET);
            error++;
        }
        if (billModel.getBill_Status()==2){
            System.out.println("Phiếu đã đc duyệt");
            error++;
        }
        if (error==0) {
            styleOuput();
            styleOutputBillDetail();
            modelList.stream().forEach(billDetailModel -> billDetailModel.outputBillDetail());
//            billModel.outputBillDetail();
            boolean isExit = false;
            do {
                System.out.println(BACKGROUND_WHITE+BLACK+"Menu cập nhật"+RESET);
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
                                System.out.println(GREEN2+"Cập nhật thành công"+RESET);
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
                        System.out.println(GREEN2+" Cập nhật thành công"+RESET);
                    } else {
                        System.out.println(RED+"Cập nhật bill thất bại"+RESET);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                }
            } while (!isExit);
        } else {
            System.out.println(RED+"Mã bill không tồn tại"+RESET);
        }

    }
    public static void updateBillDetail(Scanner scanner, List<BillDetailModel>modelList,  boolean billType ){
        System.out.println(YELLOW+"Nhập mã BillDetail Id:"+RESET);{
            try {
                int billId = Integer.parseInt(scanner.nextLine());
                boolean test = modelList.stream().anyMatch(billDetailModel -> billDetailModel.getBillDetailId()==billId);
                    if (test){
                        boolean isExit = false;
                        BillDetailModel billDetailModel = BillBusiness.getBillDetailById(billId);
                        do {
                            System.out.println(YELLOW+"1. Cập nhật Mã Sp"+RESET);
                            System.out.println(YELLOW+"2. Cập nhật Số lượng"+RESET);
                            System.out.println(YELLOW+"3. Cập nhật Giá"+RESET);
                            System.out.println(YELLOW+"0. Thoát"+RESET);

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
                        }while (!isExit);
                        if (BillBusiness.updateBill(billDetailModel, billType)) {
                            System.out.println(GREEN2+" Cập nhật thành công"+RESET);
                        } else {
                            System.out.println(RED+"Cập nhật billDetail  thất bại"+RESET);
                        }
                    }else {
                        System.out.println(RED+"Mã billDetail không có"+RESET);
                    }
            } catch (Exception e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }
        }
    }

    public static void searchBillByCode(Scanner scanner, boolean billType,AccountModel acc) {

        System.out.println(YELLOW+"Nhập Bill code cần tìm: "+RESET);
        String billCode = scanner.nextLine();
        List<BillDetailModel> modelList = BillBusiness.searchlistBillDetail(billCode,billType,acc);
        if (modelList.size() > 0) {
            styleOuput();
            styleOutputBillDetail();
            System.out.println();
            modelList.stream().forEach(billDetailModel -> {
                billDetailModel.outputBillDetail();
                System.out.println();
            });
        } else {
            System.out.println(RED+"Mã Bill không tồn tại"+RESET);
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
            System.out.println(YELLOW+"Nhập mã bill Code: "+RESET);
            String billCode = scanner.nextLine();
            if (checkBill(billCode,acc, billType)) {
                System.out.println(GREEN2+"Cập nhật thành công."+RESET);
                return;
            } else {
                System.out.println(RED+"Mã nhập không chính xác."+RESET);
            }
        } while (true);
    }
    public static void runcheckBill2(Scanner scanner, AccountModel acc, boolean billType) {
        do {
            System.out.println(YELLOW+"Nhập mã bill Code: "+RESET);
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
                    System.out.println(GREEN2+"Cập nhật thành công. "+RESET);
                    return;
                }else {
                    System.out.println(RED+"Số lượng tồn kho không đủ để xuất."+RESET);;
                    return;
                }
            }else {
                System.out.println(RED+"Mã bill không tồn tại"+RESET);
                return;
            }
        } while (true);
    }

    public static void styleOuput() {
        System.out.printf(BACKGROUND_CYAN+"| %-4s | %-7.20s | %-7.10s | %-14.30s | %-9.10s | %-9.10s | %-9.10s | %-7.10s"+RESET,
                "BillId", "BillCode", "T.loại", "NV.tạo",
                "Ngày tạo", "NV.Duyệt", "Ngày duyệt", "T.thái\t\t"
        );

    }
    public static void styleOutputBillDetail(){
        System.out.printf(BACKGROUND_CYAN+"| %-7s | %-7s |%-4s |%-5s "+RESET+"\n",
                "billDetailId", "Mã Sp","S.Lg" , "Giá");
    }

    public static void BillDetailMenu(boolean billType, AccountModel acc) {
        styleOuput();
        styleOutputBillDetail();
        System.out.println();
        getListImportBillDetail2(billType, acc);
        System.out.println();
}
    public static void createBillDetail(Scanner scanner) {
        BillDetailModel billDe = new BillDetailModel();
        billDe.inputBillDetail(scanner);
        if (BillBusiness.createBillDetail(billDe)) {
            System.out.println(GREEN2+"Tạo mới thành công"+RESET);
        } else {
            System.out.println(RED+"Tạo mới thất bại"+RESET);

        }
    }

    public static void getListImportBillDetail2(boolean billType , AccountModel acc){
        Scanner scanner = new Scanner(System.in);
        List<BillDetailModel> modelList = BillBusiness.getlistBillDetail2(billType,acc);
        int pageNumber = modelList.size();
        int indexPage = 0;
        do {
            try {
                List<BillDetailModel> billDetailModels = BillBusiness.getlistBillDetail_phantrang(billType,5,indexPage, acc);
                for (BillDetailModel billDetailModel : billDetailModels) {
                    billDetailModel.outputBillDetail();
                }
                if (indexPage==0){
                    System.out.println(GREEN2+"\t\t\t2.Trang tiếp"+RESET);
                    System.out.println("0. Thoát");
                }else if (indexPage+5>=pageNumber){
                    System.out.print(GREEN2+"\"\t\t\t\"1.Trang sau"+RESET);
                    System.out.println("0. Thoát");
                }else {
                    System.out.print(GREEN2+"\"\t\t\t\"1.Trang sau"+RESET);
                    System.out.println(GREEN2+"\t\t\t2.Trang tiếp"+RESET);
                    System.out.println("0. Thoát");
                }
                int chon = Integer.parseInt(scanner.nextLine());
                if ( chon == 2){
                    indexPage+=5;
                }else if (indexPage>0 && chon==1){
                    indexPage-=5;
                }else {
                    return;
                }
            } catch (NumberFormatException e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            } catch (Exception e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }
        }while (true);

    }


}