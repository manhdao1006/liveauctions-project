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
                                    <button class="dropdown-item">
                                        Người dùng
                                    </button>
                                </li>
                                <li>
                                    <button class="dropdown-item">
                                        Quản trị viên
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="row justify-content-evenly">
                    <div class="card-header col-xl-6">
                        <h5 class="card-title mb-0">Danh sách nhà kho</h5>
                    </div>
                    <div class="card-header col-xl-6 text-end">
                        <router-link class="text-success" :to="{ name: 'ThemMoiNhaKhoView' }">
                            <i class="fas fa-plus-circle"></i>
                            <span class="ps-1">Thêm mới</span>
                        </router-link>
                    </div>
                </div>
                <table class="table table-hover my-0">
                    <thead>
                        <tr>
                            <th class="d-none d-xl-table-cell">STT</th>
                            <th>Tên nhà kho</th>
                            <th class="d-none d-md-table-cell">Địa chỉ</th>
                            <th>Ngày hoạt động</th>
                            <th class="d-none d-xl-table-cell">Trạng thái hoạt động</th>
                            <th>Trạng thái còn trống</th>
                            <th class="d-none d-xl-table-cell">Quản lý</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(nhaKho, index) in paginatedNhaKhos" :key=" typeof nhaKho.maNhaKho === 'string' ? nhaKho.maNhaKho : undefined">
                            <td class="d-none d-xl-table-cell">
                                {{ index + 1 }}
                            </td>
                            <td>
                                {{ nhaKho.tenNhaKho }}
                            </td>
                            <td class="d-none d-md-table-cell">
                                {{ nhaKho.diaChi }}
                            </td>
                            <td>
                                {{ nhaKho.ngayHoatDong }}
                            </td>
                            <td class="d-none d-xl-table-cell">
                                {{ nhaKho.trangThaiHoatDong }}
                            </td>
                            <td>
                                {{ nhaKho.trangThaiConTrong }}
                            </td>
                            <td class="d-none d-xl-table-cell">
                                {{ nhaKho.hoTenQuanLy }}
                            </td>
                            <td>
                                <router-link :to="{ name: 'CapNhatNhaKhoView', params: { maNhaKho: Number(nhaKho.maNhaKho) } }">
                                    <i class="far fa-edit text-success" title="Cập nhật"></i
                                ></router-link>
                                |
                                <button
                                    @click="showConfirmPopup(Number(nhaKho.maNhaKho))"
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
    import { deleteNhaKho, getNhaKhos } from '@/services/quantrivien/nhaKhoService'
    import { computed, defineComponent, onMounted, ref, Ref, watch } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    export default defineComponent({
        name: 'DanhSachNhaKho',
        components: {
            SearchComponent,
            PaginationComponent,
            PopupDelete,
        },
        setup() {
            const totalPages = computed(() => Math.ceil(nhaKhos.value.length / pageSize.value))
            const route = useRoute()
            const router = useRouter()
            const currentPage = ref(Number(route.query.page) || 1) as Ref<number>
            const nhaKhos: Ref<Record<string, unknown>[]> = ref([])
            const totalElements = ref() as Ref<number>
            const pageSize = ref(10) as Ref<number>
            const showDeletePopup = ref(false) as Ref<boolean>
            const nhaKhoToDelete = ref(null) as Ref<number | null>
            const keyword = ref('') as Ref<string>

            const paginatedNhaKhos = computed(() => {
                const start = (currentPage.value - 1) * pageSize.value
                return nhaKhos.value.slice(start, start + pageSize.value)
            })

            const fetchNhaKhos = async () => {
                const result = await getNhaKhos()
                nhaKhos.value = result
            }
            onMounted(fetchNhaKhos)

            watch(currentPage, (newPage) => {
                router.replace({ query: { ...route.query, page: newPage.toString() } })
                fetchNhaKhos()
            })

            const onChangePage = (page: number) => {
                currentPage.value = page
            }

            const showConfirmPopup = (maNhaKho: unknown) => {
                if (typeof maNhaKho === "number") {
                    nhaKhoToDelete.value = maNhaKho
                    showDeletePopup.value = true
                } else {
                    console.error("Lỗi: Mã nhà kho không phải số", maNhaKho)
                }
            }

            const confirmDelete = async () => {
                if (nhaKhoToDelete.value) {
                    await deleteNhaKho(nhaKhoToDelete.value)
                    fetchNhaKhos()
                }
            }

            return { 
                paginatedNhaKhos,
                currentPage,
                totalPages,
                totalElements,
                pageSize,
                onChangePage,
                showDeletePopup,
                nhaKhoToDelete,
                showConfirmPopup,
                confirmDelete,
                keyword,
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
