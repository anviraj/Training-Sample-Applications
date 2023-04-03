package com.example.atvdemoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*

class ListFragment : RowsSupportFragment() {

    private var rootAdapter : ArrayObjectAdapter = ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = rootAdapter
    }

    fun bindData(datalist: DataModel){

        datalist.result.forEachIndexed {index,result ->
            val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())

            result.details.forEach {
                arrayObjectAdapter.add(it)
            }

            val headerItem = HeaderItem(result.title)
            val listRow = ListRow(headerItem,arrayObjectAdapter)
            rootAdapter.add(listRow)
        }



    }
//    fun setOnContentSelectedListener(listener : (DataModel.Result.Detail) -> Unit){
//        this.itemSelectedListener = listener
//    }



}