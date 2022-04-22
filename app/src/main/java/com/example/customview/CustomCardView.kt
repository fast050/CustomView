package com.example.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IntegerRes
import androidx.cardview.widget.CardView

class CustomCardView @JvmOverloads constructor(context: Context,attrs:AttributeSet?=null,defStyleAttr:Int=0) : CardView(context,attrs,defStyleAttr) {

    private var imageView :ImageView
    private var textView: TextView
    private var checkBox: CheckBox

    init {
       // val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //val view = inflater.inflate(R.layout.custom_view_item,this,true)

        val view =LayoutInflater.from(context).inflate(R.layout.custom_view_item,this,true)

        imageView = view.findViewById(R.id.custom_view_image)
        textView =view.findViewById(R.id.custom_view_text)
        checkBox = view.findViewById(R.id.custom_view_check_box)
    }



    fun setImage(res:Int){
        imageView.setImageResource(res)

        postInvalidate()
    }

    fun setText(text:String?){
      text?.let {
          textView.text = text.trim()

          postInvalidate()
      }
    }

    fun setCheckBoxState(isCheck:Boolean){
        checkBox.isChecked = isCheck
    }
}