package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, MainViewModel.Factory(activity.application)).get(MainViewModel::class.java)

    }
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: AsteroidAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setUpObservers()
    }

    private fun setupUI() {
        adapter = AsteroidAdapter(AsteroidAdapter.OnClickListener {
            this.findNavController()
                .navigate(MainFragmentDirections.actionShowDetail(it))
            Log.d("Clicked", it.codename)
        })
        binding.asteroidRecycler.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.pictureOfDayURL.observe(viewLifecycleOwner) {
            Picasso.get().load(it).into(binding.activityMainImageOfTheDay)
        }
        viewModel.asteroids.observe(viewLifecycleOwner ) {
            Log.d("asteroids" , it.toString())
            adapter.submitList(it)
        }
        viewModel.todayAsteroids.observe(viewLifecycleOwner){
            adapter.submitList(it)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_rent_menu -> viewModel.getTodayAsteroids()
        }
        return super.onOptionsItemSelected(item)    }
}
