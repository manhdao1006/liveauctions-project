<template>
    <div class="container-fluid">
        <div class="row justify-content-evenly m-0 mt-3 mb-3">
            <div class="card-header col-xl-6">
                <h5 class="card-title mb-0">Thêm mới danh mục</h5>
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
                    <label for="chonDanhMuc" class="form-label">Chọn loại danh mục</label>
                    <div>
                        <label>
                            <input type="radio" v-model="isAddChildCategory" :value="false" /> Danh
                            mục cha
                        </label>
                        <label class="ms-3">
                            <input type="radio" v-model="isAddChildCategory" :value="true" /> Danh
                            mục con
                        </label>
                    </div>
                </div>
            </div>

            <div class="col-xl-4" v-if="!isAddChildCategory">
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

            <div class="col-xl-4" v-if="isAddChildCategory">
                <div class="mb-3">
                    <label for="tenDanhMucCon" class="form-label"
                        >Tên danh mục con<span class="text-danger">*</span></label
                    >
                    <input
                        v-model="danhMucCon.tenDanhMucCon"
                        type="text"
                        class="form-control"
                        id="tenDanhMucCon"
                    />
                </div>
            </div>

            <div class="col-xl-4" v-if="isAddChildCategory">
                <div class="mb-3">
                    <label for="maDanhMuc" class="form-label"
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
    import { addDanhMucCon } from '@/services/quantrivien/danhMucConService'
    import { addDanhMuc, getDanhMucs } from '@/services/quantrivien/danhMucService'
    import { defineComponent, onMounted, Ref, ref } from 'vue'
    import { useRouter } from 'vue-router'

    export default defineComponent({
        name: 'ThemMoiDanhMuc',
        setup() {
            const router = useRouter()
            const isError = ref(false)
            const messageError = ref<string>('')
            const danhMuc: Ref<Record<string, string>> = ref({})
            const danhMucCon: Ref<Record<string, string>> = ref({})
            const danhMucs: Ref<Record<string, unknown>[]> = ref([])
            const isAddChildCategory = ref(false)

            const fetchDanhMucs = async () => {
                const response = await getDanhMucs()
                danhMucs.value = response
            }

            onMounted(() => {
                fetchDanhMucs()
            })

            const handleThemMoi = async () => {
                if (isAddChildCategory.value === true) {
                    if (!danhMucCon.value.tenDanhMucCon || !danhMucCon.value.maDanhMuc) {
                        isError.value = true
                        messageError.value = 'Vui lòng nhập đầy đủ các trường dữ liệu!'
                        setTimeout(() => {
                            isError.value = false
                            messageError.value = ''
                        }, 3000)
                        return
                    }
                } else {
                    if (!danhMuc.value.tenDanhMuc) {
                        isError.value = true
                        messageError.value = 'Vui lòng nhập đầy đủ các trường dữ liệu!'
                        setTimeout(() => {
                            isError.value = false
                            messageError.value = ''
                        }, 3000)
                        return
                    }
                }
                const formData = new FormData()
                const categoryData = isAddChildCategory.value ? danhMucCon.value : danhMuc.value
                Object.entries(categoryData).forEach(([key, value]) => {
                    if (value !== undefined) {
                        formData.append(key, value || '')
                    }
                })

                let response
                if (isAddChildCategory.value) {
                    response = await addDanhMucCon(formData)
                } else {
                    response = await addDanhMuc(formData)
                }

                if (response.success) {
                    await router.push({ name: 'DanhSachDanhMucView' })
                }
            }

            return {
                isError,
                messageError,
                danhMuc,
                danhMucCon,
                danhMucs,
                handleThemMoi,
                isAddChildCategory
            }
        }
    })
</script>
