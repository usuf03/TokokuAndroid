package com.example.tokoku.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.tokoku.model.Produk

@Dao
interface DaoKeranjang {

    @Insert(onConflict = REPLACE) // kalau ada 2 pilihan, pilih yg androidx.room
    fun insert(data: Produk)

    @Delete
    fun delete(data: Produk)

    @Update
    fun update(data: Produk): Int

    @Query("SELECT * FROM keranjang ORDER BY id ASC")
    fun getAll(): List<Produk>

    @Query("SELECT * FROM keranjang WHERE id = :id LIMIT 1")
    fun getProduk(id: Int): Produk

    @Query("DELETE FROM keranjang")
    fun deleteAll(): Int
}