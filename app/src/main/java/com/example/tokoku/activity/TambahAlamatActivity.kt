package com.example.tokoku.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.tokoku.R
import com.example.tokoku.app.ApiConfigAlamat
import com.example.tokoku.helper.Helper
import com.example.tokoku.model.ResponModel
import kotlinx.android.synthetic.main.activity_tambah_alamat.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahAlamatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_alamat)
        // setToolbar
        Helper().setToolbar(this, toolbar, "Tambah Alamat")

        getProvinsi()
    }

    private fun getProvinsi() {
        ApiConfigAlamat.instanceRetrofit.getProvinsi().enqueue(object : Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // handel ketika berhasil
                if (response.isSuccessful) {
                    pb.visibility = View.GONE
                    div_provinsi.visibility = View.VISIBLE

                    val res = response.body()!!
                    val arryString = ArrayList<String>()
                    arryString.add("Pilih Provinsi")

                    val listProvinsi = res.provinsi
                    for (prov in listProvinsi) {
                        arryString.add(prov.nama)
                    }

                    val adapter = ArrayAdapter<Any>(this@TambahAlamatActivity, R.layout.item_spinner, arryString.toTypedArray())
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spn_provinsi.adapter = adapter
                        spn_provinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                if (position != 0) {
                                    val idProv = listProvinsi[position - 1].id
                                    Log.d("respons", "Provinsi id:" + idProv + " - " + listProvinsi[position - 1].nama)
                                    getKota(idProv)
                                }
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }
                        }
                } else {
                    Log.d("Error", "gagal memuat data: " + response.message())
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // handel ketika gagal
            }
        })
    }

    private fun getKota(id: Int) {
        ApiConfigAlamat.instanceRetrofit.getKota(id).enqueue(object : Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // handel ketika berhasil
                if (response.isSuccessful) {
                    pb.visibility = View.GONE
                    div_kota.visibility = View.VISIBLE

                    val res = response.body()!!
                    val listArray = res.kota_kabupaten

                    val arryString = ArrayList<String>()
                    arryString.add("Pilih Kota")
                    for (prov in listArray) {
                        arryString.add(prov.nama)
                    }

                    val adapter = ArrayAdapter<Any>(this@TambahAlamatActivity, R.layout.item_spinner, arryString.toTypedArray())
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spn_kota.adapter = adapter

                    spn_kota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            if (position != 0) {
                                val idKota = listArray[position - 1].id
                                Log.d("respons", "Provinsi id:" + idKota + " - " + listArray[position - 1].nama)
                                getKecamatan(idKota)
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }
                    }
                } else {
                    Log.d("Error", "gagal memuat data: " + response.message())
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // handel ketika gagal
            }
        })
    }

    private fun getKecamatan(id: Int) {
        ApiConfigAlamat.instanceRetrofit.getKecamatan(id).enqueue(object : Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // handel ketika berhasil
                if (response.isSuccessful) {
                    pb.visibility = View.GONE
                    div_kecamatan.visibility = View.VISIBLE

                    val res = response.body()!!
                    val arryString = ArrayList<String>()
                    arryString.add("Pilih Kecamatan")
                    for (data  in res.kecamatan) {
                        arryString.add(data.nama)
                    }

                    val adapter = ArrayAdapter<Any>(this@TambahAlamatActivity, R.layout.item_spinner, arryString.toTypedArray())
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spn_kecamatan.adapter = adapter
                } else {
                    Log.d("Error", "gagal memuat data: " + response.message())
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // handel ketika gagal
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}