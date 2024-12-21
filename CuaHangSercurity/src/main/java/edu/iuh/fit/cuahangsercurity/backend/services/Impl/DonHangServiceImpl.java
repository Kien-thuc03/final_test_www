package edu.iuh.fit.cuahangsercurity.backend.services.Impl;

import edu.iuh.fit.cuahangsercurity.backend.entities.DonHang;
import edu.iuh.fit.cuahangsercurity.backend.enums.TrangThai;
import edu.iuh.fit.cuahangsercurity.backend.repositories.DonHangRepository;
import edu.iuh.fit.cuahangsercurity.backend.services.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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
        return donHangRepository.findByMaDonHangOrMaCuaHang_Ten(keyword, keyword);
    }

    @Override
    public DonHang addDonHang(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    @Override
    @Transactional
    public void deleteDonHang(String maDonHang) {
        donHangRepository.deleteByMaDonHang(maDonHang);

    }

    @Override
    public Boolean existsByEmailAndNgayDatHangAndTrangThai(String email, LocalDate ngayDat, TrangThai trangThai) {
        return donHangRepository.existsByEmailAndNgayDatHangAndTrangThai(email, ngayDat, trangThai);
    }
}
