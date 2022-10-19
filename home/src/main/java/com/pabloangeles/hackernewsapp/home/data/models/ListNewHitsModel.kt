package com.pabloangeles.hackernewsapp.home.data.models

import com.google.gson.annotations.SerializedName

data class ListNewHitsModel(
    @SerializedName("exhaustiveNbHits") val exhaustiveNbHits: Boolean,
    @SerializedName("exhaustiveTypo") val exhaustiveTypo: Boolean,
    @SerializedName("hits") val hits: java.util.ArrayList<Hit>,
    @SerializedName("hitsPerPage") val hitsPerPage: Int,
    @SerializedName("nbHits") val nbHits: Int,
    @SerializedName("nbPages") val nbPages: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("params") val params: String,
    @SerializedName("processingTimeMS") val processingTimeMS: Int,
    @SerializedName("query") val query: String
)

data class Hit(
    @SerializedName("author") val author: String,
    @SerializedName("comment_text") val commentText: String? = "",
    @SerializedName("created_at") val createdAt: String? = "",
    @SerializedName("created_at_i") val createdAtI: Int,
    @SerializedName("num_comments") val numComments: Int?,
    @SerializedName("objectID") val objectID: Long,
    @SerializedName("parent_id") val parentId: Int,
    @SerializedName("points") val points: Int?,
    @SerializedName("story_id") val storyId: Long?,
    @SerializedName("story_text") val storyText: String?,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("story_url") val storyUrl: String?,
    @SerializedName("_tags") val tags: List<String>,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?
)
