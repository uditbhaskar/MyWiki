package com.example.mywiki.ui.home.searchList

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.mywiki.R
import com.example.mywiki.data.model.PagesData
import com.example.mywiki.di.component.ViewHolderComponent
import com.example.mywiki.ui.base.BaseItemViewHolder
import com.example.mywiki.ui.webView.WebViewActivity

class SearchItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<PagesData, SearchItemViewModel>(R.layout.search_item_view, parent) {

    private var pageIdForWebView: String? = null
    private var title: String? = null
    private var description: String? = null

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {
        itemView.setOnClickListener {
            viewModel.saveItemInDatabase(title,description,pageIdForWebView)
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


        viewModel.pageId.observe(this, Observer {
            it?.run {
                pageIdForWebView = it.toString()
            }

        })

        viewModel.description.observe(this, Observer {
            it?.run {
                itemView.findViewById<TextView>(R.id.tv_description).text = it
                description =it
            }
        })

        viewModel.imageUrl.observe(this, Observer {
            it?.run {
                Glide.with(itemView.context).load(it)
                    .into(itemView.findViewById(R.id.iv_item_image))
            }
        })

        viewModel.title.observe(this, Observer {
            it?.run {
                itemView.findViewById<TextView>(R.id.tv_title).text = it
                title = it
            }
        })

    }
}