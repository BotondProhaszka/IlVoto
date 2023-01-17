package hu.proha.ilvoto.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import hu.proha.ilvoto.adapters.GroupsAdapter
import hu.proha.ilvoto.data.Group
import hu.proha.ilvoto.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(), GroupsAdapter.OnGroupSelectedListener {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var adapter: GroupsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //init dolgok
        initRecyclerView()
        initButton()
        binding.userEmail.text = "teszt.elek@gmail.com"
    }

    private fun initButton() {
        //TODO("Not yet implemented")
    }

    private fun initRecyclerView() {
        binding.groupList.layoutManager = LinearLayoutManager(context)
        adapter = GroupsAdapter(this)
        binding.groupList.adapter = adapter

        adapter.addGroup(Group("1", "Fiúk", arrayListOf(), ""))
        adapter.addGroup(Group("2","Lányok", arrayListOf(), "Anna"))

    }

    override fun onGroupSelected(group: Group) {
        Snackbar.make(binding.root, "Gomb megnyomva", Snackbar.LENGTH_LONG).show()
    }
}