<template>
    <nav class="navbar navbar-expand navbar-light navbar-bg">
        <div class="navbar-collapse collapse">
            <p class="ms-5 mb-0" style="color: #81561d; font-weight: 600">
                E - AUCTIONEER * NHÀ ĐẤU GIÁ CHUYÊN NGHIỆP *
            </p>
            <ul class="navbar-nav navbar-align">
                <li class="nav-item dropdown pt-2">
                    <a class="nav-icon" href="#!">
                        <div class="">
                            <i class="far fa-comment-alt"></i>
                        </div>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a
                        class="nav-link dropdown-toggle d-none d-sm-inline-block"
                        href="#"
                        data-bs-toggle="dropdown"
                        v-if="isLoggedIn"
                    >
                        <img
                            :src="
                                (nguoiDung as Record<string, unknown>)?.avatar as string ||
                                'https://res.cloudinary.com/springboot-cloud/image/upload/v1739427632/user_vqmka8.png'
                            "
                            class="avatar img-fluid rounded me-1"
                            alt=""
                        />
                        <span class="text-dark">{{ nguoiDung?.hoVaTen }}</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-end">
                        <a class="dropdown-item" href="#!">Thông tin cá nhân</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#!">Cài đặt & Riêng tư</a>
                        <a class="dropdown-item" href="#!">Trợ giúp</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="" @click="handleDangXuat">Đăng xuất</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script lang="ts">
    import { dangXuat, getNguoiDungByMaNguoiDung } from '@/services/authService'
    import { getMaNguoiDung } from '@/services/localStorageService'
    import { defineComponent, inject, onMounted, Ref, ref } from 'vue'
    import { useRouter } from 'vue-router'

    export default defineComponent({
        name: 'HeaderAdmin',
        setup() {
            const router = useRouter()
            const nguoiDung: Ref<Record<string, unknown> | undefined> = ref(undefined)
            const isLoggedIn = inject('isLoggedIn') as Ref<boolean> | undefined

            onMounted(async () => {
                const data = await getNguoiDungByMaNguoiDung(getMaNguoiDung())
                nguoiDung.value = data.nguoiDung
            })

            const handleDangXuat = () => {
                if (isLoggedIn) {
                    isLoggedIn.value = false
                }
                localStorage.removeItem('isLoggedIn')
                localStorage.removeItem('maNguoiDung')
                router.push('/trang-chu')
                dangXuat()
            }

            return { nguoiDung, isLoggedIn, handleDangXuat }
        }
    })
</script>

<style>
    .navbar-bg {
        background: #ffece1;
    }
    .navbar {
        border-bottom: 0;
        box-shadow: 0 0 2rem 0 rgba(33, 37, 41, 0.1);
    }
    .navbar-nav {
        direction: ltr;
    }
    .navbar-align {
        margin-left: auto;
    }
    .nav-icon {
        color: #000000;
    }
    .navbar .avatar {
        margin-bottom: -15px;
        margin-top: -15px;
    }
    .avatar {
        height: 30px;
        width: 30px;
    }
</style>
