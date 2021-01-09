package com.ems.oppidu.utilities

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


object AlertDialogUtils {

    /* show alert Dialog*/

    fun showDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String,
        onClick: DialogInterface.OnClickListener
    ) {

        val alertDialog = AlertDialog.Builder(context)

        // Setting Dialog Title
        alertDialog.setTitle(title)

        // Setting Dialog Message
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton(positiveButtonText, onClick)
        // Showing Alert Message
        try {
            alertDialog.create().show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun showDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        onClick: DialogInterface.OnClickListener
    ) {

        val alertDialog = AlertDialog.Builder(context)

        // Setting Dialog Title
        alertDialog.setTitle(title)

        // Setting Dialog Message
        alertDialog.setMessage(message)

        alertDialog.setPositiveButton(positiveButtonText, onClick)
        alertDialog.setNegativeButton(negativeButtonText, onClick)

        // Showing Alert Message
        alertDialog.create().show()
    }

    fun showDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        neutralButtonText: String,
        onClick: DialogInterface.OnClickListener
    ) {

        val alertDialog = AlertDialog.Builder(context)

        // Setting Dialog Title
        alertDialog.setTitle(title)

        // Setting Dialog Message
        alertDialog.setMessage(message)

        alertDialog.setPositiveButton(positiveButtonText, onClick)
        alertDialog.setNegativeButton(negativeButtonText, onClick)
        alertDialog.setNeutralButton(neutralButtonText, onClick)

        // Showing Alert Message
        alertDialog.create().show()
    }
}