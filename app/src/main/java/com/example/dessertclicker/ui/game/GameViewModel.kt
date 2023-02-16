package com.example.dessertclicker.ui.game

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.DataSource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    //only used on this class scope
    private val _uiState: MutableStateFlow<GameUiState> = MutableStateFlow(GameUiState())

    //only used outside this class scope
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    private var desserts: List<Dessert> = DataSource.dessertList

    fun updateRevenue(){
        val newRevenue = _uiState.value.revenue + _uiState.value.currentDessertPrice
        _uiState.update { currentState -> currentState.copy(
            revenue = newRevenue,
            dessertSold = currentState.dessertSold.inc()
        ) }
    }

    private fun updateCurrentDessert(index: Int){
        _uiState.update { currentDessert -> currentDessert.copy(
            currentDessertIndex = index,
            currentDessertImageId = desserts[index].imageId,
            currentDessertPrice = desserts[index].price,
        ) }
    }

    private fun gameOver(){
        _uiState.update { currentState -> currentState.copy(
            isGameOver = true
        ) }
    }

    fun determineDessertToShow() {
        var dessertToShow = desserts.first()

        if(_uiState.value.dessertSold >= desserts.map { it.startProductionAmout } .reduce { acc, startAmount -> acc + startAmount}) gameOver()

        for (dessert in desserts) {
            if(_uiState.value.dessertSold < dessert.startProductionAmout) break

            dessertToShow = dessert
        }

        updateCurrentDessert(index = desserts.indexOf(dessertToShow))
    }

    fun reset(){
        _uiState.update { currentState -> currentState.copy(
            revenue = 0,
            dessertSold = 0,
            currentDessertIndex = 0,
            currentDessert = DataSource.dessertList[0],
            currentDessertImageId = DataSource.dessertList[0].imageId,
            currentDessertPrice = DataSource.dessertList[0].price,
            isGameOver = false
        ) }
    }

    init {
        //reset all
        reset()
    }
}