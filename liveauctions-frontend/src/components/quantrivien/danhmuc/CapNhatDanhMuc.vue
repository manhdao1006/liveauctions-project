<template>
    <div class="container-fluid">
        <div class="row justify-content-evenly m-0 mt-3 mb-3">
            <div class="card-header col-xl-6">
                <h5 class="card-title mb-0">Cập nhật danh mục</h5>
            </div>
            <div class="card-header col-xl-6 text-end">
                <router-link class="text-success" :to="{ name: 'DanhSachDanhMucView' }">
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
                    <label for="tenDanhMuc" class="form-label"
                        >Tên danh mục cha<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="danhMuc.tenDanhMuc"
                        type="text"
                        class="form-control"
                        id="tenDanhMuc"
                    />
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
    import { getDanhMucByMaDanhMuc, updateDanhMuc } from '@/services/quantrivien/danhMucService'
    import { defineComponent, onMounted, Ref, ref } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    export default defineComponent({
        name: 'CapNhatDanhMuc',
        setup() {
            const route = useRoute()
            const router = useRouter()
            const isError = ref(false)
            const messageError = ref<string>('')
            const danhMuc: Ref<Record<string, string>> = ref({})

            const fetchDanhMuc = async () => {
                const response = await getDanhMucByMaDanhMuc(Number(route.params.maDanhMuc))
                danhMuc.value = response
            }

            onMounted(() => {
                fetchDanhMuc()
            })

            const handleCapNhat = async () => {
                if (!danhMuc.value.tenDanhMuc) {
                    isError.value = true
                    messageError.value = 'Vui lòng nhập đầy đủ các trường dữ liệu!'
                    setTimeout(() => {
                        isError.value = false
                        messageError.value = ''
                    }, 3000)
                    return
                }
                const formData = new FormData()
                Object.entries(danhMuc.value).forEach(([key, value]) => {
                    if (key !== 'danhMucCons' && value !== undefined) {
                        formData.append(key, value || '')
                    }
                })

                const response = await updateDanhMuc(Number(danhMuc.value.maDanhMuc), formData)
                if (response.success) {
                    await router.push({ name: 'DanhSachDanhMucView' })
                }
            }

            return {
                isError,
                messageError,
                danhMuc,
                handleCapNhat
            }
        }
    })
</script>
