package karpenko.test.kitsuapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import karpenko.test.kitsuapp.R
import karpenko.test.kitsuapp.databinding.FragmentMangaDetailBinding
import karpenko.test.kitsuapp.viewmodel.MangaDetailViewModel

class MangaDetailFragment : Fragment() {

    private lateinit var binding: FragmentMangaDetailBinding
    private var mangaId = 0
    private val viewModel: MangaDetailViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[MangaDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
            mangaId = MangaDetailFragmentArgs.fromBundle(it).mangaId
        }

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_manga_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch(mangaId)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.mangaDetailLiveData.observe(viewLifecycleOwner) {
            it?.let {
                binding.attributes = it
            }
        }
    }

}