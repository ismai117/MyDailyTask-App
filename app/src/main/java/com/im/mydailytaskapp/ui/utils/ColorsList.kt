package com.im.mydailytaskapp.ui.utils

enum class ColorsList(val color: Long) {

    Color1(color = 0xFFFFA500),
    Color2(color = 0xFFE83845),
    Color3(color = 0xFFE389B9),
    Color4(color = 0xFF746AB0),

}

fun getAllCategories(): List<ColorsList> {
    return ColorsList.values().toList()
}

fun getColor(color: String): ColorsList? {
    val map = ColorsList.values().associateBy(ColorsList::name)
    return map[color]
}