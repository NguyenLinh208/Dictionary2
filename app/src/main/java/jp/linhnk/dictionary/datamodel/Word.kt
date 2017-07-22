package jp.linhnk.dictionary.datamodel

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import org.apache.commons.lang3.StringUtils

@RealmClass
open class Word(
        @PrimaryKey
        open var id: Int = 0,

        @Required
        open var phrase: String? = StringUtils.EMPTY,

        open var category: Int? = 1,
        open var hiragana: String? = StringUtils.EMPTY,
        open var meaning: String? = StringUtils.EMPTY
) : RealmObject()
