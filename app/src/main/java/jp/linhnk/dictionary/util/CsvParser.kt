package jp.linhnk.dictionary.util

import android.content.Context
import android.content.res.AssetManager
import io.realm.Realm
import io.realm.RealmObject

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.ArrayList
import java.util.StringTokenizer

import jp.linhnk.dictionary.datamodel.Word

/**
 * Read data from CSV file
 */

class CsvParser {

    companion object {

        fun parser(context: Context, realm: Realm): List<Word> {
            val assetManager = context.resources.assets
            val wordList = ArrayList<Word>()
            var index: Long = 0
            try {
                // init stream reader
                val inputStream = assetManager.open("data.csv")
                val inputStreamReader = InputStreamReader(inputStream)

                val bufferedReader = BufferedReader(inputStreamReader)
                var line = bufferedReader.readLine()

                while (line != null) {
                    var stringTokenizer = StringTokenizer(line, ",")
                    var category = stringTokenizer.nextToken()
                    var phrase = if (stringTokenizer.hasMoreTokens()) stringTokenizer.nextToken() else ""
                    var hiragana = if (stringTokenizer.hasMoreTokens()) stringTokenizer.nextToken() else ""
                    var meaning = if (stringTokenizer.hasMoreTokens()) stringTokenizer.nextToken() else ""

                    var word = Word()
                    word.id = index
                    word.category = category.toInt()
                    word.phrase = phrase
                    word.hiragana = hiragana
                    word.meaning = meaning
                    wordList.add(word)
                    line = bufferedReader.readLine()
                    index++
                }

                bufferedReader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return wordList
        }
    }
}
