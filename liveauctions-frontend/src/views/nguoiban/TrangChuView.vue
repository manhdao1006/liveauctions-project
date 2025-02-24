<template>
    <HeaderLayout />
    <NavigationLayout />
    <ContentLayout />
    <FooterLayout />

    <button id="backToTop" class="btn btn-primary rounded-circle shadow" @click="scrollToTop">
        <i class="fa-solid fa-angle-up"></i>
    </button>
</template>

<script lang="ts">
    import ContentLayout from '@/components/trangchu/ContentLayout.vue'
    import FooterLayout from '@/components/trangchu/FooterLayout.vue'
    import HeaderLayout from '@/components/trangchu/HeaderLayout.vue'
    import NavigationLayout from '@/components/trangchu/NavigationLayout.vue'
    import { defineComponent, onMounted, onUnmounted } from 'vue'

    export default defineComponent({
        name: 'TrangChuView',
        components: {
            HeaderLayout,
            NavigationLayout,
            ContentLayout,
            FooterLayout
        },
        setup() {
            const handleScroll = () => {
                const button = document.getElementById('backToTop')
                if (button) {
                    button.style.display = window.scrollY > 300 ? 'block' : 'none'
                }
            }

            const scrollToTop = () => {
                window.scrollTo({ top: 0, behavior: 'smooth' })
            }

            onMounted(() => {
                window.addEventListener('scroll', handleScroll)
            })

            onUnmounted(() => {
                window.removeEventListener('scroll', handleScroll)
            })

            return { scrollToTop }
        }
    })
</script>

<style>
    #backToTop {
        position: fixed;
        bottom: 20px;
        right: 20px;
        width: 50px;
        height: 50px;
        font-size: 20px;
        display: none; /* Ẩn ban đầu */
        z-index: 1000;
        transition: opacity 0.3s ease-in-out;
    }
</style>
