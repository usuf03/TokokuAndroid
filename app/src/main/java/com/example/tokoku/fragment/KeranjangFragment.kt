package com.example.tokoku.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tokoku.R
import com.example.tokoku.activity.PengirimanActivity
import com.example.tokoku.adapter.AdapterKeranjang
import com.example.tokoku.helper.Helper
import com.example.tokoku.model.Produk
import com.example.tokoku.room.MyDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KeranjangFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KeranjangFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var myDb: MyDatabase

    // dipanggil sekali ketika aktivity aktif
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_keranjang, container, false)
        init(view)
        myDb = MyDatabase.getInstance(requireActivity())!! // menggunakan requireActivity() diversi terbaru agar data boleh null

        mainButton()
        return view
    }

    lateinit var adapter: AdapterKeranjang
    var listProduk = ArrayList<Produk>()
    private fun displayProduk() {
        listProduk = myDb.daoKeranjang().getAll() as ArrayList // dipaksa arraylist, karena diadapter menggunakan arraylist dan tanda seru agar bisa data null

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        adapter = AdapterKeranjang(requireActivity(), listProduk, object : AdapterKeranjang.Listener{
            override fun onUpdate() {
                hitungTotal()
            }

            override fun onDelete(position: Int) {
                listProduk.removeAt(position)
                adapter.notifyDataSetChanged()
                hitungTotal()
            }
        })
        rvProduk.adapter = adapter
        rvProduk.layoutManager = layoutManager
    }

    fun hitungTotal() {
        val listProduk = myDb.daoKeranjang().getAll() as ArrayList // dipaksa arraylist, karena diadapter menggunakan arraylist dan tanda seru agar bisa data null

        var totalHarga = 0
        var isSelectedAll = true
        for (produk in listProduk) {
            if (produk.selected) {
                val harga = Integer.valueOf(produk.harga)
                totalHarga += (harga * produk.jumlah)
            } else {
                isSelectedAll = false
            }
        }

        cbAll.isChecked = isSelectedAll
        tvTotal.text = Helper().gantiRupiah(totalHarga)
    }

    private fun mainButton() {
        btnDelete.setOnClickListener {

        }

        btnBayar.setOnClickListener {
            startActivity(Intent(requireActivity(), PengirimanActivity::class.java))
        }

        cbAll.setOnClickListener {
            for (i in listProduk.indices) {
                val produk = listProduk[i]
                produk.selected = cbAll.isChecked

                listProduk[i] = produk
            }
            adapter.notifyDataSetChanged()
        }
    }

    lateinit var btnDelete: ImageView
    lateinit var rvProduk: RecyclerView
    lateinit var tvTotal: TextView
    lateinit var btnBayar: TextView
    lateinit var cbAll: CheckBox
    private fun init(view: View) {
        btnDelete = view.findViewById(R.id.btn_delete)
        rvProduk = view.findViewById(R.id.rv_produk)
        tvTotal = view.findViewById(R.id.tv_total)
        btnBayar = view.findViewById(R.id.btn_bayar)
        cbAll = view.findViewById(R.id.cb_all)
    }

    // untuk menjalankan displayProduk() berkali2 saat onPause
    override fun onResume() {
        displayProduk()
        hitungTotal()
        super.onResume()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KeranjangFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}