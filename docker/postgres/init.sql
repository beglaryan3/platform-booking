CREATE  DATABASE platform;
CREATE USER admin PASSWORD 'admin_pwd';
GRANT  ALL PRIVILEGES ON  DATABASE platform TO admin;

\c platform

CREATE USER booking_user PASSWORD 'booking_user_pwd';
GRANT  CONNECT ON DATABASSE platform TO booking_user;

CREATE  SCHEMA  booking;
ALTER  SCHEMA booking OWNER TO admin;
GRANT  ALL PRIVILEGES  ON SCHEMA booking TO booking_user;
ALTER  DEFAULT PRIVILEGES
       FOR USER admin
       IN SCHEMA booking
       GRANT  ALL PRIVILEGES ON TABLES TO booking_user;
ALTER  DEFAULT PRIVILEGES
       FOR USER admin
       IN SCHEMA booking
        GRANT  ALL PRIVILEGES ON SEQUENCES TO booking_user;


