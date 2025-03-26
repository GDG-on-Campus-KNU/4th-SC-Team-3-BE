package pipy.node.controller.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = TextNodeDto.class, name = "text"),
    @JsonSubTypes.Type(value = CategoryNodeDto.class, name = "category"),
    @JsonSubTypes.Type(value = GroupNodeDto.class, name = "group")
})
public abstract class NodeDto {

    private String nodeId;
    private String type;
}