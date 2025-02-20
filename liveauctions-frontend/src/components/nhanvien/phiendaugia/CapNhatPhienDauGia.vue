<template>
    <div class="container-fluid">
        <div class="row justify-content-evenly m-0 mt-3 mb-3">
            <div class="card-header col-xl-6">
                <h5 class="card-title mb-0">Cập nhật phiên đấu giá</h5>
            </div>
            <div class="card-header col-xl-6 text-end">
                <router-link class="text-success" :to="{ name: 'DanhSachPhienDauGiaView' }">
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
                    <label for="tenPhienDauGia" class="form-label"
                        >Tên phiên đấu giá<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="phienDauGia.tenPhienDauGia"
                        type="text"
                        class="form-control"
                        id="tenPhienDauGia"
                    />
                </div>
                <div class="mb-3">
                    <label for="ngayBatDau" class="form-label"
                        >Ngày bắt đầu<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="phienDauGia.ngayBatDau"
                        type="datetime-local"
                        class="form-control"
                        id="ngayBatDau"
                    />
                </div>
                <div class="mb-3">
                    <label for="phiBaoHiem" class="form-label"
                        >Phí bảo hiểm (%)<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="phienDauGia.phiBaoHiem"
                        type="text"
                        class="form-control"
                        id="phiBaoHiem"
                    />
                    <div v-if="isErrorPhiBaoHiem" class="text-danger">
                        {{ messagePhiBaoHiem }}
                    </div>
                </div>
                <div class="mb-3">
                    <label for="moTa" class="form-label">Mô tả</label>
                    <textarea
                        v-model="phienDauGia.moTa"
                        class="form-control"
                        id="moTa"
                        rows="3"
                    ></textarea>
                </div>
            </div>
            <div class="col-xl-6">
                <div class="mb-3">
                    <label for="maLoaiDauGia" class="form-label"
                        >Loại đấu giá<span class="text-danger">*</span></label
                    >
                    <select
                        v-model="phienDauGia.maLoaiDauGia"
                        class="form-select"
                        aria-label="Default select example"
                    >
                        <option selected disabled>Chọn loại đấu giá</option>
                        <template v-for="loaiDauGia in loaiDauGias" :key="loaiDauGia.maLoaiDauGia">
                            <option :value="loaiDauGia.maLoaiDauGia">
                                {{ loaiDauGia.tenLoaiDauGia }}
                            </option>
                        </template>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="ngayKetThuc" class="form-label"
                        >Ngày kết thúc<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="phienDauGia.ngayKetThuc"
                        type="datetime-local"
                        class="form-control"
                        id="ngayKetThuc"
                    />
                </div>
                <div class="mb-3">
                    <label for="maNhanVien" class="form-label"
                        >Nhân viên<span class="text-danger">*</span></label
                    >
                    <select
                        v-model="phienDauGia.maNhanVien"
                        class="form-select"
                        aria-label="Default select example"
                    >
                        <option selected disabled>Chọn nhân viên</option>
                        <template v-for="nhanVien in nhanViens" :key="nhanVien.maNhanVien">
                            <option :value="nhanVien.maNhanVien">
                                {{ nhanVien.hoVaTen }}
                            </option>
                        </template>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="trangThaiHoatDong" class="form-label"
                        >Trạng thái<span class="text-danger">*</span></label
                    >
                    <select
                        class="form-select"
                        aria-label="Default select example"
                        v-model="phienDauGia.trangThaiHoatDong"
                    >
                        <option selected disabled>Chọn tình trạng</option>
                        <option value="Sắp diễn ra">Sắp diễn ra</option>
                        <option value="Đang diễn ra">Đang diễn ra</option>
                        <option value="Đã hoàn thành">Đã hoàn thành</option>
                    </select>
                </div>
            </div>
            <div class="text-center">
                <button
                    type="button"
                    class="btn btn-success"
                    title="Cập nhật"
                    @click.prevent="handleCapNhat"
                >
                    Cập nhật
                </button>
            </div>
        </div>
    </div>
