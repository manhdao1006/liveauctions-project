<template>
    <div
        class="modal fade"
        tabindex="-1"
        role="dialog"
        :class="{ show: showPopup }"
        v-show="showPopup"
        aria-labelledby="exampleModalLabel"
        :aria-hidden="!showPopup"
    >
        <div class="modal-dialog" role="document" style="margin-top: 8%">
            <div class="modal-content" style="background-color: #ffece1">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Xác nhận cấm</h5>
                    <button type="button" class="btn-close" @click="hidePopup" title="Xóa"></button>
                </div>
                <div class="modal-body">Bạn có chắc chắn muốn cấm không?</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" title="Hủy" @click="hidePopup">
                        Hủy
                    </button>
                    <button type="button" class="btn btn-danger" title="Cấm" @click="confirmBan">
                        Cấm
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import { defineComponent } from 'vue'

    export default defineComponent({
        name: 'PopupBan',
        props: {
            showPopup: Boolean,
            onBan: Function
        },
        emits: ['update:showPopup'],
        setup(props, { emit }) {
            const hidePopup = () => {
                emit('update:showPopup', false)
            }

            const confirmBan = () => {
                if (props.onBan) {
                    props.onBan()
                }
                hidePopup()
            }

            return { hidePopup, confirmBan }
        }
    })
</script>

<style>
    .modal.fade.show {
        display: block;
    }
</style>
