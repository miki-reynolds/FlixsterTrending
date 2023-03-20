package com.kaisha.flixstertrending

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class Movie() : Parcelable {
    @SerializedName("poster_path")
    var poster: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("overview")
    var overview: String? = null

    constructor(parcel: Parcel) : this() {
        poster = parcel.readString()
        title = parcel.readString()
        overview = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(poster)
        parcel.writeString(title)
        parcel.writeString(overview)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

/*
package com.kaisha.flixstertrending

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import android.support.annotation.Keep
import kotlinx.serialization.Serializable


@Keep
@Serializable
class Movie {
    @JvmField
    @SerializedName("poster_path")
    @SerialName("poster_path")
    var poster: String ? = null

    @JvmField
    @SerializedName("title")
    @SerialName("title")
    var title: String ? = null

    @JvmField
    @SerializedName("overview")
    @SerialName("overview")
    var overview: String ? = null
}
 */