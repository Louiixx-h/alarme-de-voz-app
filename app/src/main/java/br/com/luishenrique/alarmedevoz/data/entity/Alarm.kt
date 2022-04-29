package br.com.luishenrique.alarmedevoz.data.entity

import java.io.Serializable

data class Alarm(
    val title: String,
    val description: String,
    val color: Int,
    val sound: String,
    val date: Date
): Serializable