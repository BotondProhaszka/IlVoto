package hu.proha.ilvoto.service

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import hu.proha.ilvoto.data.DateOffer
import hu.proha.ilvoto.data.Event
import hu.proha.ilvoto.data.Vote

class EventRepository {
    private val database = FirebaseDatabase.getInstance().reference


    fun addEvent(event: Event) {
        val key = database.child("events").push().key
        key?.let {
            database.child("events").child(it).setValue(event)
        }
    }

    fun deleteEvent(event: Event) {
        database.child("events").child(event.id).removeValue()
    }

    fun getEventsByOwner(owner: String, callback: (List<Event>) -> Unit) {
        val eventList = mutableListOf<Event>()
        database.child("events").orderByChild("owner").equalTo(owner).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    val event = it.getValue(Event::class.java)
                    event?.let {
                        eventList.add(event)
                    }
                }
                callback(eventList)
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun getEvents(callback: (List<Event>) -> Unit) {
        val eventList = mutableListOf<Event>()
        database.child("events").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    val event = it.getValue(Event::class.java)
                    event?.let {
                        eventList.add(event)
                    }
                }
                callback(eventList)
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun getEvent(eventId: String, callback: (Event?) -> Unit) {
        database.child("events").child(eventId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val event = dataSnapshot.getValue(Event::class.java)
                callback(event)
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun addDateOffer(eventId: String, dateOffer: DateOffer) {
        val key = database.child("events").child(eventId).child("dateOffers").push().key
        key?.let {
            database.child("events").child(eventId).child("dateOffers").child(it).setValue(dateOffer)
        }
    }

    fun addVote(eventId: String, dateOfferId: String, vote: Vote) {
        val key = database.child("events").child(eventId).child("dateOffers").child(dateOfferId).child("votes").push().key
        key?.let {
            database.child("events").child(eventId).child("dateOffers").child(dateOfferId).child("votes").child(it).setValue(vote)
        }
    }

    fun deleteDateOffer(eventId: String, dateOfferId: String) {
        database.child("events").child(eventId).child("dateOffers").child(dateOfferId).removeValue()
    }

    fun deleteVote(eventId: String, dateOfferId: String, vote: Vote) {
        database.child("events").child(eventId).child("dateOffers").child(dateOfferId).child("votes").child(vote.toString()).removeValue()
    }
}
