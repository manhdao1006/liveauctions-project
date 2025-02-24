import { API_ENDPOINTS } from '@/config/apiConfig'
import axios from 'axios'

export const getSanPhamDauGiasUpcoming = async () => {
    const response = await axios.get(API_ENDPOINTS.SANPHAMDAUGIA.GET_SANPHAMDAUGIAS_UPCOMING)

    return response.data.result
}

export const getSanPhamDauGiasTrending = async () => {
    const response = await axios.get(API_ENDPOINTS.SANPHAMDAUGIA.GET_SANPHAMDAUGIAS_TRENDING)

    return response.data.result
}

export const getSanPhamDauGiasKin = async () => {
    const response = await axios.get(API_ENDPOINTS.SANPHAMDAUGIA.GET_SANPHAMDAUGIAS_KIN)

    return response.data.result
}

export const getSanPhamDauGiasOnline = async () => {
    const response = await axios.get(API_ENDPOINTS.SANPHAMDAUGIA.GET_SANPHAMDAUGIAS_ONLINE)

    return response.data.result
}
