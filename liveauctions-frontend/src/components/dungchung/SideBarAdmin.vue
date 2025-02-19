<template>
    <nav id="sidebar" class="sidebar js-sidebar">
        <div class="sidebar-content js-simplebar">
            <a class="sidebar-brand text-center" href="/">
                <img
                    class=""
                    src="https://res.cloudinary.com/springboot-cloud/image/upload/v1739451101/E-_AUTIONEER-removebg-preview_savimy.png"
                    alt="logo-BSR"
                    width="150px"
                    height="150px"
                />
            </a>

            <ul class="sidebar-nav">
                <li class="sidebar-header">Quản lý</li>

                <li class="sidebar-item">
                    <router-link class="sidebar-link" :to="{ name: 'DashBoardView' }">
                        <i class="fa-solid fa-gauge-high"></i>
                        <span class="align-middle">Dashboard</span>
                    </router-link>
                </li>

                <li class="sidebar-item">
                    <router-link
                        class="sidebar-link"
                        :to="{ name: 'DanhSachNguoiDungView', params: { maVaiTro: 2 } }"
                    >
                        <i class="fas fa-users"></i>
                        <span class="align-middle">Người dùng</span>
                    </router-link>
                </li>

                <li class="sidebar-item">
                    <router-link class="sidebar-link" :to="{ name: 'DanhSachDanhMucView' }">
                        <i class="fa-solid fa-layer-group"></i>
                        <span class="align-middle">Danh mục</span>
                    </router-link>
                </li>

                <li class="sidebar-item">
                    <router-link class="sidebar-link" :to="{ name: 'DanhSachNhaKhoView' }">
                        <i class="fa-solid fa-warehouse"></i>
                        <span class="align-middle">Nhà kho</span>
                    </router-link>
                </li>

                <li class="sidebar-item">
                    <router-link class="sidebar-link" :to="{ name: 'DanhSachNhaThamDinhView' }">
                        <i class="fa-solid fa-user-tie"></i>
                        <span class="align-middle">Nhà thẩm định</span>
                    </router-link>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="/admin/posts">
                        <i class="fa-solid fa-gavel"></i>
                        <span class="align-middle">Phiên đấu giá</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="/admin/dashboard">
                        <i class="fa-solid fa-pen-to-square"></i>
                        <span class="align-middle">Sản phẩm đăng ký</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="/admin/dashboard">
                        <i class="fa-solid fa-database"></i>
                        <span class="align-middle">Sản phẩm</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="/admin/dashboard">
                        <i class="fa-brands fa-shopify"></i>
                        <span class="align-middle">Sản phẩm đấu giá</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="/admin/dashboard">
                        <i class="fa-solid fa-clipboard-list"></i>
                        <span class="align-middle">Lịch sử đấu giá</span>
                    </a>
                </li>

                <li class="sidebar-header">Cài đặt</li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="#!">
                        <i class="fas fa-bell"></i>
                        <span class="align-middle">Thông báo</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="#!">
                        <i class="fas fa-globe"></i>
                        <span class="align-middle">Ngôn ngữ</span>
                        <i class="fas fa-chevron-down"></i>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script lang="ts">
    import { defineComponent } from 'vue'

    export default defineComponent({
        name: 'SideBarAdmin'
    })
</script>

<style>
    .sidebar,
    .sidebar-content {
        background: #ffece1;
        transition: margin-left 0.35s ease-in-out, left 0.35s ease-in-out,
            margin-right 0.35s ease-in-out, right 0.35s ease-in-out;
        max-height: 120vh;
        overflow-y: auto;
        scrollbar-width: thin;
        scrollbar-color: #6c757d transparent;
    }
    .sidebar-content::-webkit-scrollbar {
        width: 6px;
    }

    .sidebar-content::-webkit-scrollbar-thumb {
        background-color: #6c757d;
        border-radius: 3px;
    }
    .sidebar {
        direction: ltr;
        max-width: 200px;
        min-width: 200px;
    }
    .sidebar-brand {
        color: #000000;
        display: block;
        font-size: 1.15rem;
        font-weight: 600;
        padding: 10px 8px;
    }
    .sidebar-nav {
        flex-grow: 1;
        list-style: none;
        margin-bottom: 0;
        padding-left: 0;
    }
    .sidebar-header {
        background: transparent;
        color: #000000;
        font-size: 12px;
        padding: 1.5rem 1.5rem 0.375rem;
    }
    .sidebar-link,
    a.sidebar-link {
        background: #ffece1;
        border-left: 3px solid transparent;
        color: #000000;
        cursor: pointer;
        display: block;
        font-weight: 400;
        padding: 5px 8px;
        position: relative;
        text-decoration: none;
        font-size: 13px;
    }
    .sidebar-link span {
        line-height: 30px;
        margin: 0px 40px 0px 10px;
    }
    .sidebar-link:hover,
    .sidebar-link:hover i {
        color: #ff8435;
    }
</style>
