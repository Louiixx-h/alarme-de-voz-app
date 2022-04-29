package br.com.luishenrique.alarmedevoz.utils

fun String.removeRange(startIndex: Int, endIndex: Int): String =
    (this as CharSequence).removeRange(startIndex, endIndex).toString()