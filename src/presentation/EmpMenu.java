package presentation;

import business.EmpBusiness;
import model.EmployeeModel;
import java.util.List;
import java.util.Scanner;

public class EmpMenu {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    public static final String	BACKGROUND_WHITE= "\u001B[47m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";
    public static void runEmpMenu(Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println(BACKGROUND_WHITE+"******************EMPLOYEE MANAGEMENT****************"+RESET);
            System.out.println("1. Danh sách nhân viên");
            System.out.println("2. Thêm mới nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên");
            System.out.println("4. Cập nhật trạng thái nhân viên");
            System.out.println("5. Tìm kiếm nhân viên");
            System.out.println("0. Thoát");
            System.out.println();
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice) {
                    case 1:
                        getlistEmp();
                        break;
                    case 2:
                        createEmp(scanner);
                        break;
                    case 3:
                        updateEmp(scanner);
                        break;
                    case 4:
                        updateEmpStatus(scanner);
                        break;

                    case 5:
                        searchEmpByName(scanner);
                        break;
                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                        break;
                }
            } catch(Exception e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }
        }while (!isExit) ;
    }
    public static void getlistEmp(){
        styleOutput();
        List<EmployeeModel>modelList = EmpBusiness.getlistEmp();
        modelList.stream().forEach(employeeModel -> employeeModel.outputEmp());
        System.out.println("");
    }
    public static void createEmp(Scanner scanner) {
        EmployeeModel emp = new EmployeeModel();
        emp.inputEmp(scanner);
        if (EmpBusiness.creatEmp(emp)) {
            System.out.println(GREEN2+"Thêm mới thàng công"+RESET);
        } else {
            System.out.println(RED+"Thêm mới thất bại"+RESET);
        }
    }
    public static void updateEmp(Scanner scanner){
        System.out.println(YELLOW+"Nhập mã nhân viên cần cập nhật"+RESET);
        String empId = scanner.nextLine();
        EmployeeModel emp = EmpBusiness.getEmpById(empId);
        if (emp!=null){
            boolean isExit = false;
            do {
                styleOutput();
                emp.outputEmp();
                System.out.println(BACKGROUND_CYAN+"Menu cập nhật:"+RESET);
                System.out.println(YELLOW+"1.Tên nhân viên"+RESET);
                System.out.println(YELLOW+"2.Ngày sinh"+RESET);
                System.out.println(YELLOW+"3.Email"+RESET);
                System.out.println(YELLOW+"4.Số điện thoại"+RESET);
                System.out.println(YELLOW+"5.Địa chỉ"+RESET);
                System.out.println(YELLOW+"0.Thoát"+RESET);
                try {
                    byte choice = Byte.parseByte(scanner.nextLine());
                    switch (choice){
                        case 1:
                           emp.setEmp_Name(EmployeeModel.validateEmpName(scanner));
                            break;
                        case 2:
                            emp.setBirthDay(EmployeeModel.validateBirthDay(scanner));
                            break;
                        case 3:
                            emp.setEmail(EmployeeModel.validateEmail(scanner));
                            break;
                        case 4:
                            emp.setPhone(EmployeeModel.validatePhone(scanner));
                            break;
                        case 5:
                            emp.setAddress(EmployeeModel.validateAdress(scanner));
                            break;
                        case 0:
                            isExit = true;
                            break;
                        default:
                            System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                            break;
                    }
                    if (EmpBusiness.updateEmp(emp)){
                        System.out.println(GREEN2+" Cập nhật thành công"+RESET);
                    }else {
                        System.out.println(RED+"Cập nhật thất bại"+RESET);
                    }

                } catch (Exception e){
                    System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                }
            }while (!isExit);
        }else {
            System.out.println(RED+"Mã NV không tồn tại"+RESET);
        }
    }

    public static void updateEmpStatus(Scanner scanner){
        System.out.println(YELLOW+"Nhập mã nhân viên cần cập nhật"+RESET);
        String empId = scanner.nextLine();
        EmployeeModel emp = EmpBusiness.getEmpById(empId);
        if (emp!=null){
            styleOutput();
            emp.outputEmp();
            int statusUpdate = EmployeeModel.validateEmpStatus(scanner);
            if (EmpBusiness.updateEmpStatus(empId,statusUpdate)){
                System.out.println(GREEN2+"Cập nhật thành công"+RESET);
            }else{
                System.out.println(RED+"Cập nhật thất bại"+RESET);
            }
        }else {
            System.out.println(RED+"Mã NV không tồn tại"+RESET);
        }
    }
    public static void searchEmpByName(Scanner scanner){
        System.out.println(YELLOW+"Nhập Tên tìm kiếm"+RESET);
        String name = scanner.nextLine();
        List<EmployeeModel> modelList = EmpBusiness.getEmpByName(name);
        if (modelList.size()>0){
            styleOutput();
            modelList.stream().forEach(employeeModel -> employeeModel.outputEmp());
            System.out.println("");
        }else{
            System.out.println(RED+"Tên không tồn tại"+RESET);
        }
    }
    public static void styleOutput(){
        System.out.printf(BACKGROUND_CYAN+"|\t%-10.10s |\t%-15.20s |\t%-10.10s |\t%-20.30s |\t%-10.10s |\t%-10.10s |\t%-10.10s "+RESET+" \n",
                "Mã Nv", "Tên NV", "Ngày Sinh", "Email", " Số ĐTDĐ", "Địa chỉ", "Trạng thái\t\t"
        );
        System.out.printf("|\t%-10.10s |\t%-15.20s |\t%-10.10s |\t%-20.30s |\t%-10.10s |\t%-10.10s |\t%-10.10s \n",
                "----", "----", "----", "----", " ----", "----", "----"
        );
    }
}
