package service;

import Util.Color;
import model.ProductModel;
import repository.ProductRepository;

import java.util.List;
import java.util.Scanner;

public class ProductService {
    public static List<ProductModel> getListProductSplitPage(int dataPage, int indexPage){
        return ProductRepository.getListProductSplitPage(dataPage,indexPage);
    };
    public static List<ProductModel> getListProduct(){
        return ProductRepository.getListProductFull();
    };
    public static List<ProductModel> findListProductByName( String productName){
        return ProductRepository.findListProductByName(productName);
    }
    public static ProductModel findProductById(String productId){
        return ProductRepository.findProductById(productId);
    }
    public static boolean hasAddProduct(ProductModel product){
        return ProductRepository.hasAddProduct(product);
    }

    public static boolean hasUpdateProduct(ProductModel product){
        return ProductRepository.hasUpdateProduct(product);
    }
    public static void displayListProduct(){
        Scanner scanner = new Scanner(System.in);
        styleOutput();
        int pageNumber = getListProduct().size();
        int indexPage = 0;
        do {
            try {
                List<ProductModel> productModel =  getListProductSplitPage(5,indexPage);
                productModel.forEach(ProductModel::displayMessageProduct);
                if (indexPage==0){
                    System.out.println(Color.GREEN2+"\t\t\t2.Trang tiếp"+Color.RESET);
                    System.out.println("0. Thoát");
                }else if (indexPage+5>=pageNumber){
                    System.out.print(Color.GREEN2+"\"\t\t\t\"1.Trang sau"+Color.RESET);
                    System.out.println("0. Thoát");
                }else {
                    System.out.print(Color.GREEN2+"\"\t\t\t\"1.Trang sau"+Color.RESET);
                    System.out.println(Color.GREEN2+"\t\t\t2.Trang tiếp"+Color.RESET);
                    System.out.println("0. Thoát");
                }
                int choice = Integer.parseInt(scanner.nextLine());
                if ( choice == 2){
                    indexPage+=5;
                }else if (indexPage>0 && choice==1){
                    indexPage-=5;
                }else {
                    return;
                }
            } catch (NumberFormatException e){
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            } catch (Exception e){
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            }
        }while (true);
    }
    public static void createProduct(Scanner scanner){
        ProductModel product = new ProductModel();
        product.inputProduct(scanner);
        if(hasAddProduct(product)){
            System.out.println("Thêm mới thành công.");
        }else {
            System.out.println("Thêm mới thất bại.");
        }

    }
    public static void updateProduct(Scanner scanner){
        System.out.println(Color.YELLOW+"Nhập mã sản phẩm muốn cập nhật:"+Color.RESET);
        String productId = scanner.nextLine();
        ProductModel product = findProductById(productId);
        if (product!=null){
            boolean isExit = false;
            do {
                styleOutput();
                product.displayMessageProduct();
                menuUpdate();
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
                            System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                            break;
                    }
                    if (hasUpdateProduct(product)){
                        System.out.println(Color.YELLOW+" Cập nhật thành công"+Color.RESET);
                    }else {
                        System.out.println(Color.RED+"Cập nhật thất bại"+Color.RESET);
                    }

                } catch (Exception e){
                    System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                }
            }while (!isExit);
        }else {
            System.out.println(Color.RED+"Mã sản phẩm không tồn tại"+Color.RESET);
        }
    }
    public static void findProductByName(Scanner scanner){
        System.out.println(Color.YELLOW+"Nhập tên sản phẩm muốn tìm kiếm"+Color.RESET);
        String nameSearch = scanner.nextLine();
        List<ProductModel>modelList =findListProductByName(nameSearch);
        if (modelList.size()>0){
            styleOutput();
            modelList.forEach(ProductModel::displayMessageProduct);
        }else {
            System.out.println(Color.YELLOW+"Sản phẩm không tồn tại"+Color.RESET);
        }
    }
    public static void updateStatus(Scanner scanner){
        System.out.println(Color.YELLOW+"Nhập mã sản phẩm muốn cập nhật:"+Color.RESET);
        String productId = scanner.nextLine();
        ProductModel product = findProductById(productId);
        if (product!=null){
            product.setProduct_status(ProductModel.validateProductStatus(scanner)) ;
            if(hasUpdateProduct(product)){
                System.out.println(Color.RED+"Cập nhật thành công"+Color.RESET);
            }
        }else {
            System.out.println(Color.RED+"Mã sản phẩm không tồn tại"+Color.RESET);
        }
    }
    public static void styleOutput (){
        System.out.printf(Color.BACKGROUND_CYAN+"|\t%-10s| \t%-15s|\t%-15s|  %-6s | %-5s |\t%-11s |\t%-12s"+Color.RESET+" \n",
                "Mã Sp","Tên Sp","Nhà Sx","LôSp","S.Lg","Ngày tạo","Trạng thái\t\t");

        System.out.printf("|\t%-10s| \t%-15s|\t%-15s|  %-6s | %-5s |\t%-11s |\t%-12s \n",
                "===","===","======","===","===","======","======");

    }
    public static void menuUpdate(){
        System.out.println();
        System.out.println();
        System.out.println(Color.YELLOW+"************Menu cập nhật:*************"+Color.RESET);
        System.out.println(Color.YELLOW+"1.Tên sản phẩm"+Color.RESET);
        System.out.println(Color.YELLOW+"2.Nhà sản xuất"+Color.RESET);
        System.out.println(Color.YELLOW+"3.Ngày tạo"+Color.RESET);
        System.out.println(Color.YELLOW+"4.Lô s.phẩm"+Color.RESET);
        System.out.println(Color.YELLOW+"0.Thoát"+Color.RESET);
    }

}
