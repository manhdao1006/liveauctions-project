export const BASE_URL = 'http://localhost:8080'

export const API_ENDPOINTS = {
    AUTH: {
        DANGKY_QUANTRI: `${BASE_URL}/api/auth/register-admin`,
        DANGKY_NHANVIEN: `${BASE_URL}/api/auth/register-staff`,
        DANGKY_NGUOIBAN: `${BASE_URL}/api/auth/register-seller`,
        DANGKY: `${BASE_URL}/api/auth/register`,
        DANGNHAP: `${BASE_URL}/api/auth/login`,
        DANGXUAT: `${BASE_URL}/api/auth/logout`,
        REFRESH_TOKEN: `${BASE_URL}/api/auth/refresh-token`,
        GET_QUANHUYENS: `${BASE_URL}/api/auth/quan-huyen`,
        GET_QUANHUYEN_BY_MAPHUONGXA: (maPhuongXa: number) =>
            `${BASE_URL}/api/auth/quan-huyen/maPhuongXa=${maPhuongXa}`,
        GET_PHUONGXAS_BY_MAQUANHUYEN: (maQuanHuyen: number) =>
            `${BASE_URL}/api/auth/phuong-xa/maQuanHuyen=${maQuanHuyen}`,
        GET_PHUONGXAS: `${BASE_URL}/api/auth/phuong-xa`
    },
    NGUOIDUNG: {
        GET_BY_MANGUOIDUNG: (maNguoiDung: number) => `${BASE_URL}/api/auth/view/${maNguoiDung}`,
        GET_BY_EMAIL: `${BASE_URL}/api/auth/profile`,
        GET_NGUOIDUNGS_BY_MAVAITRO: (maVaiTro: number) =>
            `${BASE_URL}/api/v1/admin/nguoi-dung/role/${maVaiTro}`
    },
    NHANVIEN: {
        GET_NHANVIENS: `${BASE_URL}/api/v1/admin/nhan-vien/list`,
        GET_BY_MANGUOIDUNG: (maNguoiDung: number) =>
            `${BASE_URL}/api/v1/admin/nhan-vien/${maNguoiDung}`,
        ADD: `${BASE_URL}/api/v1/admin/nhan-vien/add`,
        UPDATE: (maNguoiDung: number) => `${BASE_URL}/api/v1/admin/nhan-vien/edit/${maNguoiDung}`,
        DELETE: (maNguoiDung: number) => `${BASE_URL}/api/v1/admin/nhan-vien/delete/${maNguoiDung}`,
        BAN: (maNguoiDung: number) => `${BASE_URL}/api/v1/admin/nhan-vien/ban/${maNguoiDung}`
    },
    NGUOIBAN: {
        GET_BY_MANGUOIDUNG: (maNguoiDung: number) =>
            `${BASE_URL}/api/v1/admin/nguoi-ban/${maNguoiDung}`,
        ADD: `${BASE_URL}/api/v1/admin/nguoi-ban/add`,
        UPDATE: (maNguoiDung: number) => `${BASE_URL}/api/v1/admin/nguoi-ban/edit/${maNguoiDung}`,
        DELETE: (maNguoiDung: number) => `${BASE_URL}/api/v1/admin/nguoi-ban/delete/${maNguoiDung}`,
        BAN: (maNguoiDung: number) => `${BASE_URL}/api/v1/admin/nguoi-ban/ban/${maNguoiDung}`
    },
    NGUOIMUA: {
        GET_BY_MANGUOIDUNG: (maNguoiDung: number) =>
            `${BASE_URL}/api/v1/admin/nguoi-mua/${maNguoiDung}`,
        ADD: `${BASE_URL}/api/v1/admin/nguoi-mua/add`,
        UPDATE: (maNguoiDung: number) => `${BASE_URL}/api/v1/admin/nguoi-mua/edit/${maNguoiDung}`,
        DELETE: (maNguoiDung: number) => `${BASE_URL}/api/v1/admin/nguoi-mua/delete/${maNguoiDung}`,
        BAN: (maNguoiDung: number) => `${BASE_URL}/api/v1/admin/nguoi-mua/ban/${maNguoiDung}`
    },
    NHATHAMDINH: {
        GET_NHATHAMDINHS: `${BASE_URL}/api/v1/admin/nha-tham-dinh/list`,
        GET_BY_MANHATHAMDINH: (maNhaThamDinh: number) =>
            `${BASE_URL}/api/v1/admin/nha-tham-dinh/maNhaThamDinh=${maNhaThamDinh}`,
        GET_BY_EMAIL: (email: string) => `${BASE_URL}/api/v1/admin/nha-tham-dinh/email=${email}`,
        ADD: `${BASE_URL}/api/v1/admin/nha-tham-dinh/add`,
        UPDATE: (maNhaThamDinh: number) =>
            `${BASE_URL}/api/v1/admin/nha-tham-dinh/edit/${maNhaThamDinh}`,
        DELETE: (maNhaThamDinh: number) =>
            `${BASE_URL}/api/v1/admin/nha-tham-dinh/delete/${maNhaThamDinh}`,
        BAN: (maNhaThamDinh: number) =>
            `${BASE_URL}/api/v1/admin/nha-tham-dinh/ban/${maNhaThamDinh}`
    },
    NHAKHO: {
        GET_NHAKHOS: `${BASE_URL}/api/v1/admin/nha-kho/list`,
        GET_BY_MANHAKHO: (maNhaKho: number) =>
            `${BASE_URL}/api/v1/admin/nha-kho/maNhaKho=${maNhaKho}`,
        ADD: `${BASE_URL}/api/v1/admin/nha-kho/add`,
        UPDATE: (maNhaKho: number) => `${BASE_URL}/api/v1/admin/nha-kho/edit/${maNhaKho}`,
        DELETE: (maNhaKho: number) => `${BASE_URL}/api/v1/admin/nha-kho/delete/${maNhaKho}`
    },
    DANHMUC: {
        GET_DANHMUCS: `${BASE_URL}/api/v1/staff/danh-muc/list`,
        GET_BY_MADANHMUC: (maDanhMuc: number) =>
            `${BASE_URL}/api/v1/staff/danh-muc/maDanhMuc=${maDanhMuc}`,
        ADD: `${BASE_URL}/api/v1/staff/danh-muc/add`,
        UPDATE: (maDanhMuc: number) => `${BASE_URL}/api/v1/staff/danh-muc/edit/${maDanhMuc}`,
        DELETE: (maDanhMuc: number) => `${BASE_URL}/api/v1/staff/danh-muc/delete/${maDanhMuc}`
    },
    DANHMUCCON: {
        GET_DANHMUCCONS: `${BASE_URL}/api/v1/staff/danh-muc-con/list`,
        GET_DANHMUCCONS_BY_MADANHMUC: (maDanhMuc: number) =>
            `${BASE_URL}/api/v1/staff/danh-muc-con/maDanhMuc=${maDanhMuc}`,
        GET_BY_MADANHMUCCON: (maDanhMucCon: number) =>
            `${BASE_URL}/api/v1/staff/danh-muc-con/maDanhMucCon=${maDanhMucCon}`,
        ADD: `${BASE_URL}/api/v1/staff/danh-muc-con/add`,
        UPDATE: (maDanhMucCon: number) =>
            `${BASE_URL}/api/v1/staff/danh-muc-con/edit/${maDanhMucCon}`,
        DELETE: (maDanhMucCon: number) =>
            `${BASE_URL}/api/v1/staff/danh-muc-con/delete/${maDanhMucCon}`
    },
    LOAIDAUGIA: {
        GET_LOAIDAUGIAS: `${BASE_URL}/api/v1/staff/loai-dau-gia/list`
    },
    PHIENDAUGIA: {
        GET_PHIENDAUGIAS: `${BASE_URL}/api/v1/staff/phien-dau-gia/list`,
        GET_BY_MAPHIENDAUGIA: (maPhienDauGia: number) =>
            `${BASE_URL}/api/v1/staff/phien-dau-gia/maPhienDauGia=${maPhienDauGia}`,
        ADD: `${BASE_URL}/api/v1/staff/phien-dau-gia/add`,
        UPDATE: (maPhienDauGia: number) =>
            `${BASE_URL}/api/v1/staff/phien-dau-gia/edit/${maPhienDauGia}`,
        DELETE: (maPhienDauGia: number) =>
            `${BASE_URL}/api/v1/staff/phien-dau-gia/delete/${maPhienDauGia}`
    }
}
