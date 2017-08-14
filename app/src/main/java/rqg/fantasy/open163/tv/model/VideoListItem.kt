package rqg.fantasy.open163.tv.model

import android.os.Parcel
import android.os.Parcelable

data class VideoListItem(
        val repovideourl: String? = null,
        //        val subList: List<Any?>? = null,
        val m3u8size: Int? = null,
        val imgpath: String? = null,
        val mlength: Int? = null,
        val mid: String? = null,
        val mp4sizeOrigin: Int? = null,
        val mp4size: Int? = null,
        val protoVersion: Int? = null,
        val m3u8sizeOrigin: Int? = null,
        val pnumber: Int? = null,
        val title: String? = null,
        val repovideourlmp4Origin: String? = null,
        val adv: String? = null,
        val repovideourlmp4: String? = null,
        val weburl: String? = null,
        val subtitle: String? = null,
        val commentid: String? = null,
        val advlink: String? = null,
        val repovideourlOrigin: String? = null,
        val subtitleLanguage: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(repovideourl)
        parcel.writeValue(m3u8size)
        parcel.writeString(imgpath)
        parcel.writeValue(mlength)
        parcel.writeString(mid)
        parcel.writeValue(mp4sizeOrigin)
        parcel.writeValue(mp4size)
        parcel.writeValue(protoVersion)
        parcel.writeValue(m3u8sizeOrigin)
        parcel.writeValue(pnumber)
        parcel.writeString(title)
        parcel.writeString(repovideourlmp4Origin)
        parcel.writeString(adv)
        parcel.writeString(repovideourlmp4)
        parcel.writeString(weburl)
        parcel.writeString(subtitle)
        parcel.writeString(commentid)
        parcel.writeString(advlink)
        parcel.writeString(repovideourlOrigin)
        parcel.writeString(subtitleLanguage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoListItem> {
        override fun createFromParcel(parcel: Parcel): VideoListItem {
            return VideoListItem(parcel)
        }

        override fun newArray(size: Int): Array<VideoListItem?> {
            return arrayOfNulls(size)
        }
    }
}
