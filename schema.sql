CREATE TABLE if not exists venues
(
    venue_id   SERIAL PRIMARY KEY,
    venue_name VARCHAR(255) NOT NULL,
    location   VARCHAR(255) NOT NULL
);


CREATE TABLE if not exists events
(
    event_id   SERIAL PRIMARY KEY,
    event_name VARCHAR(255) NOT NULL,
    event_date DATE         NOT NULL,
    venue_id   INT REFERENCES venues (venue_id)
        ON UPDATE CASCADE
);

CREATE TABLE if not exists attendees
(
    attendee_id   SERIAL PRIMARY KEY,
    attendee_name VARCHAR(255) NOT NULL,
    email         VARCHAR(255) UNIQUE
);


CREATE TABLE if not exists event_attendee
(
    attendee_id INT REFERENCES attendees (attendee_id)
        ON UPDATE CASCADE,

    event_id    INT REFERENCES events (event_id)
        ON UPDATE CASCADE ON delete cascade,

    PRIMARY KEY (attendee_id, event_id)
);

--MOCK DATA

INSERT INTO venues (venue_name, location)
VALUES ('Grand Hall', 'New York'),
       ('Conference Center', 'Los Angeles'),
       ('Open Arena', 'Chicago');

-- events
INSERT INTO events (event_name, event_date, venue_id)
VALUES ('Tech Conference', '2026-04-10', 1),
       ('Music Festival', '2026-05-15', 2),
       ('Startup Meetup', '2026-06-20', 1);

-- attendees
INSERT INTO attendees (attendee_name, email)
VALUES ('Alice Johnson', 'alice@example.com'),
       ('Bob Smith', 'bob@example.com'),
       ('Charlie Brown', 'charlie@example.com');

-- event_attendee (many-to-many relationships)
INSERT INTO event_attendee (attendee_id, event_id)
VALUES (1, 1), -- Alice attends Tech Conference
       (2, 1), -- Bob attends Tech Conference
       (2, 2), -- Bob attends Music Festival
       (3, 3), -- Charlie attends Startup Meetup
       (1, 3); -- Alice attends Startup Meetup

