package com.example.lotteryapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LotteryNumListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottery_num_list)

        val pref = getSharedPreferences("lotto_nums", Context.MODE_PRIVATE)
        // 파일 이름만 같으면 공유 가능!
        var lottoNums = pref.getString("lottonums", "")
        
//      준비물 1 => numList
        var numlist = if(lottoNums == ""){ //if문 마지막 구문이 대입이 됨 => lottoNums에 값이 없으면 빈 값 리턴
            mutableListOf<String>()
        }else{
            lottoNums!!.split(",").toMutableList()  // !!는 무조건 값이 있다는 뜻
        }

        val listView = findViewById<RecyclerView>(R.id.num_list)
        listView.setHasFixedSize(true)  // XML 상으로 존재하는 리사이클러뷰에 레이아웃 매니저를 설정해주고, 정의해둔 어댑터를 설정해줄 때
//      준비물3 => 레이아웃 매니저
        listView.layoutManager = LinearLayoutManager(this)
        
//      준비물 4,5
        listView.adapter = LotteryListAdapter(numlist)
        listView.setHasFixedSize(true)
    }
}