package edu.iuh.fit.ck.fontend;

import edu.iuh.fit.ck.backend.entities.DonHang;
import edu.iuh.fit.ck.backend.repositories.DonHangRepository;
import edu.iuh.fit.ck.backend.services.CuaHangService;
import edu.iuh.fit.ck.backend.services.DonHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
public class DonHangController {
    @Autowired
    private DonHangService donHangService;
    @Autowired
    private CuaHangService cuaHangService;

    @RequestMapping("/list")
    public String showDonHangList(Model model) {
        model.addAttribute("dsdonghang", donHangService.getAllDonHang());
        return "don-hang/ds-don-hang";
    }

    @GetMapping("/don-hang")
    public String searchDonHang(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<DonHang> dsDonHang;
        if (keyword != null && !keyword.isEmpty()) {
            dsDonHang = donHangService.searchDonHang(keyword.trim());
        } else {
            dsDonHang = donHangService.getAllDonHang();
        }
        model.addAttribute("dsdonghang", dsDonHang);
        model.addAttribute("search", keyword);
        return "don-hang/ds-don-hang";
    }

    @RequestMapping(value = "/them-don-hang", method = RequestMethod.GET)
    public String addDonHang(Model model) {
        model.addAttribute("donHang", new DonHang());
        model.addAttribute("dscuahang", cuaHangService.getAllCuaHang());
        return "don-hang/them-don-hang";
    }

//    @RequestMapping(value = "/them-don-hang", method = RequestMethod.POST)
//    public String addDonHang(DonHang donHang) {
//        donHangService.addDonHang(donHang);
//        return "redirect:/list";
//    }

    @RequestMapping(value = "/them-don-hang", method = RequestMethod.POST)
    public String addDonHang(@RequestParam("tenKhachHang") String tenKhachHang,
                             @RequestParam("email") String email,
                             @RequestParam("ngayDatHang") String ngayDatHang,
                             @RequestParam("maCuaHang") Integer maCuaHang,
                             @RequestParam("tenSp") String tenSp,
                             @RequestParam("trangThai") Boolean trangThai,
                             Model model) {
        // Kiểm tra các trường bắt buộc
        if (tenKhachHang == null || tenKhachHang.isEmpty() || email == null || email.isEmpty() ||
                ngayDatHang == null || ngayDatHang.isEmpty() || tenSp == null || tenSp.isEmpty()) {
            model.addAttribute("error", "Các trường bắt buộc không được để trống!");
            return "don-hang/them-don-hang"; // Trả về form thêm đơn hàng
        }

        // Kiểm tra độ dài Tên KH
        if (tenKhachHang.length() < 5 || tenKhachHang.length() > 50) {
            model.addAttribute("error", "Tên khách hàng phải từ 5 đến 50 ký tự!");
            return "don-hang/them-don-hang";
        }

        // Kiểm tra định dạng Email
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!email.matches(emailRegex)) {
            model.addAttribute("error", "Email không đúng định dạng!");
            return "don-hang/them-don-hang";
        }

        // Kiểm tra ngày đặt hàng
        LocalDate ngayDat;
        try {
            ngayDat = LocalDate.parse(ngayDatHang);
            if (ngayDat.isBefore(LocalDate.now())) {
                model.addAttribute("error", "Ngày đặt hàng phải lớn hơn ngày hiện tại!");
                return "don-hang/them-don-hang";
            }
        } catch (DateTimeParseException e) {
            model.addAttribute("error", "Ngày đặt hàng không đúng định dạng (yyyy-MM-dd)!");
            return "don-hang/them-don-hang";
        }

        // Kiểm tra trong ngày, 1 email chỉ được đặt 1 đơn hàng tạm
        boolean exists = donHangService.existsByEmailAndNgayDatHangAndTrangThai(email, ngayDat, false);
        if (exists) {
            model.addAttribute("error", "Email đã tồn tại đơn hàng tạm trong ngày!");
            return "don-hang/them-don-hang";
        }

        // Tạo mã đơn hàng tự động
        String maDonHang = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        // Tạo đối tượng DonHang
        DonHang donHang = new DonHang();
        donHang.setMaDonHang(maDonHang);
        donHang.setTenKhachHang(tenKhachHang);
        donHang.setEmail(email);
        donHang.setNgayDatHang(ngayDat);
        donHang.setMaCuaHang(cuaHangService.getCuaHangById(maCuaHang));
        donHang.setTenSp(tenSp);
        donHang.setTrangThai(trangThai);

        // Lưu vào CSDL
        donHangService.addDonHang(donHang);

        // Redirect về danh sách đơn hàng
        return "redirect:/list";
    }


    @RequestMapping(value = "/xoa-don-hang", method = RequestMethod.GET)
    public String deleteDonHang(@RequestParam("id") Integer id) {
        donHangService.deleteDonHang(id);
        return "redirect:/list";
    }

}
