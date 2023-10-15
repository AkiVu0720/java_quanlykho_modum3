package model;

import business.AccBusiness;
import business.EmpBusiness;
import validate.Validate;

import java.util.Scanner;
import java.util.regex.Pattern;

public class BillModel {
    static Validate validate = new Validate();
    public static final int LENGTH_MIN = 0;
    public static final int LENGTH_MAX_CODE = 10;
    //1 Attributes
    private  int bill_id;
    private String bill_Code;
    private boolean bill_Type;
    private String emp_id_created;
    private String dayCreate;
    private String emp_id_auth;
    private String auth_date;
    private int bill_Status;
    //2 get,set

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_Code() {
        return bill_Code;
    }

    public void setBill_Code(String bill_Code) {
        this.bill_Code = bill_Code;
    }

    public boolean isBill_Type() {
        return bill_Type;
    }

    public void setBill_Type(boolean bill_Type) {
        this.bill_Type = bill_Type;
    }

    public String getEmp_id_created() {
        return emp_id_created;
    }

    public void setEmp_id_created(String emp_id_created) {
        this.emp_id_created = emp_id_created;
    }

    public String getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(String dayCreate) {
        this.dayCreate = dayCreate;
    }

    public String getEmp_id_auth() {
        return emp_id_auth;
    }

    public void setEmp_id_auth(String emp_id_auth) {
        this.emp_id_auth = emp_id_auth;
    }

    public String getAuth_date() {
        return auth_date;
    }

    public void setAuth_date(String auth_date) {
        this.auth_date = auth_date;
    }

    public int getBill_Status() {
        return bill_Status;
    }

    public void setBill_Status(int bill_Status) {
        this.bill_Status = bill_Status;
    }
    //3 Constructor

    public BillModel() {
    }

    public BillModel(int bill_id, String bill_Code, boolean bill_Type,
                     String emp_id_created, String dayCreate,
                     String emp_id_auth, String auth_date, int bill_Status) {
        this.bill_id = bill_id;
        this.bill_Code = bill_Code;
        this.bill_Type = bill_Type;
        this.emp_id_created = emp_id_created;
        this.dayCreate = dayCreate;
        this.emp_id_auth = emp_id_auth;
        this.auth_date = auth_date;
        this.bill_Status = bill_Status;
    }
    //4 input, output
    public void inputBill(Scanner scanner, boolean billType){
        System.out.println("Tạo Mã Code:");
        this.bill_Code = validateBillCode(scanner, billType);
//        System.out.println("Nhân viên tạo");
//        this.emp_id_created = validteEmpId(scanner);
        System.out.println("Ngày tạo (yyyy-MM-dd)");
        this.dayCreate = validateDay(scanner);
    }
    public void output(){
        String billType = this.bill_Type?"P.nhập":"P.xuất";
        String statusBill = "";
        switch (this.bill_Status) {
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
        EmployeeModel  emp = EmpBusiness.getEmpById(this.emp_id_created);
        String nameEmpCreated = emp.getEmp_Name();
        EmployeeModel  empAuth = EmpBusiness.getEmpById(this.emp_id_auth);
        String nameEmpAuth = empAuth!=null?empAuth.getEmp_Name():"Chưa có";
        String dayauth = this.auth_date ==null ? "Chưa duyệt" : this.auth_date;
        System.out.printf("|\t%-4d |\t%-7.20s | %-7.10s | %-14.30s | %-9.10s | %-9.10s | %-7.10s | %-5.10s",
                this.bill_id, this.bill_Code,billType , nameEmpCreated,
                this.dayCreate, nameEmpAuth, dayauth,statusBill
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
    public static String validteEmpId(Scanner scanner){

            do {
                System.out.println("Mã nhân viên:");
                String empId = scanner.nextLine();
                byte error = 0;
                EmployeeModel  emp = EmpBusiness.getEmpById(empId);
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
            String emp_status_str = scanner.nextLine();
            byte error = 0;
            try {
                if (validate.isStrNull(emp_status_str)){
                    System.out.println("Không được để trống");
                    error++;
                }
                String regex = "[0-2]+";
                if (!Pattern.matches(regex,emp_status_str)){
                    System.out.println("Lựa chọn của bạn không tồn tại");
                    error++;
                }
                if (error == 0){
                    int emp_status = Integer.parseInt(emp_status_str);
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
