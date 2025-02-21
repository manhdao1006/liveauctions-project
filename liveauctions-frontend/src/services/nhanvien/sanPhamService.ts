import { API_ENDPOINTS } from '@/config/apiConfig'
import axios from 'axios'
import { getToken } from '../localStorageService'

export const getSanPhams = async () => {
    const response = await axios.get(API_ENDPOINTS.SANPHAM.GET_SANPHAMS, {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

export const getSanPhamByMaSanPham = async (maSanPham: string) => {
    const response = await axios.get(API_ENDPOINTS.SANPHAM.GET_SANPHAM_BY_MASANPHAM(maSanPham), {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

export const addSanPham = async (formData: FormData) => {
    try {
        const response = await axios.post(API_ENDPOINTS.SANPHAM.ADD, formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
                Authorization: `Bearer ${getToken()}`
            }
        })

        if (response.data.code === 200) {
            return { success: true, message: 'Thêm mới thành công' }
        } else {
            return { success: false, message: 'Thêm mới thất bại' }
        }
    } catch (error) {
        console.error('Error adding user:', error)
        return { success: false, message: 'Có lỗi khi thêm mới' }
    }
}

export const updateSanPham = async (maSanPham: string, formData: FormData) => {
    try {
        const response = await axios.put(API_ENDPOINTS.SANPHAM.UPDATE(maSanPham), formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
                Authorization: `Bearer ${getToken()}`
            }
        })

        if (response.data.code === 200) {
            return { success: true, message: response.data.result }
        } else {
            return { success: false, message: 'Email đã tồn tại!' }
        }
    } catch (error) {
        console.error('Error updating user:', error)
        return { success: false, message: 'Email đã tồn tại!' }
    }
}

export const deleteSanPham = async (maSanPham: string) => {
    try {
        const response = await axios.put(
            API_ENDPOINTS.SANPHAM.DELETE(maSanPham),
            {},
            {
                headers: {
                    Authorization: `Bearer ${getToken()}`
                }
            }
        )

        if (response.data.code === 200) {
            return { success: true, message: 'Xóa thành công' }
        } else {
            return { success: false, message: 'Xóa thất bại' }
        }
    } catch (error) {
        console.error('Error deleting user:', error)
        return { success: false, message: 'Có lỗi khi xóa' }
    }
}
