package com.example.josmer.testkotlin

import android.app.Activity
import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    var countSwitch = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchVolt.setOnClickListener{
            switch(switchVolt, volt)
        }
        switchCurrent.setOnClickListener{
            switch(switchCurrent, imp)
        }
        switchResistance.setOnClickListener{
            switch(switchResistance,resistance)
        }
        switchPower.setOnClickListener{
            switch(switchPower, power)
        }
        botoncito.setOnClickListener {
            calculate()
        }
    }

    fun switch(switch: Switch , editText: EditText){

        if (switch.isChecked && countSwitch<2) {
            countSwitch++
            editText.text=null
        }
        else if (!switch.isChecked) {
            countSwitch--
            editText.text=null
        }else
            switch.isChecked=false
            editText.isEnabled = switch.isChecked
    }

    fun calculate(){
        var voltNum:Double= if(!volt.text.isNullOrEmpty()) volt.text.toString().toDouble() else 0.0
        var impNum:Double = if(!imp.text.isNullOrEmpty()) imp.text.toString().toDouble() else 0.0
        var resNum:Double = if(!resistance.text.isNullOrEmpty()) resistance.text.toString().toDouble() else 0.0
        var powNum:Double = if(!power.text.isNullOrEmpty()) power.text.toString().toDouble() else 0.0
        val dialog = MyDialogFragment()

        if(switchVolt.isChecked.and(switchCurrent.isChecked)) {
            if(impNum==0.0){
                dialog.show(fragmentManager,"Zero Division")

            }else resNum = (voltNum/impNum)

            powNum = (voltNum*impNum)
            mostrarEnPantalla("Resistance: V/I = "+resNum+" Ω\n" +
                                    "Power: V*I = "+powNum+" Watts")
            resistance.setText(resNum.toString())
            power.setText(powNum.toString())
        }
        if(switchVolt.isChecked.and(switchResistance.isChecked)) {
            impNum = (voltNum*resNum)
            powNum = (voltNum*impNum)
            mostrarEnPantalla("Current: V * R = {$impNum} amp \n" +
                    "Power: V * I = V * R^2 = {$powNum} Watts")
            imp.setText(impNum.toString())
            power.setText(powNum.toString())
        }
        if(switchVolt.isChecked.and(switchPower.isChecked)) {
            if(powNum==0.0){
                dialog.show(fragmentManager,"Zero Division")

            }else impNum = (voltNum/powNum)

            resNum = (voltNum/impNum)
            mostrarEnPantalla("Current: V / P = {$impNum} amp \n" +
                    "Resistance: V / I = V^(1/2) / P  = {$resNum} Ω")
            imp.setText(impNum.toString())
            resistance.setText(resNum.toString())
        }
        if(switchResistance.isChecked.and(switchCurrent.isChecked)) {
            voltNum = (impNum*resNum)
            powNum = (voltNum*impNum)
            mostrarEnPantalla("Volt: I * R = {$voltNum} volt \n" +
                    "Power: V * I = I^2 * R = {$powNum} Watts")
            volt.setText(impNum.toString())
            power.setText(powNum.toString())
        }
        if(switchResistance.isChecked.and(switchPower.isChecked)) {
            if(powNum==0.0){
                dialog.show(fragmentManager,"Zero Division")

            }else impNum = Math.sqrt(powNum/resNum)

            voltNum = Math.sqrt(resNum*powNum)
            mostrarEnPantalla("Volt: (R * P)^-2 = {$voltNum} volt \n" +
                    "Current:  (P/R)^1/2 = {$powNum} Watts")
            volt.setText(impNum.toString())
            imp.setText(impNum.toString())
        }
        if(switchCurrent.isChecked.and(switchPower.isChecked)) {
            if(powNum==0.0){
                dialog.show(fragmentManager,"Zero Division")
            }else{
                voltNum = (powNum/impNum)
                resNum = (voltNum/impNum)
            }
            mostrarEnPantalla("Volt: P/I = {$voltNum} volt \n" +
                    "Resistance:  (P/R^-1/2) = {$resNum} Watts")
            volt.setText(volt.toString())
            resistance.setText(resNum.toString())
        }
    }

    fun mostrarEnPantalla(s : String){
        val txt  = findViewById<TextView>(R.id.ecuation)
        txt.text = s
    }
}

