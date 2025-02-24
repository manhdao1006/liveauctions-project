import { API_ENDPOINTS } from '@/config/apiConfig'
import axios from 'axios'
import { getToken } from '../localStorageService'

export const getNavigations = async () => {
    const response = await axios.get(API_ENDPOINTS.DANHMUC.GET_NAVIGATIONS)

    return response.data.result
}

export const getDanhMucs = async () => {
    const response = await axios.get(API_ENDPOINTS.DANHMUC.GET_DANHMUCS, {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

export const getDanhMucByMaDanhMuc = async (maDanhMuc: number) => {
    const response = await axios.get(API_ENDPOINTS.DANHMUC.GET_BY_MADANHMUC(maDanhMuc), {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

export const getDanhMucByMaDanhMucCon = async (maDanhMucCon: number) => {
    const response = await axios.get(API_ENDPOINTS.DANHMUC.GET_BY_MADANHMUCCON(maDanhMucCon), {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

export const addDanhMuc = async (formData: FormData) => {
    try {
        const response = await axios.post(API_ENDPOINTS.DANHMUC.ADD, formData, {
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

export const updateDanhMuc = async (maDanhMuc: number, formData: FormData) => {
    try {
        const response = await axios.put(API_ENDPOINTS.DANHMUC.UPDATE(maDanhMuc), formData, {
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

export const deleteDanhMuc = async (maDanhMuc: number) => {
    try {
        const response = await axios.put(
            API_ENDPOINTS.DANHMUC.DELETE(maDanhMuc),
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
