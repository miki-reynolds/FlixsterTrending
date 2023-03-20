package com.kaisha.flixstertrending

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class Show() : Parcelable {
    @SerializedName("poster_path")
    var poster: String? = null

    @SerializedName("name")
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

    companion object CREATOR : Parcelable.Creator<Show> {
        override fun createFromParcel(parcel: Parcel): Show {
            return Show(parcel)
        }

        override fun newArray(size: Int): Array<Show?> {
            return arrayOfNulls(size)
        }
    }
}


/*
-------------------------------------ABOUT PARCELABLE-------------------------------------------
he Show class implements the Parcelable interface.
This interface allows an object to be converted into a sequence of bytes that can be easily transmitted
and reconstructed back into an object. By making the Show class Parcelable, it can be included as an extra in an Intent.

In the constructor of the Show class, the parcel parameter is used to create a new Show object
from a Parcel object. The Parcel object contains the bytes that represent the Show object.
The writeToParcel function is used to write the object's state to the Parcel,
while the createFromParcel function is used to create a new Show object from the Parcel object.

In the ShowViewHolder inner class of the NowTrendingShowRecyclerViewAdapter, when a user clicks on
a particular item, an intent is created and the selected Show object is added as an extra using the putExtra method.
This Show object is then passed to the ShowDetailActivity where it can be used to display the details of the selected show.


WHY NOT PASSING OBJECT DIRECTLY?
When you pass an object directly between activities using an Intent, Android has to serialize the
object into a stream of bytes to send it across process boundaries. This process is called Marshalling.
And when the object arrives in the new Activity, Android has to unmarshall it, that is,
deserialize the stream of bytes back into the original object.

Parcelable is a lightweight alternative to Java Serialization and is used to optimize this marshalling process.
Parcelable provides a mechanism to serialize the object to a small byte stream that can be reconstructed
back to the original object with minimal overhead. So, when you implement Parcelable, you can pass
the object between activities as a Parcelable extra in the Intent.
This is much faster and more efficient than passing the object directly as the object
is only serialized and deserialized once instead of every time it's passed between activities.


This code block defines a secondary constructor for the Show class that takes a Parcel object as its parameter. This constructor is used to create a Show object from a Parcel object, which is used for passing data between activities in Android.

The Parcel object contains a serialized representation of the Show object's data. In this constructor, we read the data from the Parcel object and set the corresponding properties of the Show object.

    constructor(parcel: Parcel) : this() {
        poster = parcel.readString()
        title = parcel.readString()
        overview = parcel.readString()
    }
    The this() keyword is used to call the primary constructor with default arguments if any.
    In this case, the primary constructor does not have any parameters, so the this() call simply initializes the Show object with default values.
poster, title, and overview are the properties of the Show class that are being set in this constructor.
readString() is a method provided by the Parcel class that reads a String from the Parcel object.
By calling parcel.readString() three times, we read the values of poster, title, and overview from
the Parcel object and set them to the corresponding properties of the Show object.

override fun describeContents(): Int {...}:
This is a method that returns a bitmask indicating the set of special object types that the Parcelable class contains. It is not used in this particular implementation, so it simply returns 0.

companion object CREATOR : Parcelable.Creator<Show> {...}:
This is a nested object of the Show class that implements the Parcelable.Creator interface, which is required to create instances of Show from a Parcel.

createFromParcel(parcel: Parcel): Show is a function that reads a Parcel and returns a new instance of the Show class. This function is used to create Show objects from Parcels received from other parts of the app.

newArray(size: Int): Array<Show?> is a function that creates a new array of the Show type with the specified size. It is used to create arrays of Show objects when a variable-length array of the Show type is needed.

The Parcelable.Creator interface has two functions that need to be implemented:

createFromParcel(parcel: Parcel): T: This function should return a new instance of the Parcelable class,
instantiating it from the given Parcel whose data had previously been written by writeToParcel().
newArray(size: Int): Array<T?>: This function should return an array of the Parcelable class,
with every entry initialized to null, with the given size.
In summary, the describeContents function and the CREATOR object allow the Show class to be passed
 between activities using Parcelable, making it possible to serialize and deserialize instances of
 Show across different components of the app.

 */
