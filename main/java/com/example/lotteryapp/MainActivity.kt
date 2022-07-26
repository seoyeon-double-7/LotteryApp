package com.example.lotteryapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var currentNums: String    //lateinit => 널러블 타입을 안 해도 됨! 초기화 안해도 괜춘

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //shared preference
        val pref = getSharedPreferences("lotto_nums",
            Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()

        //미리 생성
        var nums = findViewById<TextView>(R.id.lotto_nums)
        currentNums = generateRandomLottoNum(6, "-", true)
        nums.text = currentNums // 생성된것 currenNums에 저장

        //생성
        var create_btn = findViewById<Button>(R.id.create_btn)
        create_btn.setOnClickListener {
            currentNums = generateRandomLottoNum(6, "-", true)  //콤마 쓰면 안됨
            nums.text = currentNums // 생성된것 currenNums에 저장
        }

        //저장
        var save_btn = findViewById<Button>(R.id.save_btn)
        save_btn.setOnClickListener {
            var lottoNums = pref.getString("lottonums", "")   //키가 없으면 빈 문자열 가져옴

            var list = if(lottoNums == ""){ //if문 마지막 구문이 대입이 됨 => lottoNums에 값이 없으면 빈 값 리턴
                    mutableListOf<String>()
                }else{
                lottoNums!!.split(",").toMutableList()  // !!는 무조건 값이 있다는 뜻
            }

            list.add(currentNums)
            Log.d("mytag", list.size.toString())
            Log.d("mytag", list.toString())

            editor.putString("lottonums", list.joinToString(",")).apply()
        }

        var save_chk_btn = findViewById<Button>(R.id.chk_btn)
        save_chk_btn.setOnClickListener {
            val intent = Intent(this, LotteryNumListActivity::class.java)
            startActivity(intent)
        }
        var winning_btn = findViewById<Button>(R.id.winning_btn)
        winning_btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.naver.com/"))
            startActivity(intent)
        }

    }
    private fun generateRandomLottoNum(count: Int, adapter: String, overlap: Boolean) : String {

        if(overlap === false){  // 중복 허용x
            val mlist = mutableSetOf<Int>()

            for(i in 1.. count){
                mlist.add((1..45).random())
            }
            return mlist.joinToString(adapter)
        }
        else{
            val mlist = mutableListOf<Int>()


            for(i in 1.. count){
                mlist.add((1..45).random())
            }
            return mlist.joinToString(adapter)
        }
        return ""

        // Log.d("mytag", nums.text.toString())
    }

}