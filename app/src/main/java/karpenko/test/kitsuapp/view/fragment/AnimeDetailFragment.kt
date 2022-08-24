package karpenko.test.kitsuapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import karpenko.test.kitsuapp.databinding.FragmentAnimeDetailBinding
import karpenko.test.kitsuapp.utils.getProgressDrawable
import karpenko.test.kitsuapp.utils.loadImage
import karpenko.test.kitsuapp.viewmodel.AnimeDetailViewModel

class AnimeDetailFragment : Fragment() {

    private var animeId = 0
    private lateinit var binding: FragmentAnimeDetailBinding
    private val viewModel: AnimeDetailViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[AnimeDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            animeId = AnimeDetailFragmentArgs.fromBundle(it).id
        }
        binding = FragmentAnimeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch(animeId)
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.animeDetailLiveData.observe(viewLifecycleOwner){
            it?.let {
                /*binding.animeName.text = it.canonicalTitle
                binding.createAt.text = it.startDate
                binding.updateDate.text = it.endDate
                binding.animeDescriptionTV.text = it.description
                binding.logoImageView.loadImage(it.posterImage?.original, getProgressDrawable(binding.logoImageView.context))
                binding.episodeCount.text = it.episodeCount.toString()
                binding.episodeLength.text = it.episodeLength.toString()
                binding.totalLength.text = it.totalLength.toString()
                binding.userRating.text = it.userCount.toString()
                binding.favouriteCount.text = it.favoritesCount.toString()*/
                binding.attributes = it
            }
        }
    }

}