package hu.proha.ilvoto.data

import java.util.Date

data class Event(val id: String, var name: String, var date: Date, var description: String, var dateOffers: ArrayList<DateOffer>)
