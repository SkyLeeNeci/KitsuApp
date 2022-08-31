package karpenko.test.kitsuapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import karpenko.test.kitsuapp.databinding.FragmentAnimeDetailBinding
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

    private fun observeViewModel() {
        viewModel.animeDetailLiveData.observe(viewLifecycleOwner) {
            it?.let {
                binding.attributes = it
            }
        }
    }

}