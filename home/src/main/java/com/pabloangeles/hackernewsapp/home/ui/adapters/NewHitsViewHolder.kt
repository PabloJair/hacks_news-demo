package com.pabloangeles.hackernewsapp.home.ui.adapters

import android.view.ViewGroup
import com.pabloangeles.hackernewsapp.core.base.BaseViewHolder
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsEntity
import com.pabloangeles.hackernewsapp.home.databinding.NewsHitsItemBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NewHitsViewHolder(viewGroup: ViewGroup) :
    BaseViewHolder<NewsHitsItemBinding, NewsHitsEntity>(
        NewsHitsItemBinding.inflate(
            android.view.LayoutInflater.from(viewGroup.context), viewGroup, false)) {
  /**
   * It sets up the view with the data.
   *
   * @param item NewsHitsEntity - The item that is being bound to the view.
   */
  override fun setup(item: NewsHitsEntity) {
    binding.tvAuthor.text = item.author
    val localDate =
        LocalDate.parse(item.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))

    binding.tvCreatedAt.text = localDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
    binding.tvTitle.text = if (item.storyTitle.isNullOrEmpty()) item.title else item.storyTitle

    binding.root.setOnClickListener { onClickItem?.invoke(item, binding) }
  }
}
