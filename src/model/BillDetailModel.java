package model;

import Util.Validate;
import service.ProductService;

import java.util.Scanner;

public class BillDetailModel extends BillModel {
    static Validate validate = new Validate();
    public static final int LENGTH_MIN = 0;
    public static final int LENGTH_MAX_CODE = 10;
    //1.
    private  int billDetailId;
    private  String productId;
    private  int quantity;
    private  float price;
    //2 get,set

    public int getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(int billDetailId) {
        this.billDetailId = billDetailId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    //3 Constructors


    public BillDetailModel() {
    }

    public BillDetailModel(int billDetailId, String productId, int quantity, int price) {
        this.billDetailId = billDetailId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    //4 input, output


    public void inputBillDetail(Scanner scanner){
            this.productId = validateProductId(scanner);
            this.quantity = validateQuantity(scanner);
            this.price = validatePrice(scanner);
    };
    public void inputBillDetail2(Scanner scanner, boolean billType){
        super.inputBill(scanner,billType);
        this.productId = validateProductId(scanner);
        this.quantity = validateQuantity(scanner);
        this.price = validatePrice(scanner);
    };

    public void outputBillDetail(){
        super.output();
        System.out.printf("|\t\t%-5d |%-7.10s |%-4d |%-5.0f |\n",
                this.billDetailId,this.productId,quantity , price);
    }

    public static String validateProductId(Scanner scanner){
        do {
            System.out.println("Mã sản phẩm:");
            String productId = scanner.nextLine();
            byte error = 0;
            ProductModel product = ProductService.findProductById(productId);
            if (product == null){
                System.out.println("Mã sản phẩm không tồn tại");
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
    public static int validateQuantity(Scanner scanner){
        do {
            System.out.println("Nhập số lượng: ");
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                if (quantity>0){
                    return quantity;
                }else {
                    System.out.println("Số lượng không hợp lệ");
                }
            } catch (Exception e){
                System.out.println("Vui lòng nhập số và không để trống");
            }
        }while (true);
    }

    public static float validatePrice(Scanner scanner){
        do {
            System.out.println("Nhập Giá");
            try {
                float quantity = Integer.parseInt(scanner.nextLine());
                if (quantity>0){
                    return quantity;
                }else {
                    System.out.println("Giá không hợp lệ");
                }
            } catch (Exception e){
                System.out.println("Vui lòng nhập số và không để trống");
            }
        }while (true);
    }
}
