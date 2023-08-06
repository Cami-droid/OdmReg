package com.example.odmreg

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var etFecha: EditText
    private lateinit var etHora: EditText
    private lateinit var etOdometro: EditText
    private lateinit var etCantidadGNC: EditText
    private lateinit var etCantidadNafta: EditText

    // Agregar más variables para Fecha y Hora, Tipo de Combustible, Cantidad, Importe

    private lateinit var btnGuardar: Button
    private lateinit var btnExportar: Button

    private val registros: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        etFecha = findViewById(R.id.etFecha)
        etHora = findViewById(R.id.etHora)
        etOdometro = findViewById(R.id.etOdometro)
        etCantidadGNC = findViewById(R.id.etCantidadGNC)
        etCantidadNafta = findViewById(R.id.etCantidadNafta)

        btnGuardar = findViewById(R.id.btnGuardar)
        btnExportar = findViewById(R.id.btnExportar)

        btnGuardar.setOnClickListener {
            guardarCarga()
        }

        btnExportar.setOnClickListener {
            exportarCSV()
        }
    }

    private fun guardarCarga() {
        val odometro = etOdometro.text.toString()
        val cantidadGNC = etCantidadGNC.text.toString()
        val cantidadNafta = etCantidadNafta.text.toString()
        val fecha = etFecha.text.toString()
        val hora = etHora.text.toString()
        // Obtener los demás valores de las vistas y guardarlos en variables

        // Aquí puedes agregar validaciones si es necesario

        val registro = "$odometro,$fecha,$hora,tipo_combustible,$cantidadGNC,$cantidadNafta,importe"
        registros.add(registro)

        // Limpiar los campos después de guardar
        etOdometro.text.clear()
        etCantidadGNC.text.clear()
        etCantidadNafta.text.clear()
        etFecha.text.clear()
        etHora.text.clear()
        // Limpiar los demás campos también
    }



    private fun exportarCSV() {
        val csvFile = File(this.externalCacheDir, "registros.csv")
        try {
            csvFile.createNewFile()
            val csvWriter = FileWriter(csvFile)

            for (registro in registros) {
                csvWriter.append(registro)
                csvWriter.append('\n')
            }

            csvWriter.flush()
            csvWriter.close()

            Toast.makeText(this, "Registros exportados a CSV", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Error al exportar los registros", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}
