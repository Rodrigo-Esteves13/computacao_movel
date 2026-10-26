package com.example.myapplication

sealed class Destino(val route: String, val icon: Int, val title: String) {
    object Ecra01 : Destino(route = "ecra01", icon = R.drawable.outline_accessible_24, title = "ADD")
    object Ecra02 : Destino(route = "ecra02", icon = R.drawable.baseline_assignment_24, title = "FIND")
    object Ecra03 : Destino(route = "ecra03", icon = R.drawable.outline_account_circle_24, title = "MIN/MAX")
    object Ecra04 : Destino(route = "ecra04", icon = R.drawable.baseline_app_settings_alt_24, title = "LIST")

    companion object {
        val toList = listOf(Ecra01, Ecra02, Ecra03, Ecra04)
    }
}