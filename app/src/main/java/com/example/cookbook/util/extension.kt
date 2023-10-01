package com.example.cookbook.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

//aynı baslık altında gecisYap fonksiyonunu oluşturduk, buradaki amaç ihtiyacımız doğrultusunda gerekli fonksiyonu kullanmak.
//kullanıcıyı farklı ekranlara yönlendirmek için kullanıyoruz

//fab butonuna tıklandıgında gecis yapmak için
fun Navigation.gecisYap(id:Int, it: View) {
    findNavController(it).navigate(id)
}

//recyclerviewdeki itemlara tıklandıgında gecis yapmak için
fun Navigation.gecisYap(it: View, id: NavDirections){
    findNavController(it).navigate(id)
}