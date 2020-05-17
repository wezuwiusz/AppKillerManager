package com.thelittlefireman.appkillermanager.utils

enum class Manufacturer(private val value: String) {
    XIAOMI("xiaomi"),
    SAMSUNG("samsung"),
    OPPO("oppo"),
    HUAWEI("huawei"),
    MEIZU("meizu"),
    ONEPLUS("oneplus"),
    LETV("letv"),
    ASUS("asus"),
    HTC("htc"),
    ZTE("zte"),
    VIVO("vivo");

    override fun toString() = value
}
