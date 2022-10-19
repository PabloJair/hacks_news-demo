package com.pabloangeles.hackernewsapp.core.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news_hits_entity")
data class NewsHitsEntity(
    @PrimaryKey(autoGenerate = false)
    val objectID: Long,
    @ColumnInfo(name = "storyId")
    val storyId: Long?,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "comment_text")
    val commentText: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String?,
    @ColumnInfo(name = "num_comments")
    val numComments: Int,
    @ColumnInfo(name = "points")
    val points: Int?,
    @ColumnInfo(name = "story_text")
    val storyText: String?,
    @ColumnInfo(name = "story_title")
    val storyTitle: String="",
    @ColumnInfo(name = "story_url")
    val storyUrl: String,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "status")
    var status: NewsHitsStatus
)
