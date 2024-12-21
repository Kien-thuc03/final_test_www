package edu.iuh.fit.cuahangsercurity.backend.services.Impl;

import edu.iuh.fit.cuahangsercurity.backend.entities.CuaHang;
import edu.iuh.fit.cuahangsercurity.backend.repositories.CuaHangRepository;
import edu.iuh.fit.cuahangsercurity.backend.services.CuaHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuaHangServiceImpl implements CuaHangService {
    @Autowired
    private CuaHangRepository cuaHangRepository;

    @Override
    public List<CuaHang> getAllCuaHang() {
        return (List<CuaHang>) cuaHangRepository.findAll();
    }

    @Override
    public CuaHang getCuaHangById(Integer id) {
        return cuaHangRepository.findById(id).get();
    }

    @Override
    public CuaHang addCuaHang(CuaHang cuaHang) {
        return cuaHangRepository.save(cuaHang);
    }

    @Override
    public CuaHang getCuaHangByTen(String ten) {
        return cuaHangRepository.findByTen(ten);
    }
}
