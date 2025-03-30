package com.example.mainscreen.homepage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.models.Course
import com.example.mainscreen.basefragment.BaseFragment
import com.example.mainscreen.databinding.FragmentMainScreenBinding
import com.example.mainscreen.homepage.viewmodel.HomePageViewModel
import com.example.mainscreen.homepage.viewmodel.HomePageViewModel_Factory
import com.example.mainscreen.room.OnFavoriteButtonClick
import kotlinx.coroutines.launch

class MainScreenFragment : BaseFragment(), OnFavoriteButtonClick {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomePageViewModel
    private val adapter = CoursesListAdapter(this)

    override fun shouldShowBottomNav() = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        binding.coursesRecyclerView.adapter = adapter
        viewModel = ViewModelProvider(
            requireActivity(),
            HomePageViewModel.Factory
        )[HomePageViewModel::class.java]
        binding.coursesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toPublishDateTextView.setOnClickListener {
            adapter.submitList(viewModel.sortItemsByDateDescending(adapter.currentList))
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.coursesListState.collect { list ->
                    adapter.submitList(list.toList())
                }
            }
        }
    }

    override fun onButtonClick(course: Course, position: Int) {
        course.hasLike = !course.hasLike
        adapter.notifyItemChanged(position)
        if (course.hasLike) {
            viewModel.saveACourse(course)
        } else {
            viewModel.deleteACourse(course)
        }
        viewModel.updateState(adapter.currentList.toList())
        adapter.submitList(viewModel.coursesListState.value)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainScreenFragment()
    }

}