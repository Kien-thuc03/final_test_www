package edu.iuh.fit.ck.backend.repositories;

import edu.iuh.fit.ck.backend.entities.CuaHang;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuaHangRepository extends CrudRepository<CuaHang, Integer> {
}
