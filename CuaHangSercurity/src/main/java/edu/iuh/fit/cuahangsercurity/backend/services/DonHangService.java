package edu.iuh.fit.cuahangsercurity.backend.services;

import edu.iuh.fit.cuahangsercurity.backend.entities.DonHang;
import edu.iuh.fit.cuahangsercurity.backend.enums.TrangThai;

import java.time.LocalDate;
import java.util.List;

public interface DonHangService {
    public List<DonHang> getAllDonHang();
    public List<DonHang> searchDonHang(String keyword);
    public DonHang addDonHang(DonHang donHang);
    public void deleteDonHang(String maDonHang);

    public Boolean existsByEmailAndNgayDatHangAndTrangThai(String email, LocalDate ngayDat, TrangThai trangThai);
}
