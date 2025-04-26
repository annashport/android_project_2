package com.example.myyyyapplication.presentation.fragments

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myyyyapplication.R
import com.example.myyyyapplication.presentation.viewmodel.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment(), OnMapReadyCallback {

    private val vm: MapViewModel by viewModel()

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var searchView: SearchView

    private var markers = mutableListOf<Pair<String, Marker>>() // Назва гуртка і його маркер

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view.findViewById(R.id.mapView)
        searchView = view.findViewById(R.id.searchView)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        setupSearchView()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val vinnytsia = LatLng(49.2331, 28.4682) // Вінниця
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vinnytsia, 12f))

        subscribeToWorkshopsAndAddMarkers()
    }

    private fun getBitmapDescriptor(resourceId: Int): BitmapDescriptor {
        val drawable = ContextCompat.getDrawable(requireContext(), resourceId)
            ?: return BitmapDescriptorFactory.defaultMarker()
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun subscribeToWorkshopsAndAddMarkers() {
        vm.workshopsLiveData.observe(viewLifecycleOwner) { workshops ->
            val icon = getBitmapDescriptor(R.drawable.custom_marker3_foreground)

            markers.clear()
            mMap.clear()

            workshops.forEach { workshop ->
                val location = LatLng(workshop.latitude, workshop.longitude)
                val marker = mMap.addMarker(
                    MarkerOptions()
                        .position(location)
                        .title(workshop.name)
                        .snippet(workshop.address)
                        .icon(icon)
                )
                if (marker != null) {
                    markers.add(workshop.name to marker)
                }
            }
        }
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchMarkers(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Можна також тут додати live-пошук, якщо треба
                return false
            }
        })
    }

    private fun searchMarkers(query: String) {
        val marker = markers.firstOrNull { it.first.contains(query, ignoreCase = true) }?.second
        if (marker != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 15f))
            marker.showInfoWindow()
        } else {
            Toast.makeText(requireContext(), "Гурток не знайдено", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
//change 1


