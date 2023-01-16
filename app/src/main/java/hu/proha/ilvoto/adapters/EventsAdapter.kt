package hu.proha.ilvoto.adapters

import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.proha.ilvoto.R
import hu.proha.ilvoto.data.Event
import hu.proha.ilvoto.databinding.ItemEventBinding
import java.lang.String.format
import java.text.DateFormat

class EventsAdapter(private val listener: OnEventSelectedListener) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private val events: MutableList<Event> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = events[position]
        holder.bind(item)
    }

    override fun getItemCount() = events.size

    interface OnEventSelectedListener {
        fun onEventSelected(event: Event)
    }

    fun addEvent(event: Event) {
        events.add(event)
        notifyItemInserted(events.size - 1)
    }

    fun removeEvent(event: Event) {
        val index = events.indexOf(event)
        events.remove(event)
        notifyItemRemoved(index)
        notifyDataSetChanged()
    }

    inner class EventViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemEventBinding.bind(itemView)
        lateinit var item: Event

        init {
            binding.root.setOnClickListener {
                listener.onEventSelected(item)
            }
        }

        fun bind(newItem: Event) {
            item = newItem
            binding.eventName.text = item.name
            binding.eventDate.text = item.date
        }
    }
}