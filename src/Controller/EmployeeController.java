package Controller;

import service.EmpService;

public class EmployeeController {
    public static void getListRmp (){
        EmpService.getListEmp();
    }
    public static void createEmp(){
        EmpService.handleCreateEmp();
    }
    public static void updateEmp (){
        EmpService.handleUpdateEmp();
    }
    public static void updateEmpStatus (){
        EmpService.updateEmpStatus();
    }
    public static void findEmpByName(){
        EmpService.findEmpByName();
    }
}
