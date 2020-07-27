package com.example.mvvm.Data.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mvvm.View.Fragments.ContactUsFragment
import com.example.mvvm.View.Fragments.ImagesAPICall
import com.example.mvvm.View.Fragments.ImagesFragment


class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ContactUsFragment() //ChildFragment1 at position 0
            1 -> return ImagesFragment() //ChildFragment2 at position 1
            2 -> return ImagesAPICall() //ChildFragment3 at position 2
        }
        return ContactUsFragment() //does not happen
    }

    override fun getCount(): Int {
        return 3 //three fragments
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = getItem(position).javaClass.name
        title = title.replace("_".toRegex(), " ")
        return title.subSequence(title.lastIndexOf(".") + 1, title.length)
    }
}