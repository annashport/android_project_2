package com.example.myyyyapplication.presentation.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.myyyyapplication.data.source.remote.model.WorkshopModel
import com.example.myyyyapplication.databinding.FragmentAddScheduleBinding
import com.example.myyyyapplication.presentation.viewmodel.PlanViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.DayOfWeek

class AddScheduleFragment: Fragment() {

    private var _binding: FragmentAddScheduleBinding? = null
    private val binding get() = _binding!!

    private val vm: PlanViewModel by viewModel()

    private var workShop: WorkshopModel? = null
    private var dayOfWeek: DayOfWeek? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dayOfWeek = arguments?.getInt(DAY_OF_WEEK_ARGUMENT)?.let { DayOfWeek.of(it) }
        binding.ivClose.setOnClickListener { activity?.onBackPressed() }
        binding.btnChoose.setOnClickListener {
            binding.spinner.visibility = View.VISIBLE
            binding.btnChoose.visibility = View.GONE
            binding.spinner.performClick()
        }
        vm.likedWorkshopsLiveData.observe(viewLifecycleOwner) { likedItems ->
            initSpinner(likedItems)
        }
        binding.btnSave.setOnClickListener {
            workShop?.let { vm.updateWorkshop(it) }
            activity?.onBackPressed()
        }
    }

    private fun initSpinner(likedItems: List<WorkshopModel>) {
        val items = likedItems
            .filter { workShop ->
                dayOfWeek.let {
                    workShop.getDaysOfWeek().contains(it)
                }
            }

        val monthAdapter = ArrayAdapter(
            requireContext(), R.layout.simple_spinner_item,
            items.map {
                it.name
            }
        )
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = monthAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                initHoursSpinner(items.get(position).hours)
                workShop = items.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.spinner.visibility = View.GONE
                binding.btnChoose.visibility = View.VISIBLE
            }
        }
    }

    private fun initHoursSpinner(hours: String) {
        val range = parseHoursRange(hours)
        binding.hours.minValue = 0
        binding.hours.maxValue = range.size - 1
        binding.hours.displayedValues = range.toTypedArray()
        binding.hours.setOnValueChangedListener { _, _, position ->
            dayOfWeek?.let { day ->
                val dayToTimePair = day to range[position]
                workShop = workShop?.copy(
                    scheduledTime = workShop?.scheduledTime?.toMutableMap()?.plus(dayToTimePair) ?: mapOf(dayToTimePair),
                )
            }
        }
        binding.btnSave.visibility= View.VISIBLE
        binding.hours.visibility = View.VISIBLE
    }

    private fun parseHoursRange(hours: String):List<String> {
        val split = hours.split("-")
        val start = split.first().split(":").first().toInt()
        val end = split.last().split(":").first().toInt()
       return (start..end).map {
           "$it:00"
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val DAY_OF_WEEK_ARGUMENT = "DAY_OF_WEEK_ARGUMENT"
    }
}