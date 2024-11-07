package com.sebas.interrapidismo.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Home

@Serializable
object Tables

@Serializable
object Localities

@Serializable
data class TableInformation(val nameTable: String)