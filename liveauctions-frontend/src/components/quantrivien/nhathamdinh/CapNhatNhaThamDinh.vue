<template>
    <div class="container-fluid">
        <div class="row justify-content-evenly m-0 mt-3 mb-3">
            <div class="card-header col-xl-6">
                <h5 class="card-title mb-0">Cập nhật nhà thẩm định</h5>
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
                    <label for="hoVaTen" class="form-label">Họ và tên<span class="text-danger">*</span></label>
                    <input
                        type="text"
                        class="form-control"
                        id="hoVaTen"
                        v-model="nhaThamDinh.hoVaTen"
                    />
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email<span class="text-danger">*</span></label>
                    <input
                        type="email"
                        class="form-control"
                        id="email"
                        v-model="nhaThamDinh.email"
                    />
                    <div v-if="isErrorEmail" class="text-danger">
                        {{ messageEmail }}
                    </div>
                </div>
                <div class="mb-3">
                    <label for="gioiTinh" class="form-label">Giới tính<span class="text-danger">*</span></label>
                    <select
                        class="form-select"
                        aria-label="Default select example"
                        v-model="nhaThamDinh.gioiTinh"
                    >
                        <option selected disabled>Chọn giới tính</option>
                        <option value="Nam">Nam</option>
                        <option value="Nữ">Nữ</option>
                        <option value="Khác">Khác</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="soDienThoai" class="form-label">Số điện thoại<span class="text-danger">*</span></label>
                    <input
                        type="text"
                        class="form-control"
                        id="soDienThoai"
                        v-model="nhaThamDinh.soDienThoai"
                    />
                    <div v-if="isErrorSoDienThoai" class="text-danger">
                        {{ messageSoDienThoai }}
                    </div>
                </div>
            </div>
            <div class="col-xl-4">
                <div class="mb-3">
                    <label for="diaChi" class="form-label">Địa chỉ<span class="text-danger">*</span></label>
                    <input
                        type="text"
                        class="form-control"
                        id="diaChi"
                        v-model="nhaThamDinh.diaChi"
                    />
                </div>
                <div class="mb-3">
                    <label for="loai" class="form-label">Loại thẩm định<span class="text-danger">*</span></label>
                    <select
                        class="form-select"
                        aria-label="Default select example"
                        v-model="nhaThamDinh.loai"
                    >
                        <option selected disabled>Chọn loại</option>
                        <option value="Nội bộ">Nhà thẩm định nội bộ</option>
                        <option value="Bên ngoài">Nhà thẩm định bên ngoài</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="trangThaiHoatDong" class="form-label">Trạng thái hoạt động<span class="text-danger">*</span></label>
                    <select
                        class="form-select"
                        aria-label="Default select example"
                        v-model="nhaThamDinh.trangThaiHoatDong"
                    >
                        <option selected disabled>Chọn tình trạng</option>
                        <option value="Hoạt động">Hoạt động</option>
                        <option value="Không hoạt động">Không hoạt động</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="ngaySinh" class="form-label">Ngày sinh<span class="text-danger">*</span></label>
                    <input
                        type="date"
                        class="form-control"
                        id="ngaySinh"
                        v-model="nhaThamDinh.ngaySinh"
                    />
                </div>
                <div class="mb-3">
                    <label for="moTa" class="form-label">Mô tả</label>
                    <textarea
                        class="form-control"
                        id="moTa"
                        rows="3"
                        v-model="nhaThamDinh.moTa"
                    ></textarea>
                </div>
            </div>
            <div class="col-xl-4">
                <div class="profile-img text-center">
                    <img
                        :src="previewAvatar"
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
                    <p v-if="isErrorAnh" class="text-danger">{{ messageAnh}}</p>
                </div>
            </div>
            <div class="text-center">
                <button type="button" class="btn btn-success" title="Cập nhật" @click.prevent="handleCapNhat">
                    Cập nhật
                </button>
            </div>
        </div>
    </div>
</template>
<script lang="ts">
    import { getNhaThamDinhByMaNhaThamDinh, updateNhaThamDinh } from '@/services/quantrivien/nhaThamDinhService';
    import { validateEmail, validateSoDienThoai } from '@/utils/validation';
    import { defineComponent, onMounted, ref } from 'vue'
    import { useRoute, useRouter } from 'vue-router';

    export default defineComponent({
        name: 'CapNhatNhaThamDinh',
        setup() {
            const router = useRouter()
            const route = useRoute()
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
            const previewAvatar = ref<string>('')

            onMounted(async () => {
                const maNhaThamDinh = Number(route.params.maNhaThamDinh)
                nhaThamDinh.value = await getNhaThamDinhByMaNhaThamDinh(maNhaThamDinh)
                previewAvatar.value = nhaThamDinh.value.avatar || 'https://res.cloudinary.com/springboot-cloud/image/upload/v1739427632/user_vqmka8.png'
            })

            const handleFileChange = (event: Event) => {
                const input = event.target as HTMLInputElement
                if (input.files && input.files[0]) {
                    const file = input.files[0]
                    previewAvatar.value = URL.createObjectURL(file)
                    isErrorAnh.value = false
                } else {
                    isErrorAnh.value = true
                }
            }

            const handleCapNhat = async () => {
                const file = fileInput.value?.files?.[0]

                let hasError = false

                if (!nhaThamDinh.value.hoVaTen || !nhaThamDinh.value.email || !nhaThamDinh.value.ngaySinh ||
                    !nhaThamDinh.value.soDienThoai || !nhaThamDinh.value.diaChi) {
                    isError.value = true
                    messageError.value = 'Vui lòng nhập đầy đủ các trường dữ liệu!'
                    setTimeout(() => {
                        isError.value = false
                        messageError.value = ''
                    }, 3000)
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
                    if (key !== 'sanPhams' && value !== undefined) {
                        formData.append(key, value || '')
                    }
                })
                if (file) {
                    formData.append('file', file)
                }

                const response = await updateNhaThamDinh(Number(nhaThamDinh.value.maNhaThamDinh), formData)
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
                previewAvatar,
                nhaThamDinh,
                handleFileChange,
                handleCapNhat,
            }
        }
    })
</script>

<style>
    input[type="file"] {
        display: block;
        margin-top: 10px;
    }
</style>
