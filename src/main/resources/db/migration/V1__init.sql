CREATE TABLE category_prompt_node
(
    node_id        BIGINT NOT NULL,
    category_key   VARCHAR(255) NULL,
    category_value VARCHAR(255) NULL,
    group_node_id  BIGINT NULL,
    CONSTRAINT pk_category_prompt_node PRIMARY KEY (node_id)
);

CREATE TABLE group_node
(
    node_id BIGINT NOT NULL,
    CONSTRAINT pk_group_node PRIMARY KEY (node_id)
);

CREATE TABLE member
(
    member_id BIGINT AUTO_INCREMENT NOT NULL,
    email     VARCHAR(255) NULL,
    `role`    VARCHAR(255) NULL,
    picture   VARCHAR(255) NULL,
    name      VARCHAR(255) NULL,
    CONSTRAINT pk_member PRIMARY KEY (member_id)
);

CREATE TABLE node
(
    node_id    BIGINT NOT NULL,
    dtype      VARCHAR(31) NULL,
    project_id BIGINT NULL,
    CONSTRAINT pk_node PRIMARY KEY (node_id)
);

CREATE TABLE project
(
    project_id BIGINT AUTO_INCREMENT NOT NULL,
    owner_id   BIGINT NULL,
    CONSTRAINT pk_project PRIMARY KEY (project_id)
);

CREATE TABLE text_prompt_node
(
    node_id BIGINT NOT NULL,
    content VARCHAR(255) NULL,
    CONSTRAINT pk_text_prompt_node PRIMARY KEY (node_id)
);

ALTER TABLE category_prompt_node
    ADD CONSTRAINT FK_CATEGORY_PROMPT_NODE_ON_GROUP_NODE FOREIGN KEY (group_node_id) REFERENCES group_node (node_id);

ALTER TABLE category_prompt_node
    ADD CONSTRAINT FK_CATEGORY_PROMPT_NODE_ON_NODE FOREIGN KEY (node_id) REFERENCES node (node_id);

ALTER TABLE group_node
    ADD CONSTRAINT FK_GROUP_NODE_ON_NODE FOREIGN KEY (node_id) REFERENCES node (node_id);

ALTER TABLE node
    ADD CONSTRAINT FK_NODE_ON_PROJECT FOREIGN KEY (project_id) REFERENCES project (project_id);

ALTER TABLE project
    ADD CONSTRAINT FK_PROJECT_ON_OWNER FOREIGN KEY (owner_id) REFERENCES member (member_id);

ALTER TABLE text_prompt_node
    ADD CONSTRAINT FK_TEXT_PROMPT_NODE_ON_NODE FOREIGN KEY (node_id) REFERENCES node (node_id);