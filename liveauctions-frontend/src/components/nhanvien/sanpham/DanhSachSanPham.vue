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
                        <router-link class="text-success" :to="{ name: 'ThemMoiSanPhamView' }">
                            <i class="fas fa-plus-circle"></i>
                            <span class="ps-1">Thêm mới</span>
                        </router-link>
                    </div>
                </div>
                <table class="table table-hover my-0">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã sản phẩm</th>
                            <th>Trạng thái</th>
                            <th>Tên sản phẩm</th>
                            <th>Danh mục</th>
                            <th>Giá khởi điểm</th>
                            <th>Người bán</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr
                            v-for="(item, index) in paginatedSanPhams"
                            :key="
                                typeof (item as any).sanPham.maSanPham === 'string'
                                    ? (item as any).sanPham.maSanPham
                                    : undefined
                            "
                        >
                            <td>
                                {{ index + 1 }}
                            </td>
                            <td>
                                {{ (item as any).sanPham.maSanPham }}
                            </td>
                            <td>
                                {{ (item as any).sanPham.trangThai }}
                            </td>
                            <td>
                                {{ (item as any).sanPham.tenSanPham }}
                            </td>
                            <td>
                                {{ (item as any).danhMucCon.tenDanhMucCon }}
                            </td>
                            <td>
                                {{ (item as any).sanPham.giaKhoiDiem }}
                            </td>
                            <td>
                                {{
                                    tenNguoiBans[Number((item as any).sanPham.maNguoiBan)] ||
                                    'Đang tải...'
                                }}
                            </td>
                            <td class="text-center">
                                <router-link
                                    :to="{
                                        name: 'CapNhatSanPhamView',
                                        params: { maSanPham: String((item as any).sanPham.maSanPham) }
                                    }"
                                >
                                    <i class="far fa-edit text-success" title="Cập nhật"></i
                                ></router-link>
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
    </div>
</template>

<script lang="ts">
    import PaginationComponent from '@/components/dungchung/PaginationComponent.vue'
    import SearchComponent from '@/components/dungchung/SearchComponent.vue'
    import { useDateTime } from '@/composables/useDateTime'
    import { getSanPhams } from '@/services/nhanvien/sanPhamService'
    import { getNguoiBanByMaNguoiDung } from '@/services/quantrivien/nguoiBanService'
    import { computed, defineComponent, onMounted, ref, Ref, watch } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    export default defineComponent({
        name: 'DanhSachSanPham',
        components: {
            SearchComponent,
            PaginationComponent
        },
        setup() {
            const totalPages = computed(() => Math.ceil(sanPhams.value.length / pageSize.value))
            const route = useRoute()
            const router = useRouter()
            const currentPage = ref(Number(route.query.page) || 1) as Ref<number>
            const sanPhams: Ref<Record<string, unknown>[]> = ref([])
            const tenNguoiBans = ref<Record<number, string>>({})
            const totalElements = ref() as Ref<number>
            const pageSize = ref(10) as Ref<number>
            const showDeletePopup = ref(false) as Ref<boolean>
            const sanPhamToDelete = ref(null) as Ref<string | null>
            const keyword = ref('') as Ref<string>

            const formatDateTime = (dateTime: string) => {
                return useDateTime(dateTime)
            }

            const paginatedSanPhams = computed(() => {
                const start = (currentPage.value - 1) * pageSize.value
                return sanPhams.value.slice(start, start + pageSize.value)
            })

            const fetchSanPhams = async () => {
                const result = await getSanPhams()
                sanPhams.value = result

                for (const item of result) {
                    if (item.nguoiBan && item.nguoiBan.maNguoiBan) {
                        getTenNguoiBan(Number(item.nguoiBan.maNguoiBan))
                    }
                }
            }

            onMounted(fetchSanPhams)

            const getTenNguoiBan = async (maNguoiBan: number) => {
                if (!tenNguoiBans.value[maNguoiBan]) {
                    const nguoiBan = await getNguoiBanByMaNguoiDung(maNguoiBan)
                    tenNguoiBans.value[maNguoiBan] = nguoiBan
                        ? nguoiBan.nguoiDung.hoVaTen
                        : 'Không xác định'
                }
                return tenNguoiBans.value[maNguoiBan]
            }

            watch(currentPage, (newPage) => {
                router.replace({ query: { ...route.query, page: newPage.toString() } })
                fetchSanPhams()
            })

            const onChangePage = (page: number) => {
                currentPage.value = page
            }

            return {
                formatDateTime,
                paginatedSanPhams,
                currentPage,
                totalPages,
                totalElements,
                pageSize,
                onChangePage,
                showDeletePopup,
                sanPhamToDelete,
                tenNguoiBans,
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
