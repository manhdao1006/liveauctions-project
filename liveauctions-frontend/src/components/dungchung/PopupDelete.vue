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
        <div class="modal-dialog" role="document" style="margin-top: 8%;">
            <div class="modal-content" style="background-color: #ffece1;">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Xác nhận xóa</h5>
                    <button
                        type="button"
                        class="btn-close"
                        @click="hidePopup"
                        title="Xóa"
                    ></button>
                </div>
                <div class="modal-body">Bạn có chắc chắn muốn xóa không?</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" title="Hủy" @click="hidePopup">Hủy</button>
                    <button type="button" class="btn btn-danger" title="Xóa" @click="confirmDelete">Xóa</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import { defineComponent } from 'vue'

    export default defineComponent({
        name: 'PopupDelete',
        props: {
            showPopup: Boolean,
            onDelete: Function
        },
        emits: ['update:showPopup'],
        setup(props, { emit }) {
            const hidePopup = () => {
                emit('update:showPopup', false)
            }

            const confirmDelete = () => {
                if (props.onDelete) {
                    props.onDelete()
                }
                hidePopup()
            }

            return { hidePopup, confirmDelete }
        }
    })
</script>

<style>
    .modal.fade.show {
        display: block;
    }
</style>
