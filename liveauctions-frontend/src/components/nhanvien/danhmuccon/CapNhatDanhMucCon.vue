<template>
    <div class="container-fluid">
        <div class="row justify-content-evenly m-0 mt-3 mb-3">
            <div class="card-header col-xl-6">
                <h5 class="card-title mb-0">Cập nhật danh mục</h5>
            </div>
            <div class="card-header col-xl-6 text-end">
                <router-link class="text-success" :to="{ name: 'DanhSachDanhMucConView' }">
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
                    <label for="tenDanhMucCon" class="form-label"
                        >Tên danh mục cha<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="danhMucCon.tenDanhMucCon"
                        type="text"
                        class="form-control"
                        id="tenDanhMucCon"
                    />
                </div>
            </div>
            <div class="col-xl-4">
                <div class="mb-3">
                    <label for="maDanhMucCon" class="form-label"
                        >Danh mục cha<span class="text-danger">*</span></label
                    >
                    <select
                        v-model="danhMucCon.maDanhMuc"
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
    import {
        getDanhMucConByMaDanhMucCon,
        updateDanhMucCon
    } from '@/services/nhanvien/danhMucConService'
    import { getDanhMucs } from '@/services/nhanvien/danhMucService'
    import { defineComponent, onMounted, Ref, ref } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    export default defineComponent({
        name: 'CapNhatDanhMucCon',
        setup() {
            const route = useRoute()
            const router = useRouter()
            const isError = ref(false)
            const messageError = ref<string>('')
            const danhMucCon: Ref<Record<string, string>> = ref({})
            const danhMucs: Ref<Record<string, unknown>[]> = ref([])

            const fetchDanhMucCon = async () => {
                const response = await getDanhMucConByMaDanhMucCon(
                    Number(route.params.maDanhMucCon)
                )
                danhMucCon.value = response
            }

            const fetchDanhMucs = async () => {
                const response = await getDanhMucs()
                danhMucs.value = response
            }

            onMounted(() => {
                fetchDanhMucs()
                fetchDanhMucCon()
            })

            const handleCapNhat = async () => {
                if (!danhMucCon.value.tenDanhMucCon || !danhMucCon.value.maDanhMuc) {
                    isError.value = true
                    messageError.value = 'Vui lòng nhập đầy đủ các trường dữ liệu!'
                    setTimeout(() => {
                        isError.value = false
                        messageError.value = ''
                    }, 3000)
                    return
                }
                const formData = new FormData()
                Object.entries(danhMucCon.value).forEach(([key, value]) => {
                    if (key !== 'sanPhams' && key !== 'sanPhamDangKys' && value !== undefined) {
                        formData.append(key, value || '')
                    }
                })

                const response = await updateDanhMucCon(
                    Number(danhMucCon.value.maDanhMucCon),
                    formData
                )
                if (response.success) {
                    await router.push({ name: 'DanhSachDanhMucConView' })
                }
            }

            return {
                isError,
                messageError,
                danhMucCon,
                danhMucs,
                handleCapNhat
            }
        }
    })
</script>
