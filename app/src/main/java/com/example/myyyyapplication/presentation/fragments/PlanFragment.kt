package com.example.myyyyapplication.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myyyyapplication.databinding.FragmentPlanBinding
import com.example.myyyyapplication.presentation.adapters.LikedAdapter
import com.example.myyyyapplication.presentation.adapters.ScheduleAdapter
import com.example.myyyyapplication.presentation.viewmodel.PlanViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale


class PlanFragment : Fragment() {

    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!

    private val vm: PlanViewModel by viewModel()

    private var dayOfWeek: DayOfWeek? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendarView.setOnDateSelectedListener { date ->
            BottomSheetBehavior.from<View>(binding.llBottomSheet).apply {
                setState(BottomSheetBehavior.STATE_EXPANDED);
            }
            this.dayOfWeek = date.dayOfWeek
            binding.tvSelectedDate.text = "${date.dayOfMonth} ${date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())}"
            vm.reload()
        }
        subscribeToLikedWorkshops()
        binding.rvLiked.doOnLayout {
            initBottomSheetBehavior()
        }
        binding.btnAdd.setOnClickListener {
            childFragmentManager.beginTransaction().apply {
                val fragment = AddScheduleFragment()
                fragment.arguments =  bundleOf(AddScheduleFragment.DAY_OF_WEEK_ARGUMENT to dayOfWeek?.value)
                replace(binding.fragmentContainer.id, fragment)
                addToBackStack(null)
                commit()
            }
        }
        initBackPress()
    }

    private fun initBackPress() {
        activity?.onBackPressedDispatcher?.addCallback(object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                   if (childFragmentManager.backStackEntryCount> 0) {
                       childFragmentManager.popBackStack()
                       vm.reload()
                   } else if (BottomSheetBehavior.from<View>(binding.llBottomSheet).state == BottomSheetBehavior.STATE_EXPANDED) {
                       BottomSheetBehavior.from<View>(binding.llBottomSheet).apply {
                           setState(BottomSheetBehavior.STATE_HIDDEN);
                       }
                   } else {
                       activity?.finish()
                   }
                }
            }
        )
    }

    private fun subscribeToLikedWorkshops() {
        vm.likedWorkshopsLiveData.observe(viewLifecycleOwner) {
            binding.rvLiked.layoutManager = LinearLayoutManager(context)
            binding.rvLiked.adapter = LikedAdapter(it.toMutableList()) { workshop ->
                vm.updateWorkshop(workshop)
            }
            dayOfWeek?.let {
                initSchedule(it)
            }
        }
    }

    private fun initBottomSheetBehavior() {
        val bottomSheetBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from<View>(binding.llBottomSheet)
        bottomSheetBehavior.maxHeight = binding.rvLiked.height
        bottomSheetBehavior.peekHeight = 20// min height doesnt work( so we cant show line to expand it manually
        bottomSheetBehavior.skipCollapsed = true
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN)
    }

    private fun initSchedule(dayOfWeek: DayOfWeek) {
        vm.likedWorkshopsLiveData.value?.filter { it.scheduledTime?.keys?.contains(dayOfWeek) ?: false }?.toMutableList()?.let { scheduleList ->
            binding.rvSchedule.layoutManager = LinearLayoutManager(context)
            binding.rvSchedule.adapter = ScheduleAdapter(scheduleList, dayOfWeek) {
                vm.updateWorkshop(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}