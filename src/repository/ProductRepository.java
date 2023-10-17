package repository;
import config.MysqlConfig;
import model.ProductModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static List<ProductModel> getListProductSplitPage( int dataPage, int indexPage){
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
    public static List<ProductModel> getListProductFull(){
        Connection conn =null;
        CallableStatement callSt = null;
        List<ProductModel> modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_list_full}");
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
    public static List<ProductModel> findListProductByName( String productName){
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
    public static boolean hasAddProduct(ProductModel product){
        Connection conn =null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_Create(?,?,?,?,?,?)}");
                callSt.setString(1,product.getProduct_Id());
                callSt.setString(2,product.getProduct_Name());
                callSt.setString(3,product.getManufacturer());
                callSt.setString(4,product.getDate());
                callSt.setInt(5,product.getBatch());
                callSt.setBoolean(6,product.isProduct_status());
                callSt.executeUpdate();
                result = true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return result;
    }
    public static boolean hasUpdateProduct(ProductModel product){
        Connection conn =null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_update(?,?,?,?,?,?)}");
            callSt.setString(1,product.getProduct_Id());
            callSt.setString(2,product.getProduct_Name());
            callSt.setString(3,product.getManufacturer());
            callSt.setString(4,product.getDate());
            callSt.setInt(5,product.getBatch());
            callSt.setBoolean(6,product.isProduct_status());
            callSt.executeUpdate();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return result;
    }
    public static boolean updateProductStatus(String productId, boolean productStatus){
        Connection conn =null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_update_Startus(?,?)}");
            callSt.setString(1,productId);
            callSt.setBoolean(2,productStatus);
            callSt.executeUpdate();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return result;
    }
    public static ProductModel findProductById(String productId){
        Connection conn =null;
        CallableStatement callSt = null;
        ProductModel product = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call Sp_checkById(?)}");
            callSt.setString(1,productId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                product = new ProductModel();
                product.setProduct_Id(rs.getString("Product_Id"));
                product.setProduct_Name(rs.getString("Product_Name"));
                product.setManufacturer(rs.getString("Manufacturer"));
                product.setBatch(rs.getInt("Batch"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setProduct_status(rs.getBoolean("Product_Status"));
                product.setDate(rs.getString("Created"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return product;
    }

}
