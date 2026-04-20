package com.itmentorcommunityplatform.telegrambotadapter.dto.event

import com.fasterxml.jackson.annotation.JsonProperty

data class ProjectCreatedEvent(
    @JsonProperty("author_telegram_user_id")
    val authorTelegramUserId: Long,

    @JsonProperty("author_telegram_profile_url")
    val authorTelegramProfileUrl: String,

    @JsonProperty("github_repository_url")
    val githubRepositoryUrl: String,

    @JsonProperty("programming_language")
    val programmingLanguage: String,

    @JsonProperty("roadmap_project")
    val roadmapProject: String? = null,

    @JsonProperty("added_timestamp")
    val addedTimestamp: Long,

    @JsonProperty("project_source_type")
    val projectSourceType: String
)