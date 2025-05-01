DROP TABLE edge;

DROP TABLE category_prompt_node;
DROP TABLE group_node;
DROP TABLE text_prompt_node;
DROP TABLE node;

ALTER TABLE project ADD canvas JSON NULL;
ALTER TABLE project ADD name VARCHAR(255) NULL;
ALTER TABLE project ADD thumbnail VARCHAR(255) NULL;
ALTER TABLE project ADD created_at timestamp NULL;
ALTER TABLE project ADD updated_at timestamp NULL;