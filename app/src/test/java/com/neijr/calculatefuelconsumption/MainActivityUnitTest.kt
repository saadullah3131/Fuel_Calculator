package com.neijr.calculatefuelconsumption

import android.os.Build
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1], qualifiers="pt-port")
class MainActivityUnitTest {

    @Test
    fun testFormCalculate() {
        val controller: ActivityController<MainActivity> =
            Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .resume()
        val systemUnderTest: MainActivity = controller.get()

        val buttonCalculate = systemUnderTest.findViewById(R.id.btnCalcConsumption) as Button
        val editDistance = systemUnderTest.findViewById(R.id.editDistance) as EditText
        val editPrice = systemUnderTest.findViewById(R.id.editPrice) as EditText
        val editAutonomy = systemUnderTest.findViewById(R.id.editAutonomy) as EditText
        editDistance.setText("10")
        editPrice.setText("10")
        editAutonomy.setText("10")

        buttonCalculate.performClick()

        val totalValue = systemUnderTest.findViewById(R.id.textResult) as TextView

        assertEquals("10", editDistance.text.toString())
        assertEquals("10", editPrice.text.toString())
        assertEquals("10", editAutonomy.text.toString())
        assertEquals("Calcular", buttonCalculate.text)

        assertEquals("R$ 10,00", totalValue.text)
    }
}