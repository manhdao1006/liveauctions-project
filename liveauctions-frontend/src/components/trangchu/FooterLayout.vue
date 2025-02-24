<template>
    <div class="block-about">
        <div class="container">
            <div class="row">
                <div class="col-4 pt-5 pb-5">
                    <div class="fw-bolder">Công ty</div>
                    <div><a class="text-item" href="">Giới thiệu</a></div>
                    <div><a class="text-item" href="">Lĩnh vực</a></div>
                    <div><a class="text-item" href="">Trợ giúp</a></div>
                    <div><a class="text-item" href="">Gửi phản hồi</a></div>
                </div>
                <div class="col-4 pt-5 pb-5">
                    <div class="fw-bolder">Đấu giá</div>
                    <div><a class="text-item" href="">Lịch đấu giá</a></div>
                    <div><a class="text-item" href="">Danh bạ nhà đấu giá</a></div>
                    <div><a class="text-item" href="">Kết quả đấu giá</a></div>
                    <div><a class="text-item" href="">Đấu giá gần tôi</a></div>
                    <div><a class="text-item" href="">Cách thức đấu giá hoạt động</a></div>
                </div>
                <div class="col-4 pt-5 pb-5">
                    <div class="fw-bolder">Người dùng</div>
                    <div><a class="text-item" href="">Đăng nhập người đấu giá</a></div>
                    <div><a class="text-item" href="">Trở thành người bán</a></div>
                    <div><a class="text-item" href="">Ký gửi một mặt hàng</a></div>
                    <div><a class="text-item" href="">Tài nguyên người bán</a></div>
                    <div><a class="text-item" href="">Đánh giá của người bán</a></div>
                    <div><a class="text-item" href="">Tại sao nên bán</a></div>
                </div>
            </div>
            <div class="text-center">
                <i class="p-4 fs-4 fa-brands fa-x-twitter"></i>
                <i class="p-4 fs-4 fa-brands fa-facebook-f"></i>
                <i class="p-4 fs-4 fa-brands fa-pinterest-p"></i>
                <i class="p-4 fs-4 fa-brands fa-linkedin-in"></i>
                <i class="p-4 fs-4 fa-brands fa-youtube"></i>
                <i class="p-4 fs-4 fa-brands fa-instagram"></i>
            </div>
        </div>
    </div>
    <div class="block-footer">
        <div class="container">
            <div class="row">
                <div class="content-footer">
                    <div>
                        © 2002-2025 E-Auctioneer. All Rights Reserved.
                        <a href="">Chính sách Cookie | </a><a href="">Điều khoản và Điều kiện | </a
                        ><a href="">Sở hữu trí tuệ | </a><a href="">Chính sách bảo mật</a>
                    </div>
                    <div>
                        Trang web này được bảo vệ bởi reCAPTCHA và Điều khoản dịch vụ và Chính sách
                        bảo mật của Google được áp dụng.
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import { defineComponent } from 'vue'

    export default defineComponent({
        name: 'FooterLayout'
    })
</script>

<style>
    /* css introduction */
    .block-about {
        font-family: 'Montserrat';
        background-color: #ffece0;
        font-size: 20px;
    }
    .text-item {
        font-size: 16px;
        color: gray;

        &:hover {
            color: #ff8435;
        }
    }

    /* css footer */
    .block-footer {
        background: #04002e;
    }
    .content-footer {
        font-family: 'Montserrat';
        font-size: 15.38px;
        font-weight: 400;
        line-height: 25px;
        color: #ffffff;
        margin: 20px 0px 45px;
        text-align: center;
    }

    .content-footer a {
        color: #ff8435;
    }
</style>
