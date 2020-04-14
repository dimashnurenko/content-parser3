create TABLE IF NOT EXISTS song_statistic
(
    id SERIAL PRIMARY KEY,
    "date" date,
    "timestamp" BIGINT,
    content_url varchar(256),
    "content" text,
    amount_same_words BIGINT,
    total_words_amount BIGINT,
    most_popular_words varchar(50),
    amount_unique_words BIGINT
)