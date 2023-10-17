package model;

import repository.EmpRepository;
import Util.Validate;

import java.util.Scanner;
import java.util.regex.Pattern;

public class BillModel {
    static Validate validate = new Validate();
    public static final int LENGTH_MIN = 0;
    public static final int LENGTH_MAX_CODE = 10;
    //1 Attributes
    private  int billId;
    private String billCode;
    private boolean billType;
    private String empIdCreated;
    private String dayCreate;
    private String empIdAuth;
    private String authDate;
    private int billStatus;
    //2 get,set

    public static Validate getValidate() {
        return validate;
    }

    public static void setValidate(Validate validate) {
        BillModel.validate = validate;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public boolean isBillType() {
        return billType;
    }

    public void setBillType(boolean billType) {
        this.billType = billType;
    }

    public String getEmpIdCreated() {
        return empIdCreated;
    }

    public void setEmpIdCreated(String empIdCreated) {
        this.empIdCreated = empIdCreated;
    }

    public String getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(String dayCreate) {
        this.dayCreate = dayCreate;
    }

    public String getEmpIdAuth() {
        return empIdAuth;
    }

    public void setEmpIdAuth(String empIdAuth) {
        this.empIdAuth = empIdAuth;
    }

    public String getAuthDate() {
        return authDate;
    }

    public void setAuthDate(String authDate) {
        this.authDate = authDate;
    }

    public int getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }


    //3 Constructor

    public BillModel() {
    }

    public BillModel(int billId, String billCode, boolean billType,
                     String empIdCreated, String dayCreate, String empIdAuth,
                     String authDate, int billStatus) {
        this.billId = billId;
        this.billCode = billCode;
        this.billType = billType;
        this.empIdCreated = empIdCreated;
        this.dayCreate = dayCreate;
        this.empIdAuth = empIdAuth;
        this.authDate = authDate;
        this.billStatus = billStatus;
    }

    //4 input, output
    public void inputBill(Scanner scanner, boolean billType){
        System.out.println("Tạo Mã Code:");
        this.billCode = validateBillCode(scanner, billType);
//        System.out.println("Nhân viên tạo");
//        this.emp_id_created = validteEmpId(scanner);
        System.out.println("Ngày tạo (yyyy-MM-dd)");
        this.dayCreate = validateDay(scanner);
    }
    public void output(){
        String billType = this.billType?"P.nhập":"P.xuất";
        String statusBill = "";
        switch (this.billStatus) {
            case 0:
                statusBill = "Tạo";
                break;
            case 1:
                statusBill = "Huỷ";
                break;
            case 2:
                statusBill = "Duyệt";
                break;
        }
        EmployeeModel  emp = EmpRepository.getEmpById(this.empIdCreated);
        String nameEmpCreated = emp.getEmp_Name();
        EmployeeModel  empAuth = EmpRepository.getEmpById(this.empIdCreated);
        String nameEmpAuth = empAuth!=null?empAuth.getEmp_Name():"Chưa có";
        String dayAuth = this.authDate ==null ? "Chưa duyệt" : this.authDate;
        System.out.printf("|\t%-4d |\t%-7.20s | %-7.10s | %-14.30s | %-9.10s | %-9.10s | %-7.10s | %-5.10s",
                this.billId, this.billCode,billType , nameEmpCreated,
                this.dayCreate, nameEmpAuth, dayAuth,statusBill
        );

    }
    //5

    public static   String validateBillCode(Scanner scanner, boolean billType){
        do {
            System.out.println("Mã code");
            String billCode = scanner.nextLine();
            byte error = 0;
            if (validate.checkBillByCode(billCode,billType)){
                error++;
                System.out.println("Mã đã tồn tại");
            }
            if (!validate.isLength(billCode,LENGTH_MIN,LENGTH_MAX_CODE)){
                System.out.printf("Độ dài không quá %d kí tự \n.", LENGTH_MAX_CODE);
                error++;
            }
            if (validate.isStrNull(billCode)){
                System.out.println("Không được để trống.");
                error++;
            }
            if (error == 0){
                return billCode;
            }
        }while (true);
    };
    public static boolean validateBillType(Scanner scanner) {
        System.out.println("Loại phiếu");
        System.out.println("1. Phiếu nhập 2.Phiếu xuất");
        byte error = 0;
        do {
            String statusProduct = scanner.nextLine();
            if (validate.isStrNull(statusProduct)) {
                System.out.println("Vui lòng không để trống");
                error++;
            }
            try {
                if (error == 0) {
                    byte choice = Byte.parseByte(statusProduct);
                    switch (choice) {
                        case 1:
                            return true;
                        case 2:
                            return false;
                        default:
                            System.out.println("Lựa chọn của bạn không có.");
                            break;
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Vui lòng nhập số 1 hoặc số 2");
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số 1 hoặc số 2");
            }

        } while (true);
    }
    public static String validateEmpId(Scanner scanner){

            do {
                System.out.println("Mã nhân viên:");
                String empId = scanner.nextLine();
                byte error = 0;
                EmployeeModel  emp = EmpRepository.getEmpById(empId);
                if (emp == null){
                    System.out.println("Nhân viên này không tồn tại");
                    error++;
                }
                if (validate.isStrNull(empId)){
                    System.out.println("Không được để trống.");
                    error++;
                }
                if (error == 0){
                    return empId;
                }
            }while (true);
    };
    public static String validateDay(Scanner scanner){
        do {
            return validate.validateDay(scanner);
        }while (true);

    };
    public static int validateBill_Status(Scanner scanner){
        do {
            System.out.println("Trạng thái :");
            System.out.println("0.Tạo mới, 1.Huỷ, 2.Duyệt");
            String empStatus = scanner.nextLine();
            byte error = 0;
            try {
                if (validate.isStrNull(empStatus)){
                    System.out.println("Không được để trống");
                    error++;
                }
                String regex = "[0-2]+";
                if (!Pattern.matches(regex,empStatus)){
                    System.out.println("Lựa chọn của bạn không tồn tại");
                    error++;
                }
                if (error == 0){
                    int emp_status = Integer.parseInt(empStatus);
                    return emp_status;
                }

            } catch ( NumberFormatException numberFormatException){
                System.out.println("Lựa chọn của bạn không tồn tại");
            } catch (Exception e){
                System.out.println("Lựa chọn của bạn không tồn tại");
            }


        }while (true);
    };
}
