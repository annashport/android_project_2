package com.example.myyyyapplication.presentation.fragments.clubsfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import com.example.myyyyapplication.R
import com.example.myyyyapplication.presentation.GroupType
import com.example.myyyyapplication.presentation.fragments.clubsfragments.GroupedClubsFragment.Companion.GROUP_BY_TYPE


class PageClubsFragment : Fragment() {

    private var controller: NavController? = null

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (controller?.popBackStack()?.not() != false) {
                activity?.finish()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_clubs, container, false)
    }
    //Навігація по кніпочкам
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bInterests = view.findViewById<Button>(R.id.button_Interests)
        val bPrices = view.findViewById<Button>(R.id.button_prices)
        val bDays = view.findViewById<Button>(R.id.button_days)
        val bHours = view.findViewById<Button>(R.id.button_hours)
        controller = findNavController()
        bInterests.setOnClickListener { controller?.navigate(R.id.interests, bundleOf(GROUP_BY_TYPE to GroupType.Interests)) }
        bPrices.setOnClickListener { controller?.navigate(R.id.interests, bundleOf(GROUP_BY_TYPE to GroupType.Prices)) }
        bDays.setOnClickListener { controller?.navigate(R.id.interests, bundleOf(GROUP_BY_TYPE to GroupType.Days)) }
        bHours.setOnClickListener { controller?.navigate(R.id.interests, bundleOf(GROUP_BY_TYPE to GroupType.Hours)) }
        activity?.onBackPressedDispatcher?.addCallback(onBackPressedCallback)
    }

}