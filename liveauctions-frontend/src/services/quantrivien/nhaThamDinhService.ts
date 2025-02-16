import { API_ENDPOINTS } from '@/config/apiConfig'
import { getToken } from '../localStorageService'
import axios from 'axios'

export const getNhaThamDinhs = async () => {
    const response = await axios.get(API_ENDPOINTS.NHATHAMDINH.GET_NHATHAMDINHS, {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

// export const getNhaThamDinhs = async (page: number) => {
//     const response = await axios.get(API_ENDPOINTS.NHATHAMDINH.GET_NHATHAMDINHS, {
//         headers: {
//             Authorization: `Bearer ${getToken()}`
//         },
//         params: {
//             page: page,
//             size: 12
//         }
//     })

//     return response.data.result
// }

export const getNhaThamDinhByMaNhaThamDinh = async (maNhaThamDinh: number) => {
    const response = await axios.get(API_ENDPOINTS.NHATHAMDINH.GET_BY_MANHATHAMDINH(maNhaThamDinh), {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}

export const addNhaThamDinh = async (formData: FormData) => {
    try {
        const response = await axios.post(API_ENDPOINTS.NHATHAMDINH.ADD, formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
                Authorization: `Bearer ${getToken()}`
            }
        })

        if (response.data.code === 200) {
            return { success: true, message: 'Thêm mới người dùng thành công' }
        } else {
            return { success: false, message: 'Thêm mới người dùng thất bại' }
        }
    } catch (error) {
        console.error('Error adding user:', error)
        return { success: false, message: 'Có lỗi khi thêm mới người dùng' }
    }
}

export const updateNhaThamDinh = async (maNhaThamDinh: number, formData: FormData) => {
    try {
        const response = await axios.put(API_ENDPOINTS.NHATHAMDINH.UPDATE(maNhaThamDinh), formData, {
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

export const deleteNhaThamDinh = async (maNhaThamDinh: number) => {
    try {
        const response = await axios.put(
            API_ENDPOINTS.NHATHAMDINH.DELETE(maNhaThamDinh),
            {},
            {
                headers: {
                    Authorization: `Bearer ${getToken()}`
                }
            }
        )

        if (response.data.code === 200) {
            return { success: true, message: 'Xóa người dùng thành công' }
        } else {
            return { success: false, message: 'Xóa người dùng thất bại' }
        }
    } catch (error) {
        console.error('Error deleting user:', error)
        return { success: false, message: 'Có lỗi khi xóa người dùng' }
    }
}
