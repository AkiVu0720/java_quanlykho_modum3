package model;

import repository.AccRepository;
import repository.EmpRepository;
import Util.Validate;

import java.util.Scanner;
import java.util.regex.Pattern;

public class AccountModel {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    public static final String	BACKGROUND_WHITE= "\u001B[47m";
    public static final String	BACKGROUND_GREEN	= "\u001B[42m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";
    static Validate validate = new Validate();
    public static final int LENGTH_MIN = 0;
    public static final int LENGTH_MAX_ID = 5;
    public static final int LENGTH_MAX_NAME = 30;
    //1. Attributes
    private int acc_Id;
    private String acc_name;
    private String acc_pass;
    private boolean role_acc;
    private String emp_id;
    private boolean acc_Status;
    //2. Set,get

    public int getAcc_Id() {
        return acc_Id;
    }

    public void setAcc_Id(int acc_Id) {
        this.acc_Id = acc_Id;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public String getAcc_pass() {
        return acc_pass;
    }

    public void setAcc_pass(String acc_pass) {
        this.acc_pass = acc_pass;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public boolean isAcc_Status() {
        return acc_Status;
    }

    public void setAcc_Status(boolean acc_Status) {
        this.acc_Status = acc_Status;
    }

    public boolean isRole_acc() {
        return role_acc;
    }

    public void setRole_acc(boolean role_acc) {
        this.role_acc = role_acc;
    }
    //3. Constructor

    public AccountModel() {
    }

    public AccountModel(String acc_name, String acc_pass, boolean role_acc, String emp_id, boolean acc_Status) {
        this.acc_name = acc_name;
        this.acc_pass = acc_pass;
        this.role_acc = role_acc;
        this.emp_id = emp_id;
        this.acc_Status = acc_Status;
    }

    //4. Input, Output
    public void inputAcc(Scanner scanner){
        this.acc_name = validateAccName(scanner);
        this.acc_pass = validatePass(scanner);
        this.role_acc = validateRole(scanner);
        this.emp_id = validateEmpInAcc(scanner);
        this.acc_Status = validateAccStatus(scanner);

    }
    public void displayAccountMessage(){
        String acc_status_in = this.acc_Status ? "Hoạt động" : "Khoá";
        String role = this.role_acc ? "ADMIN" : "USER";
        System.out.printf("|\t%-6d |\t%-15.15s |\t%-10.10s |\t%-10.10s |\t%-10.10s |\t%-10.10s \n",
                this.acc_Id, this.acc_name, this.acc_pass, this.emp_id, role, acc_status_in);
    }

    //5 Business methods
    public String validateAccName(Scanner scanner){
        do {
            System.out.println("Nhập tên tài khoản");
            String userName = scanner.nextLine();
            byte error = 0;
            String regex = "^[A-Za-z_][A-Za-z0-9_]{4,29}$";
            if (!Pattern.matches(regex,userName)){
                System.out.println("Tên không chứa kí tự đặc biệt. từ 4 kí tự");
                error++;
            }

            if(validate.isStrNull(userName)){
                System.out.println("Không được để trống");
                error++;
            }
            if (!validate.isLength(userName,LENGTH_MIN,LENGTH_MAX_NAME)){
                System.out.printf("Độ dài không quá %d kí tự \n.", LENGTH_MAX_NAME);
                error++;
            }
           AccountModel acc = AccRepository.getAccByName(userName);
            if (acc!=null){
                System.out.println("Tên tài khoản đã tồn tại");
                error++;
            }

            if (error ==0){
                return userName;
            }
        }while (true);
    }
    public String validatePass (Scanner scanner){
        do {
            System.out.println("Nhập Mật khẩu");
            String  passStr = scanner.nextLine();
            byte error = 0;
            if (validate.isStrNull(passStr)){
                System.out.println("Không được để trống");
                error++;
            }
            if (validate.isPassWord(passStr)){
                System.out.println("Sai định dạng. Mật khẩu có độ dài 8-30 kí tự");
                error++;
            }
            if (error ==0 ){
                return passStr;
            }
        }while (true);
    }
    public boolean validateRole (Scanner scanner){
        do {
            System.out.println("Cấp quyền cho tài khoản. ");
            System.out.println(" 0. Admin  1. User ");
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice){
                    case 0: return true;
                    case 1: return false;
                    default:
                        System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                        break;
                }
            }catch (Exception e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }
        }while (true);

    }
    public String validateEmpInAcc(Scanner scanner){
            do {
                System.out.println("Mã nhân viên:");
                String empId = scanner.nextLine();
                byte error = 0;
                EmployeeModel  emp = EmpRepository.getEmpById(empId);
                if (emp == null){
                    System.out.println("Nhân viên này không tồn tại");
                    error++;
                }

                if (!validate.isLength(empId,LENGTH_MIN,LENGTH_MAX_ID)){
                    System.out.printf("Độ dài không quá %d kí tự \n.", LENGTH_MAX_ID);
                    error++;
                }
                if (validate.isStrNull(empId)){
                    System.out.println("Không được để trống.");
                    error++;
                }
                if(AccRepository.checkExitsInAccByEmpById(empId)){
                    System.out.println("Nhân viên này đã được tạo tài khoản.");
                    error++;
                }
                if (error == 0){
                    return empId;
                }
            }while (true);
    }
    public  boolean validateAccStatus(Scanner scanner) {
        System.out.println("Trạng thái Tài khoản: ");
        System.out.println("1. Hoạt động 2.Khoá");
        byte error = 0;
        do {
            try {
                byte statusProduct = Byte.parseByte(scanner.nextLine());
                    switch (statusProduct){
                        case 1: case 0:
                            return  true;
                        case 2:
                            return  false;
                        default:
                            System.out.println("Lựa chọn của bạn không có.");
                            break;
                    }
            } catch (NumberFormatException numberFormatException){
                System.out.println("Vui lòng nhập số 1 hoặc số 2 hoặc Enter để bỏ qua");
            } catch (Exception e){
                System.out.println("Vui lòng nhập số 1 hoặc số 2 hoặc Enter để bỏ qua");
            }

        } while (true);
    }
}
