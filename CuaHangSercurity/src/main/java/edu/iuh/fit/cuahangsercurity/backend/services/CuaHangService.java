package edu.iuh.fit.cuahangsercurity.backend.services;

import edu.iuh.fit.cuahangsercurity.backend.entities.CuaHang;

import java.util.List;

public interface CuaHangService {
    public List<CuaHang> getAllCuaHang();
    public CuaHang getCuaHangById(Integer id);
    public CuaHang addCuaHang(CuaHang cuaHang);
    public CuaHang getCuaHangByTen(String ten);
}
