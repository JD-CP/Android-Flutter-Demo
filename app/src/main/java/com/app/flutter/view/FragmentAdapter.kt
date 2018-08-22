package com.app.flutter.view

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import io.flutter.facade.FlutterFragment

class FragmentAdapter(manager: FragmentManager, context: Context, titles: Array<String>, fragments: ArrayList<FlutterFragment>)
    : FragmentPagerAdapter(manager) {

    private var mContext: Context? = null

    private var mTitles = arrayOf<String>()
    private var mFragments = arrayListOf<FlutterFragment>()

    init {
        this.mContext = context;
        this.mTitles = titles;
        this.mFragments = fragments;
    }

    override fun getItem(position: Int): Fragment = mFragments[position]

    override fun getCount(): Int = mTitles.size

    override fun getPageTitle(position: Int): CharSequence = mTitles[position]

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        return fragment
    }

}