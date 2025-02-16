<template>
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <form class="d-flex w-100" @submit.prevent="handleSearch">
                <input
                    v-model="keyword"
                    class="form-control me-2"
                    type="search"
                    placeholder="Nhập từ khóa ..."
                    aria-label="Search"
                />
                <button class="btn btn-outline-success" type="submit">Tìm</button>
            </form>
        </div>
    </nav>
</template>

<script lang="ts">
    import { defineComponent, ref, watch } from 'vue'

    export default defineComponent({
        name: 'SearchComponent',
        props: {
            searchKeyword: String,
            onSearch: Function
        },
        emits: ['update:searchKeyword'],
        setup(props, { emit }) {
            const keyword = ref(props.searchKeyword)

            const handleSearch = () => {
                if (props.onSearch) {
                    props.onSearch(keyword.value)
                }
            }

            watch(keyword, (newKeyword) => {
                emit('update:searchKeyword', newKeyword)
            })

            return { keyword, handleSearch }
        }
    })
</script>

<style>
    .btn-outline-success {
        border: solid 1px #ff8435;
        color: #000000;
    }
    .btn-outline-success:hover {
        background-color: #ff8435;
        color: #ffffff;
        border: solid 1px #ff8435;
    }
</style>
