package com.example.cleanarchitecture.presentation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecture.App
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import com.example.cleanarchitecture.di.adapters.RvAdapter
import com.example.cleanarchitecture.utils.UserResource
import com.example.cleanarchitecture.viewModel.UserViewModel
import com.example.domain.mymodule1.Data
import com.example.domain.mymodule1.MyData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var rvAdapter: RvAdapter
    lateinit var list: MyData
     var myData: MyData?=null

    private val userViewModel: UserViewModel by viewModels { factory }
    private val TAG = "MainActivity12321"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        list=ArrayList()


        lifecycleScope.launch {
            userViewModel.getUsers().collect {
                when (it) {
                    is UserResource.Error -> {
                        Log.d(TAG, "Error: ${it.message}")


                    }
                    is UserResource.Success -> {
//                        list= it.list as ArrayList<UserData2>

                        myData=it.list
                        Log.d(TAG, "onCreate: ${it.list}")

                        rvAdapter= RvAdapter(myData!!.data,object :RvAdapter.OnClicListener{
                            override fun onItemClic(modelList: List<Data>, position: Int) {
                                val uri =
                                    Uri.parse("https://guidebook.com/")
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                try {
                                    startActivity(intent)
                                } catch (e: Exception) {
                                }
                            }


                        })
                        binding.rv.adapter=rvAdapter
                    }
                    is UserResource.Loading -> {

                    }
                }
            }
        }

    }
}