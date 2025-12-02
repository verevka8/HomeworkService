CREATE TABLE IF NOT EXISTS plagiarism (
                                          id serial primary key,
                                          user1_id uuid not null references users(id),
    user2_id uuid not null references users(id),
    task varchar(150) not null,
    unique (user1_id, user2_id, task)
)