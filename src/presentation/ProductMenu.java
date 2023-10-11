package presentation;

import business.ProductBusiness;
import model.ProductModel;
import validate.Validate;

import java.util.List;
import java.util.Scanner;

public class ProductMenu {
    public static void runProductMenu(Scanner scanner) {
        boolean isExit = false;
        do {
            try {
                System.out.println("******************PRODUCT MANAGEMENT****************");
                System.out.println("1. Danh sách sản phẩm");
                System.out.println("2. Thêm mới sản phẩm");
                System.out.println("3. Cập nhật sản phẩm");
                System.out.println("4. Tìm kiếm sản phẩm");
                System.out.println("5. Cập nhật trạng thái sản phẩm");
                System.out.println("0. Thoát");
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
                        System.out.println("Lựa chọn của bạn không hợp lệ");
                        break;
                }
                } catch(Exception e){
                    System.out.println("Lựa chọn của bạn không hợp lệ");
                }
        }while (!isExit) ;
    }
    public static void getListProduct(){
        Scanner scanner = new Scanner(System.in);
        styleOutput();
        List<ProductModel> productModel =  ProductBusiness.getListProduct(5,0);
        productModel.stream().forEach(productModel1 -> productModel1.outputProduct());
        do {
            try {

                System.out.println("1.Trang đầu");
                System.out.println("2.Trang sau");
                System.out.println("0. Thoát");
                int chon = Integer.parseInt(scanner.nextLine());
                if ( chon == 2){
                    styleOutput();
                    productModel =  ProductBusiness.getListProduct(5,4);
                    productModel.stream().forEach(productModel11 -> productModel11.outputProduct());
                }else if (chon==1){
                   productModel =  ProductBusiness.getListProduct(5,0);
                    productModel.stream().forEach(productModel22 -> productModel22.outputProduct());
                }else {
                    return;
                }
            } catch (NumberFormatException e){
                System.out.println("Lựa chọn của bạn ko có");
            } catch (Exception e){
                System.out.println("Lựa chọn của bạn ko có");
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
        System.out.println("Nhập mã sản phẩm muốn cập nhật:");
        String productId = scanner.nextLine();
        ProductModel product = ProductBusiness.checkById(productId);
        if (product!=null){
            boolean isExit = false;
            do {
                styleOutput();
                product.outputProduct();
                System.out.println("Menu cập nhật:");
                System.out.println("1.Tên sản phẩm");
                System.out.println("2.Nhà sản xuất");
                System.out.println("3.Ngày tạo");
                System.out.println("4.Lô chứa sản phẩm");
                System.out.println("0.Thoát");
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
                            System.out.println("Lựa chọn không hợp lệ");
                            break;
                    }
                    if (ProductBusiness.updateProduct(product)){
                        System.out.println(" Cập nhật thành công");
                    }else {
                        System.out.println("Cập nhật thất bại");
                    }

                } catch (Exception e){
                    System.out.println("Lựa chọn không hợp lệ");
                }
            }while (!isExit);
        }else {
            System.out.println("Mã sản phẩm không tồn tại");
        }
    }
    public static void seachProductByName(Scanner scanner){
        System.out.println("Nhập tên sản phẩm muốn tìm kiếm");
        String nameSeach = scanner.nextLine();
        List<ProductModel>modelList = ProductBusiness.seachProduct(nameSeach);
        if (modelList.size()>0){
            styleOutput();
            modelList.stream().forEach(productModel -> productModel.outputProduct());
        }else {
            System.out.println("Sản phẩm không tồn tại");
        }
    }
    public static void updateStatus(Scanner scanner){
        System.out.println("Nhập mã sản phẩm muốn cập nhật:");
        String productId = scanner.nextLine();
        ProductModel product = ProductBusiness.checkById(productId);
        if (product!=null){
            boolean newStatus = ProductModel.validateProductStatus(scanner);
            if(ProductBusiness.updateProductStatus(productId,newStatus)){
                System.out.println("Cập nhật thành công");
            }
        }else {
            System.out.println("Mã sp không tồn tại");
        }
    }
    public static void styleOutput (){
        System.out.printf("|\t%-10s| \t%-15s|\t%-15s|  %-6s | %-5s |\t%-11s |\t%-12s| \n",
                "Mã Sp","Tên Sp","Nhà Sx","LôSp","S.Lg","Ngày tạo","Trạng thái");

        System.out.printf("|\t%-10s| \t%-15s|\t%-15s|  %-6s | %-5s |\t%-11s |\t%-12s| \n",
                "===","===","======","===","===","======","======");

    }

}
