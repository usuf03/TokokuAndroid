package com.example.tokoku.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "keranjang") // the name of tabel
public class Produk implements Serializable {
    // jika menggunakan java didepannya harus ditambhkan public
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idTb")
    public int idTb; // id tabel

    public int id;
    public String name;
    public String harga;
    public String deskripsi;
    public int category_id;
    public String image;
    public String created_at;
    public String updated_at;

    public int jumlah = 1; // untuk menampung jumlah data produk yg dipilih dikeranjang
    public boolean selected = true; // untuk mengecek validasi checkbox dan default nya selalu true
}
