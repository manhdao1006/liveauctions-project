<template>
    <div class="bg-custom">
        <div class="container">
            <div class="align-content-center block-navbar hide-navigation" id="blockNavbar">
                <ul class="nav justify-content-center">
                    <template v-for="danhMuc in danhMucs" :key="danhMuc.maDanhMuc">
                        <NavItem link="#" :content="danhMuc.tenDanhMuc" />
                    </template>
                    <NavItem link="#" content="Danh mục khác" />
                </ul>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import { getNavigations } from '@/services/nhanvien/danhMucService'
    import { defineComponent, onMounted, ref } from 'vue'
    import NavItem from '../dungchung/NavItem.vue'

    export default defineComponent({
        name: 'NavigationLayout',
        components: {
            NavItem
        },
        setup() {
            const danhMucs = ref<Record<string, undefined>[]>([])

            const fetchDanhMucs = async () => {
                danhMucs.value = await getNavigations()
            }

            onMounted(() => {
                fetchDanhMucs()
            })

            return { danhMucs }
        }
    })
</script>

<style>
    .bg-custom {
        background-color: #ffece0;
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
    }
    .block-navbar ul li a {
        position: relative;
        color: #000000;
        font-family: 'Montserrat';
        font-size: 16px;
        font-weight: 600;
        line-height: 20px;
        padding: 14px 10px;

        &:hover {
            color: #ff8435;
        }

        &:focus {
            color: #ff8435;
        }
    }
</style>
