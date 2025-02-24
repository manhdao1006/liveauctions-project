<template>
    <li class="nav-item">
        <a class="nav-link" :href="$props.link">{{ $props.content }}</a>
    </li>
</template>

<script lang="ts">
    import { defineComponent } from 'vue'

    export default defineComponent({
        name: 'NavItem',
        props: {
            link: String,
            content: String
        }
    })
</script>