</template>
<script lang="ts">
    import { getLoaiDauGias } from '@/services/nhanvien/loaiDauGiaService'
    import {
        getPhienDauGiaByMaPhienDauGia,
        updatePhienDauGia
    } from '@/services/nhanvien/phienDauGiaService'
    import { getNhanViens } from '@/services/quantrivien/nhanVienService'
    import { validatePhiBaoHiem } from '@/utils/validation'
    import { defineComponent, onMounted, ref } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    export default defineComponent({
        name: 'CapNhatPhienDauGia',
        setup() {
            const route = useRoute()
            const router = useRouter()
            const isError = ref(false)
            const messageError = ref<string>('')
            const isErrorPhiBaoHiem = ref(false)
            const messagePhiBaoHiem = ref<string>('')
            const phienDauGia = ref<Record<string, undefined>>({})
            const nhanViens = ref<Record<string, unknown>[]>([])
            const loaiDauGias = ref<Record<string, unknown>[]>([])

            const fetchPhienDauGia = async () => {
                const response = await getPhienDauGiaByMaPhienDauGia(
                    Number(route.params.maPhienDauGia)
                )
                phienDauGia.value = response
            }

            const fetchNhanViens = async () => {
                const response = await getNhanViens()
                nhanViens.value = response.map((item: { nguoiDung: unknown }) => item.nguoiDung)
            }

            const fetchLoaiDauGias = async () => {
                const response = await getLoaiDauGias()
                loaiDauGias.value = response
            }

            onMounted(() => {
                fetchPhienDauGia()
                fetchNhanViens()
                fetchLoaiDauGias()
            })

            const handleCapNhat = async () => {
                if (
                    !phienDauGia.value.tenPhienDauGia ||
                    !phienDauGia.value.maLoaiDauGia ||
                    !phienDauGia.value.ngayBatDau ||
                    !phienDauGia.value.ngayKetThuc ||
                    !phienDauGia.value.phiBaoHiem ||
                    !phienDauGia.value.maNhanVien
                ) {
                    isError.value = true
                    messageError.value = 'Vui lòng nhập đầy đủ các trường dữ liệu!'
                    setTimeout(() => {
                        isError.value = false
                        messageError.value = ''
                    }, 3000)
                    return
                }

                const ngayBatDau = new Date(phienDauGia.value.ngayBatDau)
                const ngayKetThuc = new Date(phienDauGia.value.ngayKetThuc)
                if (ngayKetThuc <= ngayBatDau) {
                    isError.value = true
                    messageError.value = 'Ngày kết thúc phải lớn hơn ngày bắt đầu!'
                    setTimeout(() => {
                        isError.value = false
                        messageError.value = ''
                    }, 3000)
                    return
                }

                const phiBaoHiemCheck = validatePhiBaoHiem(String(phienDauGia.value.phiBaoHiem))
                if (!phiBaoHiemCheck.isValid) {
                    isErrorPhiBaoHiem.value = true
                    messagePhiBaoHiem.value = phiBaoHiemCheck.message || ''
                    setTimeout(() => {
                        isErrorPhiBaoHiem.value = false
                        messagePhiBaoHiem.value = ''
                    }, 3000)
                    return
                }

                const formData = new FormData()
                Object.entries(phienDauGia.value).forEach(([key, value]) => {
                    if (
                        key !== 'sanPhamDauGias' &&
                        key !== 'lichSuDauGias' &&
                        key !== 'phieuDatCocs' &&
                        value !== undefined
                    ) {
                        formData.append(key, value || '')
                    }
                })

                const response = await updatePhienDauGia(
                    Number(phienDauGia.value.maPhienDauGia),
                    formData
                )
                if (response.success) {
                    await router.push({ name: 'DanhSachPhienDauGiaView' })
                }
            }

            return {
                isError,
                messageError,
                isErrorPhiBaoHiem,
                messagePhiBaoHiem,
                phienDauGia,
                nhanViens,
                loaiDauGias,
                handleCapNhat
            }
        }
    })
</script>
