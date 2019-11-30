package mx.edu.ittepic.tpdm_u4_u5_practica2_luisquintanilla

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

class OyenteShake (p:MainActivity): SensorEventListener {
    var puntero = p
    var UltimaActualizacion: Long = 0

    var x = 0f
    var y = 0f
    var z = 0f
    var last_x = 0f
    var last_y = 0f
    var last_z = 0f

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
    override fun onSensorChanged(event: SensorEvent?) {
        var TiempoCorriendo: Long = System.currentTimeMillis()

        //solo permite una actualización cada 100 ms
        if ((TiempoCorriendo - UltimaActualizacion) > 100) {
            var difTiempo = (TiempoCorriendo - UltimaActualizacion)
            UltimaActualizacion = TiempoCorriendo

            x = event!!.values[0]
            y = event!!.values[1]
            z = event!!.values[2]

            var speed: Float = Math.abs(x + y + z - last_x - last_y - last_z) / difTiempo * 10000;

            if (speed > 800) {
                puntero.contadorShake++
                if (puntero.contadorShake == 4) puntero.contadorShake = 0
                //Si el contador llega a 4, reinicia su valor a 0
            }
            last_x = x
            last_y = y
            last_z = z
        }
    }
}