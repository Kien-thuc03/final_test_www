package edu.iuh.fit.cuahangsercurity.backend.repositories;

import edu.iuh.fit.cuahangsercurity.backend.entities.DonHang;
import edu.iuh.fit.cuahangsercurity.backend.enums.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {
  List<DonHang> findByMaDonHangOrMaCuaHang_Ten(String maDonHang, String ten);

  @Query("select (count(d) > 0) from DonHang d where d.email = ?1 and d.ngayDatHang = ?2 and d.trangThai = ?3")
  boolean existsByEmailAndNgayDatHangAndTrangThai(String email, LocalDate ngayDatHang, TrangThai trangThai);

  long deleteByMaDonHang(String maDonHang);

}