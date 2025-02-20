<template>
    <div class="container-fluid">
        <div class="col-12 col-lg-12 col-xxl-12 d-flex">
            <div class="card flex-fill">
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
                        <h5 class="card-title mb-0">Danh sách phiên đấu giá</h5>
                    </div>
                    <div class="card-header col-xl-6 text-end">
                        <router-link class="text-success" :to="{ name: 'ThemMoiPhienDauGiaView' }">
                            <i class="fas fa-plus-circle"></i>
                            <span class="ps-1">Thêm mới</span>
                        </router-link>
                    </div>
                </div>
                <table class="table table-hover my-0">
                    <thead>
                        <tr>
                            <th class="d-none d-xl-table-cell">STT</th>
                            <th>Tên phiên</th>
                            <th class="d-none d-md-table-cell">Nhân viên</th>
                            <th>Ngày bắt đầu</th>
                            <th class="d-none d-xl-table-cell">Ngày kết thúc</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr
                            v-for="(phienDauGia, index) in paginatedPhienDauGias"
                            :key="
                                typeof phienDauGia.maPhienDauGia === 'string'
                                    ? phienDauGia.maPhienDauGia
                                    : undefined
                            "
                        >
                            <td class="d-none d-xl-table-cell">
                                {{ index + 1 }}
                            </td>
                            <td>
                                {{ phienDauGia.tenPhienDauGia }}
                            </td>
                            <td class="d-none d-md-table-cell">
                                {{ tenNhanViens[Number(phienDauGia.maNhanVien)] || 'Đang tải...' }}
                            </td>
                            <td>
                                {{
                                    phienDauGia.ngayBatDau
                                        ? formatDateTime(String(phienDauGia.ngayBatDau))
                                        : null
                                }}
                            </td>
                            <td class="d-none d-xl-table-cell">
                                {{
                                    phienDauGia.ngayKetThuc
                                        ? formatDateTime(String(phienDauGia.ngayKetThuc))
                                        : null
                                }}
                            </td>
                            <td>
                                <router-link
                                    :to="{
                                        name: 'CapNhatPhienDauGiaView',
                                        params: { maPhienDauGia: Number(phienDauGia.maPhienDauGia) }
                                    }"
                                >
                                    <i class="far fa-edit text-success" title="Cập nhật"></i
                                ></router-link>
                                |
                                <button
                                    @click="showConfirmPopup(Number(phienDauGia.maPhienDauGia))"
                                    class="border-0 p-0 bg-transparent"
                                >
                                    <i class="fas fa-trash-alt text-danger" title="Xóa"></i>
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
    </div>
</template>

<script lang="ts">
    import PaginationComponent from '@/components/dungchung/PaginationComponent.vue'
    import PopupDelete from '@/components/dungchung/PopupDelete.vue'
    import SearchComponent from '@/components/dungchung/SearchComponent.vue'
    import { useDateTime } from '@/composables/useDateTime'
    import { deletePhienDauGia, getPhienDauGias } from '@/services/nhanvien/phienDauGiaService'
    import { getNhanVienByMaNguoiDung } from '@/services/quantrivien/nhanVienService'
    import { computed, defineComponent, onMounted, ref, Ref, watch } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    export default defineComponent({
        name: 'DanhSachPhienDauGia',
        components: {
            SearchComponent,
            PaginationComponent,
            PopupDelete
        },
        setup() {
            const totalPages = computed(() => Math.ceil(phienDauGias.value.length / pageSize.value))
            const route = useRoute()
            const router = useRouter()
            const currentPage = ref(Number(route.query.page) || 1) as Ref<number>
            const phienDauGias: Ref<Record<string, unknown>[]> = ref([])
            const tenNhanViens = ref<Record<number, string>>({})
            const totalElements = ref() as Ref<number>
            const pageSize = ref(10) as Ref<number>
            const showDeletePopup = ref(false) as Ref<boolean>
            const phienDauGiaToDelete = ref(null) as Ref<number | null>
            const keyword = ref('') as Ref<string>
            const formatDateTime = (dateTime: string) => {
                return useDateTime(dateTime)
            }
            const paginatedPhienDauGias = computed(() => {
                const start = (currentPage.value - 1) * pageSize.value
                return phienDauGias.value.slice(start, start + pageSize.value)
            })

            const fetchPhienDauGias = async () => {
                const result = await getPhienDauGias()
                phienDauGias.value = result

                for (const phien of result) {
                    getTenNhanVien(Number(phien.maNhanVien))
                }
            }

            onMounted(fetchPhienDauGias)

            const getTenNhanVien = async (maNhanVien: number) => {
                if (!tenNhanViens.value[maNhanVien]) {
                    const nhanVien = await getNhanVienByMaNguoiDung(maNhanVien)
                    tenNhanViens.value[maNhanVien] = nhanVien
                        ? nhanVien.nguoiDung.hoVaTen
                        : 'Không xác định'
                }
                return tenNhanViens.value[maNhanVien]
            }

            watch(currentPage, (newPage) => {
                router.replace({ query: { ...route.query, page: newPage.toString() } })
                fetchPhienDauGias()
            })

            const onChangePage = (page: number) => {
                currentPage.value = page
            }

            const showConfirmPopup = (maPhienDauGia: unknown) => {
                if (typeof maPhienDauGia === 'number') {
                    phienDauGiaToDelete.value = maPhienDauGia
                    showDeletePopup.value = true
                } else {
                    console.error('Lỗi: Mã phiên đấu giá không phải số', maPhienDauGia)
                }
            }

            const confirmDelete = async () => {
                if (phienDauGiaToDelete.value) {
                    await deletePhienDauGia(phienDauGiaToDelete.value)
                    fetchPhienDauGias()
                }
            }

            return {
                formatDateTime,
                paginatedPhienDauGias,
                currentPage,
                totalPages,
                totalElements,
                pageSize,
                onChangePage,
                showDeletePopup,
                phienDauGiaToDelete,
                showConfirmPopup,
                confirmDelete,
                tenNhanViens,
                keyword
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
</style>
