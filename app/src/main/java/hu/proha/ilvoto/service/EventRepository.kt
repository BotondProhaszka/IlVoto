package hu.proha.ilvoto.service

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import hu.proha.ilvoto.data.*

class EventRepository {
    private val database = FirebaseDatabase.getInstance("https://ilvoto-e25f0-default-rtdb.europe-west1.firebasedatabase.app/").reference


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

    fun addDateOffer(eventId: String, dateOffer: DateOffer, callback: (Boolean) -> Unit) {
        val key = database.child("events").child(eventId).child("dateOffers").push().key
        key?.let {
            database.child("events").child(eventId).child("dateOffers").child(it).setValue(dateOffer)
                .addOnSuccessListener {
                    callback(true)
                }
                .addOnFailureListener {
                    Log.e("EventRepository", "Error adding date offer: ${it.message}")
                    callback(false)
                }
        }
    }

    fun addVote(eventId: String, dateOfferId: String, vote: Vote, callback: (Boolean) -> Unit) {
        val key = database.child("events").child(eventId).child("dateOffers").child(dateOfferId).child("votes").push().key
        key?.let {
            database.child("events").child(eventId).child("dateOffers").child(dateOfferId).child("votes").child(it).setValue(vote)
                .addOnSuccessListener {
                    callback(true)
                }
                .addOnFailureListener {
                    Log.e("EventRepository", "Error adding vote: ${it.message}")
                    callback(false)
                }
        }
    }

    fun deleteDateOffer(eventId: String, dateOfferId: String, callback: (Boolean) -> Unit) {
        database.child("events").child(eventId).child("dateOffers").child(dateOfferId).removeValue()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                Log.e("EventRepository", "Error deleting date offer: ${it.message}")
                callback(false)
            }
    }

    fun deleteVote(eventId: String, dateOfferId: String, voteId: String, callback: (Boolean) -> Unit) {
        database.child("events").child(eventId).child("dateOffers").child(dateOfferId).child("votes").child(voteId).removeValue()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                Log.e("EventRepository", "Error deleting vote: ${it.message}")
                callback(false)
            }
    }

    fun createGroup(group: Group, callback: (Boolean) -> Unit){
        val key = database.child("groups").push().key
        key?.let {
            database.child("groups").child(it).setValue(group)
                .addOnSuccessListener {
                    callback(true)
                }
                .addOnFailureListener{
                    Log.e("EventRepository", "Error creating group: ${it.message}")
                    callback(false)
                }
        }
    }

    fun deleteGroup(groupId: String, callback: (Boolean) -> Unit){

        database.child("groups").child(groupId).removeValue()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                Log.e("EventRepository", "Error deleting group: ${it.message}")
                callback(false)
            }
    }

    fun addMemberToGroup(groupId: String, memberEmail: String, callback: (Boolean) -> Unit) {
        val key = database.child("groups").child(groupId).child("members").push().key
        key?.let {
            database.child("groups").child(groupId).child("members").child(it).setValue(memberEmail)
                .addOnSuccessListener {
                    callback(true)
                }
                .addOnFailureListener {
                    Log.e("EventRepository", "Error adding member to group: ${it.message}")
                    callback(false)
                }
        }
    }

    fun deleteMemberFromGroup(groupId: String, memberEmail: String, callback: (Boolean) -> Unit) {
        database.child("groups").child(groupId).child("members").child(memberEmail).removeValue()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                Log.e("EventRepository", "Error deleting member from group: ${it.message}")
                callback(false)
            }
    }

    fun createProfile(profile: Profile, callback: (Boolean) -> Unit) {
        val key = database.child("profiles").push().key
        key?.let {
            profile.id = key
            database.child("profiles").child(it).setValue(profile)
                .addOnSuccessListener {
                    callback(true)
                }
                .addOnFailureListener {
                    Log.e("EventRepository", "Error adding profile: ${it.message}")
                    callback(false)
                }
        }
    }

    fun deleteProfile(key: String, callback: (Boolean) -> Unit) {
            database.child("profiles").child(key).removeValue()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                Log.e("EventRepository", "Error deleting profile: ${it.message}")
                callback(false)
            }
    }


    fun addGroupToMember(groupId: String, memberEmail: String, callback: (Boolean) -> Unit) {
        val key = database.child("profiles").child(memberEmail).child("groups").push().key
        key?.let {
            database.child("profiles").child(memberEmail).child("groups").child(it).setValue(groupId)
                .addOnSuccessListener {
                    callback(true)
                }
                .addOnFailureListener {
                    Log.e("EventRepository", "Error adding group to member: ${it.message}")
                    callback(false)
                }
        }
    }

    fun deleteGroupToMember(groupId: String, memberEmail: String, callback: (Boolean) -> Unit) {
        database.child("profiles").child(memberEmail).child("groups").child(groupId).removeValue()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                Log.e("EventRepository", "Error deleting group from member: ${it.message}")
                callback(false)
            }
    }
}
