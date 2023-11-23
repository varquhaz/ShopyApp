package com.varqulabs.shopyapp.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.varqulabs.shopyapp.data.local.entity.ProductEntity
import com.varqulabs.shopyapp.navigation.Screen

@Database(entities = [ProductEntity::class], version = 1)
abstract class HomeDataBase : RoomDatabase(){

    abstract val dao: HomeDao

}