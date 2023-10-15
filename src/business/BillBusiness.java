package business;

import config.MysqlConfig;
import model.AccountModel;
import model.BillDetailModel;
import model.BillModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BillBusiness {
    public static List<BillModel> getlistBill(boolean billType, AccountModel acc,int dataPage, int indexPage){
        Connection conn = null;
        CallableStatement callSt = null;
        List<BillModel>modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_List(?,?,?,?)}");
            callSt.setBoolean(1,billType);
            callSt.setString(2,acc.getEmp_id());
            callSt.setInt(3,dataPage);
            callSt.setInt(4,indexPage);

            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){
                BillModel bill = new BillModel();
                bill.setBill_id(rs.getInt("Bill_id"));
                bill.setBill_Code(rs.getString("Bill_Code"));
                bill.setBill_Type(rs.getBoolean("Bill_Type"));
                bill.setEmp_id_created(rs.getString("Emp_id_created"));
                bill.setDayCreate(rs.getString("Created"));
                bill.setEmp_id_auth(rs.getString("Emp_id_auth"));
                bill.setAuth_date(rs.getString("Auth_date"));
                bill.setBill_Status(rs.getInt("Bill_Status"));
                modelList.add(bill);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  modelList;
    }
    public static List<BillModel> getSizeBill(boolean billType, AccountModel acc){
        Connection conn = null;
        CallableStatement callSt = null;
        List<BillModel>modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call sizeBillList(?,?)}");
            callSt.setBoolean(1,billType);
            callSt.setString(2,acc.getEmp_id());

            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){
                BillModel bill = new BillModel();
                bill.setBill_id(rs.getInt("Bill_id"));
                bill.setBill_Code(rs.getString("Bill_Code"));
                bill.setBill_Type(rs.getBoolean("Bill_Type"));
                bill.setEmp_id_created(rs.getString("Emp_id_created"));
                bill.setDayCreate(rs.getString("Created"));
                bill.setEmp_id_auth(rs.getString("Emp_id_auth"));
                bill.setAuth_date(rs.getString("Auth_date"));
                bill.setBill_Status(rs.getInt("Bill_Status"));
                modelList.add(bill);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  modelList;
    }
    public static List<BillDetailModel> getlistBillDetail2(boolean billType, AccountModel acc){
        Connection conn = null;
        CallableStatement callSt = null;
        List<BillDetailModel>modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call bill_Details_info2(?,?)}");
            callSt.setBoolean(1,billType);
            callSt.setString(2, acc.getEmp_id());
            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){
                BillDetailModel bill = new BillDetailModel();
                bill.setBill_id(rs.getInt("bd.Bill_id"));
                bill.setBill_Code(rs.getString("Bill_Code"));
                bill.setBill_Type(rs.getBoolean("Bill_Type"));
                bill.setEmp_id_created(rs.getString("Emp_id_created"));
                bill.setDayCreate(rs.getString("Created"));
                bill.setEmp_id_auth(rs.getString("Emp_id_auth"));
                bill.setAuth_date(rs.getString("Auth_date"));
                bill.setBill_Status(rs.getInt("Bill_Status"));
                bill.setBillDetailId(rs.getInt("Bill_Detail_Id"));
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setProductId(rs.getString("Product_Id"));
                bill.setQuantity(rs.getInt("Quantity"));
                bill.setPrice(rs.getFloat("Price"));
                modelList.add(bill);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  modelList;
    }
    public static List<BillDetailModel> getlistBillDetail_phantrang(boolean billType, int datapage, int indexpage, AccountModel acc){
        Connection conn = null;
        CallableStatement callSt = null;
        List<BillDetailModel>modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call bill_details_Size(?,?,?,?)}");
            callSt.setBoolean(1,billType);
            callSt.setInt(2,datapage);
            callSt.setInt(3,indexpage);
            callSt.setString(4,acc.getEmp_id());
            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){
                BillDetailModel bill = new BillDetailModel();
                bill.setBill_id(rs.getInt("bd.Bill_id"));
                bill.setBill_Code(rs.getString("Bill_Code"));
                bill.setBill_Type(rs.getBoolean("Bill_Type"));
                bill.setEmp_id_created(rs.getString("Emp_id_created"));
                bill.setDayCreate(rs.getString("Created"));
                bill.setEmp_id_auth(rs.getString("Emp_id_auth"));
                bill.setAuth_date(rs.getString("Auth_date"));
                bill.setBill_Status(rs.getInt("Bill_Status"));
                bill.setBillDetailId(rs.getInt("Bill_Detail_Id"));
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setProductId(rs.getString("Product_Id"));
                bill.setQuantity(rs.getInt("Quantity"));
                bill.setPrice(rs.getFloat("Price"));
                modelList.add(bill);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  modelList;
    }
    public static List<BillDetailModel> getlistBillDetailByRole(boolean billType, AccountModel acc){
        Connection conn = null;
        CallableStatement callSt = null;
        List<BillDetailModel>modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call bill_Details_infoByRole(?,?,?)}");
            callSt.setBoolean(1,billType);
            callSt.setBoolean(2, acc.isRole_acc());
            callSt.setString(3,acc.getEmp_id());
            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){
                BillDetailModel bill = new BillDetailModel();
                bill.setBill_id(rs.getInt("bd.Bill_id"));
                bill.setBill_Code(rs.getString("Bill_Code"));
                bill.setBill_Type(rs.getBoolean("Bill_Type"));
                bill.setEmp_id_created(rs.getString("Emp_id_created"));
                bill.setDayCreate(rs.getString("Created"));
                bill.setEmp_id_auth(rs.getString("Emp_id_auth"));
                bill.setAuth_date(rs.getString("Auth_date"));
                bill.setBill_Status(rs.getInt("Bill_Status"));
                bill.setBillDetailId(rs.getInt("Bill_Detail_Id"));
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setProductId(rs.getString("Product_Id"));
                bill.setQuantity(rs.getInt("Quantity"));
                bill.setPrice(rs.getFloat("Price"));
                modelList.add(bill);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  modelList;
    }


    public static boolean createBill(BillDetailModel bill){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_Create(?,?,?)}");
            callSt.setBoolean(1,bill.isBill_Type());
            callSt.setString(2, bill.getBill_Code());
            callSt.setString(3, bill.getEmp_id_created());
            callSt.executeUpdate();
            result = true;

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  result;
    }

    public static boolean updateBill(BillDetailModel bill, boolean billType){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_update(?,?,?,?,?,?,?,?)}");
            callSt.setBoolean(1,billType);
            callSt.setInt(2,bill.getBill_id());
            callSt.setString(3, bill.getEmp_id_created());
            callSt.setString(4, bill.getDayCreate());
            callSt.setInt(5, bill.getBill_Status());
            callSt.setString(6, bill.getProductId());
            callSt.setInt(7, bill.getQuantity());
            callSt.setFloat(8, bill.getPrice());
            callSt.executeUpdate();
            result = true;

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  result;
    }

    public static boolean updateBillCode(BillModel bill){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_updateBillCode(?,?)}");
            callSt.setInt(1,bill.getBill_id());
            callSt.setString(2, bill.getBill_Code());
            callSt.executeUpdate();
            result = true;

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  result;
    }
    public static boolean updateBillStatus(BillModel bill){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_check_2(?,?,?,?)}");
            callSt.setString(1,bill.getEmp_id_auth());
            callSt.setString(2, bill.getBill_Code());
            callSt.setInt(3, bill.getBill_Status());
            callSt.setBoolean(4, bill.isBill_Type());
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  result;
    }

    public static boolean createBillDetail(BillDetailModel bill){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_Create_Detail(?,?,?,?)}");
            callSt.setInt(1,bill.getBillId());
            callSt.setString(2, bill.getProductId());
            callSt.setInt(3, bill.getQuantity());
            callSt.setFloat(4, bill.getPrice());
            callSt.executeUpdate();
            result = true;

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  result;
    }
    public static BillDetailModel getBillById(int billId, boolean billType, AccountModel acc){
        Connection conn = null;
        CallableStatement callSt = null;
        BillDetailModel bill = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_seach_ById2(?,?,?,?)}");
            callSt.setInt(1,billId);
            callSt.setBoolean(2,billType);
            callSt.setString(3,acc.getEmp_id());
            callSt.setBoolean(4,acc.isRole_acc());
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                bill = new BillDetailModel();
                bill.setBill_id(rs.getInt("bd.Bill_id"));
                bill.setBill_Code(rs.getString("Bill_Code"));
                bill.setBill_Type(rs.getBoolean("Bill_Type"));
                bill.setEmp_id_created(rs.getString("Emp_id_created"));
                bill.setDayCreate(rs.getString("Created"));
                bill.setEmp_id_auth(rs.getString("Emp_id_auth"));
                bill.setAuth_date(rs.getString("Auth_date"));
                bill.setBill_Status(rs.getInt("Bill_Status"));
                bill.setBillDetailId(rs.getInt("Bill_Detail_Id"));
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setProductId(rs.getString("Product_Id"));
                bill.setQuantity(rs.getInt("Quantity"));
                bill.setPrice(rs.getFloat("Price"));
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  bill;
    }
    public static List<BillDetailModel> getListBillById(int billId, boolean billType){
        Connection conn = null;
        CallableStatement callSt = null;
        List<BillDetailModel> modelList= null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_seach_ById(?,?)}");
            callSt.setInt(1,billId);
            callSt.setBoolean(2,billType);
            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){
                BillDetailModel bill = new BillDetailModel();
                bill.setBill_id(rs.getInt("bd.Bill_id"));
                bill.setBill_Code(rs.getString("Bill_Code"));
                bill.setBill_Type(rs.getBoolean("Bill_Type"));
                bill.setEmp_id_created(rs.getString("Emp_id_created"));
                bill.setDayCreate(rs.getString("Created"));
                bill.setEmp_id_auth(rs.getString("Emp_id_auth"));
                bill.setAuth_date(rs.getString("Auth_date"));
                bill.setBill_Status(rs.getInt("Bill_Status"));
                bill.setBillDetailId(rs.getInt("Bill_Detail_Id"));
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setProductId(rs.getString("Product_Id"));
                bill.setQuantity(rs.getInt("Quantity"));
                bill.setPrice(rs.getFloat("Price"));
                modelList.add(bill);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  modelList;
    }

    public static BillDetailModel getBillDetailById(int billDetailId){
        Connection conn = null;
        CallableStatement callSt = null;
        BillDetailModel bill = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_seachBillDetail_ById(?)}");
            callSt.setInt(1,billDetailId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                bill = new BillDetailModel();
                bill.setBill_id(rs.getInt("bd.Bill_id"));
                bill.setBill_Code(rs.getString("Bill_Code"));
                bill.setBill_Type(rs.getBoolean("Bill_Type"));
                bill.setEmp_id_created(rs.getString("Emp_id_created"));
                bill.setDayCreate(rs.getString("Created"));
                bill.setEmp_id_auth(rs.getString("Emp_id_auth"));
                bill.setAuth_date(rs.getString("Auth_date"));
                bill.setBill_Status(rs.getInt("Bill_Status"));
                bill.setBillDetailId(rs.getInt("Bill_Detail_Id"));
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setProductId(rs.getString("Product_Id"));
                bill.setQuantity(rs.getInt("Quantity"));
                bill.setPrice(rs.getFloat("Price"));
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  bill;
    }

    public static BillModel getBillByICode(String billCode, boolean billType){
        Connection conn = null;
        CallableStatement callSt = null;
        BillModel bill = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_getByCode(?,?)}");
            callSt.setBoolean(1,billType);
            callSt.setString(2,billCode);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                bill = new BillModel();
                bill.setBill_id(rs.getInt("Bill_id"));
                bill.setBill_Code(rs.getString("Bill_Code"));
                bill.setBill_Type(rs.getBoolean("Bill_Type"));
                bill.setEmp_id_created(rs.getString("Emp_id_created"));
                bill.setDayCreate(rs.getString("Created"));
                bill.setEmp_id_auth(rs.getString("Emp_id_auth"));
                bill.setAuth_date(rs.getString("Auth_date"));
                bill.setBill_Status(rs.getInt("Bill_Status"));
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  bill;
    }
    public static List<BillDetailModel> searchlistBillDetail(String billCode,boolean billType, AccountModel acc){
        Connection conn = null;
        CallableStatement callSt = null;
        List<BillDetailModel>modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_seach_Code(?,?,?)}");
            callSt.setString(1,billCode);
            callSt.setBoolean(2,billType);
            callSt.setString(3,acc.getEmp_id());
            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){
                BillDetailModel bill = new BillDetailModel();
                bill = new BillDetailModel();
                bill.setBill_id(rs.getInt("bd.Bill_id"));
                bill.setBill_Code(rs.getString("Bill_Code"));
                bill.setBill_Type(rs.getBoolean("Bill_Type"));
                bill.setEmp_id_created(rs.getString("Emp_id_created"));
                bill.setDayCreate(rs.getString("Created"));
                bill.setEmp_id_auth(rs.getString("Emp_id_auth"));
                bill.setAuth_date(rs.getString("Auth_date"));
                bill.setBill_Status(rs.getInt("Bill_Status"));
                bill.setBillDetailId(rs.getInt("Bill_Detail_Id"));
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setProductId(rs.getString("Product_Id"));
                bill.setQuantity(rs.getInt("Quantity"));
                bill.setPrice(rs.getFloat("Price"));

                modelList.add(bill);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  modelList;
    }
    public static  int updateBillDetail(BillModel bill, boolean BillStatus){
        Connection conn = null;
        CallableStatement callSt = null;
        int result = -1;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Bill_check_All(?,?,?,?,?)}");
            callSt.setString(1,bill.getEmp_id_auth());
            callSt.setString(2, bill.getBill_Code());
            callSt.setInt(3, bill.getBill_Status());
            callSt.registerOutParameter(4, Types.INTEGER);
            callSt.setBoolean(5,BillStatus);
            callSt.execute();
            result = callSt.getInt(4);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  result;
    }
}
