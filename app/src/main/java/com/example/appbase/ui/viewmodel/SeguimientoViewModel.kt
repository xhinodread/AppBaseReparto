package com.example.appbase.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appbase.data.EstadoEnvio
import com.example.appbase.ui.common.listaRand
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.util.Log
import com.example.appbase.domain.repository.SeguimientoRepository
import com.example.appbase.domain.usecase.GetEstadoApiUseCase
import com.example.appbase.domain.usecase.GetSeguimientoUseCase
import com.example.appbase.ui.common.listadoHistoria
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SeguimientoViewModel @Inject constructor(
    private val getEstadoApiUseCase: GetEstadoApiUseCase,
    private val getSeguimientoUseCase: GetSeguimientoUseCase,
): ViewModel() {

    private val _cargando = MutableLiveData<Boolean>(false)
    val cargando : LiveData<Boolean> =_cargando

    private val _estadoDespacho = MutableLiveData<List<EstadoEnvio>>()
    val estadoDespacho: LiveData<List<EstadoEnvio>> = _estadoDespacho

    private val _nroFolio = MutableLiveData<String>("")
    val nroFolio : LiveData<String> =_nroFolio

    fun limpiarLista(){
        _estadoDespacho.value = emptyList()
    }

    fun addNrofolio(nro_folio: String){
        Log.d("onClick", "ViewModel: " + nro_folio )

        if(!nro_folio.isNullOrBlank() || nro_folio != null ){
            _nroFolio.value = nro_folio
        }

    }

    fun getStadoDespacho(nro_folio:String){
        _cargando.value = true
         viewModelScope.launch {
             val resultStatusRed = getEstadoApiUseCase()
             if(resultStatusRed){
               //  val getRecorridoDespacho = seguimientoRepository.getEstadoCarga(nro_folio)
                 val getRecorridoDespacho = getSeguimientoUseCase(nro_folio)
                 val resp = getRecorridoDespacho.resp
                 //Log.d("onList", "api: \n" + getRecorridoDespacho.resp.recorrido )
                 if(resp?.toString().isNullOrBlank() == false ){
                     val elRecorrido = resp?.recorrido?.map { valor ->
                         EstadoEnvio(valor.id, valor.fecha, valor.mensaje, valor.estado)
                     }
                     _estadoDespacho.value = elRecorrido
                 }else{
                     _estadoDespacho.value = emptyList()
                 }
             }else{
                 _estadoDespacho.value = emptyList()
             }
            _cargando.value = false
         }
    }

    fun estadoDespachoFijo(){
        // val elSta = estadoHistoria()
        // Log.d("onList", "elSta"+'\n'+ elSta.toString())

        val listaEstadoDespacho = listOf(
            EstadoEnvio("0", "Ayer", "En Sucursal", "1"),
            EstadoEnvio("1", "Hoy", "En Ruta", "1"),
            EstadoEnvio("2","Recien", "En Destino", "0"),
            EstadoEnvio("3","Ahora", "En Reparto", "0"),
            EstadoEnvio("4","Encomienda", "Encomienda Entregada al cliente", "0"),
        )
        val swData = true
        _cargando.value = true
        viewModelScope.launch {
            delay(1500)
            //Log.d("onList", listaEstadoDespacho.subList(0, listaRand(listaEstadoDespacho.size)).toString())
            if(swData) {
                _estadoDespacho.value =
                    listaEstadoDespacho.subList(0, listaRand(listaEstadoDespacho.size))
            }else{
                //_estadoDespacho.value = listaEstadoDespacho
                _estadoDespacho.value = listadoHistoria()
            }
            _cargando.value = false
            //Log.d("onList","listaEstadoDespacho"+ '\n'+  _estadoDespacho.value )
        }
    }

}



/***************************************/

data class EstadoDespacho(
    val id: String = "",
    val fecha:String = "",
    val mensaje:String = "",
    val estado:String = ""
)
