package com.example.tokoku.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.tokoku.R
import com.example.tokoku.adapter.AdapterProduk
import com.example.tokoku.adapter.AdapterSlider
import com.example.tokoku.model.Produk

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vpSlider: ViewPager
    lateinit var rvProduk: RecyclerView
    lateinit var rvProdukTerlaris: RecyclerView
    lateinit var rvElektronik: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rvProduk = view.findViewById(R.id.rv_produk)
        rvProdukTerlaris = view.findViewById(R.id.rv_produkTerlaris)
        rvElektronik = view.findViewById(R.id.rv_elektronik)

        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.slider1)
        arrSlider.add(R.drawable.slider2)
        arrSlider.add(R.drawable.slider3)
        arrSlider.add(R.drawable.slider4)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager3 = LinearLayoutManager(activity)
        layoutManager3.orientation = LinearLayoutManager.HORIZONTAL

        rvProduk.adapter = AdapterProduk(arrProduk)
        rvProduk.layoutManager = layoutManager

        rvProdukTerlaris.adapter = AdapterProduk(arrProdukTerlaris)
        rvProdukTerlaris.layoutManager = layoutManager2

        rvElektronik.adapter = AdapterProduk(arrElektronik)
        rvElektronik.layoutManager = layoutManager3

        return view
    }

    val arrProduk: ArrayList<Produk>get() {
        val arr = ArrayList<Produk>()
        val p1 = Produk()
        p1.nama = "HP OPPO"
        p1.harga = "Rp. 3.500.000"
        p1.gambar = R.drawable.hp_oppo1

        val p2 = Produk()
        p2.nama = "HP OPPO"
        p2.harga = "Rp. 4.500.000"
        p2.gambar = R.drawable.hp_oppo2

        val p3 = Produk()
        p3.nama = "HP OPPO"
        p3.harga = "Rp. 5.500.000"
        p3.gambar = R.drawable.hp_oppo3

        val p4 = Produk()
        p4.nama = "HP OPPO"
        p4.harga = "Rp. 6.500.000"
        p4.gambar = R.drawable.hp_oppo4

        val p5 = Produk()
        p5.nama = "HP OPPO"
        p5.harga = "Rp. 7.500.000"
        p5.gambar = R.drawable.hp_oppo5

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)
        arr.add(p4)
        arr.add(p5)

        return arr
    }

    val arrProdukTerlaris: ArrayList<Produk>get() {
        val arr = ArrayList<Produk>()
        val p1 = Produk()
        p1.nama = "HP OPPO"
        p1.harga = "Rp. 3.500.000"
        p1.gambar = R.drawable.hp_oppo1

        val p2 = Produk()
        p2.nama = "HP OPPO"
        p2.harga = "Rp. 4.500.000"
        p2.gambar = R.drawable.hp_oppo2

        val p3 = Produk()
        p3.nama = "HP OPPO"
        p3.harga = "Rp. 5.500.000"
        p3.gambar = R.drawable.hp_oppo3

        val p4 = Produk()
        p4.nama = "HP OPPO"
        p4.harga = "Rp. 6.500.000"
        p4.gambar = R.drawable.hp_oppo4

        val p5 = Produk()
        p5.nama = "HP OPPO"
        p5.harga = "Rp. 7.500.000"
        p5.gambar = R.drawable.hp_oppo5

        arr.add(p5)
        arr.add(p4)
        arr.add(p3)
        arr.add(p2)
        arr.add(p1)

        return arr
    }

    val arrElektronik: ArrayList<Produk>get() {
        val arr = ArrayList<Produk>()
        val p1 = Produk()
        p1.nama = "HP OPPO"
        p1.harga = "Rp. 3.500.000"
        p1.gambar = R.drawable.hp_oppo1

        val p2 = Produk()
        p2.nama = "HP OPPO"
        p2.harga = "Rp. 4.500.000"
        p2.gambar = R.drawable.hp_oppo2

        val p3 = Produk()
        p3.nama = "HP OPPO"
        p3.harga = "Rp. 5.500.000"
        p3.gambar = R.drawable.hp_oppo3

        val p4 = Produk()
        p4.nama = "HP OPPO"
        p4.harga = "Rp. 6.500.000"
        p4.gambar = R.drawable.hp_oppo4

        val p5 = Produk()
        p5.nama = "HP OPPO"
        p5.harga = "Rp. 7.500.000"
        p5.gambar = R.drawable.hp_oppo5

        arr.add(p3)
        arr.add(p5)
        arr.add(p4)
        arr.add(p1)
        arr.add(p2)

        return arr
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
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}