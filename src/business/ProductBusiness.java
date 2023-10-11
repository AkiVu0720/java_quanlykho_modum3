package business;

import config.MysqlConfig;
import model.AccountModel;
import model.ProductModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ProductBusiness {
    public static List<ProductModel> getListProduct( int dataPage, int indexPage){
        Connection conn =null;
        CallableStatement callSt = null;
        List<ProductModel> modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_list(?,?)}");
            callSt.setInt(1,dataPage);
            callSt.setInt(2,indexPage);
            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){
                ProductModel prod = new ProductModel();
                prod.setProduct_Id(rs.getString("Product_Id"));
                prod.setProduct_Name(rs.getString("Product_Name"));
                prod.setManufacturer(rs.getString("Manufacturer"));
                prod.setBatch(rs.getInt("Batch"));
                prod.setQuantity(rs.getInt("Quantity"));
                prod.setProduct_status(rs.getBoolean("Product_Status"));
                prod.setDate(rs.getString("Created"));
                modelList.add(prod);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return modelList;
    }

    public static boolean addProduct(ProductModel prod){
        Connection conn =null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_Create(?,?,?,?,?,?)}");
                callSt.setString(1,prod.getProduct_Id());
                callSt.setString(2,prod.getProduct_Name());
                callSt.setString(3,prod.getManufacturer());
                callSt.setString(4,prod.getDate());
                callSt.setInt(5,prod.getBatch());
                callSt.setBoolean(6,prod.isProduct_status());
                callSt.executeUpdate();
                result = true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return result;
    }

    public static boolean updateProduct(ProductModel prod){
        Connection conn =null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_update(?,?,?,?,?)}");
            callSt.setString(1,prod.getProduct_Id());
            callSt.setString(2,prod.getProduct_Name());
            callSt.setString(3,prod.getManufacturer());
            callSt.setString(4,prod.getDate());
            callSt.setInt(5,prod.getBatch());
            callSt.executeUpdate();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return result;
    }

    /**
     * Kiểm tra tồn tại mà Id
     * @param producId
     * @return
     */
    public static ProductModel checkById(String producId){
        Connection conn =null;
        CallableStatement callSt = null;
        ProductModel prod = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_checkById(?)}");
            callSt.setString(1,producId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                prod = new ProductModel();
                prod.setProduct_Id(rs.getString("Product_Id"));
                prod.setProduct_Name(rs.getString("Product_Name"));
                prod.setManufacturer(rs.getString("Manufacturer"));
                prod.setBatch(rs.getInt("Batch"));
                prod.setQuantity(rs.getInt("Quantity"));
                prod.setProduct_status(rs.getBoolean("Product_Status"));
                prod.setDate(rs.getString("Created"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return prod;
    }
    public static List<ProductModel> seachProduct( String productName){
        Connection conn =null;
        CallableStatement callSt = null;
        List<ProductModel> modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_seachName(?)}");
            callSt.setString(1,productName);
            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){

                ProductModel prod = new ProductModel();
                prod.setProduct_Id(rs.getString("Product_Id"));
                prod.setProduct_Name(rs.getString("Product_Name"));
                prod.setManufacturer(rs.getString("Manufacturer"));
                prod.setBatch(rs.getInt("Batch"));
                prod.setQuantity(rs.getInt("Quantity"));
                prod.setProduct_status(rs.getBoolean("Product_Status"));
                prod.setDate(rs.getString("Created"));
                modelList.add(prod);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return modelList;
    }

    public static boolean updateProductStatus(String producId, boolean producStatus){
        Connection conn =null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_update_Startus(?,?)}");
            callSt.setString(1,producId);
            callSt.setBoolean(2,producStatus);
            callSt.executeUpdate();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return result;
    }

    /**
     * Kiểm tra trùng tên
     * @param producId
     * @return
     */
    public static int checkByName(String producId){
        Connection conn = null;
        CallableStatement callSt = null;
        int result = 0;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_checkByName(?,?)}");
            callSt.setString(1,producId);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            result = callSt.getInt(2) ;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return result;
    }
}
