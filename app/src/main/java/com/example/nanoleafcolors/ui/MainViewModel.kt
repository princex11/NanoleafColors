package com.example.nanoleafcolors.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nanoleafcolors.R

class MainViewModel : ViewModel() {

    //live data string of colors clicked/selected
    private val _selectedColors = MutableLiveData<String>()
    val selectedColors: LiveData<String>
        get() = _selectedColors

    init {
        _selectedColors.value = ""
    }

    //list of selected colors
    private val selectedColorList = mutableListOf<String>()

    //map of colorName, number of time clicked, color value
    private val colorsWithCounts = mutableMapOf<String, Pair<Int, Int>>()

    //converting map to list, in order to display the values in recyclerview
    private lateinit var listOfColorNCount: List<Pair<String, Pair<Int, Int>>>

    fun onClickColor(color: String) {
        selectedColorList.add(color)
        updateSelectedColors(selectedColorList)
    }

    //clearing everything
    fun onClear() {
        selectedColorList.clear()
        updateSelectedColors(selectedColorList)
        colorsWithCounts.clear()
    }

    //updating the selected color string
    private fun updateSelectedColors(selectedColorList: MutableList<String>) {
        _selectedColors.value = selectedColorList.joinToString(",")
    }

    /*calculating colors selected and number of counts of each color,
    mapping the color name with color count and color value
     */
    fun selectedColorsWithCounts(): List<Pair<String, Pair<Int, Int>>> {
        var redCount = 0
        var yellowCount = 0
        var orangeCount = 0
        var greenCount = 0
        var blueCount = 0
        var purpleCount = 0
        colorsWithCounts.clear()
        selectedColors.value?.split(",")?.iterator()?.forEach { color ->
            when (color) {
                "Red" -> colorsWithCounts[color] = Pair(++redCount, R.color.red)
                "Yellow" -> colorsWithCounts[color] = Pair(++yellowCount, R.color.yellow)
                "Orange" -> colorsWithCounts[color] = Pair(++orangeCount, R.color.orange)
                "Green" -> colorsWithCounts[color] = Pair(++greenCount, R.color.green)
                "Blue" -> colorsWithCounts[color] = Pair(++blueCount, R.color.blue)
                "Purple" -> colorsWithCounts[color] = Pair(++purpleCount, R.color.purple)
            }
        }
        listOfColorNCount = colorsWithCounts.toList()
        return listOfColorNCount
    }
}