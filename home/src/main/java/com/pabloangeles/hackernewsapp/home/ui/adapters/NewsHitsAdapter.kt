package com.pabloangeles.hackernewsapp.home.ui.adapters

import android.view.ViewGroup
import com.pabloangeles.hackernewsapp.core.base.BaseAdapter
import com.pabloangeles.hackernewsapp.core.base.BaseViewHolder
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsEntity
import com.pabloangeles.hackernewsapp.home.databinding.NewsHitsItemBinding

class NewsHitsAdapter :
    BaseAdapter<NewsHitsEntity, BaseViewHolder<NewsHitsItemBinding, NewsHitsEntity>>() {
  override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
  ): BaseViewHolder<NewsHitsItemBinding, NewsHitsEntity> {
    return NewHitsViewHolder(parent)
  }

  /**
   * It binds the data to the view.
   *
   * @param holder BaseViewHolder<NewsHitsItemBinding, NewsHitsEntity>
   * @param position The position of the item within the adapter's data set.
   */
  override fun onBindViewHolder(
      holder: BaseViewHolder<NewsHitsItemBinding, NewsHitsEntity>,
      position: Int
  ) {
    holder.setup(items[position])
    startDragging(holder)
    holder.onClickItem = onClickItem
  }
}
