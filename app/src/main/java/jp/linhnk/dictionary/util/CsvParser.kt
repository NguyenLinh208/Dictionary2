package jp.linhnk.dictionary.util

import android.content.Context
import android.util.Log

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

import jp.linhnk.dictionary.datamodel.Word
import java.util.*

/**
 * Read data from CSV file
 */

class CsvParser {

    companion object {

        fun parser(context: Context) : MutableList<Word> {
            val assetManager = context.resources.assets
            var index: Int = 0
            var listWord : MutableList<Word> = mutableListOf()
            try {
                // init stream reader
                val inputStream = assetManager.open("data.csv")
                val inputStreamReader = InputStreamReader(inputStream)

                val bufferedReader = BufferedReader(inputStreamReader)
                var line = bufferedReader.readLine()

                while (line != null) {
                    val stringTokenizer = StringTokenizer(line, ",")
                    val category = stringTokenizer.nextToken()
                    val phrase = if (stringTokenizer.hasMoreTokens()) stringTokenizer.nextToken() else ""
                    val hiragana = if (stringTokenizer.hasMoreTokens()) stringTokenizer.nextToken() else ""
                    val meaning = if (stringTokenizer.hasMoreTokens()) stringTokenizer.nextToken() else ""

                    Log.i("word:",
                            "id = " + index
                                    + " category = " + category
                                    + " phrase = " + phrase
                                    + " hiragana = " + hiragana
                                    + " meaning = " + meaning)

                    val word = Word(index, phrase, category.toInt(), hiragana, meaning)
                    listWord.add(word)

                    line = bufferedReader.readLine()
                    index++
                }

                bufferedReader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return listWord
        }
    }
}
