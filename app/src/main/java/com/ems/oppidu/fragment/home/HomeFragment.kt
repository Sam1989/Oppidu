package com.ems.oppidu.fragment.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ems.oppidu.base.BaseActivity
import com.ems.oppidu.base.BaseFragment
import com.oppidu.oppidu.databinding.FragmentHomeBinding
import com.planetsaverapp.fragment.home.HomeViewModel

/******
 * Home is showing availble user category, featured, Hot stores & offers
 * Most popular offers, Latest fashion offers are
 * showing in home page and  user avalibe cashback amount showing in
 * header view
 * ********/

class HomeFragment : BaseFragment() {


    val mViewModel by viewModels<HomeViewModel> { getViewModelProvider() }
    lateinit var mBinding: FragmentHomeBinding

    lateinit var mClickHandler: ClickHandler

    private lateinit var mActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupObserver()
    }

    /**
     * Reterivce api response using oserver method and show data in home
     * Page listing
     **/

    private fun setupObserver() {

    }


    /**
     * Variable intilization and binding view in main class
     **/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mClickHandler = ClickHandler()
        mBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {

            viewModel = mViewModel
            clickHandler = ClickHandler()
            lifecycleOwner = this@HomeFragment

        }

        return mBinding.root
    }

    /**
     * intilization List view in home page and call web api method
     **/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mActivity = activity as BaseActivity

    }



    /**
     * Home screen click event
     **/

    inner class ClickHandler  {

    }


}
