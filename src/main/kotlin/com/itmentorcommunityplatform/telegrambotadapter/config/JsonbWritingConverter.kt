package com.itmentorcommunityplatform.telegrambotadapter.config

import com.itmentorcommunityplatform.telegrambotadapter.model.JsonbValue
import org.postgresql.util.PGobject
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter

@WritingConverter
class JsonbWritingConverter : Converter<JsonbValue, PGobject> {
    override fun convert(source: JsonbValue): PGobject {
        return PGobject().apply {
            type = "jsonb"
            value = source.value
        }
    }
}