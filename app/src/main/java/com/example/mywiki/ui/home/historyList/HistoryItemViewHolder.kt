package com.example.mywiki.ui.home.historyList

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.mywiki.R
import com.example.mywiki.data.local.entity.SavedItemEntity
import com.example.mywiki.di.component.ViewHolderComponent
import com.example.mywiki.ui.base.BaseItemViewHolder
import com.example.mywiki.ui.webView.WebViewActivity

class HistoryItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<SavedItemEntity, HistoryItemViewModel>(R.layout.saved_item, parent) {

    private var pageIdForWebView: String? = null

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {
        itemView.setOnClickListener {
            viewModel.onLaunchWebView()

        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.onLaunchWebViewActivity.observe(this, Observer {
            it.getIfNotHandled()?.run {

                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        WebViewActivity::class.java
                    ).putExtra("pageId", pageIdForWebView)
                )
            }
        })

        viewModel.description.observe(this, Observer {
            it?.run {
                itemView.findViewById<TextView>(R.id.tv_history_desc).text=it
            }
        })

        viewModel.pageId.observe(this, Observer {
            it?.run {
                pageIdForWebView = it
            }
        })

        viewModel.title.observe(this, Observer {
            it?.run {
                itemView.findViewById<TextView>(R.id.tv_history_title).text=it
            }
        })
    }

}