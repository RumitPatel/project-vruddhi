package com.project.vruddhi.utils

import android.content.SharedPreferences
import android.util.Base64
import android.util.Base64InputStream
import android.util.Base64OutputStream
import androidx.core.content.edit
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.StreamCorruptedException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MyPreference @Inject constructor(private var mSharedPref: SharedPreferences) {

    fun getValueString(
        key: String,
        defaultValue: String
    ): String? {
        return mSharedPref.getString(key, defaultValue)
    }

    fun setValueString(key: String, value: String) {
        mSharedPref.edit {
            putString(key, value)
            apply()
        }
    }

    fun getValueBoolean(
        key: String,
        defaultValue: Boolean
    ): Boolean {
        return mSharedPref.getBoolean(key, defaultValue)
    }

    fun setValueBoolean(key: String, value: Boolean) {
        mSharedPref.edit {
            putBoolean(key, value)
            apply()
        }
    }

    fun getValueInt(
        key: String,
        defaultValue: Int
    ): Int {
        return mSharedPref.getInt(key, defaultValue)
    }

    fun setValueInt(key: String, value: Int) {
        mSharedPref.edit {
            putInt(key, value)
            apply()
        }
    }

    fun getValueLong(
        key: String,
        defaultValue: Long
    ): Long {
        return mSharedPref.getLong(key, defaultValue)
    }

    fun setValueLong(key: String, value: Long) {
        mSharedPref.edit {
            putLong(key, value)
            apply()
        }
    }

    fun clearAllData() {
        mSharedPref.edit {
            clear()
            apply()
        }
    }

    fun setBeanValue(key: String, myObj: Any) {
        val prefsPrivateEditor = mSharedPref.edit()

        val arrayOutputStream = ByteArrayOutputStream()

        val objectOutput: ObjectOutputStream
        try {
            objectOutput = ObjectOutputStream(arrayOutputStream)
            objectOutput.writeObject(myObj)
            val data = arrayOutputStream.toByteArray()
            objectOutput.close()
            arrayOutputStream.close()

            val out = ByteArrayOutputStream()
            val b64 = Base64OutputStream(out, Base64.DEFAULT)
            b64.write(data)
            b64.close()
            out.close()

            prefsPrivateEditor.putString(key, String(out.toByteArray()))

            prefsPrivateEditor.apply()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getBeanValue(key: String): Any? {

        val bytes = mSharedPref.getString(key, "{}")!!.toByteArray()
        if (bytes.isEmpty()) {
            return null
        }
        val byteArray = ByteArrayInputStream(bytes)
        val base64InputStream = Base64InputStream(byteArray, Base64.DEFAULT)
        var objIn: ObjectInputStream? = null
        var myObject = Any()
        try {
            objIn = ObjectInputStream(base64InputStream)
            try {
                myObject = objIn.readObject()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        } catch (e: StreamCorruptedException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (objIn != null) {
            try {
                objIn.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return myObject
    }
}
