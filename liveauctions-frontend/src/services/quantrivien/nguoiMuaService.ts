import { API_ENDPOINTS } from '@/config/apiConfig'
import axios from 'axios'
import { getToken } from '../localStorageService'

export const getNguoiMuaByMaNguoiDung = async (maNguoiDung: number) => {
    const response = await axios.get(API_ENDPOINTS.NGUOIMUA.GET_BY_MANGUOIDUNG(maNguoiDung), {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

export const addNguoiMua = async (formData: FormData) => {
    try {
        const response = await axios.post(API_ENDPOINTS.NGUOIMUA.ADD, formData, {
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

export const updateNguoiMua = async (maNguoiMua: number, formData: FormData) => {
    try {
        const response = await axios.put(API_ENDPOINTS.NGUOIMUA.UPDATE(maNguoiMua), formData, {
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

export const deleteNguoiMua = async (maNguoiMua: number) => {
    try {
        const response = await axios.put(
            API_ENDPOINTS.NGUOIMUA.DELETE(maNguoiMua),
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

export const banNguoiMua = async (maNguoiMua: number) => {
    try {
        const response = await axios.put(
            API_ENDPOINTS.NGUOIMUA.BAN(maNguoiMua),
            {},
            {
                headers: {
                    Authorization: `Bearer ${getToken()}`
                }
            }
        )

        if (response.data.code === 200) {
            return { success: true, message: 'Cấm thành công' }
        } else {
            return { success: false, message: 'Cấm thất bại' }
        }
    } catch (error) {
        console.error('Error deleting user:', error)
        return { success: false, message: 'Có lỗi khi xóa' }
    }
}
