package jp.linhnk.dictionary.datamodel

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class Word (
    @PrimaryKey
    open var id: Long = 0,

    @Required
    open var phrase: String? = null,
    open var category: Int? = null,
    open var hiragana: String? = null,
    open var meaning: String? = null
) : RealmObject() {}
