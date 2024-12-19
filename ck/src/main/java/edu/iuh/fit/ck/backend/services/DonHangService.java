package edu.iuh.fit.ck.backend.services;

import edu.iuh.fit.ck.backend.entities.DonHang;

import java.time.LocalDate;
import java.util.List;

public interface DonHangService {
    List<DonHang> getAllDonHang();
    List<DonHang> searchDonHang(String keyword);
    DonHang addDonHang(DonHang donHang);
    List<DonHang> findByEmailAndNgayDatHang(String email, LocalDate ngayDatHang);
    void deleteDonHang(Integer id);
    boolean existsByEmailAndNgayDatHangAndTrangThai(String email, LocalDate ngayDatHang, Boolean trangThai);
}