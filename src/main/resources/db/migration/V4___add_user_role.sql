create TABLE IF NOT EXISTS user_role
(
    id SERIAL PRIMARY KEY,
    role VARCHAR(1024) NOT NULL,
    user_id BIGINT not NULL,
    FOREIGN KEY(user_id) REFERENCES users(id)
)