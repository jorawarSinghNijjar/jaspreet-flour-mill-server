DROP database IF exists jaspreetflourmill;
CREATE DATABASE jaspreetflourmill;
USE jaspreetflourmill;

DROP TABLE IF EXISTS license;

CREATE TABLE license (
	key_id VARCHAR(12) PRIMARY KEY NOT NULL,
	license_key ENUM('LICENSE_KEY') NOT NULL,
    UNIQUE (license_key)
);

INSERT INTO license VALUES (
	'12345678912',
    'LICENSE_KEY'
);
