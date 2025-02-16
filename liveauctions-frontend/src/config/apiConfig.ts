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
    POSTS: {
        GET_POSTS: `${BASE_URL}/api/posts/`,
        GET_BY_ID: (postId: number) => `${BASE_URL}/api/posts/view/${postId}`,
        ADD_POST: `${BASE_URL}/api/posts/create`,
        UPDATE_POST: (postId: number) => `${BASE_URL}/api/posts/edit/${postId}`,
        DELETE_POST: (postId: number) => `${BASE_URL}/api/posts/soft-delete/${postId}`,
        SEARCH_POST: `${BASE_URL}/api/posts/search`,
        SORT_POST_LATEST: `${BASE_URL}/api/posts/latest`,
        SORT_POST_OLDEST: `${BASE_URL}/api/posts/oldest`,
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
    
}
