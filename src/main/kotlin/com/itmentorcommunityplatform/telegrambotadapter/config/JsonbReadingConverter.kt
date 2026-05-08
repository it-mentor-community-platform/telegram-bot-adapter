package com.itmentorcommunityplatform.telegrambotadapter.config

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.postgresql.util.PGobject

@ReadingConverter
class JsonbReadingConverter : Converter<PGobject, String> {
    override fun convert(source: PGobject): String? {
        return source.value
    }
}