package rqg.fantasy.open163.tv.model

import android.os.Parcel
import android.os.Parcelable

data class MovieItem(
        val includeVirtual: String? = null,
        val description: String? = null,
        val source: String? = null,
        val title: String? = null,
        val type: String? = null,
        val midAdvSource: Int? = null,
        val ccUrl: String? = null,
        val ltime: Long? = null,
        val largeimgurl: String? = null,
        val school: String? = null,
        val playcount: Int? = null,
        val videoList: List<VideoListItem?>? = null,
        val updatedPlaycount: Int? = null,
        val ipadPlayAdvInfo: IpadPlayAdvInfo? = null,
        val plid: String? = null,
        val director: String? = null,
        val imgpath: String? = null,
        val ccPic: String? = null,
        val opening: String? = null,
        val tags: String? = null,
        val hits: Int? = null,
        val outurl: String? = null,
        val posAdvSource: Int? = null,
        val subtitle: String? = null,
        val preAdvSource: Int? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.createTypedArrayList(VideoListItem),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readParcelable(IpadPlayAdvInfo::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(includeVirtual)
        parcel.writeString(description)
        parcel.writeString(source)
        parcel.writeString(title)
        parcel.writeString(type)
        parcel.writeValue(midAdvSource)
        parcel.writeString(ccUrl)
        parcel.writeValue(ltime)
        parcel.writeString(largeimgurl)
        parcel.writeString(school)
        parcel.writeValue(playcount)
        parcel.writeTypedList(videoList)
        parcel.writeValue(updatedPlaycount)
        parcel.writeParcelable(ipadPlayAdvInfo, flags)
        parcel.writeString(plid)
        parcel.writeString(director)
        parcel.writeString(imgpath)
        parcel.writeString(ccPic)
        parcel.writeString(opening)
        parcel.writeString(tags)
        parcel.writeValue(hits)
        parcel.writeString(outurl)
        parcel.writeValue(posAdvSource)
        parcel.writeString(subtitle)
        parcel.writeValue(preAdvSource)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieItem> {
        override fun createFromParcel(parcel: Parcel): MovieItem {
            return MovieItem(parcel)
        }

        override fun newArray(size: Int): Array<MovieItem?> {
            return arrayOfNulls(size)
        }
    }
}
