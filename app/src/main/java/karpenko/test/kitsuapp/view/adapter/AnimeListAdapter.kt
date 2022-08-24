package karpenko.test.kitsuapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import karpenko.test.kitsuapp.R
import karpenko.test.kitsuapp.databinding.ItemAnimeBinding
import karpenko.test.kitsuapp.model.pojo.AnimeAttributes
import karpenko.test.kitsuapp.utils.getProgressDrawable
import karpenko.test.kitsuapp.utils.loadImage
import karpenko.test.kitsuapp.view.fragment.AnimeListFragmentDirections

class AnimeListAdapter : RecyclerView.Adapter<AnimeListAdapter.AnimeAttributeViewHolder>() {

    var animeAttributesList: List<AnimeAttributes> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class AnimeAttributeViewHolder(var view: ItemAnimeBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeAttributeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemAnimeBinding>(inflater, R.layout.item_anime, parent, false)
        return AnimeAttributeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeAttributeViewHolder, position: Int) {
        val anime = animeAttributesList[position]
        holder.view.animeAttributes = anime
        holder.itemView.setOnClickListener {
            Navigation.findNavController(it).navigate(AnimeListFragmentDirections.actionAnimesListFragmentToAnimeDetailsFragment(anime.id))
        }
    }

    override fun getItemCount() = animeAttributesList.size
}