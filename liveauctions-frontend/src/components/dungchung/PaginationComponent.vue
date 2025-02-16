<template>
    <ul class="block-pagination">
        <li>
            <button type="button" @click="onClickFirstPage" :disabled="isInFirstPage">Trước</button>
        </li>

        <li>
            <button type="button" @click="onClickPreviousPage" :disabled="isInFirstPage">
                <i class="fas fa-chevron-left"></i>
                <i class="fas fa-chevron-left"></i>
            </button>
        </li>

        <li v-for="page in pages" :key="page.name">
            <button
                type="button"
                :disabled="page.isDisabled"
                @click="onClickPage(page.name)"
                :class="{ active: isPageActive(page.name) }"
            >
                {{ page.name }}
            </button>
        </li>

        <li>
            <button type="button" @click="onClickNextPage" :disabled="isInLastPage">
                <i class="fas fa-chevron-right"></i>
                <i class="fas fa-chevron-right"></i>
            </button>
        </li>

        <li>
            <button type="button" @click="onClickLastPage" :disabled="isInLastPage">Sau</button>
        </li>
    </ul>
</template>

<script lang="ts">
    import { computed, defineComponent } from 'vue'

    export default defineComponent({
        name: 'PaginationComponent',
        props: {
            maxVisibleButtons: {
                type: Number,
                default: 3
            },
            totalPages: {
                type: Number,
                required: true
            },
            totalElements: {
                type: Number,
                required: true
            },
            pageSize: {
                type: Number,
                required: true
            },
            currentPage: {
                type: Number,
                required: true
            }
        },
        emits: ['pageChanged'],
        setup(props, { emit }) {
            const startPage = computed(() => {
                if (props.totalPages <= props.maxVisibleButtons) return 1
                if (props.currentPage === 1) return 1
                if (props.currentPage === props.totalPages) {
                    return Math.max(1, props.totalPages - props.maxVisibleButtons + 1)
                }

                return Math.max(1, props.currentPage - 1)
            })

            const endPage = computed(() => {
                if (props.totalPages <= props.maxVisibleButtons) {
                    return props.totalPages
                }

                return Math.min(startPage.value + props.maxVisibleButtons - 1, props.totalPages)
            })

            const pages = computed(() => {
                const range = []
                for (let i = startPage.value; i <= endPage.value; i++) {
                    range.push({
                        name: i,
                        isDisabled: i === props.currentPage
                    })
                }
                return range
            })

            const isInFirstPage = computed(() => props.currentPage === 1)
            const isInLastPage = computed(() => props.currentPage === props.totalPages)

            const onClickFirstPage = () => {
                emit('pageChanged', 1)
            }
            const onClickPreviousPage = () => {
                emit('pageChanged', props.currentPage - 1)
            }
            const onClickPage = (page: number) => {
                emit('pageChanged', page)
            }
            const onClickNextPage = () => {
                emit('pageChanged', props.currentPage + 1)
            }
            const onClickLastPage = () => {
                emit('pageChanged', props.totalPages)
            }
            const isPageActive = (page: number) => {
                return props.currentPage === page
            }

            return {
                pages,
                isInFirstPage,
                isInLastPage,
                onClickFirstPage,
                onClickPreviousPage,
                onClickPage,
                onClickNextPage,
                onClickLastPage,
                isPageActive
            }
        }
    })
</script>

<style>
    .block-pagination {
        list-style-type: none;
    }
    .block-pagination > li {
        display: inline-block;
    }
    .block-pagination > li {
        display: inline-block;
    }
    .block-pagination > li > button {
        border: none;
        background-color: transparent;
        color: #000000;
        font-size: 16px;
        padding: 8px 16px;

        &.active {
            background-color: #23689b;
            color: #ffffff;
        }
    }
</style>
