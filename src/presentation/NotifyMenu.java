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
    static Validate validate = new Validate();
    public static final boolean BILL_TYPE_IMPORT  = true;
    public static final boolean BILL_TYPE_EXPORT  = false;

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        runNotifyMenu(scanner);
//    }
    public static void runNotifyMenu(Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.println(" ******************REPORT MANAGEMENT****************");
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
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice) {
                    case 1:
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
                        SanPhamXuatIt();
                        break;

                    case 0:
                        isExit = true;
                        break;
                    default:
                        System.out.println("Lựa chọn của bạn không hợp lệ");
                        break;
                }
            } catch (NumberFormatException numberFormatException){
                System.out.println("Lựa chọn của bạn không hợp lệ");
            }
            catch (Exception e){
                System.out.println("Lựa chọn của bạn không hợp lệ");
            }
        }while (!isExit) ;
    }
    public static void ThongKeChiPhi(Scanner scanner){
        System.out.println("Nhập ngày:");
        String days = validate.validateDay(scanner);
        int Tong = NotifyBusiness.ThongKeChiPhiTheoNgay(days);
        System.out.println("Tổng chi phí là: "+ Tong);
    }
    public static void ThongKeChiPhiTheoKhoangThoiGian(Scanner scanner){
        System.out.println("Ngày bắt đầu:");
        String startDay = validate.validateDay(scanner);
        System.out.println("Ngày kết thúc:");
        String endDay = validate.validateDay(scanner);
        int T0ngChiPhi = NotifyBusiness.ThongKeChiPhiTheoKhoangThoiGian(startDay,endDay);
        System.out.println("Tổng chi phí trong khoảng trên là: "+ T0ngChiPhi);
    }
    public static void ThongKeDoanhThuTheoNgay(Scanner scanner){
        System.out.println("Ngày muốn tìm kiếm");
        String startDay = validate.validateDay(scanner);
        int doanhThu = NotifyBusiness.DoanhThuTheoNgay(startDay);
        System.out.println("Doanh Thu của ngày là: " + doanhThu);
    }
    public static void ThongKeDoanhThuTheoKhoangThoiGian(Scanner scanner){
        System.out.println("Ngày bắt đầu:");
        String startDay = validate.validateDay(scanner);
        System.out.println("Ngày kết thúc:");
        String endDay = validate.validateDay(scanner);
        int doanhThu = NotifyBusiness.DoanhThuKhoangThoiGian(startDay,endDay);
        System.out.println("Doanh Thu của khung thời gian " + doanhThu);
    }
    public static void ThongKeNhanVienTheoTrangThai(){

    }
    public static void SanPhamNhapNhieu(Scanner scanner){
        System.out.println("Ngày bắt đầu:");
        String startDay = validate.validateDay(scanner);
        System.out.println("Ngày kết thúc:");
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyBusiness.SPNhieuNhat(startDay,endDay,BILL_TYPE_IMPORT);
        System.out.println("Sản Phẩm nhập nhiều ");
        if (modelList.size()>0){
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println("Không có sản phẩm nào");
        }
    }
    public static void SanPhamNhapIt(Scanner scanner){ // Nhập vào ít
        System.out.println("Ngày bắt đầu:");
        String startDay = validate.validateDay(scanner);
        System.out.println("Ngày kết thúc:");
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyBusiness.SPItNhat(startDay,endDay,BILL_TYPE_IMPORT);
        System.out.println("Sản Phẩm nhập ít ");
        if (modelList.size()>0){
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println("Không có sản phẩm nào");
        }
    }
    public static void SanPhamXuatIt(Scanner scanner){
        System.out.println("Ngày bắt đầu:");
        String startDay = validate.validateDay(scanner);
        System.out.println("Ngày kết thúc:");
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyBusiness.SPItNhat(startDay,endDay,BILL_TYPE_EXPORT);
        System.out.println("Sản Phẩm nhập nhiều ");
        if (modelList.size()>0){
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println("Không có sản phẩm nào");
        }
    }
    public static void SanPhamXuatNhieu(Scanner scanner){
        System.out.println("Ngày bắt đầu:");
        String startDay = validate.validateDay(scanner);
        System.out.println("Ngày kết thúc:");
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyBusiness.SPNhieuNhat(startDay,endDay,BILL_TYPE_EXPORT);
        System.out.println("Sản Phẩm xuất nhiều");
        if (modelList.size()>0){
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println("Không có sản phẩm nào");
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
        System.out.println(" Số Nhân Viên Đang Hoạt động: "+ HoatDong );
        System.out.println(" Số Nhân Viên Nghỉ chế độ: "+ NghiCheDo);
        System.out.println(" Số Nhân Viên nghỉ việc: "+NghiViec);
    }
    public static void SanPhamXuatIt(){
        System.out.println("Ngày bắt đầu:");
        System.out.println("Ngày kết thúc:");

    }
}
