package config;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class MysqlConfig {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/QuanLyKho?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "vuanhtuan1";
//        public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//        public static final String URL = "jdbc:mysql://localhost:3306/QuanLyKho?useSSL=false";
//        public static final String USER = "root";
//        public static final String PASSWORD = "vuanhtuan1";

    /**
     * mở kết nối đến database
     * @return
     */
    public static Connection openConnection (){
            Connection conn = null;
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USERNAME,PASSWORD);
            } catch (ClassNotFoundException | SQLException e){
                e.printStackTrace();
            }
            return  conn;
        }

    /**
     * Đóng kết nối
     * @param conn
     * @param callSt
     */
    public static void closeConnection(Connection conn, CallableStatement callSt){
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(callSt != null){
                try {
                    callSt.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
}
