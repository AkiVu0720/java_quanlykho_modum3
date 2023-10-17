package presentation;

import Controller.ProductController;
import Util.Color;
import repository.ProductRepository;
import model.ProductModel;
import service.ProductService;

import java.util.List;
import java.util.Scanner;

import static service.ProductService.getListProduct;

public class ProductMenu extends ProductController {
    public static void handleProductMenu(Scanner scanner) {
        boolean isExit = false;
        do {
            try {
                System.out.println();
                System.out.println();
                System.out.println(Color.BACKGROUND_CYAN+"******************PRODUCT MANAGEMENT******************"+Color.RESET);
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
                        displayListProduct();
                        break;
                    case 2:
                        createProduct(scanner);
                        break;
                    case 3:
                        updateProduct(scanner);
                        break;
                    case 4:
                        findProductByName(scanner);
                        break;
                    case 5:
                        updateStatus(scanner);
                        break;
                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                        break;
                }
                } catch(Exception e){
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                }
        }while (!isExit) ;
    }
}
