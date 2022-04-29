package br.com.luishenrique.alarmedevoz.data.entity

data class AlarmRequest(
    var title: String? = null,
    var description: String? = null,
    var color: Int? = null,
    var sound: String? = null,
    var date: Date? = null
) {
    fun toAlarm(): Alarm? {
        if (title == null || color == null || sound == null || date == null) return null
        return Alarm(title!!, description ?: "", color!!, sound!!, date!!)
    }
}