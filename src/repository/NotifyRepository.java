package repository;

import config.MysqlConfig;
import model.ProductModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class NotifyRepository {
    public static List<ProductModel> SPNhieuNhat(String startDay, String endDay, Boolean billType){
        Connection conn = null;
        CallableStatement callSt = null;
        List<ProductModel> modelList = null;
                try {
                    conn = MysqlConfig.openConnection();
                    callSt = conn.prepareCall("{call ThongKeAllMax(?,?,?)}");
                    callSt.setString(1,startDay);
                    callSt.setString(2,endDay);
                    callSt.setBoolean(3,billType);
                    ResultSet rs = callSt.executeQuery();
                    modelList = new ArrayList<>();
                    while (rs.next()){
                        ProductModel product = new ProductModel();
                        product.setProduct_Id(rs.getString("pr.Product_Id"));
                        product.setProduct_Name(rs.getString("pr.Product_Name"));
                        product.setQuantity(rs.getInt("bd.Quantity"));
                    modelList.add(product);
                    }
                } catch (Exception ex){
        ex.printStackTrace();
            } finally {
        MysqlConfig.closeConnection(conn,callSt);
                }
        return modelList;
    }
    public static List<ProductModel> SPItNhat(String startDay, String endDay, Boolean billType){
        Connection conn = null;
        CallableStatement callSt = null;
        List<ProductModel> modelList = null;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call ThongKeAllMin(?,?,?)}");
            callSt.setString(1,startDay);
            callSt.setString(2,endDay);
            callSt.setBoolean(3,billType);
            ResultSet rs = callSt.executeQuery();
            modelList = new ArrayList<>();
            while (rs.next()){
                ProductModel product = new ProductModel();
                product.setProduct_Id(rs.getString("pr.Product_Id"));
                product.setProduct_Name(rs.getString("pr.Product_Name"));
                product.setManufacturer(rs.getString("pr.Manufacturer"));
                product.setQuantity(rs.getInt("bd.Quantity"));
                modelList.add(product);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return modelList;
    }
    public static int ThongKeChiPhiTheoNgay(String day){
        Connection conn = null;
        CallableStatement callSt = null;
        int tong = 0;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call ChiPhiTheoNgay(?,?)}");
            callSt.setString(1,day);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            tong = callSt.getInt(2);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return tong;
    }
    public static int ThongKeChiPhiTheoKhoangThoiGian(String startDay, String endDay){
        Connection conn = null;
        CallableStatement callSt = null;
        int tong = 0;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call ChiPhiTheoKhoangThoiGian(?,?,?)}");
            callSt.setString(1,startDay);
            callSt.setString(2,endDay);
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            tong = callSt.getInt(3);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return tong;
    }
    public static int DoanhThuTheoNgay(String day){
        Connection conn = null;
        CallableStatement callSt = null;
        int tong = 0;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call DoanhThuTheongay3(?,?)}");
            callSt.setString(1,day);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            tong = callSt.getInt(2);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return tong;
    }

    public static int DoanhThuKhoangThoiGian(String startDay, String endDay){
        Connection conn = null;
        CallableStatement callSt = null;
        int tong = 0;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call DoanhThuThekhoangtime(?,?,?)}");
            callSt.setString(1,startDay);
            callSt.setString(2,endDay);
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            tong = callSt.getInt(3);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return tong;
    }


    //test
    public static ProductModel test(){
        Connection conn = null;
        CallableStatement callSt = null;
        ProductModel model = null;
        int tong = 0;
        try {
            conn = MysqlConfig.openConnection();
            callSt = conn.prepareCall("{call test1}");
            callSt.executeQuery();
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                model = new ProductModel();
                model.setQuantity(rs.getInt("tong"));
                tong= rs.getInt("tong");
            }
            System.out.println(tong);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            MysqlConfig.closeConnection(conn,callSt);
        }
        return  model;
    }
}
