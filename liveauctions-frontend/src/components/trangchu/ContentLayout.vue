<template>
    <div class="container mt-5">
        <div class="heading-title-auctions">Sắp diễn ra</div>
        <div class="row">
            <template v-for="(item, index) in sanPhamDauGiasUpcoming" :key="index">
                <div class="col-6">
                    <div class="card" style="border: 1px solid #efefef">
                        <div class="row g-0">
                            <div
                                class="col-7"
                                style="
                                    border-bottom: 1px solid #efefef;
                                    border-right: 1px solid #efefef;
                                "
                            >
                                <img
                                    :src="(item as any).sanPham.anhSanPhams[0].tenAnh"
                                    class="w-100"
                                    alt="Ảnh 1"
                                    height="280px"
                                />
                            </div>
                            <div class="col-5">
                                <div class="" style="border-bottom: 1px solid #efefef">
                                    <img
                                        :src="(item as any).sanPham.anhSanPhams[1].tenAnh"
                                        class="w-100"
                                        alt="Ảnh 2"
                                        height="140px"
                                    />
                                </div>
                                <div class="" style="border-bottom: 1px solid #efefef">
                                    <img
                                        :src="(item as any).sanPham.anhSanPhams[2].tenAnh"
                                        class="w-100"
                                        alt="Ảnh 3"
                                        height="140px"
                                    />
                                </div>
                            </div>
                        </div>
                        <div class="card-body row">
                            <h4
                                class="card-title text-capitalize limited-width-main"
                                :title="(item as any).phienDauGia.tenPhienDauGia"
                            >
                                {{ (item as any).phienDauGia.tenPhienDauGia }}
                            </h4>
                            <p class="card-text">
                                {{
                                    tenNguoiBansUpcoming[
                                        Number((item as any).sanPham.maNguoiBan)
                                    ] || 'Đang tải...'
                                }}
                            </p>
                            <h5
                                class="card-title text-capitalize limited-width-main"
                                :title="(item as any).sanPham.tenSanPham"
                            >
                                {{ (item as any).sanPham.tenSanPham }}
                            </h5>
                            <div class="col-6">
                                <p class="card-text fst-italic">
                                    Bắt đầu:
                                    {{ formatDateTime((item as any).phienDauGia.ngayBatDau) }}
                                </p>
                                <p class="card-text fst-italic">
                                    Kết thúc:
                                    {{ formatDateTime((item as any).phienDauGia.ngayKetThuc) }}
                                </p>
                            </div>
                            <div class="col-6 text-end">
                                <a href="#" class="btn btn-primary">Xem chi tiết</a>
                            </div>
                        </div>
                    </div>
                </div>
            </template>
        </div>
        <div class="heading-title-auctions">Sản phẩm nổi bật</div>
        <div id="carouselSanPhamNoiBat" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div
                    v-for="(chunk, index) in chunkedSanPhamDauGiasTrending"
                    :key="index"
                    class="carousel-item"
                    :class="{ active: index === 0 }"
                    data-bs-interval="5000"
                >
                    <div class="row">
                        <SlideItem
                            v-for="(item, i) in chunk"
                            :key="i"
                            :link="'#'"
                            :source="(item as any).sanPham.anhSanPhams[0].tenAnh"
                            :tenPhienDauGia="(item as any).phienDauGia.tenPhienDauGia"
                            :ngayBatDau="(item as any).phienDauGia.ngayBatDau ? formatDate((item as any).phienDauGia.ngayBatDau) : ''"
                            :tenSanPham="(item as any).sanPham.tenSanPham"
                            :giaKhoiDiem="(item as any).sanPham.giaKhoiDiem"
                        />
                    </div>
                </div>
            </div>
            <button
                class="carousel-control-prev"
                type="button"
                data-bs-target="#carouselSanPhamNoiBat"
                data-bs-slide="prev"
            >
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button
                class="carousel-control-next"
                type="button"
                data-bs-target="#carouselSanPhamNoiBat"
                data-bs-slide="next"
            >
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <div class="heading-title-auctions">Đấu giá kín</div>
        <div id="carouselDauGiaKin" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div
                    v-for="(chunk, index) in chunkedSanPhamDauGiasKin"
                    :key="index"
                    class="carousel-item"
                    :class="{ active: index === 0 }"
                    data-bs-interval="5000"
                >
                    <div class="row">
                        <SlideItem
                            v-for="(item, i) in chunk"
                            :key="i"
                            :link="'#'"
                            :source="(item as any).sanPham.anhSanPhams[0].tenAnh"
                            :tenPhienDauGia="(item as any).phienDauGia.tenPhienDauGia"
                            :ngayBatDau="(item as any).phienDauGia.ngayBatDau ? formatDate((item as any).phienDauGia.ngayBatDau) : ''"
                            :tenSanPham="(item as any).sanPham.tenSanPham"
                            :giaKhoiDiem="(item as any).sanPham.giaKhoiDiem"
                        />
                    </div>
                </div>
            </div>
            <button
                class="carousel-control-prev"
                type="button"
                data-bs-target="#carouselDauGiaKin"
                data-bs-slide="prev"
            >
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button
                class="carousel-control-next"
                type="button"
                data-bs-target="#carouselDauGiaKin"
                data-bs-slide="next"
            >
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <div class="heading-title-auctions">Đấu giá trực tuyến</div>
        <div class="row">
            <template v-for="(item, index) in sanPhamDauGiasOnline" :key="index">
                <div class="col-4 mb-5">
                    <div class="card" style="border: 1px solid #efefef">
                        <div class="row g-0">
                            <div
                                class="col-7"
                                style="
                                    border-bottom: 1px solid #efefef;
                                    border-right: 1px solid #efefef;
                                "
                            >
                                <img
                                    :src="(item as any).sanPham.anhSanPhams[0].tenAnh"
                                    class="w-100"
                                    alt="Ảnh 1"
                                    height="240px"
                                />
                            </div>
                            <div class="col-5">
                                <div class="" style="border-bottom: 1px solid #efefef">
                                    <img
                                        :src="(item as any).sanPham.anhSanPhams[1].tenAnh"
                                        class="w-100"
                                        alt="Ảnh 2"
                                        height="120px"
                                    />
                                </div>
                                <div class="" style="border-bottom: 1px solid #efefef">
                                    <img
                                        :src="(item as any).sanPham.anhSanPhams[2].tenAnh"
                                        class="w-100"
                                        alt="Ảnh 3"
                                        height="120px"
                                    />
                                </div>
                            </div>
                        </div>
                        <div class="card-body row">
                            <h4
                                class="card-title text-capitalize limited-width-main"
                                :title="(item as any).phienDauGia.tenPhienDauGia"
                            >
                                {{ (item as any).phienDauGia.tenPhienDauGia }}
                            </h4>
                            <p class="card-text">
                                {{
                                    tenNguoiBansOnline[Number((item as any).sanPham.maNguoiBan)] ||
                                    'Đang tải...'
                                }}
                            </p>
                            <h5
                                class="card-title text-capitalize limited-width-main"
                                :title="(item as any).sanPham.tenSanPham"
                            >
                                {{ (item as any).sanPham.tenSanPham }}
                            </h5>
                            <div class="col-6">
                                <p class="card-text fst-italic">
                                    Bắt đầu:
                                    {{ formatDateTime((item as any).phienDauGia.ngayBatDau) }}
                                </p>
                                <p class="card-text fst-italic">
                                    Kết thúc:
                                    {{ formatDateTime((item as any).phienDauGia.ngayKetThuc) }}
                                </p>
                            </div>
                            <div class="col-6 text-end">
                                <a href="#" class="btn btn-primary">Xem chi tiết</a>
                            </div>
                        </div>
                    </div>
                </div>
            </template>
        </div>
    </div>
