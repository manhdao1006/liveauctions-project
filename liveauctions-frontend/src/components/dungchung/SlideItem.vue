<template>
    <div class="col-md-3">
        <a :href="link">
            <div class="card shadow-sm border-0">
                <div class="ps-4 pe-4">
                    <img
                        :src="source"
                        class="card-img-top card-img-bottom"
                        alt="..."
                        height="200px"
                    />
                </div>
                <div class="card-body">
                    <div
                        class="card-title card-heading limited-width-slide"
                        :title="tenPhienDauGia"
                    >
                        {{ tenPhienDauGia }}
                    </div>
                    <div class="card-date">{{ ngayBatDau }}</div>
                    <div class="card-text card-content limited-width-slide" :title="tenSanPham">
                        {{ tenSanPham }}
                    </div>
                    <div class="card-text card-price">{{ giaKhoiDiem }} VNĐ</div>
                </div>
            </div>
        </a>
    </div>
</template>

<script lang="ts">
    import { defineComponent } from 'vue'

    export default defineComponent({
        name: 'SlideItem',
        props: {
            link: String,
            source: String,
            tenPhienDauGia: String,
            ngayBatDau: String,
            tenSanPham: String,
            giaKhoiDiem: String
        }
    })
</script>

<style>
    .limited-width-slide {
        max-width: 230px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        display: inline-block;
        vertical-align: middle;
    }
    a {
        text-decoration: none;
    }
    .card-heading {
        color: #23689b;
        font-family: 'Montserrat';
        font-size: 16px;
        font-weight: 700;
        line-height: 18px;
        text-transform: capitalize;
    }
    .card-date {
        color: #333333;
        font-family: 'Montserrat';
        font-size: 14px;
        font-weight: 400;
        line-height: 18px;
        font-style: italic;
    }
    .card-content {
        color: #000000;
        font-family: 'Montserrat';
        font-size: 16px;
        font-weight: 500;
        line-height: 19.5px;
        text-transform: capitalize;
    }
    .card-price {
        color: #dc7916;
        font-family: 'Montserrat';
        font-size: 18px;
        font-weight: 600;
        line-height: 19.5px;
        margin-top: 10px;
    }
</style>
