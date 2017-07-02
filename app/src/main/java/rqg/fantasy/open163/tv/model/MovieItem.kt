package rqg.fantasy.open163.tv.model

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
)
