package hu.proha.ilvoto.data

import java.util.Date

data class DateOffer(
    var id: String = "",
    var date: Date,
    var owner: String,
    var votes: ArrayList<Vote>
    )
