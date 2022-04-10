package com.lasha.laCam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "photos")
data class Photo(
    @ColumnInfo(name = "file_path")val filePath: String,
    @ColumnInfo(name = "file_name")val fileName: String
)
