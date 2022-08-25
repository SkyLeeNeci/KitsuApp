package karpenko.test.kitsuapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import karpenko.test.kitsuapp.R
import karpenko.test.kitsuapp.databinding.ItemMangaBinding
import karpenko.test.kitsuapp.model.pojo.mangaPojo.MangaAttributes

class MangaListAdapter : RecyclerView.Adapter<MangaListAdapter.MangaAttributeViewHolder>() {

    var mangaAttributesList: List<MangaAttributes> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    class MangaAttributeViewHolder(var view: ItemMangaBinding): RecyclerView.ViewHolder(view.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaAttributeViewHolder {
        val view = DataBindingUtil.inflate<ItemMangaBinding>(LayoutInflater.from(parent.context),
        R.layout.item_manga, parent, false)
        return MangaAttributeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MangaAttributeViewHolder, position: Int) {
        val manga = mangaAttributesList[position]
        holder.view.mangaAttributes = manga

        /*holder.itemView.setOnClickListener {
            Navigation.findNavController(it).navigate()
        }*/
    }

    override fun getItemCount() = mangaAttributesList.size
}