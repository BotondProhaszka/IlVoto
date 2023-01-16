package hu.proha.ilvoto.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import hu.proha.ilvoto.R
import hu.proha.ilvoto.data.Group
import hu.proha.ilvoto.databinding.ItemGroupBinding

class GroupsAdapter(private val listener: OnGroupSelectedListener) : RecyclerView.Adapter<GroupsAdapter.GroupViewHolder>() {

    private val groups: MutableList<Group> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val item = groups[position]
        holder.bind(item)
    }

    override fun getItemCount() = groups.size

    interface OnGroupSelectedListener {
        fun onGroupSelected(group: Group)
    }

    fun addGroup(group: Group) {
        groups.add(group)
        notifyItemInserted(groups.size - 1)
    }

    fun removeGroup(group: Group) {
        val index = groups.indexOf(group)
        groups.remove(group)
        notifyItemRemoved(index)
        notifyDataSetChanged()
    }

    inner class GroupViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemGroupBinding.bind(itemView)
        lateinit var item: Group

        init {
            binding.removebtn.setOnClickListener {
                Snackbar.make(binding.root, "Gomb megnyomva", Snackbar.LENGTH_LONG).show()
            }
        }

        fun bind(newItem: Group) {
            item = newItem
            binding.groupName.text = newItem.name
            if(item.owner == "")
                binding.removebtn.setBackgroundResource(R.drawable.ic_baseline_logout_24)
            else
                binding.removebtn.setBackgroundResource(R.drawable.ic_baseline_delete_24)
        }
    }
}