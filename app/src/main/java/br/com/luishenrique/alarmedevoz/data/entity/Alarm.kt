package br.com.luishenrique.alarmedevoz.data.entity

import java.io.Serializable

data class Alarm(
    val title: String,
    val description: String,
    val color: Int,
    val sound: String,
    val date: Date
): Serializable {
    
    fun equals(value: Alarm): Boolean {
        return this.title == value.title
                && this.color == value.color
                && this.description == value.description
                && this.sound == value.sound
                && this.date == value.date
    }
}