CREATE TABLE IF NOT EXISTS request (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ip VARCHAR(45) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    uri VARCHAR(2048) NOT NULL,
    user_agent VARCHAR(1024)
);

-- Optional index on timestamp for typically time-based queries
CREATE INDEX IF NOT EXISTS idx_request_timestamp ON request (timestamp);