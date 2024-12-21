package edu.iuh.fit.cuahangsercurity.backend.repositories;

import edu.iuh.fit.cuahangsercurity.backend.entities.CuaHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuaHangRepository extends JpaRepository<CuaHang, Integer> {
    CuaHang findByTen(String ten);
}