package service;

import Util.Color;
import model.AccountModel;
import model.BillDetailModel;
import model.BillModel;
import repository.BillRepository;

import java.util.List;
import java.util.Scanner;

public class BillService {

    public static List<BillModel> getListBillFull( boolean typeBill, AccountModel acc){
        return BillRepository.getListBill(typeBill,acc);
    }
    public static List<BillModel> getListBillSplitPage(boolean typeBill,AccountModel acc, int dataPage, int indexPage){
        return BillRepository.getListBillSplitPage(typeBill,acc,dataPage,indexPage);
    }
    public static BillModel getBillByICode(String billId, boolean billType){
        return BillRepository.getBillByICode(billId,billType);
    }
    public static boolean hasCreateBill(BillDetailModel bill){
        return BillRepository.createBill(bill);
    }
    public static boolean hasCreateBillDetail(BillDetailModel bill){
        return BillRepository.createBillDetail(bill);
    }
    public static boolean updateBillStatus(BillModel bill){
        return BillRepository.updateBillStatus(bill);
    }
    public static List<BillDetailModel> findListBillDetail (String billCode,boolean billType, AccountModel acc){
        return BillRepository.findListBillDetail(billCode,billType,acc);
    }
    public static void handleGetListBill(boolean typeBill, AccountModel acc ) {
        Scanner scanner = new Scanner(System.in);
        List<BillModel> modelListFull = getListBillFull(typeBill, acc) ;
        int pageNumber = modelListFull.size();
        int indexPage = 0;
        do {
            try {
                if (pageNumber == 0){
                    System.out.println(Color.RED+"Chưa có phiếu xuất nào"+Color.RESET);
                    System.out.println();
                    return;
                }else {
                    styleOutput();
                    System.out.println();
                    List<BillModel> modelList = getListBillSplitPage(typeBill, acc, 7, indexPage);
                    modelList.forEach(billModel -> {
                        billModel.output();
                        System.out.println();
                    });
                    if (indexPage == 0) {
                        System.out.println(Color.GREEN2 + "\t\t\t2.Trang tiếp" + Color.RESET);
                        System.out.println("0. Thoát");
                    } else if (indexPage + 5 >= pageNumber) {
                        System.out.print(Color.GREEN2 + "\"\t\t\t\"1.Trang sau" + Color.RESET);
                        System.out.println("0. Thoát");
                    } else {
                        System.out.print(Color.GREEN2 + "\"\t\t\t\"1.Trang sau" + Color.RESET);
                        System.out.println(Color.GREEN2 + "\t\t\t2.Trang tiếp" + Color.RESET);
                        System.out.println("0. Thoát");
                    }
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 2) {
                        indexPage += 5;
                    } else if (indexPage > 0 && choice == 1) {
                        indexPage -= 5;
                    } else {
                        return;
                    }
                }
            } catch (Exception e){
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            }
        }while (true);
    }
    public static void handleCreatBill(boolean billType, AccountModel acc) {
        Scanner scanner = new Scanner(System.in);
        boolean iExits = false;
        BillDetailModel billDetail = new BillDetailModel();
        billDetail.inputBillDetail2(scanner,billType);
        billDetail.setBillType(billType);
        billDetail.setEmpIdCreated(acc.getEmp_id());

        if (hasCreateBill(billDetail)){
            System.out.println(Color.GREEN2+"Tạo bill thành công"+Color.RESET);
        }else {
            System.out.println(Color.RED+"Tạo mới thất bại"+Color.RESET);
        }
        BillModel billModel1 = getBillByICode(billDetail.getBillCode(),billType);
        billDetail.setBillId(billModel1.getBillId());
        if (hasCreateBillDetail(billDetail)){
            System.out.println(Color.GREEN2+"Tạo chi tiết phiếu thành công"+Color.RESET);
        }else {
            System.out.println(Color.RED+"Tạo mới chi tiết phiếu thất bại"+Color.RESET);
        }
        do {
            System.out.println(Color.YELLOW+"1. Tạo Thêm"+Color.RESET);
            System.out.println(Color.YELLOW+"2. Thoát"+Color.RESET);
            byte choice = Byte.parseByte(scanner.nextLine());
            switch (choice){
                case 1:BillDetailModel billDetail2 = new BillDetailModel();
                    billDetail2.inputBillDetail(scanner);
                    billDetail2.setBillId(billModel1.getBillId());
                    if (hasCreateBillDetail(billDetail2)){
                        System.out.println(Color.GREEN2+"Done!"+Color.RESET);
                    }else {
                        System.out.println(Color.RED+"Tạo chi tiết phiếu Thất bại"+Color.RESET);
                    }
                    break;
                case 2:
                    iExits = true;
            }
        }while (!iExits);
    }
    public static void handleUpdateBill( boolean billType, AccountModel acc) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(Color.YELLOW+"Nhập Bill Id: "+Color.RESET);
            try {
                int bill = Integer.parseInt(scanner.nextLine());
                updateBill(bill, billType, acc);
                return;
            } catch (NumberFormatException numberFormatException){
                System.out.println(Color.RED+"Mã Id là kiểu số."+Color.RESET);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            }
        } while (true);
    }
    public static void updateBill(int billId, boolean billType, AccountModel acc) {
        Scanner scanner = new Scanner(System.in);
        BillDetailModel billModel = BillRepository.getBillById(billId,billType,acc);
        List<BillDetailModel>modelList = BillRepository.getListBillById(billId,billType);
        int error = 0;
        if (billModel == null){
            System.out.println(Color.RED+"Mã bill không tồn tại.1"+Color.RESET);
            error++;
        }
        if (billModel.getBillStatus()==2){
            System.out.println("Phiếu đã đc duyệt");
            error++;
        }
        if (error==0) {
            styleOutput();
            styleOutputBillDetail();
            modelList.forEach(BillDetailModel::outputBillDetail);
            boolean isExit = false;
            do {
                System.out.println(Color.BACKGROUND_CYAN+"Menu cập nhật"+Color.RESET);
                System.out.println("1. Cập nhật ngày tạo.");
                System.out.println("2. Cập nhật chi tiết phiếu");
                System.out.println("0. Thoát");
                try {
                    byte choice = Byte.parseByte(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            billModel.setDayCreate(BillModel.validateDay(scanner));
                            break;
                        case 2:
                            updateBillDetail(scanner,modelList,billType);
                            break;
                        case 0:
                            isExit = true;
                            break;
                    }
                    if (BillRepository.updateBill(billModel, billType)) {
                        System.out.println(Color.GREEN2+" Cập nhật thành công"+Color.RESET);
                    } else {
                        System.out.println(Color.RED+"Cập nhật bill thất bại"+Color.RESET);
                    }
                } catch (Exception e) {
                    System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                }
            } while (!isExit);
        }

    }
    public static void updateBillDetail(Scanner scanner, List<BillDetailModel>modelList,  boolean billType ){
        System.out.println(Color.YELLOW+"Nhập mã BillDetail Id:"+Color.RESET);{
            do {
            try {
                int billId = Integer.parseInt(scanner.nextLine());
                boolean isSuccess = modelList.stream().anyMatch(billDetailModel -> billDetailModel.getBillDetailId()==billId);
                if (isSuccess){
                    boolean isExit = false;
                    BillDetailModel billDetailModel = BillRepository.getBillDetailById(billId);
                    do {
                        System.out.println(Color.YELLOW+"1. Cập nhật Mã Sp"+Color.RESET);
                        System.out.println(Color.YELLOW+"2. Cập nhật Số lượng"+Color.RESET);
                        System.out.println(Color.YELLOW+"3. Cập nhật Giá"+Color.RESET);
                        System.out.println(Color.YELLOW+"0. Thoát"+Color.RESET);

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
                    if (BillRepository.updateBill(billDetailModel, billType)) {
                        System.out.println(Color.GREEN2+" Cập nhật thành công"+Color.RESET);
                        return;
                    } else {
                        System.out.println(Color.RED+"Cập nhật billDetail  thất bại"+Color.RESET);
                    }
                }else {
                    System.out.println(Color.RED+"Mã billDetail không có"+Color.RESET);

                }
            } catch (Exception e){
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            }
            }while (true);
        }
    }
    public static void findBillByCode(boolean billType,AccountModel acc) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.YELLOW+"Nhập Bill code cần tìm: "+Color.RESET);
        String billCode = scanner.nextLine();
        List<BillDetailModel> modelList = findListBillDetail(billCode,billType,acc);
        if (modelList.size() > 0) {
            styleOutput();
            styleOutputBillDetail();
            System.out.println();
            modelList.forEach(billDetailModel -> {
                billDetailModel.outputBillDetail();
                System.out.println();
            });
        } else {
            System.out.println(Color.RED+"Mã Bill không tồn tại"+Color.RESET);
        }
    }
    public static boolean checkBill(String billCode, AccountModel acc, boolean billType) {
        Scanner scanner = new Scanner(System.in);
        BillModel billModel = getBillByICode(billCode, billType);
        boolean result = false;
        if (billModel != null) {
            styleOutput();
            System.out.println();
            billModel.output();
            System.out.println();
            billModel.setEmpIdAuth(acc.getEmp_id());
            billModel.setBillStatus(BillModel.validateBill_Status(scanner));
            if (updateBillStatus(billModel)) {
                result = true;
            }
        }
        return result;
    }
    public static void handleCheckBill2(Scanner scanner, AccountModel acc, boolean billType) {
        do {
            System.out.println(Color.YELLOW+"Nhập mã bill Code: "+Color.RESET);
            String billCode = scanner.nextLine();
            if (checkBill(billCode,acc, billType)) {
                System.out.println(Color.GREEN2+"Cập nhật thành công."+Color.RESET);
                return;
            } else {
                System.out.println(Color.RED+"Mã nhập không chính xác."+Color.RESET);
            }
        } while (true);
    }
    public static void handleCheckBill(AccountModel acc, boolean billType) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(Color.YELLOW+"Nhập mã bill Code: "+Color.RESET);
            String billCode = scanner.nextLine();
            BillModel billModel = getBillByICode(billCode, billType);
            if (billModel != null) {
                styleOutput();
                System.out.println();
                billModel.output();
                System.out.println();
                billModel.setEmpIdAuth(acc.getEmp_id());
                billModel.setBillStatus( BillModel.validateBill_Status(scanner));
                if (BillRepository.updateBillDetail(billModel,billType)==0) {
                    System.out.println(Color.GREEN2+"Cập nhật thành công. "+Color.RESET);
                    return;
                }else {
                    System.out.println(Color.RED+"Số lượng tồn kho không đủ để xuất."+Color.RESET);;
                    return;
                }
            }else {
                System.out.println(Color.RED+"Mã bill không tồn tại"+Color.RESET);
                return;
            }
        } while (true);
    }
    public static void styleOutput() {
        System.out.printf(Color.BACKGROUND_CYAN+"| %-4s | %-7.20s | %-7.10s | %-14.30s | %-9.10s | %-9.10s | %-9.10s | %-7.10s"+Color.RESET,
                "BillId", "BillCode", "T.loại", "NV.tạo",
                "Ngày tạo", "NV.Duyệt", "Ngày duyệt", "T.thái"
        );

    }
    public static void styleOutputBillDetail(){
        System.out.printf(Color.BACKGROUND_CYAN+"| %-7s | %-7s |%-4s |%-5s "+Color.RESET+"\n",
                "billDetailId", "Mã Sp","S.Lg" , "Giá");
    }
    public static void displayBillDetailMenu(boolean billType, AccountModel acc) {
        styleOutput();
        styleOutputBillDetail();
        System.out.println();
        getListBillDetail(billType, acc);
        System.out.println();
    }
    public static void createBillDetail(Scanner scanner) {
        BillDetailModel billDe = new BillDetailModel();
        billDe.inputBillDetail(scanner);
        if (BillRepository.createBillDetail(billDe)) {
            System.out.println(Color.GREEN2+"Tạo mới thành công"+Color.RESET);
        } else {
            System.out.println(Color.RED+"Tạo mới thất bại"+Color.RESET);
        }
    }
    public static void getListBillDetail(boolean billType , AccountModel acc){
        Scanner scanner = new Scanner(System.in);
        List<BillDetailModel> modelList = BillRepository.getlistBillDetail2(billType,acc);
        int pageNumber = modelList.size();
        int indexPage = 0;
        do {
            try {
                List<BillDetailModel> billDetailModels = BillRepository.getlistBillDetailSplitPage(billType,5,indexPage, acc);
                for (BillDetailModel billDetailModel : billDetailModels) {
                    billDetailModel.outputBillDetail();
                }
                if (indexPage == 0){
                    System.out.println(Color.GREEN2+"\t\t\t2.Trang tiếp"+Color.RESET);
                    System.out.println("0. Thoát");
                }else if (indexPage+5>=pageNumber){
                    System.out.print(Color.GREEN2+"\"\t\t\t\"1.Trang sau"+Color.RESET);
                    System.out.println("0. Thoát");
                }else {
                    System.out.print(Color.GREEN2+"\"\t\t\t\"1.Trang sau"+Color.RESET);
                    System.out.println(Color.GREEN2+"\t\t\t2.Trang tiếp"+Color.RESET);
                    System.out.println("0. Thoát");
                }
                int choice = Integer.parseInt(scanner.nextLine());
                if ( choice == 2){
                    indexPage+=5;
                }else if (indexPage>0 && choice==1){
                    indexPage-=5;
                }else {
                    return;
                }
            } catch (NumberFormatException e){
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            } catch (Exception e){
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            }
        }while (true);

    }
}
