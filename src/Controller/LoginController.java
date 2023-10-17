package Controller;

import Util.Color;
import core.SaveAccount;
import model.AccountModel;
import presentation.AdminMenu;
import presentation.UserMenu;
import service.AccService;

import java.util.Scanner;

public class LoginController {
    public static void Login(String userName, String passWord){
        Scanner scanner = new Scanner(System.in);
        AccountModel acc = AccService.findAccountByUserNameAndPassWord(userName,passWord);
        boolean isSuccess = AccService.isExistAccount(acc);
        if (isSuccess){
            SaveAccount.EmpId = acc.getEmp_id();
            SaveAccount.accountModel = acc;
            if (acc.isAcc_Status()){
                if(!acc.isRole_acc()){
                    AdminMenu.handleAdminMenu(scanner, acc);
                }else {
                    UserMenu.handleUserMenu(scanner,acc);
                };
            }else {
                System.out.println(Color.RED+"Tài khoản bạn đang bị khoá"+Color.RESET);
            }

        }else {
            System.out.println(Color.RED+"Tài khoản không hợp lệ."+Color.RESET);
        }
    }

}
