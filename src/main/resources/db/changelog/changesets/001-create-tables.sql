CREATE TABLE telegram_bot_tasks (
                                    id         BIGSERIAL PRIMARY KEY,
                                    task_type  VARCHAR NOT NULL,
                                    payload    JSONB   NOT NULL,
                                    created_at BIGINT  NOT NULL,
                                    sent       BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_telegram_bot_tasks_sent ON telegram_bot_tasks (sent);