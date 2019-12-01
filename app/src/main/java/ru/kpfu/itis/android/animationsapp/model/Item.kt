package ru.kpfu.itis.android.animationsapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val title: String,
    val imageId: Int
) : Parcelable
