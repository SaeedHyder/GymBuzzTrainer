package com.app.gymbuztrainer.ui.binders

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.app.gymbuztrainer.R
import com.app.gymbuztrainer.interfaces.ItemClickListener
import com.app.gymbuztrainer.ui.viewbinders.abstracts.RecyclerViewsBinder
import com.nostra13.universalimageloader.core.ImageLoader

/**
 * Created on 5/23/2018.
 */


class BinderGymDetail constructor(itemClickListener: ItemClickListener) : RecyclerViewsBinder<String>() {

     var imageLoader: ImageLoader
     var itemClickListener: ItemClickListener? = null

    init {
        this.itemClickListener = itemClickListener
        imageLoader = ImageLoader.getInstance()
    }


    override fun createViewHolder(view: View): RecyclerViewsBinder.BaseViewHolder {
        return ViewHolder(view)
    }

    override fun bindView(path: String, position: Int, viewHolder: Any, context: Context) {
        val holder = viewHolder as ViewHolder

        imageLoader.displayImage(path, holder.ivGym)

        holder.ivGym.setOnClickListener {
            itemClickListener?.itemClicked(path,position)

        }

    }

    override fun bindItemId(position: Int) {

    }

    class ViewHolder(view: View) : BaseViewHolder(view) {

         val ivGym: ImageView

        init {
            ivGym = view.findViewById(R.id.ivGym) as ImageView
        }
    }

}