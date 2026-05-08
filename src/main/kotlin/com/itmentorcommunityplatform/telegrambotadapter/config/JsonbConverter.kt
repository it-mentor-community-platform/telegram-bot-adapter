package com.itmentorcommunityplatform.telegrambotadapter.config

import org.postgresql.util.PGobject
import org.springframework.data.convert.WritingConverter
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@WritingConverter
class JsonbConverter : Converter<String, PGobject> {
    override fun convert(source: String): PGobject {
        return PGobject().apply {
            type = "jsonb"
            value = source
        }
    }
}