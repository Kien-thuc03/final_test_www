package edu.iuh.fit.ck.backend.services;

import edu.iuh.fit.ck.backend.entities.CuaHang;

import java.util.List;

public interface CuaHangService {
    public List<CuaHang> getAllCuaHang();
    public CuaHang getCuaHangById(Integer id);
}
