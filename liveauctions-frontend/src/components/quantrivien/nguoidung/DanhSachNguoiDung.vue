<template>
    <div class="container-fluid">
        <div class="col-12 col-lg-12 col-xxl-12 d-flex">
            <div class="card flex-fill">
                <div class="row justify-content-center">
                    <div class="btn-group">
                        <router-link
                            :to="{
                                name: 'DanhSachNguoiDungView',
                                params: { maVaiTro: 2 }
                            }"
                            class="btn btn-primary text-uppercase border-end"
                            :class="{ active: maVaiTroPrams === 2 }"
                            aria-current="page"
                            >Nhân viên</router-link
                        >
                        <router-link
                            :to="{
                                name: 'DanhSachNguoiDungView',
                                params: { maVaiTro: 3 }
                            }"
                            class="btn btn-primary text-uppercase border-start border-end"
                            :class="{ active: maVaiTroPrams === 3 }"
                            >Người bán</router-link
                        >
                        <router-link
                            :to="{
                                name: 'DanhSachNguoiDungView',
                                params: { maVaiTro: 4 }
                            }"
                            class="btn btn-primary text-uppercase border-start"
                            :class="{ active: maVaiTroPrams === 4 }"
                            >Người mua</router-link
                        >
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <SearchComponent class="w-100" v-model="keyword" />
                    </div>
                    <div class="col-6 text-end pt-2 pe-4">
                        <div class="dropdown">
                            <a
                                class="btn btn-outline-dark dropdown-toggle"
                                href="#"
                                role="button"
                                id="dropdownMenuLink"
                                data-bs-toggle="dropdown"
                                aria-expanded="false"
                            >
                                Mặc định
                            </a>

                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <li>
                                    <button class="dropdown-item">Người dùng</button>
                                </li>
                                <li>
                                    <button class="dropdown-item">Quản trị viên</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="row justify-content-evenly">
                    <div class="card-header col-xl-6">
                        <h5 class="card-title mb-0">Danh sách người dùng</h5>
                    </div>
                    <div class="card-header col-xl-6 text-end">
                        <router-link
                            v-if="maVaiTroPrams === 2"
                            class="text-success"
                            :to="{
                                name: 'ThemMoiNhanVienView',
                                params: { maVaiTro: maVaiTroPrams }
                            }"
                        >
                            <i class="fas fa-plus-circle"></i>
                            <span class="ps-1">Thêm mới</span>
                        </router-link>
                        <router-link
                            v-if="maVaiTroPrams === 3"
                            class="text-success"
                            :to="{
                                name: 'ThemMoiNguoiBanView',
                                params: { maVaiTro: maVaiTroPrams }
                            }"
                        >
                            <i class="fas fa-plus-circle"></i>
                            <span class="ps-1">Thêm mới</span>
                        </router-link>
                        <router-link
                            v-if="maVaiTroPrams === 4"
                            class="text-success"
                            :to="{
                                name: 'ThemMoiNguoiMuaView',
                                params: { maVaiTro: maVaiTroPrams }
                            }"
                        >
                            <i class="fas fa-plus-circle"></i>
                            <span class="ps-1">Thêm mới</span>
                        </router-link>
                    </div>
                </div>
                <table class="table table-hover my-0">
                    <thead>
                        <tr>
                            <th class="d-none d-xl-table-cell">STT</th>
                            <th>Họ và tên</th>
                            <th class="d-none d-md-table-cell">Email</th>
                            <th>Số điện thoại</th>
                            <th class="d-none d-xl-table-cell">Địa chỉ</th>
                            <th>Trạng thái hoạt động</th>
                            <th class="d-none d-xl-table-cell">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr
                            v-for="(nguoiDung, index) in paginatedNguoiDungs"
                            :key="
                                typeof nguoiDung.maNguoiDung === 'string'
                                    ? nguoiDung.maNguoiDung
                                    : undefined
                            "
                        >
                            <td class="d-none d-xl-table-cell">
                                {{ index + 1 }}
                            </td>
                            <td>
                                {{ nguoiDung.hoVaTen }}
                            </td>
                            <td class="d-none d-md-table-cell">
                                {{ nguoiDung.email }}
                            </td>
                            <td>
                                {{ nguoiDung.soDienThoai }}
                            </td>
                            <td class="d-none d-xl-table-cell">
                                {{ nguoiDung.diaChi }}
                            </td>
                            <td>
                                {{ nguoiDung.trangThaiHoatDong }}
                            </td>
                            <td>
                                <router-link
                                    v-if="maVaiTroPrams === 2"
                                    :to="{
                                        name: 'CapNhatNhanVienView',
                                        params: {
                                            maVaiTro: maVaiTroPrams,
                                            maNguoiDung: Number(nguoiDung.maNguoiDung)
                                        }
                                    }"
                                >
                                    <i class="far fa-edit text-success" title="Cập nhật"></i
                                ></router-link>
                                <router-link
                                    v-if="maVaiTroPrams === 3"
                                    :to="{
                                        name: 'CapNhatNguoiBanView',
                                        params: {
                                            maVaiTro: maVaiTroPrams,
                                            maNguoiDung: Number(nguoiDung.maNguoiDung)
                                        }
                                    }"
                                >
                                    <i class="far fa-edit text-success" title="Cập nhật"></i
                                ></router-link>
                                <router-link
                                    v-if="maVaiTroPrams === 4"
                                    :to="{
                                        name: 'CapNhatNguoiMuaView',
                                        params: {
                                            maVaiTro: maVaiTroPrams,
                                            maNguoiDung: Number(nguoiDung.maNguoiDung)
                                        }
                                    }"
                                >
                                    <i class="far fa-edit text-success" title="Cập nhật"></i
                                ></router-link>
                                |
                                <button
                                    @click="showConfirmDeletePopup(Number(nguoiDung.maNguoiDung))"
                                    class="border-0 p-0 bg-transparent"
                                >
                                    <i class="fas fa-trash-alt text-danger" title="Xóa"></i>
                                </button>
                                |
                                <button
                                    @click="showConfirmBanPopup(Number(nguoiDung.maNguoiDung))"
                                    class="border-0 p-0 bg-transparent"
                                >
                                    <i class="fa-solid fa-ban text-warning" title="Cấm"></i>
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <PaginationComponent
            class="text-center mt-3"
            :currentPage="currentPage"
            :totalPages="totalPages"
            :totalElements="totalElements"
            :pageSize="pageSize"
            @pageChanged="onChangePage"
        />

        <PopupDelete
            :showPopup="showDeletePopup"
            @update:showPopup="showDeletePopup = $event"
            :onDelete="confirmDelete"
        />
        <PopupBan
            :showPopup="showBanPopup"
            @update:showPopup="showBanPopup = $event"
            :onBan="confirmBan"
        />
    </div>
