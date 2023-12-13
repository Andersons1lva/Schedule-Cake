package com.anderson.schedulecake.model

import java.util.Date
import java.util.UUID

class Encomenda(val id: UUID, val numero_da_encomenda:String,
                val cliente_id:UUID,val bolo:String,val recheio:String,
                val data:Date,val valor:Double,val sinal:Double) {
}