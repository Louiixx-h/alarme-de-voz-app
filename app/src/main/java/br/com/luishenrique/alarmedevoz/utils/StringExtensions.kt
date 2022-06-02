package br.com.luishenrique.alarmedevoz.utils

import br.com.luishenrique.alarmedevoz.data.entity.Date

fun String.removeRange(startIndex: Int, endIndex: Int): String =
    (this as CharSequence).removeRange(startIndex, endIndex).toString()

fun Date.getDateString(): String {
    return "${this.hour}:${this.minute}"
}