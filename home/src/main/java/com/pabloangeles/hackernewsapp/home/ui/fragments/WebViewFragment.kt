package com.pabloangeles.hackernewsapp.home.ui.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.pabloangeles.hackernewsapp.core.base.BaseFragment
import com.pabloangeles.hackernewsapp.home.databinding.FragmentWebviewBinding

class WebViewFragment(
    override val bindingInflater: (LayoutInflater) -> FragmentWebviewBinding =
        FragmentWebviewBinding::inflate
) : BaseFragment<FragmentWebviewBinding>() {

  /* A variable that is initialized later. */
  lateinit var url: String

  /**
   * It loads a webview with the url passed as a parameter.
   */
  @SuppressLint("SetJavaScriptEnabled")
  override fun setupView() {

    url = arguments?.getString(URL, "") ?: ""

    binding.webview.settings.javaScriptEnabled = true
    showLoader()
    binding.webview.webViewClient =
        object : WebViewClient() {
          override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
          }
          override fun onLoadResource(view: WebView, url: String) = Unit
          override fun onPageFinished(view: WebView, url: String) {
            hideLoader()
          }
          override fun onReceivedError(
              view: WebView?,
              request: WebResourceRequest?,
              error: WebResourceError?
          ) {
            super.onReceivedError(view, request, error)
            binding.webview.visibility = View.GONE
            showError(
                "Error de conexión", "Conectate a una red para cargar esta sección", "Regresar") {
                  requireActivity().onBackPressed()
                }
          }
        }
    binding.webview.loadUrl(url)
  }

  companion object {
    /* A constant value that is used to pass the url to the fragment. */
    const val URL = "URL"
  }
}
