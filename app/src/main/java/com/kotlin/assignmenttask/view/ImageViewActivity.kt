package com.kotlin.assignmenttask.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.assignmenttask.R
import com.kotlin.assignmenttask.repo.RepoistoryClass
import com.kotlin.assignmenttask.utils.CustomProgressDialog
import com.kotlin.assignmenttask.view.adapter.ListAdapter
import com.kotlin.assignmenttask.viewModel.ImageDetailsViewModel
import com.kotlin.circleapisdemo.utils.Resource
import com.kotlin.circleapisdemo.utils.ViewModelFactory
import com.kotlin.finaltarget.data.image.Row
import kotlinx.android.synthetic.main.imageview_activity.*

class ImageViewActivity : AppCompatActivity() {

    lateinit var imageviewModel: ImageDetailsViewModel
    private lateinit var adapter: ListAdapter
    private var alertDialog: CustomProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imageview_activity)

        inits()


    }

    private fun inits() {

        /* initalization */
        alertDialog = CustomProgressDialog()
        alertDialog!!.createDialog(this)


        /* set ViewModel*/

        val repoistoryClass = RepoistoryClass()
        val factory = ViewModelFactory(application, repoistoryClass)
        imageviewModel = ViewModelProvider(this, factory).get(ImageDetailsViewModel::class.java)


        /* SetAdapter with list */
        callAdapter()

        /* calling  background thread  by launching coroutines */
        imageviewModel.thread()


        /* calling Live Data from ViewModel*/

        dataGettingFromViewModel()


        /* call swipe listener */
        swipeLister()

    }


    private fun swipeLister() {

        swipleListener_mSl.setOnRefreshListener {
            swipleListener_mSl.isRefreshing = true

            /* SetAdapter with list */
            callAdapter()

            /* calling  background thread  by launching coroutines */
            imageviewModel.thread()


            /* calling Live Data from ViewModel*/

            dataGettingFromViewModel()
        }
    }

    private fun dataGettingFromViewModel() {

        imageviewModel.liveData.observe(this, { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {

                    is Resource.Success -> {
                        response.data?.let { respons ->
                            retrieveList(respons.rows)
                            header_mtv.text = respons?.title
                            hideProgressBar()
                            swipleListener_mSl.isRefreshing = false
                        }

                    }

                    is Resource.Error -> {
                        hideProgressBar()
                        response.message?.let { message ->
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }

            }
        })
    }

    private fun callAdapter() {
        list_mRv.layoutManager = LinearLayoutManager(this)
        adapter = ListAdapter(arrayListOf())
        list_mRv.addItemDecoration(
            DividerItemDecoration(
                list_mRv.context,
                (list_mRv.layoutManager as LinearLayoutManager).orientation
            )
        )
        list_mRv.adapter = adapter

    }

    private fun retrieveList(users: List<Row>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }


    private fun hideProgressBar() {
        alertDialog?.dismissDialog()
    }

    private fun showProgressBar() {
        alertDialog?.showDialog()
    }
}