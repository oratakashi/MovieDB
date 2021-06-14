package com.oratakashi.oratamovie.ui.genre

import android.util.Log
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.AdapterPagingBinding
import com.oratakashi.oratamovie.domain.`object`.Pagination
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.viewBinding

class PaginationAdapter(
    private val onClick: (Pagination, Int) -> Unit
) : RecyclerView.Adapter<ViewHolder<AdapterPagingBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterPagingBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterPagingBinding>, position: Int) {
        with(holder.binding) {
            tvText.text = when (data[position].number) {
                0 -> data[position].value
                else -> data[position].number.toString()
            }
            if (data[position].selected){
                tvText.setTextColor(ContextCompat.getColor(root.context, R.color.primary))
            }else{
                tvText.setTextColor(ContextCompat.getColor(root.context, R.color.white))
            }

            root.onClick {
                onClick.invoke(data[position], total)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitData(data: List<Pagination>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun moveToPosition(data: Pagination) {
        currentData = data.number

        val data: MutableList<Pagination> = ArrayList()

        val numberOfPage : Int = if(currentData + 8 > 1000){
            5
        } else if(currentData + 8 > 100) {
            6
        } else if(currentData + 8 > 20) {
            7
        } else if(currentData + 8 > 15) {
            7
        }else if(currentData + 8 > 11) {
            8
        }else if(currentData + 8 > 10) {
            9
        }else{
            9
        }

        if(currentData <= minData){
            for(i in currentData until when(currentData + 8 < total){
                true -> currentData + numberOfPage
                false -> total + 1
            }){
                data.add(
                    Pagination(i, "$i", i == currentData)
                )
            }
        }else{
            for(i in minData until when(minData + 8 < total){
                true -> currentData + numberOfPage
                false -> total + 1
            }){
                data.add(
                    Pagination(i, "$i", i == currentData)
                )
            }
        }

        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun moveToNext(){
        if(data.find { it.number == currentData + 1 } != null){
            onClick.invoke(data.find { it.number == currentData + 1 }!!, total)
        }
    }

    fun moveToBack(){
        onClick.invoke(Pagination(currentData -1, "${currentData - 1}", true), total)
    }

    fun submitTotal(total: Int) {
        this.total = total
        this.minData = total - 8
    }

    private val data: MutableList<Pagination> by lazy {
        ArrayList()
    }

    private var total: Int = 0
    private var currentData = 1
    private var minData = 0
}