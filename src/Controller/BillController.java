package Controller;

import model.AccountModel;
import service.BillService;

public class BillController {
    public static void getListBill(boolean typeBill, AccountModel acc ){
        BillService.handleGetListBill(typeBill,acc);
    }
    public static void creatBill(boolean billType, AccountModel acc){
        BillService.handleCreatBill(billType,acc);
    }
    public static void updateBill( boolean billType, AccountModel acc){
        BillService.handleUpdateBill(billType,acc);
    }
    public static void displayBillDetailMenu(boolean billType, AccountModel acc){
        BillService.displayBillDetailMenu(billType,acc);
    }
    public static void checkBill(AccountModel acc, boolean billType){
        BillService.handleCheckBill(acc,billType);
    }
    public static void findBillByCode(boolean billType,AccountModel acc){
        BillService.findBillByCode(billType,acc);
    }
}
