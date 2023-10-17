package model;

import repository.ProductRepository;
import Util.Validate;
import service.ProductService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProductModel {
    //1. Attributes
    static Validate validate = new Validate();
    public static final int LENGTH_MIN = 0;
    public static final int LENGTH_MAX_ID = 5;
    public static final int LENGTH_MAX_NAME = 150;
    public static final int LENGTH_MAX_MF = 200;
    private String product_Id;
    private String product_Name;
    private String manufacturer;
    private String date;
    private int batch;
    private int quantity;
    private  boolean product_status;
    //2. get, set

    public String getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(String product_Id) {
        this.product_Id = product_Id;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isProduct_status() {
        return product_status;
    }

    public void setProduct_status(boolean product_status) {
        this.product_status = product_status;
    }
    //3 Constructors

    public ProductModel() {
    }

    public ProductModel(String product_Id, String product_Name, String manufacturer, String date, int batch, int quantity, boolean product_status) {
        this.product_Id = product_Id;
        this.product_Name = product_Name;
        this.manufacturer = manufacturer;
        this.date = date;
        this.batch = batch;
        this.quantity = quantity;
        this.product_status = product_status;
    }
    //4. Input, output
    public void inputProduct(Scanner scanner){
        this.product_Id = validateProductId(scanner);
        this.product_Name = validateProductName(scanner);
        this.manufacturer = validateManufacturer(scanner);
        this.date = validateDay(scanner);
        this.batch = validateBatch(scanner);
        this.product_status = validateProductStatus(scanner);
    }
    public void displayMessageProduct(){
        String statusStr = this.product_status?"Hoạt động":"Không hoạt động";
        System.out.printf("|\t%-10.10s| \t%-15.15s| \t%-15.15s| \t%-5d| \t%-5d| \t%-12.15s| \t%-12.15s \n",
                this.product_Id, this.product_Name, this.manufacturer, this.batch, this.quantity, this.date, statusStr);
    }
    public void outputStyle2(){

        System.out.printf("|\t%-7.10s| \t%-10.10s| \t%-5d\n",
                this.product_Id, this.product_Name,this.quantity);
    }
    //5 Business methods
    public String validateProductId(Scanner scanner){
        do {
            System.out.println("Mã sản phẩm:");
            String productId = scanner.nextLine();
            byte error = 0;
            ProductModel product = ProductService.findProductById(productId);
            if (product!= null){
                System.out.println("Mã sản phẩm đã tồn tại");
                error++;
            }
            if (!validate.isLength(productId,LENGTH_MIN,LENGTH_MAX_ID)){
                System.out.printf("Độ dài không quá %d kí tự \n.", LENGTH_MAX_ID);
              error++;
            }
            if (validate.isStrNull(productId)){
                System.out.println("Không được để trống.");
                error++;
            }
            if (error == 0){
                return productId;
            }
        }while (true);
    }
    public static String validateProductName(Scanner scanner){
        do {
            System.out.println("Tên sản phẩm:");
            String productName = scanner.nextLine();
            byte error = 0;
            List<ProductModel> modelList = ProductService.findListProductByName(productName);
            if (modelList.size()>0){
                System.out.println(" Tên sản phẩm đã tồn tại");
                error++;
            }
            if (!validate.isLength(productName,LENGTH_MIN,LENGTH_MAX_NAME)){
                System.out.printf("Độ dài không quá %d kí tự \n.", LENGTH_MAX_NAME);
                error++;
            }
            if (validate.isStrNull(productName)){
                System.out.println("Không được để trống.");
                error++;
            }
            if (error == 0){
                return productName;
            }
        }while (true);
    }
    public static String validateManufacturer(Scanner scanner){
        do {
            System.out.println("Tên Nhà sản xuất:");
            String productMf = scanner.nextLine();
            byte error = 0;
            if (!validate.isLength(productMf,LENGTH_MIN,LENGTH_MAX_MF)){
                System.out.printf("Độ dài không quá %d kí tự \n.", LENGTH_MAX_MF);
                error++;
            }
            if (validate.isStrNull(productMf)){
                System.out.println("Không được để trống.");
                error++;
            }
            if (error == 0){
                return productMf;
            }
        }while (true);
    }
    public static String validateDay(Scanner scanner){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String day = "";
        do {
            System.out.println("Ngày tạo (yyyy-MM-dd)");
            try {
                String dateInput = scanner.nextLine();
                if (!Validate.isStrNull(dateInput)){
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
            /*
            if (!validate.isStrNull(date)){
                validate.isDate(date){
                    date = validate.isDate(date);
                    return date;
                }else {
                    System.out.println("Vui lòng nhập đúng địng dạng");
                }
            }*/
        } while (true);

    }
    public static int validateBatch(Scanner scanner){
        do {
            byte error = 0;
            System.out.println("Lô chứ sản phẩm. Số:");
            try {
                String batchStr = scanner.nextLine();
                if (validate.isStrNull(batchStr)){
                    System.out.println("Không được để trống");
                    error++;
                }
                if (error == 0){
                    int batch = Integer.parseInt(batchStr);
                    return batch;
                }
            } catch (NumberFormatException numberEF){
                System.out.println("Vui Lòng nhập kiểu số nguyên");
            } catch (Exception e){
                System.out.println("Vui Lòng nhập kiểu số nguyên");
            }

        }while (true);
    }

    public static boolean validateProductStatus(Scanner scanner) {
        System.out.println("Trạng thái sản phẩm");
        System.out.println("1. Hoạt động 2.Không hoạt động");
        byte error = 0;
        do {
            String statusProduct = scanner.nextLine();
            if (validate.isStrNull(statusProduct)) {
                System.out.println("Vui lòng không để trống");
                error++;
            }
            try {
                if (error==0){
                    byte choice = Byte.parseByte(statusProduct);
                    switch (choice){
                        case 1:
                            return  true;
                        case 2:
                            return  false;
                        default:
                            System.out.println("Lựa chọn của bạn không có.");
                            break;
                    }
                }
            } catch (NumberFormatException numberFormatException){
                System.out.println("Vui lòng nhập số 1 hoặc số 2");
            } catch (Exception e){
                System.out.println("Vui lòng nhập số 1 hoặc số 2");
            }

        } while (true);
    }

}
