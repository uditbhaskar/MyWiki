package com.example.mywiki.ui.webView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import com.example.mywiki.databinding.ActivityWebviewBinding
import com.example.mywiki.di.component.ActivityComponent
import com.example.mywiki.ui.base.BaseActivity
import javax.inject.Inject


class WebViewActivity : BaseActivity<WebViewModel>() {

    companion object {
        const val TAG = "WebViewActivity"
    }

    @Inject
    lateinit var binding: ActivityWebviewBinding

    override fun provideLayoutView(): View = binding.root

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupView(savedInstanceState: Bundle?) {

        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url)
                    return true
                }

                override fun onPageCommitVisible(view: WebView?, url: String?) {
                    super.onPageCommitVisible(view, url)
                    binding.progressBar.visibility = View.GONE
                }

            }
            binding.webView.loadUrl("https://en.wikipedia.org/?curid=${intent.getStringExtra("pageId")}")
        }

        binding.ivBack.setOnClickListener{
            viewModel.onBackPressed()
        }

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchHome.observe(this, Observer {
            it.getIfNotHandled()?.run {
                finish()
            }
        })
    }


}