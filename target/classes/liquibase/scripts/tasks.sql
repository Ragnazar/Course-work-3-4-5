-- liquibase formatted sql

-- changeset iavdeyev:1
CREATE TABLE notification_task (
    id        SERIAL,
    chat_id   BIGINT,
    message   TEXT,
    time_send TIMESTAMP
)

-- changeset iavdeyev:2
ALTER TABLE notification_task
    ALTER COLUMN id TYPE BIGINT USING (id::BIGINT);