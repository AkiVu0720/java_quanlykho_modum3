package presentation;

import business.EmpBusiness;
import business.ProductBusiness;
import model.AccountModel;
import model.EmployeeModel;
import model.ProductModel;
import validate.Validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmpMenu {
    public static void runEmpMenu(Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.println("******************EMPLOYEE MANAGEMENT****************");
            System.out.println("1. Danh sách nhân viên");
            System.out.println("2. Thêm mới nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên");
            System.out.println("4. Cập nhật trạng thái nhân viên");
            System.out.println("5. Tìm kiếm nhân viên");
            System.out.println("0. Thoát");
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
                        System.out.println("Lựa chọn của bạn không hợp lệ");
                        break;
                }
            } catch(Exception e){
                System.out.println("Lựa chọn của bạn không hợp lệ");
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
            System.out.println("Thêm mới thàng công");
        } else {
            System.out.println("Thêm mới thất bại");
        }
    }
    public static void updateEmp(Scanner scanner){
        System.out.println("Nhập mã nhân viên cần cập nhật");
        String empId = scanner.nextLine();
        EmployeeModel emp = EmpBusiness.getEmpById(empId);
        if (emp!=null){
            boolean isExit = false;
            do {
                styleOutput();
                emp.outputEmp();
                System.out.println("Menu cập nhật:");
                System.out.println("1.Tên nhân viên");
                System.out.println("2.Ngày sinh");
                System.out.println("3.Email");
                System.out.println("4.Số điện thoại");
                System.out.println("5.Địa chỉ");
                System.out.println("0.Thoát");
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
                            System.out.println("Lựa chọn không hợp lệ");
                            break;
                    }
                    if (EmpBusiness.updateEmp(emp)){
                        System.out.println(" Cập nhật thành công");
                    }else {
                        System.out.println("Cập nhật thất bại");
                    }

                } catch (Exception e){
                    System.out.println("Lựa chọn không hợp lệ");
                }
            }while (!isExit);
        }else {
            System.out.println("Mã NV không tồn tại");
        }
    }

    public static void updateEmpStatus(Scanner scanner){
        System.out.println("Nhập mã nhân viên cần cập nhật");
        String empId = scanner.nextLine();
        EmployeeModel emp = EmpBusiness.getEmpById(empId);
        if (emp!=null){
            styleOutput();
            emp.outputEmp();
            int statusUpdate = EmployeeModel.validateEmpStatus(scanner);
            if (EmpBusiness.updateEmpStatus(empId,statusUpdate)){
                System.out.println("Cập nhật thành công");
            }else{
                System.out.println("Cập nhật thất bại");
            }
        }else {
            System.out.println("Mã NV không tồn tại");
        }
    }
    public static void searchEmpByName(Scanner scanner){
        System.out.println("Nhập Tên tìm kiếm");
        String name = scanner.nextLine();
        List<EmployeeModel> modelList = EmpBusiness.getEmpByName(name);
        if (modelList.size()>0){
            styleOutput();
            modelList.stream().forEach(employeeModel -> employeeModel.outputEmp());
            System.out.println("");
        }else{
            System.out.println("Tên không tồn tại");
        }
    }
    public static void styleOutput(){
        System.out.printf("|\t%-10.10s |\t%-15.20s |\t%-10.10s |\t%-20.30s |\t%-10.10s |\t%-10.10s |\t%-10.10s \n",
                "Mã Nv", "Tên NV", "Ngày Sinh", "Email", " Số ĐTDĐ", "Địa chỉ", "Trạng thái"
        );
        System.out.printf("|\t%-10.10s |\t%-15.20s |\t%-10.10s |\t%-20.30s |\t%-10.10s |\t%-10.10s |\t%-10.10s \n",
                "----", "----", "----", "----", " ----", "----", "----"
        );
    }
}
