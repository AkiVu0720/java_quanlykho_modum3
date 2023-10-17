package Util;

import repository.BillRepository;
import model.BillModel;
import model.EmployeeModel;
import model.ProductModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validate {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";

    Scanner scanner = new Scanner(System.in);
    public static boolean isStrNull(String str) {

        return str==null || str.isEmpty();
    };
    public  boolean isLength(String str, double min, double max) {
            return (str.length() >= min && str.length() <= max) ? true : false;
    };
    public boolean isExitsProduct(String str, List<ProductModel>modelList){
        return modelList.stream().anyMatch(productModel -> {
            if (productModel.getProduct_Name().equalsIgnoreCase(str)
                    || productModel.getProduct_Id().equalsIgnoreCase(str)){
                return  true;
            }else {
                return false;
            }
        });
    }
    public boolean checkBillByCode(String billCode, boolean billType) {
        BillModel billModel = BillRepository.getBillByICode(billCode,billType);
        if (billModel != null) {
         return true;
        }
        return false;
    }

    public boolean isExitsEmp(String str, List<EmployeeModel>modelList){
        return modelList.stream().anyMatch(productModel -> {
            if (productModel.getEmp_Id().equalsIgnoreCase(str)
                    || productModel.getEmp_Name().equalsIgnoreCase(str)){
                return  true;
            }else {
                return false;
            }
        });
    }


    public boolean isDate (String str){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {

                System.out.println(format.parse(str));
                return true;
            }
            catch(ParseException e){
                return false;
            }
    }
    public static String validateDay(Scanner scanner){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String day = "";
        do {
            try {
                String dateInput = scanner.nextLine();
                if (!isStrNull(dateInput)){
                    Date dateStr = format.parse(dateInput);
                    //Convert từ java.util.date sang java.sql.date
                    java.sql.Date dateSql = new java.sql.Date(dateStr.getTime());
                    day = format.format(dateSql);
                    return day;
                }else {
                    Date date = new Date();
                    day = format.format(date);
                    System.out.println(day);
                    return day;
                }
            } catch (ParseException e) {
                System.err.println("Không đúng định dạng");
            }
        } while (true);
    }
    public  boolean isEmail(String email){
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if(Pattern.matches(emailRegex,email)){
                return true;
            }else{
                return false;
            }
    }
    public boolean isPhone (String phone){
        String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        return Pattern.matches(pattern,phone);

    }
    public boolean isPassWord(String pass){
        String regexPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return Pattern.matches(regexPass,pass);
    }

}
