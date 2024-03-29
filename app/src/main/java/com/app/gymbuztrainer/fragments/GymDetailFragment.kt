package com.app.gymbuztrainer.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gymbuztrainer.R
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment
import com.app.gymbuztrainer.interfaces.ItemClickListener
import com.app.gymbuztrainer.ui.adapters.RecyclerViewsAdapter
import com.app.gymbuztrainer.ui.binders.BinderGymDetail
import com.app.gymbuztrainer.ui.views.TitleBar
import kotlinx.android.synthetic.main.fragment_gym_detail.*

/**
 * Created on 5/23/2018.
 */


class GymDetailFragment:BaseFragment(),ItemClickListener{

    lateinit var gymImageList: MutableList<String>

    private var rvAdapter: RecyclerViewsAdapter<String>? = null

    companion object {
        val Tag: String = "GymDetailFragment"
        fun newInstance(): GymDetailFragment {
            val fragment = GymDetailFragment()
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gym_detail,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindData()

    }

    fun bindData(){

        gymImageList = mutableListOf<String>()
        gymImageList.add(0,"drawable://" + R.drawable.gym_floor_1)
        gymImageList.add(1,"drawable://" + R.drawable.gym_floor_2)
        gymImageList.add(2,"drawable://" + R.drawable.gym_floor_1)

        rvAdapter = RecyclerViewsAdapter(gymImageList, BinderGymDetail(this), dockActivity, R.layout.gym_detail_item)
        rvGymImages.setLayoutManager(LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false))
        rvGymImages.setAdapter(rvAdapter)
        rvAdapter?.notifyDataSetChanged()


    }

    override fun setTitleBar(titleBar: TitleBar?) {
        super.setTitleBar(titleBar)

        titleBar?.hideButtons()
        titleBar?.showBackButton()
        titleBar?.setSubHeading(getString(R.string.gym_detail))

    }

    override fun itemClicked(imagePath: String?, position: Int) {
        dockActivity.replaceDockableFragment(ImageViewFragment.newInstance(), "ImageViewFragment")
    }

}