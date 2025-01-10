CREATE TABLE IF NOT EXISTS team (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(100),
    account_balance DOUBLE,
    commission_percentage DOUBLE
    );

CREATE TABLE IF NOT EXISTS player (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100),
    age INT,
    months_of_experience INT,
    team_id BIGINT,
    FOREIGN KEY (team_id) REFERENCES team(id)
    );