</template>

<script lang="ts">
    import { useDate } from '@/composables/useDate'
    import { useDateTime } from '@/composables/useDateTime'
    import {
        getSanPhamDauGiasKin,
        getSanPhamDauGiasOnline,
        getSanPhamDauGiasTrending,
        getSanPhamDauGiasUpcoming
    } from '@/services/nhanvien/sanPhamDauGiaService'
    import { getNguoiBanByMaNguoiDung } from '@/services/quantrivien/nguoiBanService'
    import { computed, defineComponent, onMounted, ref } from 'vue'
    import SlideItem from '../dungchung/SlideItem.vue'

    export default defineComponent({
        name: 'ContentLayout',
        components: {
            SlideItem
        },
        setup() {
            const sanPhamDauGiasUpcoming = ref<Record<string, undefined>[]>([])
            const sanPhamDauGiasTrending = ref<Record<string, undefined>[]>([])
            const sanPhamDauGiasKin = ref<Record<string, undefined>[]>([])
            const sanPhamDauGiasOnline = ref<Record<string, undefined>[]>([])
            const tenNguoiBansUpcoming = ref<Record<number, string>>({})
            const tenNguoiBansOnline = ref<Record<number, string>>({})

            const formatDate = (date: string) => {
                return useDate(date)
            }

            const formatDateTime = (dateTime: string) => {
                return useDateTime(dateTime)
            }

            const fetchUpcomings = async () => {
                const response = await getSanPhamDauGiasUpcoming()
                sanPhamDauGiasUpcoming.value = response

                for (const sanPhamDauGia of response) {
                    getTenNguoiBanUpcoming(Number(sanPhamDauGia.sanPham.maNguoiBan))
                }
            }

            const getTenNguoiBanUpcoming = async (maNguoiBan: number) => {
                if (!tenNguoiBansUpcoming.value[maNguoiBan]) {
                    const nguoiBan = await getNguoiBanByMaNguoiDung(maNguoiBan)
                    tenNguoiBansUpcoming.value[maNguoiBan] = nguoiBan
                        ? nguoiBan.nguoiDung.hoVaTen
                        : 'Không xác định'
                }
                return tenNguoiBansUpcoming.value[maNguoiBan]
            }

            const fetchTrendings = async () => {
                const response = await getSanPhamDauGiasTrending()
                sanPhamDauGiasTrending.value = response
            }

            const chunkedSanPhamDauGiasTrending = computed(() => {
                const chunkSize = 4
                return sanPhamDauGiasTrending.value.reduce<Record<string, undefined>[][]>(
                    (result, item, index) => {
                        const chunkIndex = Math.floor(index / chunkSize)
                        if (!result[chunkIndex]) result[chunkIndex] = []
                        result[chunkIndex].push(item)
                        return result
                    },
                    []
                )
            })

            const fetchKins = async () => {
                const response = await getSanPhamDauGiasKin()
                sanPhamDauGiasKin.value = response
            }

            const chunkedSanPhamDauGiasKin = computed(() => {
                const chunkSize = 4
                return sanPhamDauGiasTrending.value.reduce<Record<string, undefined>[][]>(
                    (result, item, index) => {
                        const chunkIndex = Math.floor(index / chunkSize)
                        if (!result[chunkIndex]) result[chunkIndex] = []
                        result[chunkIndex].push(item)
                        return result
                    },
                    []
                )
            })

            const fetchOnlines = async () => {
                const response = await getSanPhamDauGiasOnline()
                sanPhamDauGiasOnline.value = response

                for (const sanPhamDauGia of response) {
                    getTenNguoiBanOnline(Number(sanPhamDauGia.sanPham.maNguoiBan))
                }
            }

            const getTenNguoiBanOnline = async (maNguoiBan: number) => {
                if (!tenNguoiBansOnline.value[maNguoiBan]) {
                    const nguoiBan = await getNguoiBanByMaNguoiDung(maNguoiBan)
                    tenNguoiBansOnline.value[maNguoiBan] = nguoiBan
                        ? nguoiBan.nguoiDung.hoVaTen
                        : 'Không xác định'
                }
                return tenNguoiBansOnline.value[maNguoiBan]
            }

            onMounted(() => {
                fetchUpcomings()
                fetchTrendings()
                fetchKins()
                fetchOnlines()
            })

            return {
                formatDate,
                formatDateTime,
                sanPhamDauGiasUpcoming,
                chunkedSanPhamDauGiasTrending,
                chunkedSanPhamDauGiasKin,
                sanPhamDauGiasOnline,
                tenNguoiBansUpcoming,
                tenNguoiBansOnline
            }
        }
    })
