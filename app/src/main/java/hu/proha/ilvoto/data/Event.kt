package hu.proha.ilvoto.data

import java.util.Date
import kotlin.time.TimeSource

data class Event(val id: String, var name: String, var date: String, var description: String, var dateOffers: ArrayList<String>)
