package rqg.fantasy.open163.tv.model

import android.os.Parcel
import android.os.Parcelable

data class IpadPlayAdvInfo(
        val advPreId: String? = null,
        val advPosId: String? = null,
        val advSource: Int? = null,
        val advMidId: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(advPreId)
        parcel.writeString(advPosId)
        parcel.writeValue(advSource)
        parcel.writeString(advMidId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IpadPlayAdvInfo> {
        override fun createFromParcel(parcel: Parcel): IpadPlayAdvInfo {
            return IpadPlayAdvInfo(parcel)
        }

        override fun newArray(size: Int): Array<IpadPlayAdvInfo?> {
            return arrayOfNulls(size)
        }
    }
}
