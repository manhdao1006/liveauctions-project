<template>
    <div class="container-fluid">
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
        <div class="row justify-content-evenly m-0 mt-3 mb-3">
            <div class="card-header col-xl-6">
                <h5 class="card-title mb-0">Thêm mới nhân viên</h5>
            </div>
            <div class="card-header col-xl-6 text-end">
                <router-link
                    class="text-success"
                    :to="{ name: 'DanhSachNguoiDungView', params: { maVaiTro: maVaiTroPrams } }"
                >
                    <i class="fas fa-chevron-circle-left"></i>
                    <span class="ps-1">Quay lại danh sách</span>
                </router-link>
            </div>
        </div>
        <div class="row m-0">
            <div v-if="isError" class="alert alert-danger">
                {{ messageError }}
            </div>
            <div class="col-xl-4">
                <div class="mb-3">
                    <label for="hoVaTen" class="form-label"
                        >Họ và tên<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nguoiDung.hoVaTen"
                        type="text"
                        class="form-control"
                        id="hoVaTen"
                    />
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label"
                        >Email<span class="text-danger">*</span></label
                    >
                    <input v-model="nguoiDung.email" type="email" class="form-control" id="email" />
                    <div v-if="isErrorEmail" class="text-danger">
                        {{ messageEmail }}
                    </div>
                </div>
                <div class="mb-3">
                    <label for="gioiTinh" class="form-label"
                        >Giới tính<span class="text-danger">*</span></label
                    >
                    <select
                        v-model="nguoiDung.gioiTinh"
                        class="form-select"
                        aria-label="Default select example"
                    >
                        <option selected disabled>Chọn giới tính</option>
                        <option value="Nam">Nam</option>
                        <option value="Nữ">Nữ</option>
                        <option value="Khác">Khác</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="viTri" class="form-label"
                        >Vị trí<span class="text-danger">*</span></label
                    >
                    <input v-model="nhanVien.viTri" type="text" class="form-control" id="viTri" />
                </div>
                <div class="mb-3">
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
            </div>
            <div class="col-xl-4">
                <div class="mb-3">
                    <label for="ngaySinh" class="form-label"
                        >Ngày sinh<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nguoiDung.ngaySinh"
                        type="date"
                        class="form-control"
                        id="ngaySinh"
                    />
                </div>
                <div class="mb-3">
                    <label for="matKhau" class="form-label"
                        >Mật khẩu<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nguoiDung.matKhau"
                        type="text"
                        class="form-control"
                        id="matKhau"
                    />
                </div>
                <div class="mb-3">
                    <label for="soDienThoai" class="form-label"
                        >Số điện thoại<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nguoiDung.soDienThoai"
                        type="text"
                        class="form-control"
                        id="soDienThoai"
                    />
                    <div v-if="isErrorSoDienThoai" class="text-danger">
                        {{ messageSoDienThoai }}
                    </div>
                </div>
                <div class="mb-3">
                    <label for="diaChi" class="form-label"
                        >Địa chỉ<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nguoiDung.diaChi"
                        type="text"
                        class="form-control"
                        id="diaChi"
                    />
                </div>
                <div class="mb-3">
                    <label for="maPhuongXa" class="form-label"
                        >Phường/Xã<span class="text-danger">*</span></label
                    >
                    <select
                        v-model="nguoiDung.maPhuongXa"
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
            <div class="col-xl-4">
                <div class="profile-img text-center">
                    <img
                        :src="
                            previewImage ||
                            'https://res.cloudinary.com/springboot-cloud/image/upload/v1739427632/user_vqmka8.png'
                        "
                        alt=""
                        width="240px"
                        height="240px"
                    />
                    <input
                        type="file"
                        name="file"
                        ref="fileInput"
                        @change="handleFileChange"
                        accept="image/*"
                        class="form-control mt-2"
                    />
                    <p v-if="isErrorAnh" class="text-danger">{{ messageAnh }}</p>
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
    import { getPhuongXasByMaQuanHuyen, getQuanHuyens } from '@/services/authService'
    import { addNhanVien } from '@/services/quantrivien/nhanVienService'
    import { validateEmail, validateSoDienThoai } from '@/utils/validation'
    import { defineComponent, onMounted, ref, watch } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    export default defineComponent({
        name: 'ThemMoiNhanVien',
        setup() {
            const route = useRoute()
            const router = useRouter()
            const fileInput = ref<HTMLInputElement | null>(null)
            const isError = ref(false)
            const messageError = ref<string>('')
            const nguoiDung = ref<Record<string, undefined>>({})
            const nhanVien = ref<Record<string, undefined>>({})
            const quanHuyens = ref<Array<{ maQuanHuyen: number; tenQuanHuyen: string }>>([])
            const phuongXas = ref<Array<{ maPhuongXa: number; tenPhuongXa: string }>>([])
            const selectedQuanHuyen = ref<string | null>(null)
            const maVaiTroPrams = Number(route.params.maVaiTro)
            const isErrorEmail = ref(false)
            const messageEmail = ref<string>('')
            const isErrorSoDienThoai = ref(false)
            const messageSoDienThoai = ref<string>('')
            const isErrorAnh = ref(false)
            const messageAnh = ref<string>('')
            const previewImage = ref<string | null>(null)

            const fetchQuanHuyens = async () => {
                const result = await getQuanHuyens()
                quanHuyens.value = result
            }

            const fetchPhuongXas = async (maQuanHuyen: number) => {
                const result = await getPhuongXasByMaQuanHuyen(maQuanHuyen)
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

            const handleFileChange = (event: Event) => {
                const target = event.target as HTMLInputElement
                const file = target.files?.[0]

                if (file) {
                    previewImage.value = URL.createObjectURL(file)
                    isError.value = false
                } else {
                    previewImage.value = null
                    isError.value = true
                }
            }

            const handleThemMoi = async () => {
                const file = fileInput.value?.files?.[0]
                let hasError = false

                if (
                    !nguoiDung.value.hoVaTen ||
                    !nguoiDung.value.email ||
                    !nguoiDung.value.matKhau ||
                    !nguoiDung.value.gioiTinh ||
                    !nguoiDung.value.diaChi ||
                    !selectedQuanHuyen.value ||
                    !nguoiDung.value.maPhuongXa ||
                    !nguoiDung.value.soDienThoai ||
                    !nguoiDung.value.ngaySinh ||
                    !nhanVien.value.viTri
                ) {
                    isError.value = true
                    messageError.value = 'Vui lòng nhập đầy đủ các trường dữ liệu!'
                    setTimeout(() => {
                        isError.value = false
                        messageError.value = ''
                    }, 3000)
                    return
                }

                if (!file) {
                    isErrorAnh.value = true
                    messageAnh.value = 'Vui lòng chọn ảnh!'
                    hasError = true
                    return
                }

                const soDienThoaiCheck = validateSoDienThoai(String(nguoiDung.value.soDienThoai))
                if (!soDienThoaiCheck.isValid) {
                    isErrorSoDienThoai.value = true
                    messageSoDienThoai.value = soDienThoaiCheck.message || ''
                    hasError = true
                }

                const emailCheck = validateEmail(String(nguoiDung.value.email))
                if (!emailCheck.isValid) {
                    isErrorEmail.value = true
                    messageEmail.value = emailCheck.message || ''
                    hasError = true
                }

                if (hasError) {
                    setTimeout(() => {
                        isErrorAnh.value = false
                        messageAnh.value = ''
                        isErrorSoDienThoai.value = false
                        messageSoDienThoai.value = ''
                        isErrorEmail.value = false
                        messageEmail.value = ''
                    }, 3000)
                    return
                }

                const formData = new FormData()
                Object.entries(nguoiDung.value).forEach(([key, value]) => {
                    if (value !== undefined) {
                        formData.append(key, value || '')
                    }
                })
                Object.entries(nhanVien.value).forEach(([key, value]) => {
                    if (value !== undefined) {
                        formData.append(key, value || '')
                    }
                })
                formData.append('file', file)

                const response = await addNhanVien(formData)
                if (response.success) {
                    await router.push({
                        name: 'DanhSachNguoiDungView',
                        params: { maVaiTro: maVaiTroPrams }
                    })
                }
            }

            return {
                fileInput,
                isError,
                messageError,
                isErrorEmail,
                messageEmail,
                isErrorSoDienThoai,
                messageSoDienThoai,
                isErrorAnh,
                messageAnh,
                selectedQuanHuyen,
                nguoiDung,
                nhanVien,
                quanHuyens,
                phuongXas,
                previewImage,
                handleFileChange,
                handleThemMoi,
                maVaiTroPrams
            }
        }
    })
</script>
