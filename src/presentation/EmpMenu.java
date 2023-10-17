package presentation;

import Controller.EmployeeController;
import Util.Color;
import repository.EmpRepository;
import model.EmployeeModel;
import java.util.List;
import java.util.Scanner;

public class EmpMenu extends EmployeeController {
    public static void handleEmpMenu(Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println(Color.BACKGROUND_WHITE+"******************EMPLOYEE MANAGEMENT****************"+Color.RESET);
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
                        getListRmp();
                        break;
                    case 2:
                        createEmp();
                        break;
                    case 3:
                        updateEmp();
                        break;
                    case 4:
                        updateEmpStatus();
                        break;
                    case 5:
                        findEmpByName();
                        break;
                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                        break;
                }
            } catch(Exception e){
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            }
        }while (!isExit) ;
    }
}
