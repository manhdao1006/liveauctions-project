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
                        <h5 class="card-title mb-0">Danh sách nhà thẩm định</h5>
                    </div>
                    <div class="card-header col-xl-6 text-end">
                        <router-link class="text-success" :to="{ name: 'ThemMoiNhaThamDinhView' }">
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
                            <th class="d-none d-md-table-cell">Giới tính</th>
                            <th>Số điện thoại</th>
                            <th class="d-none d-xl-table-cell">Email</th>
                            <th>Loại</th>
                            <th class="d-none d-xl-table-cell">Trạng thái</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr
                            v-for="(nhaThamDinh, index) in paginatedNhaThamDinhs"
                            :key="
                                typeof nhaThamDinh.maNhaThamDinh === 'string'
                                    ? nhaThamDinh.maNhaThamDinh
                                    : undefined
                            "
                        >
                            <td class="d-none d-xl-table-cell">
                                {{ index + 1 }}
                            </td>
                            <td>
                                {{ nhaThamDinh.hoVaTen }}
                            </td>
                            <td class="d-none d-md-table-cell">
                                {{ nhaThamDinh.gioiTinh }}
                            </td>
                            <td>
                                {{ nhaThamDinh.soDienThoai }}
                            </td>
                            <td class="d-none d-xl-table-cell">
                                {{ nhaThamDinh.email }}
                            </td>
                            <td>
                                {{ nhaThamDinh.loai }}
                            </td>
                            <td class="d-none d-xl-table-cell">
                                {{ nhaThamDinh.trangThaiHoatDong }}
                            </td>
                            <td>
                                <router-link
                                    :to="{
                                        name: 'CapNhatNhaThamDinhView',
                                        params: { maNhaThamDinh: Number(nhaThamDinh.maNhaThamDinh) }
                                    }"
                                >
                                    <i class="far fa-edit text-success" title="Cập nhật"></i
                                ></router-link>
                                |
                                <button
                                    @click="showConfirmPopup(Number(nhaThamDinh.maNhaThamDinh))"
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
    import { deleteNhaThamDinh, getNhaThamDinhs } from '@/services/quantrivien/nhaThamDinhService'
    import { computed, defineComponent, onMounted, ref, Ref, watch } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    export default defineComponent({
        name: 'DanhSachNhaThamDinh',
        components: {
            SearchComponent,
            PaginationComponent,
            PopupDelete
        },
        setup() {
            const totalPages = computed(() => Math.ceil(nhaThamDinhs.value.length / pageSize.value))
            const route = useRoute()
            const router = useRouter()
            const currentPage = ref(Number(route.query.page) || 1) as Ref<number>
            const nhaThamDinhs: Ref<Record<string, unknown>[]> = ref([])
            const totalElements = ref() as Ref<number>
            const pageSize = ref(10) as Ref<number>
            const showDeletePopup = ref(false) as Ref<boolean>
            const nhaThamDinhToDelete = ref(null) as Ref<number | null>
            const keyword = ref('') as Ref<string>

            const paginatedNhaThamDinhs = computed(() => {
                const start = (currentPage.value - 1) * pageSize.value
                return nhaThamDinhs.value.slice(start, start + pageSize.value)
            })

            const fetchNhaThamDinhs = async () => {
                const result = await getNhaThamDinhs()
                nhaThamDinhs.value = result
            }
            onMounted(fetchNhaThamDinhs)

            watch(currentPage, (newPage) => {
                router.replace({ query: { ...route.query, page: newPage.toString() } })
                fetchNhaThamDinhs()
            })

            const onChangePage = (page: number) => {
                currentPage.value = page
            }

            const showConfirmPopup = (maNhaThamDinh: unknown) => {
                if (typeof maNhaThamDinh === 'number') {
                    nhaThamDinhToDelete.value = maNhaThamDinh
                    showDeletePopup.value = true
                } else {
                    console.error('Lỗi: Mã nhà thẩm định không phải số', maNhaThamDinh)
                }
            }

            const confirmDelete = async () => {
                if (nhaThamDinhToDelete.value) {
                    await deleteNhaThamDinh(nhaThamDinhToDelete.value)
                    fetchNhaThamDinhs()
                }
            }

            return {
                paginatedNhaThamDinhs,
                currentPage,
                totalPages,
                totalElements,
                pageSize,
                onChangePage,
                showDeletePopup,
                nhaThamDinhToDelete,
                showConfirmPopup,
                confirmDelete,
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
