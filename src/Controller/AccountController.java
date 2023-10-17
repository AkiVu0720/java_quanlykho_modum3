package Controller;

import service.AccService;

public class AccountController {
    public static void getListAcc(){
        AccService.getListAcc();
    }
    public static void createAccount(){
        AccService.handleCreateAccount();
    }
    public static void handleUpdateAccount(){
        AccService.handleUpdateAccount();
    }
    public static void findAccByName(){
        AccService.findAccByName();
    }
}