</template>

<script lang="ts">
    import PaginationComponent from '@/components/dungchung/PaginationComponent.vue'
    import PopupBan from '@/components/dungchung/PopupBan.vue'
    import PopupDelete from '@/components/dungchung/PopupDelete.vue'
    import SearchComponent from '@/components/dungchung/SearchComponent.vue'
    import { banNguoiBan, deleteNguoiBan } from '@/services/quantrivien/nguoiBanService'
    import { getNguoiDungsByMaVaiTro } from '@/services/quantrivien/nguoiDungService'
    import { banNguoiMua, deleteNguoiMua } from '@/services/quantrivien/nguoiMuaService'
    import { banNhanVien, deleteNhanVien } from '@/services/quantrivien/nhanVienService'
    import { computed, defineComponent, onMounted, ref, Ref, watch } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    export default defineComponent({
        name: 'DanhSachNguoiDung',
        components: {
            SearchComponent,
            PaginationComponent,
            PopupDelete,
            PopupBan
        },
        setup() {
            const totalPages = computed(() => Math.ceil(nguoiDungs.value.length / pageSize.value))
            const route = useRoute()
            const router = useRouter()
            const currentPage = ref(Number(route.query.page) || 1) as Ref<number>
            const nguoiDungs: Ref<Record<string, unknown>[]> = ref([])
            const totalElements = ref() as Ref<number>
            const pageSize = ref(10) as Ref<number>
            const showDeletePopup = ref(false) as Ref<boolean>
            const showBanPopup = ref(false) as Ref<boolean>
            const nguoiDungToDelete = ref(null) as Ref<number | null>
            const nguoiDungToBan = ref(null) as Ref<number | null>
            const keyword = ref('') as Ref<string>
            const maVaiTroPrams = Number(route.params.maVaiTro)

            const paginatedNguoiDungs = computed(() => {
                const start = (currentPage.value - 1) * pageSize.value
                return nguoiDungs.value.slice(start, start + pageSize.value)
            })

            const fetchNguoiDungs = async () => {
                const result = await getNguoiDungsByMaVaiTro(Number(route.params.maVaiTro))
                nguoiDungs.value = result
            }

            onMounted(() => {
                fetchNguoiDungs()
            })

            watch(currentPage, (newPage) => {
                router.replace({ query: { ...route.query, page: newPage.toString() } })
                fetchNguoiDungs()
            })

            watch(
                () => route.params.maVaiTro,
                () => {
                    window.location.reload()
                }
            )

            const onChangePage = (page: number) => {
                currentPage.value = page
            }

            const showConfirmDeletePopup = (maNguoiDung: unknown) => {
                if (typeof maNguoiDung === 'number') {
                    nguoiDungToDelete.value = maNguoiDung
                    showDeletePopup.value = true
                } else {
                    console.error('Lỗi: Mã nhà kho không phải số', maNguoiDung)
                }
            }

            const showConfirmBanPopup = (maNguoiDung: unknown) => {
                if (typeof maNguoiDung === 'number') {
                    nguoiDungToBan.value = maNguoiDung
                    showBanPopup.value = true
                } else {
                    console.error('Lỗi: Mã nhà kho không phải số', maNguoiDung)
                }
            }

            const confirmDelete = async () => {
                if (nguoiDungToDelete.value) {
                    if (maVaiTroPrams === 2) {
                        await deleteNhanVien(nguoiDungToDelete.value)
                    } else if (maVaiTroPrams === 3) {
                        await deleteNguoiBan(nguoiDungToDelete.value)
                    } else if (maVaiTroPrams === 4) {
                        await deleteNguoiMua(nguoiDungToDelete.value)
                    }
                    fetchNguoiDungs()
                }
            }

            const confirmBan = async () => {
                if (nguoiDungToBan.value) {
                    if (maVaiTroPrams === 2) {
                        await banNhanVien(nguoiDungToBan.value)
                    } else if (maVaiTroPrams === 3) {
                        await banNguoiBan(nguoiDungToBan.value)
                    } else if (maVaiTroPrams === 4) {
                        await banNguoiMua(nguoiDungToBan.value)
                    }
                    fetchNguoiDungs()
                }
            }

            return {
                paginatedNguoiDungs,
                currentPage,
                totalPages,
                totalElements,
                pageSize,
                onChangePage,
                showDeletePopup,
                showBanPopup,
                keyword,
                showConfirmDeletePopup,
                showConfirmBanPopup,
                confirmDelete,
                confirmBan,
                maVaiTroPrams
            }
        }
    })
</script>

<style>
    td a {
        text-decoration: none;
        color: #000000;

        &:hover {
            color: #000000;
        }
    }
    .btn-primary {
        --bs-btn-color: #fff;
        --bs-btn-bg: #ff8435;
        --bs-btn-border-color: #ff8435;
        --bs-btn-hover-color: #fff;
        --bs-btn-hover-bg: #81561d;
        --bs-btn-hover-border-color: #81561d;
        --bs-btn-focus-shadow-rgb: 49, 132, 253;
        --bs-btn-active-color: #fff;
        --bs-btn-active-bg: #81561d;
        --bs-btn-active-border-color: #81561d;
        --bs-btn-active-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
        --bs-btn-disabled-color: #fff;
        --bs-btn-disabled-bg: #ff8435;
        --bs-btn-disabled-border-color: #ff8435;
    }
</style>
