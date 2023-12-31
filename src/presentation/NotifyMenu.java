package presentation;

import Util.Color;
import repository.EmpRepository;
import repository.NotifyRepository;
import model.EmployeeModel;
import model.ProductModel;
import Util.Validate;

import java.util.List;
import java.util.Scanner;

public class NotifyMenu {
    static Validate validate = new Validate();
    public static final boolean BILL_TYPE_IMPORT  = true;
    public static final boolean BILL_TYPE_EXPORT  = false;

    public static void runNotifyMenu(Scanner scanner) {
        boolean isExit = false;
        do { System.out.println();
            System.out.println();
            System.out.println(Color.BACKGROUND_CYAN+"***********************REPORT MANAGEMENT*********************"+Color.RESET);
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
//
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
                        System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
                        break;
                }
            } catch (NumberFormatException numberFormatException){
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println(Color.RED+"Lựa chọn của bạn không hợp lệ"+Color.RESET);
            }
        }while (!isExit) ;
    }
    public static void ThongKeChiPhi(Scanner scanner){
        System.out.println(Color.YELLOW+"Nhập ngày:"+Color.RESET);
        String days = validate.validateDay(scanner);
        int Tong = NotifyRepository.ThongKeChiPhiTheoNgay(days);
        System.out.println(Color.YELLOW+"Tổng chi phí là: "+Color.RESET+ Tong);
    }
    public static void ThongKeChiPhiTheoKhoangThoiGian(Scanner scanner){
        System.out.println(Color.YELLOW+"Ngày bắt đầu:"+Color.RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(Color.YELLOW+"Ngày kết thúc:"+Color.RESET);
        String endDay = validate.validateDay(scanner);
        int T0ngChiPhi = NotifyRepository.ThongKeChiPhiTheoKhoangThoiGian(startDay,endDay);
        System.out.println(Color.YELLOW+"Tổng chi phí trong khoảng trên là: "+Color.RESET+ T0ngChiPhi);
    }
    public static void ThongKeDoanhThuTheoNgay(Scanner scanner){
        System.out.println(Color.YELLOW+"Ngày muốn tìm kiếm"+Color.RESET);
        String startDay = validate.validateDay(scanner);
        int doanhThu = NotifyRepository.DoanhThuTheoNgay(startDay);
        System.out.println(Color.YELLOW+"Doanh Thu của ngày là: "+Color.RESET + doanhThu);
    }
    public static void ThongKeDoanhThuTheoKhoangThoiGian(Scanner scanner){
        System.out.println(Color.YELLOW+"Ngày bắt đầu:"+Color.RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(Color.YELLOW+"Ngày kết thúc:"+Color.RESET);
        String endDay = validate.validateDay(scanner);
        int doanhThu = NotifyRepository.DoanhThuKhoangThoiGian(startDay,endDay);
        System.out.println(Color.YELLOW+"Doanh Thu của khung thời gian "+Color.RESET + doanhThu);
    }
    public static void SanPhamNhapNhieu(Scanner scanner){
        System.out.println(Color.YELLOW+"Ngày bắt đầu:"+Color.RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(Color.YELLOW+"Ngày kết thúc:"+Color.RESET);
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyRepository.SPNhieuNhat(startDay,endDay,BILL_TYPE_IMPORT);
        System.out.println(Color.YELLOW+"Sản Phẩm nhập nhiều "+Color.RESET);
        if (modelList.size()>0){
            outputMini();
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println(Color.RED+"Không có sản phẩm nào"+Color.RESET);
        }
    }
    public static void SanPhamNhapIt(Scanner scanner){ // Nhập vào ít
        System.out.println(Color.YELLOW+"Ngày bắt đầu:"+Color.RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(Color.YELLOW+"Ngày kết thúc:"+Color.RESET);
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyRepository.SPItNhat(startDay,endDay,BILL_TYPE_IMPORT);
        System.out.println(Color.YELLOW+"Sản Phẩm nhập ít "+Color.RESET);
        if (modelList.size()>0){
            outputMini();
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println(Color.RED+"Không có sản phẩm nào"+Color.RESET);
        }
    }
    public static void SanPhamXuatIt(Scanner scanner){
        System.out.println(Color.YELLOW+"Ngày bắt đầu:"+Color.RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(Color.YELLOW+"Ngày kết thúc:"+Color.RESET);
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyRepository.SPItNhat(startDay,endDay,BILL_TYPE_EXPORT);
        System.out.println(Color.YELLOW+"Sản Phẩm xuất ít: "+Color.RESET);
        if (modelList.size()>0){
            outputMini();
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println(Color.RED+"Không có sản phẩm nào"+Color.RESET);
        }
    }
    public static void SanPhamXuatNhieu(Scanner scanner){
        System.out.println(Color.YELLOW+"Ngày bắt đầu:"+Color.RESET);
        String startDay = validate.validateDay(scanner);
        System.out.println(Color.YELLOW+"Ngày kết thúc:"+Color.RESET);
        String endDay = validate.validateDay(scanner);
        List<ProductModel> modelList= NotifyRepository.SPNhieuNhat(startDay,endDay,BILL_TYPE_EXPORT);
        System.out.println(Color.YELLOW+"Sản Phẩm xuất nhiều"+Color.RESET);
        if (modelList.size()>0){
            outputMini();
            modelList.stream().forEach(ProductModel::outputStyle2);
        }else {
            System.out.println(Color.RED+"Không có sản phẩm nào"+Color.RESET);
        }
    }
    public static void ThongkeNhanVienTheoTrangThai(){
        List<EmployeeModel> modelList = EmpRepository.getlistEmp();
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
        System.out.println(Color.YELLOW+" Số Nhân Viên Đang Hoạt động: "+Color.RESET+ HoatDong );
        System.out.println(Color.YELLOW+" Số Nhân Viên Nghỉ chế độ: "+Color.RESET+ NghiCheDo);
        System.out.println(Color.YELLOW+" Số Nhân Viên nghỉ việc: "+Color.RESET+NghiViec);
    }
    public  static void outputMini(){
        System.out.printf(Color.BACKGROUND_CYAN+"|\t%-7.10s| \t%-10.10s| \t%-7s\n",
                "Mã SP", "Tên Sp","Số Lg\t"+Color.RESET);
    }
}
