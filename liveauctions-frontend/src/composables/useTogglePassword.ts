import { Ref, ref } from 'vue'

export const useTogglePassword = () => {
    const showMatKhau = ref(false) as Ref<boolean>

    const toggle = () => {
        showMatKhau.value = !showMatKhau.value
    }

    return { showMatKhau, toggle }
}
