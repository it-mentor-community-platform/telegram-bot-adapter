package com.itmentorcommunityplatform.telegrambotadapter.docs

import com.itmentorcommunityplatform.telegrambotadapter.dto.response.TaskResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)

@Operation(
    summary = "Получить список необработанных задач",
    description = """
        Возвращает до 10 задач, которые еще не были отправлены боту.
        После успешного получения задачи атомарно помечаются как отправленные (sent=true).
    """
)
@Parameter(
    name = "count",
    description = "Количество запрашиваемых задач. Допустимые значения: от 1 до 10.",
    required = true,
    `in` = ParameterIn.QUERY,
    example = "5",
    schema = Schema(type = "integer")
)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Список задач успешно получен и помечен как отправленный",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = TaskResponseDto::class),
                examples = [
                    ExampleObject(
                        name = "С задачами",
                        value = """{"count": 2, "tasks": [{"task_type": "project.created", "payload": {"id": 1}}]}"""
                    ),
                    ExampleObject(
                        name = "Задач нет",
                        value = """{"count": 0, "tasks": []}"""
                    )
                ]
            )]
        ),
        ApiResponse(
            responseCode = "400",
            description = "Количество запрошеных задач выходит за допустимый лимит",
            content = [Content(
                mediaType = "application/json",
                examples = [ExampleObject(
                    name = "Bad Request",
                    value = """{"message": "The number of requested tasks exceeds the allowed limit."}"""
                )]
            )]
        ),
    ]
)
annotation class GetTasksDocs