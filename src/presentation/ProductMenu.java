package presentation;

import business.ProductBusiness;
import model.ProductModel;
import validate.Validate;

import java.util.List;
import java.util.Scanner;

public class ProductMenu {
    public static final String	BLACK= "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    public static final String	BACKGROUND_WHITE= "\u001B[47m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";

    public static void runProductMenu(Scanner scanner) {
        boolean isExit = false;
        do {
            try {
                System.out.println();
                System.out.println();
                System.out.println(BACKGROUND_CYAN+"******************PRODUCT MANAGEMENT******************"+RESET);
                System.out.println("1. Danh sách sản phẩm");
                System.out.println("2. Thêm mới sản phẩm");
                System.out.println("3. Cập nhật sản phẩm");
                System.out.println("4. Tìm kiếm sản phẩm");
                System.out.println("5. Cập nhật trạng thái sản phẩm");
                System.out.println("0. Thoát");
                System.out.println();
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice) {
                    case 1:
                        getListProduct();
                        break;
                    case 2:
                        cretaeProduct(scanner);
                        break;
                    case 3:
                        updateProduc(scanner);
                        break;
                    case 4:
                        seachProductByName(scanner);
                        break;
                    case 5:
                        updateStatus(scanner);
                        break;
                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                        break;
                }
                } catch(Exception e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                }
        }while (!isExit) ;
    }
    public static void getListProduct(){
        Scanner scanner = new Scanner(System.in);
        styleOutput();
        List<ProductModel> productModelfull = ProductBusiness.getListProductfull();
        int pageNumber = productModelfull.size();
        int indexPage = 0;


        do {
            try {
                List<ProductModel> productModel =  ProductBusiness.getListProduct(5,indexPage);
                productModel.stream().forEach(productModel1 -> productModel1.outputProduct());
                if (indexPage==0){
                    System.out.println(GREEN2+"\t\t\t2.Trang tiếp"+RESET);
                    System.out.println("0. Thoát");
                }else if (indexPage+5>=pageNumber){
                    System.out.print(GREEN2+"\"\t\t\t\"1.Trang sau"+RESET);
                    System.out.println("0. Thoát");
                }else {
                    System.out.print(GREEN2+"\"\t\t\t\"1.Trang sau"+RESET);
                    System.out.println(GREEN2+"\t\t\t2.Trang tiếp"+RESET);
                    System.out.println("0. Thoát");
                }
                int chon = Integer.parseInt(scanner.nextLine());
                if ( chon == 2){
                    indexPage+=5;
                }else if (indexPage>0 && chon==1){
                   indexPage-=5;
                }else {
                    return;
                }
            } catch (NumberFormatException e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            } catch (Exception e){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }
        }while (true);
    }
    public static void cretaeProduct(Scanner scanner){
        ProductModel prod = new ProductModel();
        prod.inputProduct(scanner);
        if(ProductBusiness.addProduct(prod)){
            System.out.println("Thêm mới thành công.");
        }else {
            System.out.println("Thêm mới thất bại.");
        }

    }
    public static void updateProduc(Scanner scanner){
        System.out.println(YELLOW+"Nhập mã sản phẩm muốn cập nhật:"+RESET);
        String productId = scanner.nextLine();
        ProductModel product = ProductBusiness.checkById(productId);
        if (product!=null){
            boolean isExit = false;
            do {
                styleOutput();
                product.outputProduct();
                System.out.println();
                System.out.println();
                System.out.println(YELLOW+"************Menu cập nhật:*************"+RESET);
                System.out.println(YELLOW+"1.Tên sản phẩm"+RESET);
                System.out.println(YELLOW+"2.Nhà sản xuất"+RESET);
                System.out.println(YELLOW+"3.Ngày tạo"+RESET);
                System.out.println(YELLOW+"4.Lô s.phẩm"+RESET);
                System.out.println(YELLOW+"0.Thoát"+RESET);
                try {
                    byte choice = Byte.parseByte(scanner.nextLine());
                    switch (choice){
                        case 1:
                            product.setProduct_Name(ProductModel.validateProductName(scanner));
                            break;
                        case 2:
                            product.setManufacturer(ProductModel.validateManufacturer(scanner));
                            break;
                        case 3:
                            product.setDate(ProductModel.validateDay(scanner));
                            break;
                        case 4:
                            product.setBatch(ProductModel.validateBatch(scanner));
                            break;
                        case 0:
                            isExit = true;
                            break;
                        default:
                            System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                            break;
                    }
                    if (ProductBusiness.updateProduct(product)){
                        System.out.println(YELLOW+" Cập nhật thành công"+RESET);
                    }else {
                        System.out.println(RED+"Cập nhật thất bại"+RESET);
                    }

                } catch (Exception e){
                    System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                }
            }while (!isExit);
        }else {
            System.out.println(RED+"Mã sản phẩm không tồn tại"+RESET);
        }
    }
    public static void seachProductByName(Scanner scanner){
        System.out.println(YELLOW+"Nhập tên sản phẩm muốn tìm kiếm"+RESET);
        String nameSeach = scanner.nextLine();
        List<ProductModel>modelList = ProductBusiness.seachProduct(nameSeach);
        if (modelList.size()>0){
            styleOutput();
            modelList.stream().forEach(productModel -> productModel.outputProduct());
        }else {
            System.out.println(YELLOW+"Sản phẩm không tồn tại"+RESET);
        }
    }
    public static void updateStatus(Scanner scanner){
        System.out.println(YELLOW+"Nhập mã sản phẩm muốn cập nhật:"+RESET);
        String productId = scanner.nextLine();
        ProductModel product = ProductBusiness.checkById(productId);
        if (product!=null){
            boolean newStatus = ProductModel.validateProductStatus(scanner);
            if(ProductBusiness.updateProductStatus(productId,newStatus)){
                System.out.println(RED+"Cập nhật thành công"+RESET);
            }
        }else {
            System.out.println(RED+"Mã sản phẩm không tồn tại"+RESET);
        }
    }
    public static void styleOutput (){
        System.out.printf(BACKGROUND_CYAN+"|\t%-10s| \t%-15s|\t%-15s|  %-6s | %-5s |\t%-11s |\t%-12s"+RESET+" \n",
                "Mã Sp","Tên Sp","Nhà Sx","LôSp","S.Lg","Ngày tạo","Trạng thái\t\t");

        System.out.printf("|\t%-10s| \t%-15s|\t%-15s|  %-6s | %-5s |\t%-11s |\t%-12s \n",
                "===","===","======","===","===","======","======");

    }

}
