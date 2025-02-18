<template>
    <div class="container-fluid">
        <div class="row justify-content-evenly m-0 mt-3 mb-3">
            <div class="card-header col-xl-6">
                <h5 class="card-title mb-0">Thêm mới nhà kho</h5>
            </div>
            <div class="card-header col-xl-6 text-end">
                <router-link class="text-success" :to="{ name: 'DanhSachNhaKhoView' }">
                    <i class="fas fa-chevron-circle-left"></i>
                    <span class="ps-1">Quay lại danh sách</span>
                </router-link>
            </div>
        </div>
        <div class="row m-0">
            <div v-if="isError" class="alert alert-danger">
                {{ messageError }}
            </div>
            <div class="col-xl-6">
                <div class="mb-3">
                    <label for="tenNhaKho" class="form-label"
                        >Tên nhà kho<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nhaKho.tenNhaKho"
                        type="text"
                        class="form-control"
                        id="tenNhaKho"
                    />
                </div>
                <div class="mb-3">
                    <label for="hoTenQuanLy" class="form-label"
                        >Quản lý<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nhaKho.hoTenQuanLy"
                        type="text"
                        class="form-control"
                        id="hoTenQuanLy"
                    />
                </div>
                <div class="mb-3">
                    <label for="diaChi" class="form-label"
                        >Địa chỉ<span class="text-danger">*</span></label
                    >
                    <input v-model="nhaKho.diaChi" type="text" class="form-control" id="diaChi" />
                </div>
            </div>
            <div class="col-xl-6">
                <div class="row mb-3">
                    <div class="col-xl-6">
                        <label for="quanHuyen" class="form-label"
                            >Quận/Huyện<span class="text-danger">*</span></label
                        >
                        <select
                            class="form-select"
                            aria-label="Default select example"
                            v-model="selectedQuanHuyen"
                        >
                            <option selected disabled>Chọn quận/huyện</option>
                            <template v-for="quanHuyen in quanHuyens" :key="quanHuyen.maQuanHuyen">
                                <option :value="quanHuyen.maQuanHuyen">
                                    {{ quanHuyen.tenQuanHuyen }}
                                </option>
                            </template>
                        </select>
                    </div>
                    <div class="col-xl-6">
                        <label for="maPhuongXa" class="form-label"
                            >Phường/Xã<span class="text-danger">*</span></label
                        >
                        <select
                            v-model="nhaKho.maPhuongXa"
                            class="form-select"
                            aria-label="Default select example"
                        >
                            <option selected disabled>Chọn phường/xã</option>
                            <template v-for="phuongXa in phuongXas" :key="phuongXa.maPhuongXa">
                                <option :value="phuongXa.maPhuongXa">
                                    {{ phuongXa.tenPhuongXa }}
                                </option>
                            </template>
                        </select>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="trangThaiConTrong" class="form-label"
                        >Trạng thái còn trống<span class="text-danger">*</span></label
                    >
                    <select
                        v-model="nhaKho.trangThaiConTrong"
                        class="form-select"
                        aria-label="Default select example"
                    >
                        <option selected disabled>Chọn tình trạng</option>
                        <option value="Còn trống">Còn trống</option>
                        <option value="Đã đầy">Đã đầy</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="ngayHoatDong" class="form-label"
                        >Ngày hoạt động<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nhaKho.ngayHoatDong"
                        type="text"
                        class="form-control"
                        id="ngayHoatDong"
                    />
                </div>
            </div>
            <div class="text-center">
                <button
                    type="button"
                    class="btn btn-success"
                    title="Thêm mới"
                    @click.prevent="handleThemMoi"
                >
                    Thêm mới
                </button>
            </div>
        </div>
    </div>
</template>
<script lang="ts">
    import { getPhuongXaByMaQuanHuyen, getQuanHuyens } from '@/services/authService'
    import { addNhaKho } from '@/services/quantrivien/nhaKhoService'
    import { defineComponent, onMounted, ref, watch } from 'vue'
    import { useRouter } from 'vue-router'

    export default defineComponent({
        name: 'ThemMoiNhaKho',
        setup() {
            const router = useRouter()
            const isError = ref(false)
            const messageError = ref<string>('')
            const nhaKho = ref<Record<string, undefined>>({})
            const quanHuyens = ref<Array<{ maQuanHuyen: number; tenQuanHuyen: string }>>([])
            const phuongXas = ref<Array<{ maPhuongXa: number; tenPhuongXa: string }>>([])
            const selectedQuanHuyen = ref<string | null>(null)

            const fetchQuanHuyens = async () => {
                const result = await getQuanHuyens()
                quanHuyens.value = result
            }

            const fetchPhuongXas = async (maQuanHuyen: number) => {
                const result = await getPhuongXaByMaQuanHuyen(maQuanHuyen)
                if (result) {
                    phuongXas.value = result
                } else {
                    phuongXas.value = []
                }
            }

            watch(selectedQuanHuyen, (newMaQuanHuyen) => {
                if (newMaQuanHuyen) {
                    fetchPhuongXas(Number(newMaQuanHuyen))
                } else {
                    phuongXas.value = []
                }
            })

            onMounted(() => {
                fetchQuanHuyens()
            })

            const handleThemMoi = async () => {
                if (
                    !nhaKho.value.tenNhaKho ||
                    !nhaKho.value.hoTenQuanLy ||
                    !nhaKho.value.diaChi ||
                    !nhaKho.value.trangThaiConTrong ||
                    !nhaKho.value.ngayHoatDong ||
                    !selectedQuanHuyen.value ||
                    !nhaKho.value.maPhuongXa
                ) {
                    isError.value = true
                    messageError.value = 'Vui lòng nhập đầy đủ các trường dữ liệu!'
                    setTimeout(() => {
                        isError.value = false
                        messageError.value = ''
                    }, 3000)
                    return
                }
                const formData = new FormData()
                Object.entries(nhaKho.value).forEach(([key, value]) => {
                    if (value !== undefined) {
                        formData.append(key, value || '')
                    }
                })

                const response = await addNhaKho(formData)
                if (response.success) {
                    await router.push({ name: 'DanhSachNhaKhoView' })
                }
            }

            return {
                isError,
                messageError,
                selectedQuanHuyen,
                nhaKho,
                quanHuyens,
                phuongXas,
                handleThemMoi
            }
        }
    })
</script>
