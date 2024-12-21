package edu.iuh.fit.cuahangsercurity.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuahang")
public class CuaHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MACUAHANG", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "TEN", nullable = false, length = 100)
    private String ten;

    @OneToMany(mappedBy = "maCuaHang")
    private Set<DonHang> donHangs = new LinkedHashSet<>();

}