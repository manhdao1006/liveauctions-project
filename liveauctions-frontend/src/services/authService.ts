import { apiClient } from '@/config/apiClient'
import { API_ENDPOINTS } from '@/config/apiConfig'
import axios from 'axios'
import {
    getToken,
    removeRefreshToken,
    removeToken,
    setMaNguoiDung,
    setRefreshToken,
    setToken
} from './localStorageService'

export const getNguoiDungByMaNguoiDung = async (maNguoiDung: number) => {
    const response = await apiClient.get(API_ENDPOINTS.NGUOIDUNG.GET_BY_MANGUOIDUNG(maNguoiDung), {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

export const fetchNguoiDung = async () => {
    const response = await apiClient.get(API_ENDPOINTS.NGUOIDUNG.GET_BY_EMAIL, {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })
    return response.data.result.maNguoiDung
}

export const dangNhap = async (email: string, matKhau: string) => {
    try {
        const response = await apiClient.post(API_ENDPOINTS.AUTH.DANGNHAP, {
            email,
            matKhau
        })

        if (response.data.code === 200) {
            setToken(response.data.result.accessToken)
            setRefreshToken(response.data.result.refreshToken)
            const maNguoiDung = await fetchNguoiDung()
            if (maNguoiDung) {
                setMaNguoiDung(maNguoiDung)
            }
            return { success: true, message: 'Đăng nhập thành công!' }
        } else {
            return { success: false, message: 'Tên đăng nhập hoặc mật khẩu không đúng!' }
        }
    } catch (error) {
        console.log(error)
        return { success: false, message: 'Tên đăng nhập hoặc mật khẩu không đúng!' }
    }
}

export const dangXuat = () => {
    removeToken()
    removeRefreshToken()
}

export const dangKy = async (email: string, matKhau: string, hoVaTen: string) => {
    const response = await apiClient.post(API_ENDPOINTS.AUTH.DANGKY, {
        email,
        matKhau,
        hoVaTen
    })

    if (response.data.code === 200) {
        return { success: true, message: 'Đăng ký thành công!' }
    } else {
        return { success: false, message: 'Tên đăng nhập hoặc email đã tồn tại!' }
    }
}

export const getQuanHuyens = async () => {
    const response = await axios.get(API_ENDPOINTS.AUTH.GET_QUANHUYENS)

    return response.data.result
}

export const getQuanHuyenByMaPhuongXa = async (maPhuongXa: number) => {
    const response = await axios.get(API_ENDPOINTS.AUTH.GET_QUANHUYEN_BY_MAPHUONGXA(maPhuongXa))

    return response.data.result
}

export const getPhuongXasByMaQuanHuyen = async (maQuanHuyen: number) => {
    const response = await axios.get(API_ENDPOINTS.AUTH.GET_PHUONGXAS_BY_MAQUANHUYEN(maQuanHuyen))

    return response.data.result
}

export const getPhuongXas = async () => {
    const response = await axios.get(API_ENDPOINTS.AUTH.GET_PHUONGXAS)

    return response.data.result
}