</script>

<style>
    .limited-width-main {
        max-width: 1000px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        display: inline-block;
        vertical-align: middle;
    }
    .heading-title-auctions {
        font-family: 'Montserrat';
        font-size: 20px;
        font-weight: 700;
        line-height: 20px;
        text-align: left;
        color: #23689b;
        text-transform: uppercase;
        margin: 50px 0px 30px;
    }

    .carousel-control-prev-icon,
    .carousel-control-next-icon {
        background-color: #efefef;
        padding: 90% 10px;
    }
    .carousel-control-prev-icon {
        margin-left: -200px;
        background-image: url("data:image/svg+xml;charset=UTF8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000000' viewBox='0 0 16 16'%3E%3Cpath fill-rule='evenodd' d='M11.354 14.354a.5.5 0 0 0 0-.708L5.707 8l5.647-5.646a.5.5 0 0 0-.708-.708l-6 6a.5.5 0 0 0 0 .708l6 6a.5.5 0 0 0 .708 0z'/%3E%3C/svg%3E");
    }
    .carousel-control-next-icon {
        margin-right: -200px;
        background-image: url("data:image/svg+xml;charset=UTF8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000000' viewBox='0 0 16 16'%3E%3Cpath fill-rule='evenodd' d='M4.646 14.354a.5.5 0 0 1 0-.708L10.293 8 4.646 2.354a.5.5 0 0 1 .708-.708l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708 0z'/%3E%3C/svg%3E");
    }
</style>
