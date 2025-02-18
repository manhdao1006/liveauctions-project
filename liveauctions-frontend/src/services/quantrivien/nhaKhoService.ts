import { API_ENDPOINTS } from '@/config/apiConfig'
import axios from 'axios'
import { getToken } from '../localStorageService'

export const getNhaKhos = async () => {
    const response = await axios.get(API_ENDPOINTS.NHAKHO.GET_NHAKHOS, {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

export const getNhaKhoByMaNhaKho = async (maNhaKho: number) => {
    const response = await axios.get(API_ENDPOINTS.NHAKHO.GET_BY_MANHAKHO(maNhaKho), {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

export const addNhaKho = async (formData: FormData) => {
    try {
        const response = await axios.post(API_ENDPOINTS.NHAKHO.ADD, formData, {
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

export const updateNhaKho = async (maNhaKho: number, formData: FormData) => {
    try {
        const response = await axios.put(API_ENDPOINTS.NHAKHO.UPDATE(maNhaKho), formData, {
            headers: {
                'Content-Type': 'application/json',
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

export const deleteNhaKho = async (maNhaKho: number) => {
    try {
        const response = await axios.put(
            API_ENDPOINTS.NHAKHO.DELETE(maNhaKho),
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
