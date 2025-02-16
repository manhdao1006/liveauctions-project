export const useDate = (dateTime: string) => {
    const date = new Date(dateTime)
    return new Intl.DateTimeFormat('vi-VN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
    }).format(date)
}
