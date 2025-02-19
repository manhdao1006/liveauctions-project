import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import DangKy from './components/auth/DangKy.vue'
import DangNhap from './components/auth/DangNhap.vue'
import { getNguoiDungByMaNguoiDung } from './services/authService'
import { getMaNguoiDung } from './services/localStorageService'
import CapNhatDanhMucView from './views/nhanvien/danhmuc/CapNhatDanhMucView.vue'
import DanhSachDanhMucView from './views/nhanvien/danhmuc/DanhSachDanhMucView.vue'
import ThemMoiDanhMucView from './views/nhanvien/danhmuc/ThemMoiDanhMucView.vue'
import CapNhatDanhMucConView from './views/nhanvien/danhmuccon/CapNhatDanhMucConView.vue'
import DanhSachDanhMucConView from './views/nhanvien/danhmuccon/DanhSachDanhMucConView.vue'
import DashBoardView from './views/quantrivien/DashBoardView.vue'
import DanhSachNguoiDungView from './views/quantrivien/nguoidung/DanhSachNguoiDungView.vue'
import CapNhatNguoiBanView from './views/quantrivien/nguoidung/nguoiban/CapNhatNguoiBanView.vue'
import ThemMoiNguoiBanView from './views/quantrivien/nguoidung/nguoiban/ThemMoiNguoiBanView.vue'
import CapNhatNguoiMuaView from './views/quantrivien/nguoidung/nguoimua/CapNhatNguoiMuaView.vue'
import ThemMoiNguoiMuaView from './views/quantrivien/nguoidung/nguoimua/ThemMoiNguoiMuaView.vue'
import CapNhatNhanVienView from './views/quantrivien/nguoidung/nhanvien/CapNhatNhanVienView.vue'
import ThemMoiNhanVienView from './views/quantrivien/nguoidung/nhanvien/ThemMoiNhanVienView.vue'
import CapNhatNhaKhoView from './views/quantrivien/nhakho/CapNhatNhaKhoView.vue'
import DanhSachNhaKhoView from './views/quantrivien/nhakho/DanhSachNhaKhoView.vue'
import ThemMoiNhaKhoView from './views/quantrivien/nhakho/ThemMoiNhaKhoView.vue'
import CapNhatNhaThamDinhView from './views/quantrivien/nhathamdinh/CapNhatNhaThamDinhView.vue'
import DanhSachNhaThamDinhView from './views/quantrivien/nhathamdinh/DanhSachNhaThamDinhView.vue'
import ThemMoiNhaThamDinhView from './views/quantrivien/nhathamdinh/ThemMoiNhaThamDinhView.vue'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/dang-ky',
        name: 'DangKy',
        component: DangKy
    },
    {
        path: '/dang-nhap',
        name: 'DangNhap',
        component: DangNhap
    },
    {
        path: '/quan-tri/dashboard',
        name: 'DashBoardView',
        component: DashBoardView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nguoi-dung/danh-sach/:maVaiTro',
        name: 'DanhSachNguoiDungView',
        component: DanhSachNguoiDungView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nguoi-dung/them-moi/:maVaiTro',
        name: 'ThemMoiNhanVienView',
        component: ThemMoiNhanVienView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nguoi-dung/them-moi/:maVaiTro',
        name: 'ThemMoiNguoiBanView',
        component: ThemMoiNguoiBanView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nguoi-dung/them-moi/:maVaiTro',
        name: 'ThemMoiNguoiMuaView',
        component: ThemMoiNguoiMuaView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nguoi-dung/cap-nhat/:maVaiTro/:maNguoiDung',
        name: 'CapNhatNhanVienView',
        component: CapNhatNhanVienView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nguoi-dung/cap-nhat/:maVaiTro/:maNguoiDung',
        name: 'CapNhatNguoiBanView',
        component: CapNhatNguoiBanView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nguoi-dung/cap-nhat/:maVaiTro/:maNguoiDung',
        name: 'CapNhatNguoiMuaView',
        component: CapNhatNguoiMuaView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/danh-muc/danh-sach',
        name: 'DanhSachDanhMucView',
        component: DanhSachDanhMucView,
        meta: { requiresRole: ['ROLE_ADMIN', 'ROLE_STAFF'] }
    },
    {
        path: '/quan-tri/danh-muc/them-moi',
        name: 'ThemMoiDanhMucView',
        component: ThemMoiDanhMucView,
        meta: { requiresRole: ['ROLE_ADMIN', 'ROLE_STAFF'] }
    },
    {
        path: '/quan-tri/danh-muc/cap-nhat/:maDanhMuc',
        name: 'CapNhatDanhMucView',
        component: CapNhatDanhMucView,
        meta: { requiresRole: ['ROLE_ADMIN', 'ROLE_STAFF'] }
    },
    {
        path: '/quan-tri/danh-muc-con/danh-sach/:maDanhMuc',
        name: 'DanhSachDanhMucConView',
        component: DanhSachDanhMucConView,
        meta: { requiresRole: ['ROLE_ADMIN', 'ROLE_STAFF'] }
    },
    {
        path: '/quan-tri/danh-muc-con/cap-nhat/:maDanhMuc/:maDanhMucCon',
        name: 'CapNhatDanhMucConView',
        component: CapNhatDanhMucConView,
        meta: { requiresRole: ['ROLE_ADMIN', 'ROLE_STAFF'] }
    },
    {
        path: '/quan-tri/nha-kho/danh-sach',
        name: 'DanhSachNhaKhoView',
        component: DanhSachNhaKhoView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nha-kho/them-moi',
        name: 'ThemMoiNhaKhoView',
        component: ThemMoiNhaKhoView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nha-kho/cap-nhat/:maNhaKho',
        name: 'CapNhatNhaKhoView',
        component: CapNhatNhaKhoView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nha-tham-dinh/danh-sach',
        name: 'DanhSachNhaThamDinhView',
        component: DanhSachNhaThamDinhView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nha-tham-dinh/them-moi',
        name: 'ThemMoiNhaThamDinhView',
        component: ThemMoiNhaThamDinhView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    },
    {
        path: '/quan-tri/nha-tham-dinh/cap-nhat/:maNhaThamDinh',
        name: 'CapNhatNhaThamDinhView',
        component: CapNhatNhaThamDinhView,
        meta: { requiresRole: 'ROLE_ADMIN' }
    }
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
        const hasVaiTroQuanTri =
            result.vaiTro.tenVaiTro.includes('ROLE_ADMIN') ||
            result.vaiTro.tenVaiTro.includes('ROLE_STAFF')

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
