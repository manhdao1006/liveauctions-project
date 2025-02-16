import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import { getNguoiDungByMaNguoiDung } from './services/authService'
import { getMaNguoiDung } from './services/localStorageService'
import DangKy from './components/auth/DangKy.vue'
import DangNhap from './components/auth/DangNhap.vue'
import ThemMoiNhaThamDinhView from './views/quantrivien/nhathamdinh/ThemMoiNhaThamDinhView.vue'
import DanhSachNhaThamDinhView from './views/quantrivien/nhathamdinh/DanhSachNhaThamDinhView.vue'
import CapNhatNhaThamDinhView from './views/quantrivien/nhathamdinh/CapNhatNhaThamDinhView.vue'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/dang-ky',
        name: 'DangKy',
        component: DangKy,
    },
    {
        path: '/dang-nhap',
        name: 'DangNhap',
        component: DangNhap,
    },
    {
        path: '/quan-tri/nha-tham-dinh/danh-sach',
        name: 'DanhSachNhaThamDinhView',
        component: DanhSachNhaThamDinhView,
        meta: { requiresRole: 'ROLE_ADMIN' },
    },
    {
        path: '/quan-tri/nha-tham-dinh/them-moi',
        name: 'ThemMoiNhaThamDinhView',
        component: ThemMoiNhaThamDinhView,
        meta: { requiresRole: 'ROLE_ADMIN' },
    },
    {
        path: '/quan-tri/nha-tham-dinh/cap-nhat/:maNhaThamDinh',
        name: 'CapNhatNhaThamDinhView',
        component: CapNhatNhaThamDinhView,
        meta: { requiresRole: 'ROLE_ADMIN' },
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

router.beforeEach(async (to, from, next) => {
    if (!to.meta.requiresRole) {
        return next()
    }

    try {
        const result = await getNguoiDungByMaNguoiDung(getMaNguoiDung())
        const hasVaiTroQuanTri = result.vaiTro.tenVaiTro.includes('ROLE_ADMIN')

        if (!hasVaiTroQuanTri) {
            return next('/')
        }

        next()
    } catch (error) {
        console.error('Lỗi khi lấy thông tin người dùng:', error)
        next('/')
    }
})

export default router
