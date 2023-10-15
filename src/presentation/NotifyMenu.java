package presentation;

import business.BillBusiness;
import business.EmpBusiness;
import business.NotifyBusiness;
import model.EmployeeModel;
import model.ProductModel;
import validate.Validate;

import java.util.List;
import java.util.Scanner;

public class NotifyMenu {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";
    static Validate validate = new Validate();
    public static final boolean BILL_TYPE_IMPORT  = true;
    public static final boolean BILL_TYPE_EXPORT  = false;

    public static void runNotifyMenu(Scanner scanner) {
        boolean isExit = false;
        do { System.out.println();
            System.out.println();
            System.out.println(BACKGROUND_CYAN+"***********************REPORT MANAGEMENT*********************"+RESET);
            System.out.println("1. Thống kê chi phí theo ngày, tháng, năm");
            System.out.println("2. Thống kê chi phí theo khoảng thời gian");
            System.out.println("3. Thống kê doanh thu theo ngày, tháng, năm");
            System.out.println("4. Thống kê doanh thu theo khoảng thời gian");
            System.out.println("5. Thống kê số nhân viên theo từng trạng thái");
            System.out.println("6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian");
            System.out.println("7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian");
            System.out.println("8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian");
            System.out.println("9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian");
            System.out.println("0.Thoát");
            System.out.println();
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice) {
                    case 1:
//                        ProductModel productModel = new ProductModel();
//                        productModel  = NotifyBusiness.test();
//                        productModel.test1();
                         ThongKeChiPhi(scanner);
                        break;
                    case 2:
                        ThongKeChiPhiTheoKhoangThoiGian(scanner);
                        break;
                    case 3:
                        ThongKeDoanhThuTheoNgay(scanner);
                        break;
                    case 4:
                        ThongKeDoanhThuTheoKhoangThoiGian(scanner);
                        break;
                    case 5:
                        ThongkeNhanVienTheoTrangThai();
                        break;
                    case 6:SanPhamNhapNhieu(scanner);
                        break;
                    case 7:SanPhamNhapIt(scanner);
                        break;
                    case 8:
                        SanPhamXuatNhieu(scanner);
                        break;
                    case 9:
                        SanPhamXuatIt(scanner);
                        break;

                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
                        break;
                }
            } catch (NumberFormatException numberFormatException){
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println(RED+"Lựa chọn của bạn không hợp lệ"+RESET);
            }
        }while (!isExit) ;
    }
    public static void ThongKeChiPhi(Scanner scanner){
        System.out.println(YELLOW+"Nhập ngày:"+RESET);
        String days = validate.validateDay(scanner);
        int Tong = NotifyBusiness.ThongKeChiPhiTheoNgay(days);
        System.out.println(YELLOW+"Tổng chi phí là: "+RESET+ Tong);
    }
    public static void ThongKeChiPhiTheoKhoangThoiGian(Scanner scanner){
        System.out.println(YELLOW+"Ngày bắt đầu:"+RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(YELLOW+"Ngày kết thúc:"+RESET);
        String endDay = validate.validateDay(scanner);
        int T0ngChiPhi = NotifyBusiness.ThongKeChiPhiTheoKhoangThoiGian(startDay,endDay);
        System.out.println(YELLOW+"Tổng chi phí trong khoảng trên là: "+RESET+ T0ngChiPhi);
    }
    public static void ThongKeDoanhThuTheoNgay(Scanner scanner){
        System.out.println(YELLOW+"Ngày muốn tìm kiếm"+RESET);
        String startDay = validate.validateDay(scanner);
        int doanhThu = NotifyBusiness.DoanhThuTheoNgay(startDay);
        System.out.println(YELLOW+"Doanh Thu của ngày là: "+RESET + doanhThu);
    }
    public static void ThongKeDoanhThuTheoKhoangThoiGian(Scanner scanner){
        System.out.println(YELLOW+"Ngày bắt đầu:"+RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(YELLOW+"Ngày kết thúc:"+RESET);
        String endDay = validate.validateDay(scanner);
        int doanhThu = NotifyBusiness.DoanhThuKhoangThoiGian(startDay,endDay);
        System.out.println(YELLOW+"Doanh Thu của khung thời gian "+RESET + doanhThu);
    }
    public static void ThongKeNhanVienTheoTrangThai(){

    }
    public static void SanPhamNhapNhieu(Scanner scanner){
        System.out.println(YELLOW+"Ngày bắt đầu:"+RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(YELLOW+"Ngày kết thúc:"+RESET);
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyBusiness.SPNhieuNhat(startDay,endDay,BILL_TYPE_IMPORT);
        System.out.println(YELLOW+"Sản Phẩm nhập nhiều "+RESET);
        if (modelList.size()>0){
            outputMini();
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println(RED+"Không có sản phẩm nào"+RESET);
        }
    }
    public static void SanPhamNhapIt(Scanner scanner){ // Nhập vào ít
        System.out.println(YELLOW+"Ngày bắt đầu:"+RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(YELLOW+"Ngày kết thúc:"+RESET);
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyBusiness.SPItNhat(startDay,endDay,BILL_TYPE_IMPORT);
        System.out.println(YELLOW+"Sản Phẩm nhập ít "+RESET);
        if (modelList.size()>0){
            outputMini();
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println(RED+"Không có sản phẩm nào"+RESET);
        }
    }
    public static void SanPhamXuatIt(Scanner scanner){
        System.out.println(YELLOW+"Ngày bắt đầu:"+RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(YELLOW+"Ngày kết thúc:"+RESET);
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyBusiness.SPItNhat(startDay,endDay,BILL_TYPE_EXPORT);
        System.out.println(YELLOW+"Sản Phẩm xuất ít: "+RESET);
        if (modelList.size()>0){
            outputMini();
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println(RED+"Không có sản phẩm nào"+RESET);
        }
    }
    public static void SanPhamXuatNhieu(Scanner scanner){
        System.out.println(YELLOW+"Ngày bắt đầu:"+RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(YELLOW+"Ngày kết thúc:"+RESET);
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyBusiness.SPNhieuNhat(startDay,endDay,BILL_TYPE_EXPORT);
        System.out.println(YELLOW+"Sản Phẩm xuất nhiều"+RESET);
        if (modelList.size()>0){
            outputMini();
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println(RED+"Không có sản phẩm nào"+RESET);
        }
    }
    public static void ThongkeNhanVienTheoTrangThai(){
        List<EmployeeModel> modelList = EmpBusiness.getlistEmp();
        int HoatDong = 0;
        int NghiCheDo = 0;
        int NghiViec = 0;
        for (EmployeeModel employeeModel :modelList){
            if (employeeModel.getEmp_Status() == 0){
                HoatDong++;
            }if (employeeModel.getEmp_Status() == 1){
                NghiCheDo++;
            }
            if (employeeModel.getEmp_Status() == 2){
                NghiViec++;
            }
        }
        System.out.println(YELLOW+" Số Nhân Viên Đang Hoạt động: "+RESET+ HoatDong );
        System.out.println(YELLOW+" Số Nhân Viên Nghỉ chế độ: "+RESET+ NghiCheDo);
        System.out.println(YELLOW+" Số Nhân Viên nghỉ việc: "+RESET+NghiViec);
    }
    public  static void outputMini(){
        System.out.printf(BACKGROUND_CYAN+"|\t%-7.10s| \t%-10.10s| \t%-7s\n",
                "Mã SP", "Tên Sp","Số Lg\t"+RESET);
    }
}
