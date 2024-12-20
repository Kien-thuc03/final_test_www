package edu.iuh.fit.phone.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_dien_thoai", nullable = false, length = 50)
    private Long maDienThoai;

    @ColumnDefault("'0'")
    @Column(name = "ten_dien_thoai", nullable = false, length = 50)
    private String tenDienThoai;

    @ColumnDefault("'0'")
    @Column(name = "dia_chi", length = 100)
    private String diaChi;

    @ColumnDefault("0")
    @Column(name = "gia_von", nullable = false)
    private Double giaVon;

    @ColumnDefault("'0'")
    @Column(name = "loai", nullable = false, length = 50)
    private String loai;

    @ColumnDefault("'0'")
    @Column(name = "`nha_cung_ cap`", nullable = false, length = 50)
    private String nhaCungCap;

}