package hu.proha.ilvoto.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import hu.proha.ilvoto.adapters.EventsAdapter
import hu.proha.ilvoto.data.Event
import hu.proha.ilvoto.databinding.FragmentEventsBinding
import java.text.SimpleDateFormat
import java.util.*

class EventsFragment : Fragment(), EventsAdapter.OnEventSelectedListener {

    private lateinit var binding: FragmentEventsBinding
    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initButton()
    }

    private fun initRecyclerView() {
        binding.eventsList.layoutManager = LinearLayoutManager(context)
        adapter = EventsAdapter(this)
        binding.eventsList.adapter = adapter

        adapter.addEvent(Event("1", "Esti játék", SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().time), "Jó lesz", arrayListOf()))
        adapter.addEvent(Event("2", "Délutáni játék", SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().time), "Jó lesz", arrayListOf()))
    }

    private fun initButton() {
        binding.newEventFab.setOnClickListener {
            Snackbar.make(binding.root, "Új esemény hozzáadása", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onEventSelected(event: Event) {
        Snackbar.make(binding.root, event.name + " lett kiválasztva", Snackbar.LENGTH_LONG).show()
    }


}