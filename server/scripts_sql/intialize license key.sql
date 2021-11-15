USE jaspreetflourmill;

DROP TABLE IF EXISTS license;

CREATE TABLE license (
	key_id VARCHAR(12),
	license_key ENUM('LICENSE KEY') NOT NULL,
    UNIQUE (license_key)
);

INSERT INTO license VALUES (
	'12345678912',
    'LICENSE KEY'
);

SELECT * FROM license;