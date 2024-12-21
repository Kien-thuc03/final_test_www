package edu.iuh.fit.cuahangsercurity.backend.enums;

public enum TrangThai {
    CHUA_CHAP_NHAN(0),
    CHAP_NHAN(1);

    private final int value;

    TrangThai(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TrangThai fromValue(int value) {
        for (TrangThai trangThai : TrangThai.values()) {
            if (trangThai.getValue() == value) {
                return trangThai;
            }
        }
        return null;
    }
}
