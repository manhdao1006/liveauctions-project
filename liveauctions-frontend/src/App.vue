<template>
    <router-view />
</template>
<script lang="ts">
    import { defineComponent, provide, ref } from 'vue'

    export default defineComponent({
        name: 'App',
        setup() {
            const isLoggedIn = ref<boolean>(localStorage.getItem('isLoggedIn') === 'true')
            provide('isLoggedIn', isLoggedIn)

            return { isLoggedIn }
        }
    })
</script>
