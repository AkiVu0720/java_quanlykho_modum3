package presentation;

import business.AccBusiness;
import business.EmpBusiness;
import model.AccountModel;
import model.EmployeeModel;

import java.util.List;
import java.util.Scanner;

public class AccMenu {
    public static void runAccMenu(Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.println("******************ACCOUNT MANAGEMENT****************");
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
                        System.out.println("Lựa chọn của bạn không hợp lệ");
                        break;
                }
            } catch(Exception e){
                e.printStackTrace();
                System.out.println("Lựa chọn của bạn không hợp lệ");
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
            System.out.println("Tạo tài khoản thành công");
        }else {
            System.out.println("Tạo tài khoản thất bại");
        }
    }
    public static void updateAccStatusById(int accId, Scanner scanner  ) {
            AccountModel acc = AccBusiness.getAccById(accId);
            if (acc != null) {
                styleOutput();
                acc.outputAcc();
                boolean accStatus = acc.validateAccStatus(scanner);
                if (AccBusiness.updateAccStatus(accId, accStatus)) {
                    System.out.println("Cập nhập thành công");
                } else {
                    System.out.println("Cập nhật thất bại");
                }
            }else {
                System.out.println("Tài khoản không tồn tại");
            }
    }
    public static void runUpdade(Scanner scanner ){
            do {
                try {
                    System.out.println("Nhập Acc id: ");{
                        int accStatus = Integer.parseInt(scanner.nextLine());
                        updateAccStatusById(accStatus,scanner);
                        return;
                    }
                } catch (NumberFormatException numberFormatException){
                    System.out.println("Vui lòng nhập kiểu số");
                }
                catch (Exception e){
                    System.out.println("Tài khoản ko tồn tại");
                }
            }while (true);
        }
        public static void searchAccByName(Scanner scanner){

                System.out.println("Nhập tên tài khoản");
                String userName = scanner.nextLine();
                byte error = 0;
                List<AccountModel> modelList = AccBusiness.searchAccByName(userName);
                if (modelList.size()>0){
                    styleOutput();
                    modelList.stream().forEach(AccountModel::outputAcc);
                }else {
                    System.out.println("Tài khoản không tồn tại");
                }
        }


    public static void styleOutput(){
        System.out.printf("|\t%-6s |\t%-15.15s |\t%-10.10s |\t%-10.10s |\t%-10.10s |\t%-10.10s \n",
                "Acc id", "Tên đăng nhập", "Mật khẩu", "Người dùng", "Chức vụ", "Trạng thái");
        System.out.printf("|\t%-6s |\t%-15.15s |\t%-10.10s |\t%-10.10s |\t%-10.10s |\t%-10.10s \n",
                "-----", "-------", "-------", "-----", "------", "--------");
    }

}
