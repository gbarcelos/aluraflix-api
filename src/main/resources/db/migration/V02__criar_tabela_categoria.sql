CREATE TABLE categoria (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(30) NOT NULL,
	cor VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (id, titulo, cor) VALUES
(1, 'Livre', '#3c3c3c');

ALTER TABLE video ADD COLUMN categoria_id BIGINT;

ALTER TABLE video ADD CONSTRAINT fk_video_categoria
FOREIGN KEY (categoria_id) REFERENCES categoria (id);

UPDATE video set categoria_id = 1;

ALTER TABLE video MODIFY COLUMN categoria_id BIGINT NOT NULL;