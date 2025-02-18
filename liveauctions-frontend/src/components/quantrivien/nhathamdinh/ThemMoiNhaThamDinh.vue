<template>
    <div class="container-fluid">
        <div class="row justify-content-evenly m-0 mt-3 mb-3">
            <div class="card-header col-xl-6">
                <h5 class="card-title mb-0">Thêm mới nhà thẩm định</h5>
            </div>
            <div class="card-header col-xl-6 text-end">
                <router-link class="text-success" :to="{ name: 'DanhSachNhaThamDinhView' }">
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
                        v-model="nhaThamDinh.hoVaTen"
                        type="text"
                        class="form-control"
                        id="hoVaTen"
                    />
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label"
                        >Email<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nhaThamDinh.email"
                        type="email"
                        class="form-control"
                        id="email"
                    />
                    <div v-if="isErrorEmail" class="text-danger">
                        {{ messageEmail }}
                    </div>
                </div>
                <div class="mb-3">
                    <label for="gioiTinh" class="form-label"
                        >Giới tính<span class="text-danger">*</span></label
                    >
                    <select
                        v-model="nhaThamDinh.gioiTinh"
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
                    <label for="soDienThoai" class="form-label"
                        >Số điện thoại<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nhaThamDinh.soDienThoai"
                        type="text"
                        class="form-control"
                        id="soDienThoai"
                    />
                    <div v-if="isErrorSoDienThoai" class="text-danger">
                        {{ messageSoDienThoai }}
                    </div>
                </div>
            </div>
            <div class="col-xl-4">
                <div class="mb-3">
                    <label for="diaChi" class="form-label"
                        >Địa chỉ<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nhaThamDinh.diaChi"
                        type="text"
                        class="form-control"
                        id="diaChi"
                    />
                </div>
                <div class="mb-3">
                    <label for="loai" class="form-label"
                        >Loại thẩm định<span class="text-danger">*</span></label
                    >
                    <select v-model="nhaThamDinh.loai" class="form-select">
                        <option value="" disabled selected hidden>Chọn loại</option>
                        <option value="Nội bộ">Nhà thẩm định nội bộ</option>
                        <option value="Bên ngoài">Nhà thẩm định bên ngoài</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="ngaySinh" class="form-label"
                        >Ngày sinh<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="nhaThamDinh.ngaySinh"
                        type="date"
                        class="form-control"
                        id="ngaySinh"
                    />
                </div>
                <div class="mb-3">
                    <label for="moTa" class="form-label">Mô tả</label>
                    <textarea
                        v-model="nhaThamDinh.moTa"
                        class="form-control"
                        id="moTa"
                        rows="3"
                    ></textarea>
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
    import { addNhaThamDinh } from '@/services/quantrivien/nhaThamDinhService'
    import { validateEmail, validateSoDienThoai } from '@/utils/validation'
    import { defineComponent, ref } from 'vue'
    import { useRouter } from 'vue-router'

    export default defineComponent({
        name: 'ThemMoiNhaThamDinh',
        setup() {
            const router = useRouter()
            const fileInput = ref<HTMLInputElement | null>(null)
            const isError = ref(false)
            const isErrorAnh = ref(false)
            const isErrorSoDienThoai = ref(false)
            const isErrorEmail = ref(false)
            const messageError = ref<string>('')
            const messageAnh = ref<string>('')
            const messageSoDienThoai = ref<string>('')
            const messageEmail = ref<string>('')
            const nhaThamDinh = ref<Record<string, undefined>>({})
            const previewImage = ref<string | null>(null)

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
                    !nhaThamDinh.value.hoVaTen ||
                    !nhaThamDinh.value.email ||
                    !nhaThamDinh.value.gioiTinh ||
                    !nhaThamDinh.value.ngaySinh ||
                    !nhaThamDinh.value.soDienThoai ||
                    !nhaThamDinh.value.diaChi ||
                    !nhaThamDinh.value.loai
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

                const soDienThoaiCheck = validateSoDienThoai(String(nhaThamDinh.value.soDienThoai))
                if (!soDienThoaiCheck.isValid) {
                    isErrorSoDienThoai.value = true
                    messageSoDienThoai.value = soDienThoaiCheck.message || ''
                    hasError = true
                }

                const emailCheck = validateEmail(String(nhaThamDinh.value.email))
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
                Object.entries(nhaThamDinh.value).forEach(([key, value]) => {
                    if (value !== undefined) {
                        formData.append(key, value || '')
                    }
                })
                formData.append('file', file)

                const response = await addNhaThamDinh(formData)
                if (response.success) {
                    await router.push({ name: 'DanhSachNhaThamDinhView' })
                }
            }

            return {
                fileInput,
                isError,
                isErrorAnh,
                isErrorSoDienThoai,
                isErrorEmail,
                messageError,
                messageAnh,
                messageSoDienThoai,
                messageEmail,
                previewImage,
                nhaThamDinh,
                handleFileChange,
                handleThemMoi
            }
        }
    })
</script>

<style>
    input[type='file'] {
        display: block;
        margin-top: 10px;
    }
</style>
