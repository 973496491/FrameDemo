package com.lolko.frame_module.helper

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix


/**
 * <h2> 二维码帮助类 </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年08月25日 9:37
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object QRCodeHelp {

    /**
     *   指定String生成生成二维码
     *
     *   @param content 二维码内容
     *   @param width 生成的二维码宽
     *   @param height 生成的二维码高
     *
     *   @return 生成的二维码图片
     */
    fun createQRcode(content: String, width: Int, height: Int): Bitmap? {
        if (content.isEmpty()) return null

        var matrix = MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height)
        matrix = deleteWhite(matrix)

        val sWidth = matrix.width
        val sHeight = matrix.height
        val pixels = IntArray(sWidth * sHeight)

        for (y in 0 until sHeight) {
            for (x in 0 until sWidth) {
                if (matrix[x, y])
                    pixels[y * sWidth + x] = -0x1000000
                else
                    pixels[y * sWidth + x] = Color.WHITE
            }
        }

        val bitmap: Bitmap = Bitmap.createBitmap(sWidth, sHeight, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, sWidth, 0, 0, sWidth, sHeight)
        return bitmap
    }

    /**
     * 去白边
     */
    fun deleteWhite(matrix: BitMatrix): BitMatrix {
        val rec = matrix.enclosingRectangle
        val resWidth = rec[2] + 1
        val resHeight = rec[3] + 1

        val resMatrix = BitMatrix(resWidth, resHeight)
        resMatrix.clear()
        for (i in 0 until resWidth) {
            for (j in 0 until resHeight) {
                if (matrix[i + rec[0], j + rec[1]]) resMatrix[i] = j
            }
        }
        return resMatrix
    }

}