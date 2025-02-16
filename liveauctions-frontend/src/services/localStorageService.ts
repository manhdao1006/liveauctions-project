export const KEY_TOKEN = 'accessToken'
export const KEY_REFRESH_TOKEN = 'refreshToken'

export const setToken = (token: string) => {
    localStorage.setItem(KEY_TOKEN, token)
}

export const getToken = () => {
    return localStorage.getItem(KEY_TOKEN)
}

export const removeToken = () => {
    return localStorage.removeItem(KEY_TOKEN)
}

export const setMaNguoiDung = (maNguoiDung: number) => {
    localStorage.setItem('maNguoiDung', maNguoiDung.toString())
}

export const getMaNguoiDung = () => {
    return Number(localStorage.getItem('maNguoiDung'))
}

export const setRefreshToken = (token: string) => {
    localStorage.setItem(KEY_REFRESH_TOKEN, token)
}

export const getRefreshToken = () => {
    return localStorage.getItem(KEY_REFRESH_TOKEN)
}

export const removeRefreshToken = () => {
    return localStorage.removeItem(KEY_REFRESH_TOKEN)
}
