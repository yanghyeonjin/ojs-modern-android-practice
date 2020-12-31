package com.example.ojsmodernandroidpractice.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.ojsmodernandroidpractice.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var _mBinding: FragmentDetailsBinding? = null
    private val mBinding get() = _mBinding!!

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receivedData = args.text
        mBinding.tvReceivedData.text = receivedData
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}