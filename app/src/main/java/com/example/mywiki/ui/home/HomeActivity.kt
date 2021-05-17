package com.example.mywiki.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywiki.databinding.ActivityHomeBinding
import com.example.mywiki.di.RecycleViewHorizontal
import com.example.mywiki.di.component.ActivityComponent
import com.example.mywiki.ui.base.BaseActivity
import com.example.mywiki.ui.home.historyList.HistoryAdapter
import com.example.mywiki.ui.home.searchList.SearchAdapter
import com.example.mywiki.utils.common.DismissKeyboard
import javax.inject.Inject
import javax.inject.Named

class HomeActivity : BaseActivity<HomeViewModel>() {

    @Inject
    lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var searchSuggestionAdapter: ArrayAdapter<String>


    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    @Named("Horizontal_layout")
    lateinit var linearLayoutManagerHorizontal: LinearLayoutManager

    @Inject
    lateinit var searchListAdapter: SearchAdapter

    @Inject
    lateinit var historyAdapter: HistoryAdapter


    companion object {
        const val TAG = "HomeActivity"
    }


    override fun provideLayoutView(): View = binding.root


    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {


        binding.searchBar.setAdapter(searchSuggestionAdapter)

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onSearchingSuggestion(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.etSearch.setOnClickListener {

            viewModel.onSearching(binding.searchBar.text.toString())

            DismissKeyboard.hideSoftKeyBoard(
                this,
                window.decorView.findViewById(android.R.id.content)
            )
        }

        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
            adapter = historyAdapter
        }


        binding.rvSearchResult.apply {
            layoutManager = linearLayoutManager
            adapter = searchListAdapter
        }


    }

    override fun onPause() {
        super.onPause()
        viewModel.onFetchingHistoryData()
    }

    override fun setupObservers() {
        super.setupObservers()



        viewModel.searchFieldSuggestion.observe(this, Observer {
            searchSuggestionAdapter.clear()
            searchSuggestionAdapter.addAll(it)

        })

        viewModel.searchQueryData.observe(this, Observer {
            it.data.run {
                if (this.isNullOrEmpty()) {
                    binding.rvSearchResult.visibility = View.GONE
                } else {
                    binding.rvSearchResult.visibility = View.VISIBLE

                }
                searchListAdapter.appendData(this)
                searchListAdapter.notifyDataSetChanged()
            }

        })

        viewModel.savedHistoryData.observe(this, Observer {
            it.data?.run {
                historyAdapter.appendData(this)
            }
        })



    }
}