package edu.iuh.fit.ck.backend.services.Implement;

import edu.iuh.fit.ck.backend.entities.CuaHang;
import edu.iuh.fit.ck.backend.repositories.CuaHangRepository;
import edu.iuh.fit.ck.backend.services.CuaHangService;
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
}
