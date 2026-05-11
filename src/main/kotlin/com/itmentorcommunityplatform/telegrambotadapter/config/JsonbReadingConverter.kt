package com.itmentorcommunityplatform.telegrambotadapter.config

import com.itmentorcommunityplatform.telegrambotadapter.model.JsonbValue
import org.postgresql.util.PGobject
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter

@ReadingConverter
class JsonbReadingConverter : Converter<PGobject, JsonbValue> {
    override fun convert(source: PGobject): JsonbValue {
        return JsonbValue(source.value ?: "")
    }
}