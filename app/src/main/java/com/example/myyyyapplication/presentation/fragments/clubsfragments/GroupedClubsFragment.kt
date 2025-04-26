package com.example.myyyyapplication.presentation.fragments.clubsfragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myyyyapplication.R
import com.example.myyyyapplication.databinding.FragmentInterestsBinding
import com.example.myyyyapplication.presentation.viewmodel.GroupedClubsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.view.isGone
import com.example.myyyyapplication.presentation.GroupType
import org.koin.core.parameter.parametersOf

class GroupedClubsFragment : Fragment() {

    private var _binding: FragmentInterestsBinding? = null
    private val binding get() = _binding!!

    private val vm: GroupedClubsViewModel by viewModel {
        parametersOf(requireArguments().getSerializable(GROUP_BY_TYPE) as GroupType)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInterestsBinding.inflate(inflater, container, false)
        val view = binding.root
        subscribeToWorkshops()
        return view
    }

    private fun subscribeToWorkshops() {
        vm.workshopsLiveData.observe(viewLifecycleOwner) {
            it.forEach { group, values ->

                // todo replace with recycler view
                val cardsRoot = layoutInflater.inflate(R.layout.workshop_cardview,binding.cardsContainer, false)
                val container = cardsRoot.findViewById<LinearLayout>(R.id.container)
                val tvTitle = cardsRoot.findViewById<TextView>(R.id.clubInterests8)
                val expandButton = cardsRoot.findViewById<ImageView>(R.id.expandButton8)
                expandButton.setOnClickListener {
                    if (container.isGone) {
                        container.visibility = View.VISIBLE
                        expandButton.setImageResource(R.drawable.arrow_up_24)
                    } else {
                        container.visibility = View.GONE
                        expandButton.setImageResource(R.drawable.arrow_down_24)
                    }
                }
                tvTitle.text = group
                values.forEach { workshop ->
                    val info = layoutInflater.inflate(R.layout.info_layout,container, false)

                    val nameTV = info.findViewById<TextView>(R.id.clubNameTextView8)
                    nameTV.text = workshop.name

                    val addressTV = info.findViewById<TextView>(R.id.clubAddressTextView8)
                    addressTV.text = workshop.address

                    val phoneTV = info.findViewById<TextView>(R.id.clubPhoneTextView8)
                    phoneTV.text = workshop.phone

                    val siteTV = info.findViewById<TextView>(R.id.clubWebsiteTextView8)
                    siteTV.text = workshop.website

                    val save = info.findViewById<ImageView>(R.id.saveButton8)
                    if (workshop.isLiked) {
                        save.setImageResource(R.drawable.favorite_saved_foreground)
                    }
                    save.setOnClickListener(object : OnClickListener{
                        private var isLiked = workshop.isLiked
                        override fun onClick(v: View?) {
                            isLiked = isLiked.not()
                            if (isLiked) {
                                save.setImageResource(R.drawable.favorite_saved_foreground)
                            } else {
                                save.setImageResource(R.drawable.favorite_foreground)
                            }
                            vm.updateWorkshop(workshop.copy(isLiked = isLiked))
                        }

                    })
                    container.addView(info)
                }
                binding.cardsContainer.addView(cardsRoot)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val GROUP_BY_TYPE = "GROUP_BY_TYPE"
    }

}