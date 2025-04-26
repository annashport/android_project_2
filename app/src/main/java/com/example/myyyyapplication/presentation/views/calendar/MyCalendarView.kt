package com.example.myyyyapplication.presentation.views.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myyyyapplication.R
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.util.Calendar

class MyCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val calendarRecyclerView: RecyclerView
    private val monthSpinner: Spinner
    private val yearSpinner: Spinner
    private val daysList = ArrayList<String>()
    private var selectedDayPosition = -1
    private val years = ArrayList<Int>()
    private val calendar = Calendar.getInstance()
    private var daySelectedListener: ((LocalDate) -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_custom_calendar, this, true)
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthSpinner = findViewById(R.id.monthSpinner)
        yearSpinner = findViewById(R.id.yearSpinner)
        setupSpinners()
        setupCalendar()
    }

    fun setOnDateSelectedListener(listener: (LocalDate) -> Unit) {
       daySelectedListener = listener
    }

    private fun setupSpinners() {
        val monthAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, DateFormatSymbols().months)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        monthSpinner.adapter = monthAdapter
        monthSpinner.setSelection(calendar.get(Calendar.MONTH))

        for (i in calendar.get(Calendar.YEAR)..calendar.get(Calendar.YEAR).plus(10)) {
            years.add(i)
        }

        val yearAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, years)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearSpinner.adapter = yearAdapter
        yearSpinner.setSelection(years.indexOf(calendar.get(Calendar.YEAR)))

        monthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calendar.set(Calendar.MONTH, position)
                setupCalendar()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calendar.set(Calendar.YEAR, years[position])
                setupCalendar()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupCalendar() {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val monthStartDay = calendar.get(Calendar.DAY_OF_WEEK) - 1
        val totalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        daysList.clear()
        repeat(monthStartDay) { daysList.add("") }
        for (i in 1..totalDays) {
            daysList.add(i.toString())
        }

        calendarRecyclerView.layoutManager = GridLayoutManager(context, 7)
        calendarRecyclerView.adapter = CalendarAdapter(daysList, selectedDayPosition) { position, day ->
            selectedDayPosition = position
            val loc = LocalDate.of(yearSpinner.selectedItem.toString().toInt(),  monthSpinner.selectedItemPosition + 1, day.toInt())
            daySelectedListener?.invoke(loc)
        }
    }
}
