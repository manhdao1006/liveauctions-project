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
        GET_PHUONGXA_BY_MAQUANHUYEN: (maQuanHuyen: number) => `${BASE_URL}/api/auth/phuong-xa/maQuanHuyen=${maQuanHuyen}`,
        GET_PHUONGXAS: `${BASE_URL}/api/auth/phuong-xa`,
    },
    NGUOIDUNG: {
        GET_BY_MANGUOIDUNG: (maNguoiDung: number) => `${BASE_URL}/api/auth/view/${maNguoiDung}`,
        GET_BY_EMAIL: `${BASE_URL}/api/auth/profile`,
        GET_USERS: `${BASE_URL}/api/users/all`,
        GET_USERS_PAGE: `${BASE_URL}/api/users/`,
        ADD: `${BASE_URL}/api/users/create`,
        UPDATE: (userId: number) => `${BASE_URL}/api/users/edit/${userId}`,
        DELETE: (userId: number) => `${BASE_URL}/api/users/delete/${userId}`,
        SEARCH: `${BASE_URL}/api/users/search`,
        FILTER: `${BASE_URL}/api/users/filter`,
    },
    NHATHAMDINH: {
        GET_NHATHAMDINHS: `${BASE_URL}/api/v1/admin/nha-tham-dinh/list`,
        GET_BY_MANHATHAMDINH: (maNhaThamDinh: number) =>`${BASE_URL}/api/v1/admin/nha-tham-dinh/maNhaThamDinh=${maNhaThamDinh}`,
        GET_BY_EMAIL: (email: string) =>`${BASE_URL}/api/v1/admin/nha-tham-dinh/email=${email}`,
        ADD: `${BASE_URL}/api/v1/admin/nha-tham-dinh/add`,
        UPDATE: (maNhaThamDinh: number) => `${BASE_URL}/api/v1/admin/nha-tham-dinh/edit/${maNhaThamDinh}`,
        DELETE: (maNhaThamDinh: number) => `${BASE_URL}/api/v1/admin/nha-tham-dinh/delete/${maNhaThamDinh}`,
        BAN: (maNhaThamDinh: number) => `${BASE_URL}/api/v1/admin/nha-tham-dinh/ban/${maNhaThamDinh}`,
    },
    NHAKHO: {
        GET_NHAKHOS: `${BASE_URL}/api/v1/admin/nha-kho/list`,
        GET_BY_MANHAKHO: (maNhaKho: number) =>`${BASE_URL}/api/v1/admin/nha-kho/maNhaKho=${maNhaKho}`,
        ADD: `${BASE_URL}/api/v1/admin/nha-kho/add`,
        UPDATE: (maNhaKho: number) => `${BASE_URL}/api/v1/admin/nha-kho/edit/${maNhaKho}`,
        DELETE: (maNhaKho: number) => `${BASE_URL}/api/v1/admin/nha-kho/delete/${maNhaKho}`,
    },
    DANHMUC: {
        GET_DANHMUCS: `${BASE_URL}/api/v1/staff/danh-muc/list`,
        GET_BY_MADANHMUC: (maDanhMuc: number) =>`${BASE_URL}/api/v1/staff/danh-muc/maDanhMuc=${maDanhMuc}`,
        ADD: `${BASE_URL}/api/v1/staff/danh-muc/add`,
        UPDATE: (maDanhMuc: number) => `${BASE_URL}/api/v1/staff/danh-muc/edit/${maDanhMuc}`,
        DELETE: (maDanhMuc: number) => `${BASE_URL}/api/v1/staff/danh-muc/delete/${maDanhMuc}`,
    },
    DANHMUCCON: {
        GET_DANHMUCCONS: `${BASE_URL}/api/v1/staff/danh-muc-con/list`,
        GET_DANHMUCCONS_BY_MADANHMUC: (maDanhMuc: number) =>`${BASE_URL}/api/v1/staff/danh-muc-con/maDanhMuc=${maDanhMuc}`,
        GET_BY_MADANHMUCCON: (maDanhMucCon: number) =>`${BASE_URL}/api/v1/staff/danh-muc-con/maDanhMucCon=${maDanhMucCon}`,
        ADD: `${BASE_URL}/api/v1/staff/danh-muc-con/add`,
        UPDATE: (maDanhMucCon: number) => `${BASE_URL}/api/v1/staff/danh-muc-con/edit/${maDanhMucCon}`,
        DELETE: (maDanhMucCon: number) => `${BASE_URL}/api/v1/staff/danh-muc-con/delete/${maDanhMucCon}`,
    },
    
}
