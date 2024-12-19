package edu.iuh.fit.ck.backend.repositories;

import edu.iuh.fit.ck.backend.entities.DonHang;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DonHangRepository extends CrudRepository<DonHang, Integer> {
    List<DonHang> findByMaDonHangContainingOrMaCuaHang_TenContaining(String maDonHang, String tenCuaHang);
    List<DonHang> findByEmailAndNgayDatHang(String email, LocalDate ngayDatHang);
    boolean existsByEmailAndNgayDatHangAndTrangThai(String email, LocalDate ngayDatHang, Boolean trangThai);
}