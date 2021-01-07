package com.dadang.resepmakanan_dadangwibowo020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.name
    val itemList = ArrayList<ListItemMakanan>()
    lateinit var adapter: RVMakananAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // inisialisasi adapter
        adapter = RVMakananAdapter()
        recycle_view_1.adapter = adapter
        // memanggil api untuk GET data
        loadData()
    }
    private fun loadData() {
        // menampilkan loading progress bar
        pbLoading.visibility = View.VISIBLE
        APIManager.getInstance().service.listMakanan()
            .enqueue(object : Callback<ResponseData<List<ListItemMakanan>>> {
                override fun onResponse(
                    call: Call<ResponseData<List<ListItemMakanan>>>,
                    response: Response<ResponseData<List<ListItemMakanan>>>
                ) {
                    val listData: List<ListItemMakanan> = response.body()!!.results
                    // update data dari jaringan ke adapter
                    itemList.clear()
                    itemList.addAll(listData)
                    adapter.updateData(itemList)
                    // hide loading progress bar
                    pbLoading.visibility = View.GONE
                }
                override fun onFailure(call: Call<ResponseData<List<ListItemMakanan>>>, t: Throwable) {
                    // jika ada error pada jaringan
                    Log.e(TAG, "Error on loading data")
                    pbLoading.visibility = View.GONE
                }
            })
    }
}