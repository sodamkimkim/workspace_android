package com.example.tablayout_pager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * @ pager
 * - 종이넘기듯이 화면을 넘겨주는 역할을 하는 오브젝트
 * - 데이터와 화면을 잘 연결해주기 위해서 adaper가 필요하다.
 *
 * @ TabLayout까지 연계해서 생각
 * - tab을 담당하는 역할
 * - 보통 같이 작성을 하지만, 따로 따로 만들어도 상관이 없다.
 *
 * @ Listener
 * - pager와 TabLayout을 연결해 주기 위해 필요하다.
 *
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}