import { API_ENDPOINTS } from '@/config/apiConfig'
import axios from 'axios'
import { getToken } from '../localStorageService'

export const getPhienDauGias = async () => {
    const response = await axios.get(API_ENDPOINTS.PHIENDAUGIA.GET_PHIENDAUGIAS, {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

export const getPhienDauGiaByMaPhienDauGia = async (maPhienDauGia: number) => {
    const response = await axios.get(
        API_ENDPOINTS.PHIENDAUGIA.GET_BY_MAPHIENDAUGIA(maPhienDauGia),
        {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        }
    )

    return response.data.result
}

export const addPhienDauGia = async (formData: FormData) => {
    try {
        const response = await axios.post(API_ENDPOINTS.PHIENDAUGIA.ADD, formData, {
            headers: {
                'Content-Type': 'application/json',
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

export const updatePhienDauGia = async (maPhienDauGia: number, formData: FormData) => {
    try {
        const response = await axios.put(
            API_ENDPOINTS.PHIENDAUGIA.UPDATE(maPhienDauGia),
            formData,
            {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${getToken()}`
                }
            }
        )

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

export const deletePhienDauGia = async (maPhienDauGia: number) => {
    try {
        const response = await axios.put(
            API_ENDPOINTS.PHIENDAUGIA.DELETE(maPhienDauGia),
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
