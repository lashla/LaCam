package com.lasha.laCam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File

@Entity(tableName = "photos")
data class Photo(
    @ColumnInfo(name = "file_path")val filePath: String,
    @PrimaryKey @ColumnInfo(name = "file_name")val fileName: String
)
