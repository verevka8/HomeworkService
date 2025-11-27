CREATE TABLE homeworks (
                       id UUID PRIMARY KEY,
                       date date not null,
                       task  VARCHAR(255) not null,
                       author_id UUID not null references Users(id)
);