<template>
    <div class="container-fluid">
        <div class="row justify-content-evenly m-0 mt-3 mb-3">
            <div class="card-header col-xl-6">
                <h5 class="card-title mb-0">Cập nhật sản phẩm</h5>
            </div>
            <div class="card-header col-xl-6 text-end">
                <router-link class="text-success" :to="{ name: 'DanhSachSanPhamView' }">
                    <i class="fas fa-chevron-circle-left"></i>
                    <span class="ps-1">Quay lại danh sách</span>
                </router-link>
            </div>
        </div>
        <div class="row m-0">
            <div v-if="isError" class="alert alert-danger">
                {{ messageError }}
            </div>
            <div class="col-xl-5">
                <div class="mb-3">
                    <label for="maSanPham" class="form-label"
                        >Mã sản phẩm<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="sanPham.maSanPham"
                        type="text"
                        class="form-control"
                        id="maSanPham"
                    />
                </div>
                <div class="row mb-3">
                    <div class="col-xl-6">
                        <label for="maDanhMuc" class="form-label"
                            >Danh mục cha<span class="text-danger">*</span></label
                        >
                        <select
                            v-model="selectedDanhMuc"
                            class="form-select"
                            aria-label="Default select example"
                        >
                            <option selected disabled>Chọn danh mục cha</option>
                            <template v-for="danhMuc in danhMucs" :key="danhMuc.maDanhMuc">
                                <option :value="danhMuc.maDanhMuc">
                                    {{ danhMuc.tenDanhMuc }}
                                </option>
                            </template>
                        </select>
                    </div>
                    <div class="col-xl-6">
                        <label for="maDanhMucCon" class="form-label"
                            >Danh mục con<span class="text-danger">*</span></label
                        >
                        <select
                            v-model="sanPham.maDanhMucCon"
                            class="form-select"
                            aria-label="Default select example"
                        >
                            <option selected disabled>Chọn danh mục con</option>
                            <template
                                v-for="danhMucCon in danhMucCons"
                                :key="danhMucCon.maDanhMucCon"
                            >
                                <option :value="danhMucCon.maDanhMucCon">
                                    {{ danhMucCon.tenDanhMucCon }}
                                </option>
                            </template>
                        </select>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="moTa" class="form-label">Mô tả</label>
                    <textarea
                        v-model="sanPham.moTa"
                        class="form-control"
                        id="moTa"
                        rows="3"
                    ></textarea>
                </div>
            </div>
            <div class="col-xl-7">
                <div class="mb-3">
                    <label for="tenSanPham" class="form-label"
                        >Tên sản phẩm<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="sanPham.tenSanPham"
                        type="text"
                        class="form-control"
                        id="tenSanPham"
                    />
                </div>
                <div class="row mb-3">
                    <div class="col-xl-6">
                        <label for="maLoaiDauGia" class="form-label"
                            >Loại đấu giá<span class="text-danger">*</span></label
                        >
                        <select
                            v-model="sanPham.maLoaiDauGia"
                            class="form-select"
                            aria-label="Default select example"
                        >
                            <option selected disabled>Chọn loại đấu giá</option>
                            <template
                                v-for="loaiDauGia in loaiDauGias"
                                :key="loaiDauGia.maLoaiDauGia"
                            >
                                <option :value="loaiDauGia.maLoaiDauGia">
                                    {{ loaiDauGia.tenLoaiDauGia }}
                                </option>
                            </template>
                        </select>
                    </div>
                    <div class="col-xl-6">
                        <label for="maNhaKho" class="form-label"
                            >Nhà kho<span class="text-danger">*</span></label
                        >
                        <select
                            v-model="sanPham.maNhaKho"
                            class="form-select"
                            aria-label="Default select example"
                        >
                            <option selected disabled>Chọn nhà kho</option>
                            <template v-for="nhaKho in nhaKhos" :key="nhaKho.maNhaKho">
                                <option :value="nhaKho.maNhaKho">
                                    {{ nhaKho.tenNhaKho }}
                                </option>
                            </template>
                        </select>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-xl-6">
                        <label for="maNguoiBan" class="form-label"
                            >Người bán<span class="text-danger">*</span></label
                        >
                        <select
                            v-model="sanPham.maNguoiBan"
                            class="form-select"
                            aria-label="Default select example"
                        >
                            <option selected disabled>Chọn người bán</option>
                            <template v-for="nguoiBan in nguoiBans" :key="nguoiBan.maNguoiBan">
                                <option :value="nguoiBan.maNguoiBan">
                                    {{ nguoiBan.hoVaTen }}
                                </option>
                            </template>
                        </select>
                    </div>
                    <div class="col-xl-6">
                        <label for="maNhaThamDinh" class="form-label"
                            >Nhà thẩm định<span class="text-danger">*</span></label
                        >
                        <select
                            v-model="sanPham.maNhaThamDinh"
                            class="form-select"
                            aria-label="Default select example"
                        >
                            <option selected disabled>Chọn nhà thẩm định</option>
                            <template
                                v-for="nhaThamDinh in nhaThamDinhs"
                                :key="nhaThamDinh.maNhaThamDinh"
                            >
                                <option :value="nhaThamDinh.maNhaThamDinh">
                                    {{ nhaThamDinh.hoVaTen }}
                                </option>
                            </template>
                        </select>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-xl-4">
                        <label for="giaKhoiDiem" class="form-label"
                            >Giá khởi điểm<span class="text-danger">*</span></label
                        >
                        <input
                            v-model="sanPham.giaKhoiDiem"
                            type="text"
                            class="form-control"
                            id="giaKhoiDiem"
                        />
                        <div v-if="isErrorGiaKhoiDiem" class="text-danger">
                            {{ messageGiaKhoiDiem }}
                        </div>
                    </div>
                    <div class="col-xl-4">
                        <label for="giaNhoNhat" class="form-label"
                            >Giá nhỏ nhất<span class="text-danger">*</span></label
                        >
                        <input
                            v-model="sanPham.giaNhoNhat"
                            type="text"
                            class="form-control"
                            id="giaNhoNhat"
                        />
                        <div v-if="isErrorGiaNhoNhat" class="text-danger">
                            {{ messageGiaNhoNhat }}
                        </div>
                    </div>
                    <div class="col-xl-4">
                        <label for="giaLonNhat" class="form-label"
                            >Giá lớn nhất<span class="text-danger">*</span></label
                        >
                        <input
                            v-model="sanPham.giaLonNhat"
                            type="text"
                            class="form-control"
                            id="giaLonNhat"
                        />
                        <div v-if="isErrorGiaLonNhat" class="text-danger">
                            {{ messageGiaLonNhat }}
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="profile-img text-center">
                    <input
                        type="file"
                        name="files"
                        ref="fileInput"
                        @change="handleFileChange"
                        accept="image/*"
                        multiple
                        class="form-control mt-2"
                    />
                    <div class="image-preview mt-3 d-flex flex-wrap">
                        <div
                            v-for="(img, index) in previewImages"
                            :key="index"
                            class="position-relative me-2 mb-2"
                        >
                            <img
                                :src="img"
                                alt="Ảnh xem trước"
                                width="120px"
                                height="120px"
                                class="border rounded"
                            />
                            <button
                                @click="removeImage(index)"
                                class="btn btn-danger btn-sm position-absolute top-0 end-0"
                            >
                                X
                            </button>
                        </div>
                    </div>
                    <p v-if="isErrorAnh" class="text-danger">{{ messageAnh }}</p>
                </div>
            </div>
            <div class="text-center mt-3">
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
    import { getDanhMucConsByMaDanhMuc } from '@/services/nhanvien/danhMucConService'
    import { getDanhMucByMaDanhMucCon, getDanhMucs } from '@/services/nhanvien/danhMucService'
    import { getLoaiDauGias } from '@/services/nhanvien/loaiDauGiaService'
    import { getSanPhamByMaSanPham, updateSanPham } from '@/services/nhanvien/sanPhamService'
    import { getNguoiBans } from '@/services/quantrivien/nguoiBanService'
    import { getNhaKhos } from '@/services/quantrivien/nhaKhoService'
    import { getNhaThamDinhs } from '@/services/quantrivien/nhaThamDinhService'
    import { validateChuSo } from '@/utils/validation'
    import { defineComponent, onMounted, ref, watch } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    export default defineComponent({
        name: 'CapNhatSanPham',
        setup() {
            const route = useRoute()
            const router = useRouter()
            const isError = ref(false)
            const messageError = ref<string>('')
            const isErrorAnh = ref(false)
            const messageAnh = ref<string>('')
            const isErrorPhiBaoHiem = ref(false)
            const messagePhiBaoHiem = ref<string>('')
            const isErrorGiaKhoiDiem = ref(false)
            const messageGiaKhoiDiem = ref<string>('')
            const isErrorGiaNhoNhat = ref(false)
            const messageGiaNhoNhat = ref<string>('')
            const isErrorGiaLonNhat = ref(false)
            const messageGiaLonNhat = ref<string>('')
            const sanPham = ref<Record<string, undefined>>({})
            const nguoiBans = ref<Record<string, unknown>[]>([])
            const loaiDauGias = ref<Record<string, unknown>[]>([])
            const previewImages = ref<string[]>([])
            const oldImages = ref<string[]>([])
            const deletedImageNames = ref<string[]>([])
            const selectedFiles = ref<File[]>([])
            const fileInput = ref<HTMLInputElement | null>(null)
            const nhaKhos = ref<Record<string, unknown>[]>([])
            const nhaThamDinhs = ref<Record<string, unknown>[]>([])
            const danhMucs = ref<Record<string, unknown>[]>([])
            const danhMucCons = ref<Record<string, unknown>[]>([])
            const selectedDanhMuc = ref<string | null>(null)

            const fetchDanhMucs = async () => {
                const response = await getDanhMucs()
                danhMucs.value = response
            }

            const fetchDanhMucCons = async (maDanhMucCon: number) => {
                const result = await getDanhMucConsByMaDanhMuc(maDanhMucCon)
                danhMucCons.value = result
                selectedDanhMuc.value =
                    result.find(
                        (dmc: { maDanhMucCon: number }) => dmc.maDanhMucCon === maDanhMucCon
                    )?.maDanhMuc || null
            }

            watch(
                () => sanPham.value.maDanhMucCon,
                async (newMaDanhMucCon) => {
                    if (newMaDanhMucCon) {
                        try {
                            const danhMucResponse = await getDanhMucByMaDanhMucCon(newMaDanhMucCon)
                            selectedDanhMuc.value = danhMucResponse.maDanhMuc

                            const danhMucConResponse = await getDanhMucConsByMaDanhMuc(
                                danhMucResponse.maDanhMuc
                            )
                            danhMucCons.value = danhMucConResponse
                        } catch (error) {
                            console.error('Lỗi khi lấy dữ liệu danh mục:', error)
                        }
                    }
                }
            )

            watch(selectedDanhMuc, async (newMaDanhMuc) => {
                if (newMaDanhMuc) {
                    try {
                        const danhMucConResponse = await getDanhMucConsByMaDanhMuc(
                            Number(newMaDanhMuc)
                        )
                        danhMucCons.value = danhMucConResponse

                        if (
                            !danhMucCons.value.some(
                                (dmc) => dmc.maDanhMucCon === sanPham.value.maDanhMucCon
                            )
                        ) {
                            sanPham.value.maDanhMucCon = undefined
                        }
                    } catch (error) {
                        console.error('Lỗi khi lấy danh sách danh mục con:', error)
                    }
                }
            })

            const fetchNhaThamDinhs = async () => {
                const response = await getNhaThamDinhs()
                nhaThamDinhs.value = response
            }

            const fetchNhaKhos = async () => {
                const response = await getNhaKhos()
                nhaKhos.value = response
            }

            const fetchNguoiBans = async () => {
                const response = await getNguoiBans()
                nguoiBans.value = response.map((item: { nguoiDung: unknown }) => item.nguoiDung)
            }

            const fetchLoaiDauGias = async () => {
                const response = await getLoaiDauGias()
                loaiDauGias.value = response
            }

            const fetchSanPham = async () => {
                const response = await getSanPhamByMaSanPham(String(route.params.maSanPham))
                sanPham.value = response.sanPham

                oldImages.value = response.anhSanPhams.map((img: { tenAnh: string }) => img.tenAnh)
                previewImages.value = [...oldImages.value]

                if (sanPham.value.maDanhMucCon) {
                    await fetchDanhMucCons(sanPham.value.maDanhMucCon)
                }
            }

            onMounted(() => {
                fetchSanPham()
                fetchNguoiBans()
                fetchLoaiDauGias()
                fetchNhaKhos()
                fetchNhaThamDinhs()
                fetchDanhMucs()
            })

            const handleFileChange = (event: Event) => {
                const target = event.target as HTMLInputElement
                const files = target.files
                if (!files?.length) return

                selectedFiles.value = Array.from(files)

                for (const file of selectedFiles.value) {
                    const objectUrl = URL.createObjectURL(file)
                    previewImages.value.push(objectUrl)
                }
            }

            const removeImage = (index: number) => {
                const imageUrl = previewImages.value[index]

                if (oldImages.value.includes(imageUrl)) {
                    oldImages.value.splice(oldImages.value.indexOf(imageUrl), 1)
                    deletedImageNames.value.push(imageUrl)
                } else {
                    URL.revokeObjectURL(imageUrl)
                    selectedFiles.value.splice(index - oldImages.value.length, 1)
                }
                previewImages.value.splice(index, 1)

                if (previewImages.value.length === 0 && fileInput.value) {
                    fileInput.value.value = ''
                }
            }

            const handleCapNhat = async () => {
                let hasError = false

                if (
                    !sanPham.value.maSanPham ||
                    !selectedDanhMuc.value ||
                    !sanPham.value.maDanhMucCon ||
                    !sanPham.value.tenSanPham ||
                    !sanPham.value.maLoaiDauGia ||
                    !sanPham.value.maNhaKho ||
                    !sanPham.value.maNguoiBan ||
                    !sanPham.value.maNhaThamDinh ||
                    !sanPham.value.giaKhoiDiem ||
                    !sanPham.value.giaNhoNhat ||
                    !sanPham.value.giaLonNhat
                ) {
                    isError.value = true
                    messageError.value = 'Vui lòng nhập đầy đủ các trường dữ liệu!'
                    setTimeout(() => {
                        isError.value = false
                        messageError.value = ''
                    }, 3000)
                    return
                }

                const giaLonNhat = Number(sanPham.value.giaLonNhat)
                const giaNhoNhat = Number(sanPham.value.giaNhoNhat)
                if (giaLonNhat <= giaNhoNhat) {
                    isError.value = true
                    messageError.value = 'Giá lớn nhất phải lớn hơn giá nhỏ nhất!'
                    setTimeout(() => {
                        isError.value = false
                        messageError.value = ''
                    }, 3000)
                    return
                }

                if (previewImages.value.length === 0) {
                    isErrorAnh.value = true
                    messageAnh.value = 'Vui lòng chọn ảnh!'
                    setTimeout(() => {
                        isError.value = false
                        messageError.value = ''
                    }, 3000)
                    return
                }

                const giaKhoiDiemCheck = validateChuSo(String(sanPham.value.giaKhoiDiem))
                if (!giaKhoiDiemCheck.isValid) {
                    isErrorGiaKhoiDiem.value = true
                    messageGiaKhoiDiem.value = 'Giá khởi điếm chỉ được chứa chữ số!'
                    hasError = true
                }

                const giaNhoNhatCheck = validateChuSo(String(sanPham.value.giaNhoNhat))
                if (!giaNhoNhatCheck.isValid) {
                    isErrorGiaNhoNhat.value = true
                    messageGiaNhoNhat.value = 'Giá khởi điếm chỉ được chứa chữ số!'
                    hasError = true
                }

                const giaLonNhatCheck = validateChuSo(String(sanPham.value.giaLonNhat))
                if (!giaLonNhatCheck.isValid) {
                    isErrorGiaLonNhat.value = true
                    messageGiaLonNhat.value = 'Giá khởi điếm chỉ được chứa chữ số!'
                    hasError = true
                }

                if (hasError) {
                    setTimeout(() => {
                        isErrorGiaKhoiDiem.value = false
                        messageGiaKhoiDiem.value = ''
                        isErrorGiaNhoNhat.value = false
                        messageGiaNhoNhat.value = ''
                        isErrorGiaLonNhat.value = false
                        messageGiaLonNhat.value = ''
                    }, 3000)
                    return
                }

                const formData = new FormData()
                Object.entries(sanPham.value).forEach(([key, value]) => {
                    if (
                        key !== 'sanPhamDauGias' &&
                        key !== 'lichSuDauGias' &&
                        key !== 'anhSanPhams' &&
                        value !== undefined
                    ) {
                        formData.append(key, value || '')
                    }
                })

                selectedFiles.value.forEach((file) => {
                    formData.append('anhSanPhamList', file)
                })

                deletedImageNames.value.forEach((fileName) => {
                    formData.append('deletedImageNames', fileName)
                })

                const response = await updateSanPham(String(sanPham.value.maSanPham), formData)
                if (response.success) {
                    await router.push({ name: 'DanhSachSanPhamView' })
                }
            }

            return {
                isError,
                messageError,
                isErrorPhiBaoHiem,
                messagePhiBaoHiem,
                sanPham,
                nguoiBans,
                loaiDauGias,
                handleCapNhat,
                previewImages,
                handleFileChange,
                removeImage,
                isErrorAnh,
                messageAnh,
                fileInput,
                isErrorGiaKhoiDiem,
                messageGiaKhoiDiem,
                isErrorGiaNhoNhat,
                messageGiaNhoNhat,
                isErrorGiaLonNhat,
                messageGiaLonNhat,
                nhaKhos,
                nhaThamDinhs,
                danhMucs,
                danhMucCons,
                selectedDanhMuc
            }
        }
    })
</script>
