package edu.iuh.fit.cuahangsercurity.fontend.controllers;

import edu.iuh.fit.cuahangsercurity.backend.entities.CuaHang;
import edu.iuh.fit.cuahangsercurity.backend.entities.DonHang;
import edu.iuh.fit.cuahangsercurity.backend.enums.TrangThai;
import edu.iuh.fit.cuahangsercurity.backend.services.CuaHangService;
import edu.iuh.fit.cuahangsercurity.backend.services.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Controller
public class DonHangController {
    @Autowired
    private DonHangService donHangService;
    @Autowired
    private CuaHangService cuaHangService;

    @RequestMapping(value = "/don-hang" ,method = RequestMethod.GET)
    public String searchDonHang(@RequestParam(name = "keyword", required = false) String keyword, Model model){

        if (keyword == null || keyword.isEmpty()){
            model.addAttribute("donHangs",donHangService.getAllDonHang());
        }else {
            model.addAttribute("donHangs",donHangService.searchDonHang(keyword.trim()));
        }
        return "don-hang/ds-don-hang";
    }

    @RequestMapping(value = "/don-hang/them-don-hang", method = RequestMethod.GET)
    public String addDonHang(Model model){
        model.addAttribute("donHang",new DonHang());
        model.addAttribute("cuaHangs",cuaHangService.getAllCuaHang());
        return "don-hang/them-don-hang";
    }

    @RequestMapping(value = "don-hang/them-don-hang", method = RequestMethod.POST)
    public String addDonHang(DonHang donHang, Model model) {
        model.addAttribute("cuaHangs",cuaHangService.getAllCuaHang());
        // Kiểm tra các trường bắt buộc
        if (donHang.getTenKhachHang() == null || donHang.getTenKhachHang().isEmpty() || donHang.getEmail() == null || donHang.getEmail().isEmpty() ||
                donHang.getNgayDatHang() == null || donHang.getTenSp() == null || donHang.getTenSp().isEmpty()) {
            model.addAttribute("error", "Các trường bắt buộc không được để trống!");
            return "don-hang/them-don-hang"; // Trả về form thêm đơn hàng
        }

        // Kiểm tra độ dài Tên KH
        if (donHang.getTenKhachHang().length() < 5 || donHang.getTenKhachHang().length() > 50) {
            model.addAttribute("error", "Tên khách hàng phải từ 5 đến 50 ký tự!");
            return "don-hang/them-don-hang";
        }

        // Kiểm tra định dạng Email
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!donHang.getEmail().matches(emailRegex)) {
            model.addAttribute("error", "Email không đúng định dạng!");
            return "don-hang/them-don-hang";
        }

        // Kiểm tra ngày đặt hàng
        if (donHang.getNgayDatHang().isBefore(LocalDate.now())){
            model.addAttribute("error", "Ngày đặt hàng không được nhỏ hơn ngày hiện tại!");
            return "don-hang/them-don-hang";
        }

        // Kiểm tra trong ngày, 1 email chỉ được đặt 1 đơn hàng tạm
        boolean exists = donHangService.existsByEmailAndNgayDatHangAndTrangThai(donHang.getEmail(), donHang.getNgayDatHang(), donHang.getTrangThai());
        if (exists) {
            model.addAttribute("error", "Email đã tồn tại đơn hàng tạm trong ngày!");
            return "don-hang/them-don-hang";
        }
        // Tạo mã đơn hàng tự động
        String maDonHang = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        // Lưu vào CSDL
        donHang.setMaDonHang(maDonHang);
        donHangService.addDonHang(donHang);

        // Redirect về danh sách đơn hàng
        return "redirect:/don-hang";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/xoa-don-hang/{maDonHang}", method = RequestMethod.GET)
    public String deleteDonHang(@PathVariable(name = "maDonHang") String maDonHang) {
        donHangService.deleteDonHang(maDonHang);
        return "redirect:/don-hang";
    }


}
