package model;

import repository.EmpRepository;
import Util.Validate;

import java.util.Scanner;
import java.util.regex.Pattern;

public class EmployeeModel {
    //1. Attributes
    static Validate validate = new Validate();
    public static final int LENGTH_MIN = 0;
    public static final int LENGTH_MAX_ID = 5;
    public static final int LENGTH_MAX_NAME = 100;
    private String emp_Id;
    private String emp_Name;
    private String birthDay;
    private String email;
    private String phone;
    private String address;
    private int emp_Status;
    //2. Get, set

    public String getEmp_Id() {
        return emp_Id;
    }

    public void setEmp_Id(String emp_Id) {
        this.emp_Id = emp_Id;
    }

    public String getEmp_Name() {
        return emp_Name;
    }

    public void setEmp_Name(String emp_Name) {
        this.emp_Name = emp_Name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmp_Status() {
        return emp_Status;
    }

    public void setEmp_Status(int emp_Status) {
        this.emp_Status = emp_Status;
    }
    //3. Constructor

    public EmployeeModel() {
    }

    public EmployeeModel(String emp_Id, String emp_Name, String birthDay, String email, String phone, String address, int emp_Status) {
        this.emp_Id = emp_Id;
        this.emp_Name = emp_Name;
        this.birthDay = birthDay;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.emp_Status = emp_Status;
    }
    //4. Input, Output

    public void inputEmp(Scanner scanner){
        this.emp_Id = validateEmpId(scanner);
        this.emp_Name = validateEmpName(scanner);
        this.birthDay = validateBirthDay(scanner);
        this.email  = validateEmail(scanner);
        this.phone = validatePhone(scanner);
        this.address = validateAdress(scanner);
        this.emp_Status = validateEmpStatus(scanner);
    }
    public void outputEmp(){
        String emp_status_mess = "";
        switch (this.emp_Status){
            case 0: emp_status_mess = "Hoạt động";
                break;
            case 1: emp_status_mess = "Nghỉ chế độ";
                break;
            case 2: emp_status_mess = "Nghỉ việc";
                break;
        }
        System.out.printf("|\t%-10.10s |\t%-15.20s |\t%-10.10s |\t%-20.30s |\t%-10.10s |\t%-10.10s |\t%-10s \n",
                this.emp_Id, this.emp_Name, this.birthDay, this.email, this.phone, this.address, emp_status_mess
                );
    }
    //5. Business methods
    public static String validateEmpId(Scanner scanner){
        do {
            System.out.println("Mã nhân viên:");
            String empId = scanner.nextLine();
            byte error = 0;
            if (EmpRepository.getEmpById(empId)!=null){
                System.out.println("Mã đã tồn tại");
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
            if (error == 0){
                return empId;
            }
        }while (true);
    }

    public static String validateEmpName(Scanner scanner){
        do {
            System.out.println("Họ và tên:");
            String empName = scanner.nextLine();
            byte error = 0;
            if (EmpRepository.CheckEmpName(empName)){
                System.out.println(" Tên đã tồn tại");
                error++;
            }
            if (!validate.isLength(empName,LENGTH_MIN,LENGTH_MAX_NAME)){
                System.out.printf("Độ dài không quá %d kí tự \n.", LENGTH_MAX_NAME);
                error++;
            }
            if (validate.isStrNull(empName)){
                System.out.println("Không được để trống.");
                error++;
            }
            if (error == 0){
                return empName;
            }
        }while (true);
    }
    public static String validateBirthDay (Scanner scanner){
        do {
            System.out.println("Ngày tháng năm sinh (yyyy-MM-dd):");
            String birth = scanner.nextLine();
            byte error = 0;
            if (validate.isStrNull(birth)){
                System.out.println("Không được để trống");
                error++;
            }
            if (!validate.isDate(birth)){
                System.out.println("Nhập sai định dạng.");
                error++;
            }
            if (error == 0){
                return birth;
            }
        }while (true);
    }
    public static String validateEmail( Scanner scanner){
        do {
            System.out.println("Nhập Email: ");
            String email = scanner.nextLine();
            byte error = 0;
            if (validate.isStrNull(email)){
                System.out.println("Không để trống");
                error++;
            }
            if (!validate.isEmail(email)){
                System.out.println("Sai đinh dạng email");
                error++;
            }
            if (error == 0){
                return email;
            }
        }while (true);
    }
    public static String validatePhone(Scanner scanner){
        do {
            System.out.println("Nhập số điện thoại: ");
            String phone = scanner.nextLine();
            byte error = 0;
            if (validate.isStrNull(phone)){
                System.out.println("Không để trống");
                error++;
            }
            if (!validate.isPhone(phone)){
                System.out.println("Vui lòng nhập đinh dạng kiểu số và 10 kí tự");
                error++;
            }
            if (error == 0){
                return phone;
            }
        }while (true);
    }
     public static String validateAdress( Scanner scanner){
        do {
            System.out.println("Địa chỉ: ");
            String adress = scanner.nextLine();
            byte error = 0;
            if (validate.isStrNull(adress)){
                System.out.println("Không được để trống");
                error++;
            }
            if (error == 0){
                return  adress;
            }
        }while (true);
     }
     public static int validateEmpStatus(Scanner scanner){
        do {
            System.out.println("Trạng thái :");
            System.out.println("0.Hoạt động, 1.Nghỉ chế độ, 2.Nghỉ việc");
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
     }

}
