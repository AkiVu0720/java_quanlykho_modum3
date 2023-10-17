package service;

import Util.Color;
import model.EmployeeModel;
import repository.EmpRepository;

import java.util.List;
import java.util.Scanner;

public class EmpService {

    public static boolean hasUpdateEmp(EmployeeModel emp){
        return  EmpRepository.updateEmp(emp);
    }
    public static boolean hasUpdateEmpStatus(String empId, int status){
        return  EmpRepository.updateEmpStatus(empId,status);
    }
    public static boolean hasCreateEmp(EmployeeModel emp){
        return EmpRepository.creatEmp(emp);
    }
    public static EmployeeModel getEmpById(String empId){
        return EmpRepository.getEmpById(empId);
    }
    public static void getListEmp(){
        styleOutput();
        List<EmployeeModel> modelList = EmpRepository.getlistEmp();
        modelList.forEach(EmployeeModel::outputEmp);
        System.out.println();
    }
    public static void handleCreateEmp() {
        Scanner scanner = new Scanner(System.in);
        EmployeeModel emp = new EmployeeModel();
        emp.inputEmp(scanner);
        if (hasCreateEmp(emp)) {
            System.out.println(Color.GREEN2+"Thêm mới thàng công"+Color.RESET);
        } else {
            System.out.println(Color.RED+"Thêm mới thất bại"+Color.RESET);
        }
    }
    public static void handleUpdateEmp(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.YELLOW+"Nhập mã nhân viên cần cập nhật"+Color.RESET);
        String empId = scanner.nextLine();
        EmployeeModel emp = getEmpById(empId);
        if (emp!=null){
            boolean isExit = false;
            do {
                styleOutput();
                emp.outputEmp();
                menuUpdate();
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
                            System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                            break;
                    }
                    if (hasUpdateEmp(emp)){
                        System.out.println(Color.GREEN2+" Cập nhật thành công"+Color.RESET);
                    }else {
                        System.out.println(Color.RED+"Cập nhật thất bại"+Color.RESET);
                    }

                } catch (Exception e){
                    System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                }
            }while (!isExit);
        }else {
            System.out.println(Color.RED+"Mã NV không tồn tại"+Color.RESET);
        }
    }
    public static void updateEmpStatus(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.YELLOW+"Nhập mã nhân viên cần cập nhật"+Color.RESET);
        String empId = scanner.nextLine();
        EmployeeModel emp = getEmpById(empId);
        if (emp!=null){
            styleOutput();
            emp.outputEmp();
            int statusUpdate = EmployeeModel.validateEmpStatus(scanner);
            if (hasUpdateEmpStatus(empId,statusUpdate)){
                System.out.println(Color.GREEN2+"Cập nhật thành công"+Color.RESET);
            }else{
                System.out.println(Color.RED+"Cập nhật thất bại"+Color.RESET);
            }
        }else {
            System.out.println(Color.RED+"Mã NV không tồn tại"+Color.RESET);
        }
    }
    public static void findEmpByName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.YELLOW+"Nhập Tên tìm kiếm"+Color.RESET);
        String name = scanner.nextLine();
        List<EmployeeModel> modelList = EmpRepository.getEmpByName(name);
        if (modelList.size()>0){
            styleOutput();
            modelList.forEach(EmployeeModel::outputEmp);
            System.out.println();
        }else{
            System.out.println(Color.RED+"Tên không tồn tại"+Color.RESET);
        }
    }
    public static void menuUpdate (){
        System.out.println(Color.BACKGROUND_CYAN+"Menu cập nhật:"+Color.RESET);
        System.out.println(Color.YELLOW+"1.Tên nhân viên"+Color.RESET);
        System.out.println(Color.YELLOW+"2.Ngày sinh"+Color.RESET);
        System.out.println(Color.YELLOW+"3.Email"+Color.RESET);
        System.out.println(Color.YELLOW+"4.Số điện thoại"+Color.RESET);
        System.out.println(Color.YELLOW+"5.Địa chỉ"+Color.RESET);
        System.out.println(Color.YELLOW+"0.Thoát"+Color.RESET);
    }
    public static void styleOutput(){
        System.out.printf(Color.BACKGROUND_CYAN+"|\t%-10.10s |\t%-15.20s |\t%-10.10s |\t%-20.30s |\t%-10.10s |\t%-10.10s |\t%-10.10s "+Color.RESET+" \n",
                "Mã Nv", "Tên NV", "Ngày Sinh", "Email", " Số ĐTDĐ", "Địa chỉ", "Trạng thái\t\t"
        );
        System.out.printf("|\t%-10.10s |\t%-15.20s |\t%-10.10s |\t%-20.30s |\t%-10.10s |\t%-10.10s |\t%-10.10s \n",
                "----", "----", "----", "----", " ----", "----", "----"
        );
    }
}
