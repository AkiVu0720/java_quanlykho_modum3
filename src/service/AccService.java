package service;

import Util.Color;
import model.AccountModel;
import repository.AccRepository;

import java.util.List;
import java.util.Scanner;

public class AccService {
    public static boolean createAcc (AccountModel account){
        return AccRepository.createAccount(account);
    }

    public static AccountModel findAccountByUserNameAndPassWord(String userName, String passWord){
        AccountModel acc = AccRepository.findAccountByUserNameAndPassWord(userName,passWord);
        return  acc ;
    }
    public static boolean isExistAccount(AccountModel acc ){
        return acc !=null;
    }
    public static void getListAcc(){
        styleOutput();
        List<AccountModel> modelList = AccRepository.getListAcc();
        modelList.stream().forEach(AccountModel::displayAccountMessage);
    }
    public static void handleCreateAccount(){
        Scanner scanner = new Scanner(System.in);
        AccountModel acc = new AccountModel();
        acc.inputAcc(scanner);
        if (createAcc(acc)){
            System.out.println(Color.GREEN+"Tạo tài khoản thành công"+Color.RESET);
        }else {
            System.out.println(Color.RED+"Tạo tài khoản thất bại"+Color.RESET);
        }
    }
    public static void updateAccStatusById(int accId, Scanner scanner  ) {
        AccountModel acc = AccRepository.getAccById(accId);
        if (acc != null) {
            styleOutput();
            acc.displayAccountMessage();
            boolean accStatus = acc.validateAccStatus(scanner);
            if (AccRepository.updateAccStatus(accId, accStatus)) {
                System.out.println(Color.GREEN2+"Cập nhập thành công"+Color.RESET);
            } else {
                System.out.println(Color.RED+"Cập nhật thất bại"+Color.RESET);
            }
        }else {
            System.out.println(Color.RED+"Tài khoản không tồn tại"+Color.RESET);
        }
    }
    public static void handleUpdateAccount(){
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println(Color.YELLOW+"Nhập Acc id: "+Color.RESET);{
                    int accStatus = Integer.parseInt(scanner.nextLine());
                    updateAccStatusById(accStatus,scanner);
                    return;
                }
            } catch (NumberFormatException numberFormatException){
                System.out.println(Color.RED+"Vui lòng nhập kiểu số"+Color.RESET);
            }
            catch (Exception e){
                System.out.println(Color.RED+"Tài khoản ko tồn tại"+Color.RESET);
            }
        }while (true);
    }
    public static void findAccByName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.YELLOW+"Nhập tên tài khoản"+Color.RESET);
        String userName = scanner.nextLine();
        byte error = 0;
        List<AccountModel> modelList = AccRepository.findAccountByName(userName);
        if (modelList.size()>0){
            styleOutput();
            modelList.stream().forEach(AccountModel::displayAccountMessage);
        }else {
            System.out.println(Color.RED+"Tài khoản không tồn tại"+Color.RESET);
        }
    }
    public static void styleOutput(){
        System.out.printf(Color.BACKGROUND_CYAN+"|\t%-6s |\t%-15.15s |\t%-10.10s |\t%-10.10s |\t%-10.10s |\t%-10.10s "+Color.RESET+"\n",
                "Acc id", "Tên đăng nhập", "Mật khẩu", "Người dùng", "Chức vụ", "Trạng thái\t\t");
        System.out.printf("|\t%-6s |\t%-15.15s |\t%-10.10s |\t%-10.10s |\t%-10.10s |\t%-10.10s \n",
                "-----", "-------", "-------", "-----", "------", "--------");
    }


}
