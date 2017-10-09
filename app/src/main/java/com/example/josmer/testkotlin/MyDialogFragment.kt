package com.example.josmer.testkotlin

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.content.DialogInterface



/**
 * Created by josmer on 10/8/17.
 */

class MyDialogFragment : DialogFragment() {
    override fun  onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle("Wrong! 0 Division")
        builder.setMessage("This involve a 0 Division")
        builder.setPositiveButton("OK") { dialog, id ->
            // You don't have to do anything here if you just want it dismissed when clicked
        }
        return builder.create();
    }
}