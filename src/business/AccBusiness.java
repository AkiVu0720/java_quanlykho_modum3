package business;

import config.MysqlConfig;
import model.AccountModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AccBusiness {
    public static List<AccountModel> getListAcc(){
        Connection conn =null;
        CallableStatement callSt = null;
       List<AccountModel> modelList = null;
        int result = 0;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call acc_list()}");
            //set tham số đầu vào.
            // thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            // Lấy giá trị tham số trả ra.
            while (rs.next()){
                AccountModel acc = new AccountModel();
                acc.setAcc_Id(rs.getInt("Acc_id"));
                acc.setAcc_name(rs.getString("User_name"));
                acc.setAcc_pass(rs.getString("Pass"));
                acc.setRole_acc(rs.getBoolean("Permission"));
                acc.setEmp_id(rs.getString("Emp_id"));
                acc.setAcc_Status(rs.getBoolean("Acc_status"));
                modelList.add(acc);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return modelList;
    }

    public static AccountModel getAccByName( String name){
        Connection conn =null;
        CallableStatement callSt = null;
        AccountModel acc = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call acc_checkAccByName(?)}");
            //set tham số đầu vào.
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            // Lấy giá trị tham số trả ra.
            while (rs.next()){
                acc = new AccountModel();
                acc.setAcc_Id(rs.getInt("Acc_id"));
                acc.setAcc_name(rs.getString("User_name"));
                acc.setAcc_pass(rs.getString("Pass"));
                acc.setRole_acc(rs.getBoolean("Permission"));
                acc.setEmp_id(rs.getString("Emp_id"));
                acc.setAcc_Status(rs.getBoolean("Acc_status"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return acc;
    }
    public static List<AccountModel> searchAccByName( String name){
        Connection conn =null;
        CallableStatement callSt = null;
        List<AccountModel>modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call acc_seach_useName(?)}");
            //set tham số đầu vào.
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            // Lấy giá trị tham số trả ra.
            modelList = new ArrayList<>();
            while (rs.next()){
                AccountModel acc  = new AccountModel();
                acc.setAcc_Id(rs.getInt("Acc_id"));
                acc.setAcc_name(rs.getString("User_name"));
                acc.setAcc_pass(rs.getString("pass"));
                acc.setRole_acc(rs.getBoolean("Permission"));
                acc.setEmp_id(rs.getString("Emp_id"));
                acc.setAcc_Status(rs.getBoolean("Acc_status"));
                modelList.add(acc);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return modelList;
    }
    public static AccountModel getAccById( int accId){
        Connection conn =null;
        CallableStatement callSt = null;
        AccountModel acc = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call acc_checkAccById(?)}");
            //set tham số đầu vào.
            callSt.setInt(1, accId);
            ResultSet rs = callSt.executeQuery();
            // Lấy giá trị tham số trả ra.
            while (rs.next()){
                acc = new AccountModel();
                acc.setAcc_Id(rs.getInt("Acc_id"));
                acc.setAcc_name(rs.getString("User_name"));
                acc.setAcc_pass(rs.getString("Pass"));
                acc.setRole_acc(rs.getBoolean("Permission"));
                acc.setEmp_id(rs.getString("Emp_id"));
                acc.setAcc_Status(rs.getBoolean("Acc_status"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return acc;
    }


    public static AccountModel checkAcc( String name, String pass){
        Connection conn =null;
        CallableStatement callSt = null;
        AccountModel acc = null;
        int result = 0;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call check_acc(?,?)}");
            //set tham số đầu vào.
            callSt.setString(1, name);
            callSt.setString(2, pass);
            // đăng kí tham số ra.
            // thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            // Lấy giá trị tham số trả ra.
            while (rs.next()){
                acc = new AccountModel();
                acc.setAcc_Id(rs.getInt("Acc_id"));
                acc.setAcc_name(rs.getString("User_name"));
                acc.setAcc_pass(rs.getString("Pass"));
                acc.setRole_acc(rs.getBoolean("Permission"));
                acc.setEmp_id(rs.getString("Emp_id"));
                acc.setAcc_Status(rs.getBoolean("Acc_status"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return acc;
    }
    public static boolean createAcc( AccountModel acc){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call acc_create(?,?,?,?,?)}");
            callSt.setString(1, acc.getAcc_name());
            callSt.setString(2, acc.getAcc_pass());
            callSt.setBoolean(3, acc.isRole_acc());
            callSt.setString(4, acc.getEmp_id());
            callSt.setBoolean(5,acc.isAcc_Status());
            callSt.executeUpdate();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  result;
    }

    public static boolean updateAccStatus( int accId, boolean accStatus){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call acc_update_statust(?,?)}");
            callSt.setInt(1, accId);
            callSt.setBoolean(2, accStatus);
            callSt.executeUpdate();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  result;
    }

    public static boolean checkExitsInAccByEmpById( String empId){
        Connection conn =null;
        CallableStatement callSt = null;
        int result = 0;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call checkExitsEmpinAcc_byId(?,?)}");
            //set tham số đầu vào.
            callSt.setString(1,empId);
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
