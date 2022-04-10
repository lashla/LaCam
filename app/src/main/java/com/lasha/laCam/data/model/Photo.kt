package com.lasha.laCam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey(autoGenerate = true)val id: Int,
    @ColumnInfo(name = "file_path")val filePath: String,
    @ColumnInfo(name = "file_name")val fileName: String
)
