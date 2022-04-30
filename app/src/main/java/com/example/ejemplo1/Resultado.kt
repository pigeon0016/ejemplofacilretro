package com.example.ejemplo1

import com.google.gson.annotations.SerializedName

// tiene anidados por lo cual se crea otro data class para ver lo q hay adentrp
data class Resultado(
    @SerializedName("title") var title: String,
    @SerializedName("url") var url: String,
    @SerializedName("published_date") var published_date: String,
    @SerializedName("media") var  media: List<Media>
)


data class Media(
    @SerializedName("media-metadata") var metadata: List<MediaData>

)

data class MediaData(
    @SerializedName("url") var urlmetadata: String
)