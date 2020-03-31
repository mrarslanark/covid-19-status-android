package tech.appclub.covid_19status.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import tech.appclub.covid_19status.R
import tech.appclub.covid_19status.adapters.SafetyAdapter
import tech.appclub.covid_19status.databinding.FragmentSafetyBinding
import tech.appclub.covid_19status.response.SafetyTips

class SafetyFragment : Fragment() {

    private lateinit var binding: FragmentSafetyBinding

    companion object {
        const val COVID_GOV_PK = "http://covid.gov.pk/"
        const val HELP_LINE = "tel:1166"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_safety, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SafetyAdapter(safetyTipsList())
        binding.safetyTipsRv.adapter = adapter

        binding.pkWebsite.setOnClickListener {
            val uri = Uri.parse(COVID_GOV_PK)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.helpline.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse(HELP_LINE)
            startActivity(intent)
        }
    }

    private fun safetyTipsList(): List<SafetyTips> {

        val list = mutableListOf<SafetyTips>()

        val handWash = SafetyTips(
            requireActivity().resources.getDrawable(R.drawable.ic_wash_hands, null),
            getString(R.string.hand_wash_title), getString(R.string.hand_wash_description)
        )
        list.add(handWash)

        val medicalMask = SafetyTips(
            requireActivity().resources.getDrawable(R.drawable.ic_medical_mask, null),
            getString(R.string.medical_mask_title), getString(R.string.medical_mask_description)
        )
        list.add(medicalMask)

        val socialDistancing = SafetyTips(
            requireActivity().resources.getDrawable(R.drawable.ic_social_distancing, null),
            getString(R.string.social_distancing_title),
            getString(R.string.social_distancing_description)
        )
        list.add(socialDistancing)

        val stayHome = SafetyTips(
            requireActivity().resources.getDrawable(R.drawable.ic_stay_home, null),
            getString(R.string.stay_home_title), getString(R.string.stay_home_description)
        )
        list.add(stayHome)

        val fruitsAndVeges = SafetyTips(
            requireActivity().resources.getDrawable(R.drawable.ic_vitamic_c, null),
            getString(R.string.fruits_veges_title), getString(R.string.fruits_veges_description)
        )
        list.add(fruitsAndVeges)

        val kindness = SafetyTips(
            requireActivity().resources.getDrawable(R.drawable.ic_kindness, null),
            getString(R.string.kindness_title), getString(R.string.kindness_description)
        )
        list.add(kindness)

        return list

    }

}
