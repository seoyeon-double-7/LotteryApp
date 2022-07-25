package com.example.lotteryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LotteryListAdapter(val dataList: List<String>)
    :RecyclerView.Adapter<LotteryListAdapter.ItemViewHolder>()
    {
        class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view)
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
//      한 항목을 표시할 레이아웃 관련 뷰를 만들어 줌
//      (viewType 값이 바로 geItemViewType에서 반환한 레이아웃 리소스 식별자)
            val view = LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)

            return ItemViewHolder(view)
        }

//      내용 바꿔주는 친구(스크롤 할때 데이터 변경) => 바꿀 포지션에 있는 값을 텍스트에 대입
//      내용만 재활용 => 뷰를 절약 할 수 있음! => 끝에 있는 객체를 재활용한다.
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//            holder.bind(dataList[position])
            holder.view.findViewById<TextView>(R.id.num).text = dataList[position]
        }

        override fun getItemCount(): Int {  //길이를 리턴
            return dataList.size
        }
        override fun getItemViewType (position: Int) :Int{  //
            return R.layout.lotto_item
        }
    }