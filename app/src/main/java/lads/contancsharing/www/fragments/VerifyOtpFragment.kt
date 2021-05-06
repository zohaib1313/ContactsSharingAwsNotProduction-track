package lads.contancsharing.www.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import lads.contancsharing.www.R
import lads.contancsharing.www.activities.MainActivity
import lads.contancsharing.www.databinding.FragmentVerifyOtpBinding
import lads.contancsharing.www.utils.Helper


class VerifyOtpFragment : BaseFragment() {

    lateinit var mBinding: FragmentVerifyOtpBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        isAttached = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentVerifyOtpBinding.inflate(layoutInflater)
//        mBinding.btnNext.setOnClickListener {
//            changeFragment()
//        }
        mBinding.btnDone.setOnClickListener {
            changeFragment(ProfileInfoFragment.newInstance(0),false)
        }



        lads.contancsharing.www.utils.Helper.hideKeyboard(requireActivity())

        return mBinding.root
    }

    companion object {
        private val ARG_DATA = "position"
        fun newInstance(index: Int): VerifyOtpFragment {
            val fragment = VerifyOtpFragment()
            val args = Bundle()
            args.putInt(ARG_DATA, index)
            fragment.arguments = args
            return fragment
        }
    }


    private fun changeFragment(fragment: Fragment, needToAddBackstack: Boolean) {
        val mFragmentTransaction: FragmentTransaction =
            activity?.supportFragmentManager!!.beginTransaction()
        mFragmentTransaction.replace(R.id.fragmentContainerLogin, fragment)
        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        if (needToAddBackstack) mFragmentTransaction.addToBackStack(null)
        mFragmentTransaction.commit()
    }
}