package karpenko.test.kitsuapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import karpenko.test.kitsuapp.databinding.FragmentAnimeListBinding
import karpenko.test.kitsuapp.view.adapter.AnimeListAdapter
import karpenko.test.kitsuapp.viewmodel.AnimListViewModel

class AnimeListFragment : Fragment() {

    private lateinit var binding: FragmentAnimeListBinding
    private val viewModel: AnimListViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[AnimListViewModel::class.java]
    }
    private var animeListAdapter: AnimeListAdapter = AnimeListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimeListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) viewModel.loadDataFromRemote() else viewModel.loadDataFromDatabase()

        observeViewModel()

        binding.animeListRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = animeListAdapter

        }

        binding.swipeToRefresh.setOnRefreshListener {
            binding.animeListRV.visibility = View.GONE
            binding.errorMessageTV.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.loadDataFromRemote()
            binding.swipeToRefresh.isRefreshing = false
        }

    }

    private fun observeViewModel() {
        viewModel.listOfAnimeLiveData.observe(viewLifecycleOwner) {

            it?.let {
                binding.animeListRV.visibility = View.VISIBLE
                animeListAdapter.animeAttributesList = it
            }
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            it?.let {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.errorMessageTV.visibility = View.GONE
                    binding.animeListRV.visibility = View.GONE
                }
            }
        }
        viewModel.animeLoadErrorLiveData.observe(viewLifecycleOwner) {

            it?.let {
                binding.errorMessageTV.visibility = if (it) View.VISIBLE else View.GONE
                if (it) binding.progressBar.visibility = View.GONE
            }

        }
    }
}