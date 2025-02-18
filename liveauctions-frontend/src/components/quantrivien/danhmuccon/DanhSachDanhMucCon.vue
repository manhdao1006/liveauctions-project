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
                        <h5 class="card-title mb-0">Danh sách danh mục con</h5>
                    </div>
                    <div class="card-header col-xl-6 text-end">
                        <router-link class="text-success me-3" :to="{ name: 'ThemMoiDanhMucView' }">
                            <i class="fas fa-plus-circle"></i>
                            <span class="ps-1">Thêm mới</span>
                        </router-link>
                        <router-link
                            class="text-primary-emphasis"
                            :to="{ name: 'DanhSachDanhMucView' }"
                        >
                            <i class="fas fa-chevron-circle-left"></i>
                            <span class="ps-1">Quay lại</span>
                        </router-link>
                    </div>
                </div>
                <table class="table table-hover my-0">
                    <thead>
                        <tr>
                            <th class="d-none d-xl-table-cell">STT</th>
                            <th>Tên danh mục</th>
                            <th class="d-none d-md-table-cell">Danh mục cha</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr
                            v-for="(danhMucCon, index) in paginatedDanhMucCons"
                            :key="
                                typeof danhMucCon.maDanhMucCon === 'string'
                                    ? danhMucCon.maDanhMucCon
                                    : undefined
                            "
                        >
                            <td class="d-none d-xl-table-cell">
                                {{ index + 1 }}
                            </td>
                            <td>
                                {{ danhMucCon.tenDanhMucCon }}
                            </td>
                            <td class="d-none d-md-table-cell">
                                {{ getTenDanhMuc(Number(danhMucCon.maDanhMuc)) }}
                            </td>
                            <td>
                                <router-link
                                    :to="{
                                        name: 'DanhSachDanhMucConView',
                                        params: { maDanhMuc: Number(danhMucCon.maDanhMuc) }
                                    }"
                                >
                                    <i
                                        class="fa-solid fa-layer-group text-warning"
                                        title="Xem cấp con"
                                    ></i>
                                </router-link>
                                |
                                <router-link
                                    :to="{
                                        name: 'CapNhatDanhMucConView',
                                        params: {
                                            maDanhMuc: Number(danhMucCon.maDanhMuc),
                                            maDanhMucCon: Number(danhMucCon.maDanhMucCon)
                                        }
                                    }"
                                >
                                    <i class="far fa-edit text-success" title="Cập nhật"></i
                                ></router-link>
                                |
                                <button
                                    @click="showConfirmPopup(Number(danhMucCon.maDanhMucCon))"
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
    import {
        deleteDanhMucCon,
        getDanhMucConsByMaDanhMuc
    } from '@/services/quantrivien/danhMucConService'
    import { getDanhMucs } from '@/services/quantrivien/danhMucService'
    import { computed, defineComponent, onMounted, ref, Ref, watch } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    export default defineComponent({
        name: 'DanhSachDanhMucCon',
        components: {
            SearchComponent,
            PaginationComponent,
            PopupDelete
        },
        setup() {
            const totalPages = computed(() => Math.ceil(danhMucCons.value.length / pageSize.value))
            const route = useRoute()
            const router = useRouter()
            const maDanhMuc = ref(Number(route.params.maDanhMuc))
            const currentPage = ref(Number(route.query.page) || 1) as Ref<number>
            const danhMucCons: Ref<Record<string, unknown>[]> = ref([])
            const danhMucs: Ref<Record<string, unknown>[]> = ref([])
            const totalElements = ref() as Ref<number>
            const pageSize = ref(10) as Ref<number>
            const showDeletePopup = ref(false) as Ref<boolean>
            const danhMucConToDelete = ref(null) as Ref<number | null>
            const keyword = ref('') as Ref<string>

            const fetchDanhMucCons = async () => {
                const result = await getDanhMucConsByMaDanhMuc(maDanhMuc.value)
                danhMucCons.value = result
            }

            const fetchDanhMucs = async () => {
                const result = await getDanhMucs()
                danhMucs.value = result
            }

            const paginatedDanhMucCons = computed(() => {
                const start = (currentPage.value - 1) * pageSize.value
                return danhMucCons.value.slice(start, start + pageSize.value)
            })

            const getTenDanhMuc = (maDanhMuc: number) => {
                const danhMuc = danhMucs.value.find((dm) => dm.maDanhMuc === maDanhMuc)
                return danhMuc ? danhMuc.tenDanhMuc : 'Không xác định'
            }

            watch(currentPage, (newPage) => {
                router.replace({ query: { ...route.query, page: newPage.toString() } })
                fetchDanhMucCons()
            })

            const onChangePage = (page: number) => {
                currentPage.value = page
            }

            const showConfirmPopup = (maDanhMucCon: unknown) => {
                if (typeof maDanhMucCon === 'number') {
                    danhMucConToDelete.value = maDanhMucCon
                    showDeletePopup.value = true
                } else {
                    console.error('Lỗi: Mã danh mục không phải số', maDanhMucCon)
                }
            }

            const confirmDelete = async () => {
                if (danhMucConToDelete.value) {
                    await deleteDanhMucCon(danhMucConToDelete.value)
                    fetchDanhMucCons()
                }
            }

            onMounted(() => {
                fetchDanhMucCons()
                fetchDanhMucs()
            })

            return {
                paginatedDanhMucCons,
                currentPage,
                totalPages,
                totalElements,
                pageSize,
                onChangePage,
                showDeletePopup,
                danhMucConToDelete,
                showConfirmPopup,
                confirmDelete,
                keyword,
                getTenDanhMuc
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
