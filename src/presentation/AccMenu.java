package presentation;

import business.AccBusiness;
import business.EmpBusiness;
import model.AccountModel;
import model.EmployeeModel;

import java.util.List;
import java.util.Scanner;

public class AccMenu {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    public static final String	BACKGROUND_WHITE= "\u001B[47m";
    public static final String	BACKGROUND_GREEN	= "\u001B[42m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";
    public static final boolean BILL_TYPE_IMPORT  = true;
    public static final boolean BILL_TYPE_EXPORT  = false;
    public static void runAccMenu(Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println(BACKGROUND_WHITE+"******************ACCOUNT MANAGEMENT****************"+RESET);
            System.out.println("1. Danh sách tài khoản");
            System.out.println("2. Tạo tài khoản mới");
            System.out.println("3. Cập nhật trạng thái tài khoản");
            System.out.println("4. Tìm kiếm tài khoản");
            System.out.println("0. Thoát");
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice) {
                    case 1:
                        getListAcc();
                        break;
                    case 2:
                        createAcc(scanner);
                        break;
                    case 3:
                        runUpdade(scanner);
                        break;
                    case 4:
                        searchAccByName(scanner);
                        break;
                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                        break;
                }
            } catch(Exception e){
                e.printStackTrace();
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }
        }while (!isExit) ;
    }
    public static void getListAcc(){
        styleOutput();
        List<AccountModel>modelList = AccBusiness.getListAcc();
        modelList.stream().forEach(AccountModel::outputAcc);
    }
    public static void createAcc(Scanner scanner){
        AccountModel acc = new AccountModel();
        acc.inputAcc(scanner);
        if (AccBusiness.createAcc(acc)){
            System.out.println(GREEN+"Tạo tài khoản thành công"+RESET);
        }else {
            System.out.println(RED+"Tạo tài khoản thất bại"+RESET);
        }
    }
    public static void updateAccStatusById(int accId, Scanner scanner  ) {
            AccountModel acc = AccBusiness.getAccById(accId);
            if (acc != null) {
                styleOutput();
                acc.outputAcc();
                boolean accStatus = acc.validateAccStatus(scanner);
                if (AccBusiness.updateAccStatus(accId, accStatus)) {
                    System.out.println(GREEN2+"Cập nhập thành công"+RESET);
                } else {
                    System.out.println(RED+"Cập nhật thất bại"+RESET);
                }
            }else {
                System.out.println(RED+"Tài khoản không tồn tại"+RESET);
            }
    }
    public static void runUpdade(Scanner scanner ){
            do {
                try {
                    System.out.println(YELLOW+"Nhập Acc id: "+RESET);{
                        int accStatus = Integer.parseInt(scanner.nextLine());
                        updateAccStatusById(accStatus,scanner);
                        return;
                    }
                } catch (NumberFormatException numberFormatException){
                    System.out.println(RED+"Vui lòng nhập kiểu số"+RESET);
                }
                catch (Exception e){
                    System.out.println(RED+"Tài khoản ko tồn tại"+RESET);
                }
            }while (true);
        }
        public static void searchAccByName(Scanner scanner){

                System.out.println(YELLOW+"Nhập tên tài khoản"+RESET);
                String userName = scanner.nextLine();
                byte error = 0;
                List<AccountModel> modelList = AccBusiness.searchAccByName(userName);
                if (modelList.size()>0){
                    styleOutput();
                    modelList.stream().forEach(AccountModel::outputAcc);
                }else {
                    System.out.println(RED+"Tài khoản không tồn tại"+RESET);
                }
        }


    public static void styleOutput(){
        System.out.printf(BACKGROUND_CYAN+"|\t%-6s |\t%-15.15s |\t%-10.10s |\t%-10.10s |\t%-10.10s |\t%-10.10s "+RESET+"\n",
                "Acc id", "Tên đăng nhập", "Mật khẩu", "Người dùng", "Chức vụ", "Trạng thái\t\t");
        System.out.printf("|\t%-6s |\t%-15.15s |\t%-10.10s |\t%-10.10s |\t%-10.10s |\t%-10.10s \n",
                "-----", "-------", "-------", "-----", "------", "--------");
    }

}
