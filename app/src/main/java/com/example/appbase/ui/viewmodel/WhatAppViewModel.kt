package com.example.appbase.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appbase.domain.usecase.EnviarWhatAppCotizacionUseCase
import com.example.appbase.domain.usecase.GetEstadoApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhatAppViewModel @Inject constructor(
    private val enviarWhatAppCotizacionUseCase: EnviarWhatAppCotizacionUseCase
): ViewModel() {

    fun enviarPedidoWhatApp(context: Context){
        viewModelScope.launch {
          //  val listPedidoCarroCompra = pedidoCarroCompraDbUseCase()
         //   val elCliente = getClienteDbUseCase()
            //Log.d("onCreate", "elCliente: "+ elCliente.toString())
            var mensaje =  ".:::: Nueva contización recibido ::::.\n"
            mensaje += "\n Cliente Rut: {elCliente.rut}\n Nombre: {elCliente.nombre}\n Direccion: {elCliente.direccion}"
            mensaje += "\n.:::::::::::::::::::::::::::::::."
            enviarWhatAppCotizacionUseCase(mensaje, context)
        }
    }

}