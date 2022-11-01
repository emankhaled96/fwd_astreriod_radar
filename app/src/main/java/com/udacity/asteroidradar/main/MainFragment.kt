package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
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
            Log.d("Clicked", it.codename)
        })
        binding.asteroidRecycler.adapter = adapter
        val asteroid1 = Asteroid(1, "test 1", "1/11/2022", 22.5, 15.5, 20.5, 10000.5, true)
        val asteroid2 = Asteroid(2, "test 2", "1/11/2022", 22.5, 15.5, 20.5, 10000.5, false)
        val list = listOf(asteroid1, asteroid2, asteroid2, asteroid1, asteroid2)
        adapter.submitList(list)
    }

    private fun setUpObservers() {
        viewModel.pictureOfDayURL.observe(viewLifecycleOwner) {
            Picasso.get().load(it).into(binding.activityMainImageOfTheDay)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
