package repository;

import config.MysqlConfig;
import model.EmployeeModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class EmpRepository {
    public static List<EmployeeModel> getlistEmp(){
        Connection conn = null;
        CallableStatement callSt = null;
        List<EmployeeModel>modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Emp_List()}");
            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){
                EmployeeModel emp = new EmployeeModel();
                emp.setEmp_Id(rs.getString("Emp_Id"));
                emp.setEmp_Name(rs.getString("Emp_Name"));
                emp.setBirthDay(rs.getString("Birth_Of_Date"));
                emp.setEmail(rs.getString("Email"));
                emp.setPhone(rs.getString("Phone"));
                emp.setAddress(rs.getString("Address"));
                emp.setEmp_Status(rs.getInt("Emp_Status"));
                modelList.add(emp);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
          MysqlConfig.closeConnection(conn,callSt);
        }
        return  modelList;
    }

    public static boolean creatEmp(EmployeeModel emp){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
    try {
        conn = MysqlConfig.openConnection();
        callSt = conn.prepareCall("{call Emp_Create(?,?,?,?,?,?,?)}");
        callSt.setString(1,emp.getEmp_Id());
        callSt.setString(2,emp.getEmp_Name());
        callSt.setString(3,emp.getBirthDay());
        callSt.setString(4,emp.getEmail());
        callSt.setString(5,emp.getPhone());
        callSt.setString(6,emp.getAddress());
        callSt.setInt(7,emp.getEmp_Status());
        callSt.executeQuery();
        result = true;
    } catch (Exception e){
        e.printStackTrace();
    }finally {
        MysqlConfig.closeConnection(conn,callSt);
    }
    return result;
}

    public static boolean updateEmp(EmployeeModel emp){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Emp_Update(?,?,?,?,?,?)}");
            callSt.setString(1,emp.getEmp_Id());
            callSt.setString(2,emp.getEmp_Name());
            callSt.setString(3,emp.getBirthDay());
            callSt.setString(4,emp.getEmail());
            callSt.setString(5,emp.getPhone());
            callSt.setString(6,emp.getAddress());
            callSt.executeQuery();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return result;
    }

    public static boolean updateEmpStatus(String empId, int status){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Emp_Update_Startus(?,?)}");
            callSt.setString(1,empId);
            callSt.setInt(2,status);
            callSt.executeUpdate();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return result;
    }
    public static EmployeeModel getEmpById(String empId){
        Connection conn = null;
        CallableStatement callStr = null;
        EmployeeModel emp = null;
        try {
            conn = MysqlConfig.openConnection();
            callStr = conn.prepareCall("{call Emp_seachId(?)}");
            callStr.setString(1,empId);
            ResultSet rs = callStr.executeQuery();
            while (rs.next()){
                emp = new EmployeeModel();
                emp.setEmp_Id(rs.getString("Emp_Id"));
                emp.setEmp_Name(rs.getString("Emp_Name"));
                emp.setBirthDay(rs.getString("Birth_Of_Date"));
                emp.setEmail(rs.getString("Email"));
                emp.setPhone(rs.getString("Phone"));
                emp.setAddress(rs.getString("Address"));
                emp.setEmp_Status(rs.getInt("Emp_Status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callStr);
        }
        return  emp;
}

    public static List<EmployeeModel> getEmpByName(String empName){
        Connection conn = null;
        CallableStatement callStr = null;
        List<EmployeeModel> modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callStr = conn.prepareCall("{call Emp_seachName(?)}");
            callStr.setString(1,empName);
            ResultSet rs = callStr.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){
                EmployeeModel emp = new EmployeeModel();
                emp.setEmp_Id(rs.getString("Emp_Id"));
                emp.setEmp_Name(rs.getString("Emp_Name"));
                emp.setBirthDay(rs.getString("Birth_Of_Date"));
                emp.setEmail(rs.getString("Email"));
                emp.setPhone(rs.getString("Phone"));
                emp.setAddress(rs.getString("Address"));
                emp.setEmp_Status(rs.getInt("Emp_Status"));
                modelList.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callStr);
        }
        return  modelList;
    }
//public static boolean searchEmpById(String empId){
//
//}
    public static boolean CheckEmpName( String empName){
        Connection conn =null;
        CallableStatement callSt = null;
        int result = 0;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Emp_CheckExitbyName(?,?)}");
            //set tham số đầu vào.
            callSt.setString(1,empName);
            // đăng kí tham số ra.
            callSt.registerOutParameter(2, Types.INTEGER);
            // thực hiện gọi procedure
            callSt.execute();
            // Lấy giá trị tham số trả ra.
            result = callSt.getInt(2);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        if (result>0){
            return true;
        }
        return false;
    }

}
