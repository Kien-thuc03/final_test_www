package edu.iuh.fit.ck.backend.services.Implement;

import edu.iuh.fit.ck.backend.entities.DonHang;
import edu.iuh.fit.ck.backend.repositories.DonHangRepository;
import edu.iuh.fit.ck.backend.services.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DonHangServiceImpl implements DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    @Override
    public List<DonHang> getAllDonHang() {
        return (List<DonHang>) donHangRepository.findAll();
    }

    @Override
    public List<DonHang> searchDonHang(String keyword) {
        return donHangRepository.findByMaDonHangContainingOrMaCuaHang_TenContaining(keyword, keyword);
    }

    @Override
    public DonHang addDonHang(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    @Override
    public void deleteDonHang(Integer id) {
        Optional<DonHang> donHang = donHangRepository.findById(id);
        if (donHang.isPresent() && !donHang.get().getTrangThai()) {
            donHangRepository.deleteById(id);
        }
    }

    @Override
    public List<DonHang> findByEmailAndNgayDatHang(String email, LocalDate ngayDatHang) {
        return donHangRepository.findByEmailAndNgayDatHang(email, ngayDatHang);
    }

    @Override
    public boolean existsByEmailAndNgayDatHangAndTrangThai(String email, LocalDate ngayDatHang, Boolean trangThai) {
        return donHangRepository.existsByEmailAndNgayDatHangAndTrangThai(email, ngayDatHang, trangThai);
    }
}