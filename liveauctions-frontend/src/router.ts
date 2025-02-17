import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import { getNguoiDungByMaNguoiDung } from './services/authService'
import { getMaNguoiDung } from './services/localStorageService'
import DangKy from './components/auth/DangKy.vue'
import DangNhap from './components/auth/DangNhap.vue'
import ThemMoiNhaThamDinhView from './views/quantrivien/nhathamdinh/ThemMoiNhaThamDinhView.vue'
import DanhSachNhaThamDinhView from './views/quantrivien/nhathamdinh/DanhSachNhaThamDinhView.vue'
import CapNhatNhaThamDinhView from './views/quantrivien/nhathamdinh/CapNhatNhaThamDinhView.vue'
import DanhSachDanhMucView from './views/quantrivien/danhmuc/DanhSachDanhMucView.vue'
import ThemMoiDanhMucView from './views/quantrivien/danhmuc/ThemMoiDanhMucView.vue'
import CapNhatDanhMucView from './views/quantrivien/danhmuc/CapNhatDanhMucView.vue'
import DanhSachNhaKhoView from './views/quantrivien/nhakho/DanhSachNhaKhoView.vue'
import ThemMoiNhaKhoView from './views/quantrivien/nhakho/ThemMoiNhaKhoView.vue'
import CapNhatNhaKhoView from './views/quantrivien/nhakho/CapNhatNhaKhoView.vue'

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
        path: '/quan-tri/danh-muc/danh-sach',
        name: 'DanhSachDanhMucView',
        component: DanhSachDanhMucView,
        meta: { requiresRole: 'ROLE_ADMIN' },
    },
    {
        path: '/quan-tri/danh-muc/them-moi',
        name: 'ThemMoiDanhMucView',
        component: ThemMoiDanhMucView,
        meta: { requiresRole: 'ROLE_ADMIN' },
    },
    {
        path: '/quan-tri/danh-muc/cap-nhat/:maDanhMuc',
        name: 'CapNhatDanhMucView',
        component: CapNhatDanhMucView,
        meta: { requiresRole: 'ROLE_ADMIN' },
    },
    {
        path: '/quan-tri/nha-kho/danh-sach',
        name: 'DanhSachNhaKhoView',
        component: DanhSachNhaKhoView,
        meta: { requiresRole: 'ROLE_ADMIN' },
    },
    {
        path: '/quan-tri/nha-kho/them-moi',
        name: 'ThemMoiNhaKhoView',
        component: ThemMoiNhaKhoView,
        meta: { requiresRole: 'ROLE_ADMIN' },
    },
    {
        path: '/quan-tri/nha-kho/cap-nhat/:maNhaKho',
        name: 'CapNhatNhaKhoView',
        component: CapNhatNhaKhoView,
        meta: { requiresRole: 'ROLE_ADMIN' },
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
