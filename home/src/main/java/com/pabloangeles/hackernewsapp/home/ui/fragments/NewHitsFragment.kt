package com.pabloangeles.hackernewsapp.home.ui.fragments

import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pabloangeles.hackernewsapp.core.base.BaseFragment
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsEntity
import com.pabloangeles.hackernewsapp.home.R
import com.pabloangeles.hackernewsapp.home.databinding.FragmentNewsHitsBinding
import com.pabloangeles.hackernewsapp.home.ui.adapters.ItemDragDecorator
import com.pabloangeles.hackernewsapp.home.ui.adapters.NewsHitsAdapter
import com.pabloangeles.hackernewsapp.home.viewmodel.NewHitsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewHitsFragment(
    override val bindingInflater: (LayoutInflater) -> FragmentNewsHitsBinding =
        FragmentNewsHitsBinding::inflate
) : BaseFragment<FragmentNewsHitsBinding>() {

  /* A Hilt dependency injection. */
  private val viewModel by viewModels<NewHitsViewModel>()
  /* Creating a new instance of the NewsHitsAdapter class. */
  private var adapter = NewsHitsAdapter()
  /* A variable that is used to keep track of the page number. */
  private var noPage = 1
  /* Used to keep track of the loader. */
  private var isLoader = false

  private var LIMIT = 10

  /** `restartItems()` is called when the game is restarted */
  override fun init() {
    restartItems()
  }
  /** It sets up an observer for the viewModel.fetchData() function. */
  override fun setupObserver() {
    viewModel.fetchData().observe(this, ::onObserverViewModel)
  }
  /** It sets up the recycler view and adds a swipe to delete feature. */
  override fun setupView() {
    binding.recyclerView.adapter = adapter

    ItemDragDecorator(requireContext(), binding.recyclerView)
        .apply { onSwipedLeft = ::onItemDelete }
        .setup()
    adapter.onClickItem = ::onClickItem

    binding.refreshLayout.setOnRefreshListener { restartItems() }

    binding.recyclerView.addOnScrollListener(
        object : RecyclerView.OnScrollListener() {
          override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) &&
                newState == RecyclerView.SCROLL_STATE_IDLE) {
              if (!isLoader) {
                noPage++
                viewModel.getNewsHits(noPage)
              }
            }
          }
        })
  }

  /**
   * It navigates to the WebViewFragment.
   *
   * @param item NewsHitsEntity - The item that was clicked.
   * @param view ViewBinding - The view binding object for the item.
   * @return A list of NewsHitsEntity
   */
  private fun onClickItem(item: NewsHitsEntity, view: ViewBinding) {

    if (item.storyUrl.isEmpty()) {
      showError(
          getString(R.string.title_error),
          getString(R.string.error_news_hits),
          getString(R.string.close))
      return
    }
    Navigation.findNavController(binding.root)
        .navigate(R.id.goWebViewFragment, bundleOf(Pair(WebViewFragment.URL, item.storyUrl)))
  }

  /**
   * It shows a warning dialog and if the user clicks yes, it deletes the item from the list.
   *
   * @param position Int - The position of the item in the list
   */
  private fun onItemDelete(position: Int) {

    showWarning(
        getString(R.string.hello),
        getString(R.string.would_like_deleted),
        getString(R.string.yes),
        getString(R.string.No),
        {
          viewModel.deleteNewHit(adapter.items[position])
          adapter.removeElement(adapter.items[position])
        },
        {})
  }

  /**
   * A function that is called when the data is successfully fetched from the server.
   *
   * @param data Any - The data returned from the API call.
   */
  override fun onSuccess(data: Any) {
    when (data) {
      is List<*> -> {
        val items = data as List<NewsHitsEntity>
        if (items.isEmpty()) {
          binding.notFound.visibility = View.VISIBLE
          binding.recyclerView.visibility = View.GONE

          return
        } else {
          binding.recyclerView.visibility = View.VISIBLE
          binding.notFound.visibility = View.GONE
        }
        items.forEach { adapter.insertNewElement(it) }
        binding.recyclerView.scrollToPosition(adapter.itemCount - LIMIT)
      }
      is NewsHitsEntity -> {}
    }
    isLoader = false
  }

  /**
   * It shows an error message to the user.
   *
   * @param message The message to be displayed in the dialog.
   */
  override fun onFails(message: String) {
    showError(
        getString(R.string.title_error),
        getString(R.string.error_news_hits),
        getString(R.string.close))
  }
  /**
   * It shows a loader when the data is true and hides it when the data is false.
   *
   * @param data Boolean - This is the data that is passed to the loader.
   */
  override fun onLoader(data: Boolean) {
    if (data) showLoader() else hideLoader()
  }

  /** It clears the adapter and refreshes the data. */
  private fun restartItems() {
    noPage = 1
    adapter.clearItems()
    viewModel.getNewsHits(noPage)
    binding.refreshLayout.isRefreshing = false
  }
}
