import { API_ENDPOINTS } from '@/config/apiConfig'
import axios from 'axios'
import { getToken } from '../localStorageService'

export const getLoaiDauGias = async () => {
    const response = await axios.get(API_ENDPOINTS.LOAIDAUGIA.GET_LOAIDAUGIAS, {
        headers: {
            Authorization: `Bearer ${getToken()}`
        }
    })

    return response.data.result
}
