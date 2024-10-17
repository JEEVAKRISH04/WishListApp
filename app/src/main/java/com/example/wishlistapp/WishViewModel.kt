package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
):ViewModel() {

    var wishnameState by mutableStateOf("")
    var wishpositionState by mutableStateOf("")
    var wishcontactNumberState by mutableStateOf("")
    var wishgendersState by mutableStateOf("Male")
    var wishDepartmentsState by mutableStateOf("")


    fun onWishnameChanged(newString:String){
        wishnameState = newString
    }

    fun onWishpositionChanged(newString: String){
        wishpositionState = newString
    }

    fun onWishcontactNumberChanged(newString: String){
        wishcontactNumberState = newString
    }

    fun onWishgendersChanged(gender: String){
        wishgendersState = gender
    }

    fun onWishDepartmentsChanged(newString: String){
        wishDepartmentsState = newString
    }



    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getWishes()
        }
    }

    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addAWish(wish= wish)
        }
    }

    fun getAWishById(id:Long):Flow<Wish> {
        return wishRepository.getAWishById(id)
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateAWish(wish= wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteAWish(wish= wish)
            getAllWishes = wishRepository.getWishes()
        }
    }
}
