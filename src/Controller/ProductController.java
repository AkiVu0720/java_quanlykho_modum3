package Controller;

import service.ProductService;

import java.util.Scanner;

public class ProductController {
    public static void displayListProduct(){
        ProductService.displayListProduct();
    };
    public static void createProduct(Scanner scanner){
        ProductService.createProduct(scanner);
    };
    public static void updateProduct(Scanner scanner) {
        ProductService.updateProduct(scanner);
    };
    public static void findProductByName(Scanner scanner){
        ProductService.findProductByName(scanner);
    };
    public static void updateStatus(Scanner scanner){
        ProductService.updateStatus(scanner);
    };

}
