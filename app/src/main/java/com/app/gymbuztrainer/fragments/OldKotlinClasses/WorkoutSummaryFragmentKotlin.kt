package com.app.gymbuztrainer.fragments.OldKotlinClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gymbuztrainer.R
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment
import com.app.gymbuztrainer.helpers.UIHelper
import com.app.gymbuztrainer.ui.views.TitleBar
import kotlinx.android.synthetic.main.fragment_workout_summary.*

/**
 * Created on 5/22/2018.
 */


class WorkoutSummaryFragmentKotlin:BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_workout_summary,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnChangeView.setOnClickListener {
            UIHelper.showShortToastInCenter(mainActivity,getString(R.string.will_be_imp_beta))
        }

    }

    override fun setTitleBar(titleBar: TitleBar?) {
        super.setTitleBar(titleBar)

        titleBar?.hideButtons()
        titleBar?.showBackButton()
        titleBar?.setSubHeading(getString(R.string.you_did_great))


    }

}