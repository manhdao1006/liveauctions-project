<template>
    <div class="container-fluid">
        <div class="row justify-content-evenly m-0">
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
            <div class="col-xl-4">
                <div class="mb-3">
                    <label for="hoVaTen" class="form-label">Họ và tên<span class="text-danger">*</span></label>
                    <input
                        type="text"
                        class="form-control"
                        id="hoVaTen"
                        required
                        v-model="nhaThamDinh.hoVaTen"
                    />
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email<span class="text-danger">*</span></label>
                    <input
                        type="email"
                        class="form-control"
                        id="email"
                        required
                        v-model="nhaThamDinh.email"
                    />
                </div>
                <div class="mb-3">
                    <label for="gioiTinh" class="form-label">Giới tính<span class="text-danger">*</span></label>
                    <select
                        class="form-select"
                        aria-label="Default select example"
                        required
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
                        required
                        v-model="nhaThamDinh.soDienThoai"
                    />
                </div>
            </div>
            <div class="col-xl-4">
                <div class="mb-3">
                    <label for="diaChi" class="form-label">Địa chỉ<span class="text-danger">*</span></label>
                    <input
                        type="text"
                        class="form-control"
                        id="diaChi"
                        required
                        v-model="nhaThamDinh.diaChi"
                    />
                </div>
                <div class="mb-3">
                    <label for="loai" class="form-label">Loại thẩm định<span class="text-danger">*</span></label>
                    <select
                        class="form-select"
                        aria-label="Default select example"
                        required
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
                        required
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
                        required
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
                        width="80%"
                        height="80%"
                    />
                    <input
                        type="file"
                        name="file"
                        ref="fileInput"
                        @change="handleFileChange"
                        required
                        accept="image/*"
                        class="form-control mt-2"
                    />
                    <p v-if="error" class="text-danger">Vui lòng chọn ảnh.</p>
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
    import { defineComponent, onMounted, ref } from 'vue'
    import { useRoute, useRouter } from 'vue-router';

    export default defineComponent({
        name: 'CapNhatNhaThamDinh',
        setup() {
            const router = useRouter()
            const route = useRoute()
            const fileInput = ref<HTMLInputElement | null>(null)
            const error = ref(false)
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
                    error.value = false
                } else {
                    error.value = true
                }
            }

            const handleCapNhat = async () => {
                const file = fileInput.value?.files?.[0]
                error.value = false
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
                error,
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
