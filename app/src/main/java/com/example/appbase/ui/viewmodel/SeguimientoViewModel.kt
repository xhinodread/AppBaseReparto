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
import com.example.appbase.ui.common.listadoHistoria
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SeguimientoViewModel @Inject constructor(
    private val seguimientoRepository : SeguimientoRepository,
    private val getEstadoApiUseCase: GetEstadoApiUseCase,
): ViewModel() {

    private val _cargando = MutableLiveData<Boolean>(false)
    val cargando : LiveData<Boolean> =_cargando

    private val _estadoDespacho = MutableLiveData<List<EstadoEnvio>>()
    val estadoDespacho: LiveData<List<EstadoEnvio>> = _estadoDespacho

    fun limpiarLista(){
        _estadoDespacho.value = emptyList()
    }

    fun getStadoDespacho(nro_folio:String){
        _cargando.value = true
         viewModelScope.launch {
             val resultStatusRed = getEstadoApiUseCase()
             if(resultStatusRed){
                 val getRecorridoDespacho = seguimientoRepository.getEstadoCarga(nro_folio)
                 val resp = getRecorridoDespacho?.resp
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
/***
fun listadoHistoria_(): List<EstadoEnvio> {
    //val fechas = listOf("Ayer", "Hoy", "Recien", "Ahora", "Encomienda")
    val fechas = listOf("22/05/2023", "23/05/2023", "24/05/2023", "24/05/2023", "24/05/2023")
    val etiquetas = listOf("En Sucursal", "En Ruta", "En Destino", "En Reparto", "Encomienda Entregada al cliente")
    var elEstado = "1"
    var sw = 0
    val miLista = fechas.mapIndexed { ind, valor ->
        elEstado = estadoHistoria(elEstado.toInt(), sw)
        sw = 1
        //EstadoEnvio(ind.toString(), valor, etiquetas[ind]+'\n' +elEstado, elEstado)
        EstadoEnvio(ind.toString(), valor, etiquetas[ind], elEstado)
    }
    return miLista
}

fun estadoHistoria_(valorInicial:Int, sw:Int): String{
    //Log.d("onList","valorInicial"+ '\n'+  valorInicial )
    var resutladoEstado = valorInicial.toString()
    if(sw == 1) {
        if (valorInicial == 1) resutladoEstado = listaRand(1).toString()
    }
    return resutladoEstado
}
****/
data class EstadoDespacho(
    val id: String = "",
    val fecha:String = "",
    val mensaje:String = "",
    val estado:String = ""
)
