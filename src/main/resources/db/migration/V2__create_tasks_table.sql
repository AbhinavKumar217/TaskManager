CREATE TABLE tasks (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       description VARCHAR(500),
                       status VARCHAR(20) NOT NULL,
                       priority VARCHAR(20) NOT NULL,
                       due_date DATE,
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL,
                       assigned_to BIGINT,
                       CONSTRAINT fk_task_user
                           FOREIGN KEY (assigned_to)
                               REFERENCES users(id)
);