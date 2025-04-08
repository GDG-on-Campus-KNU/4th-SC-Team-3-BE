CREATE TABLE edge
(
    edge_id      BIGINT AUTO_INCREMENT NOT NULL,
    from_node_id BIGINT NULL,
    to_node_id   BIGINT NULL,
    CONSTRAINT pk_edge PRIMARY KEY (edge_id)
);

ALTER TABLE edge
    ADD CONSTRAINT FK_EDGE_ON_FROM_NODE FOREIGN KEY (from_node_id) REFERENCES node (node_id);

ALTER TABLE edge
    ADD CONSTRAINT FK_EDGE_ON_TO_NODE FOREIGN KEY (to_node_id) REFERENCES node (node_id);