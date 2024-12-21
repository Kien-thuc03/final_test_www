package edu.iuh.fit.cuahangsercurity.backend.entities;

import edu.iuh.fit.cuahangsercurity.backend.enums.TrangThai;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donhang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "MADONHANG", nullable = false, length = 50)
    private String maDonHang;

    @Size(max = 100)
    @NotNull
    @Column(name = "TENKHACHHANG", nullable = false, length = 100)
    private String tenKhachHang;

    @Size(max = 100)
    @NotNull
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @NotNull
    @Column(name = "NGAYDATHANG", nullable = false)
    private LocalDate ngayDatHang;

    @Size(max = 100)
    @NotNull
    @Column(name = "TENSP", nullable = false, length = 100)
    private String tenSp;

    @ColumnDefault("1")
    @Column(name = "TRANGTHAI")
    private TrangThai trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MACUAHANG")
    private CuaHang maCuaHang;

}