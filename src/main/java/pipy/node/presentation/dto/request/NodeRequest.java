package pipy.node.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = TextPromptNodeRequest.class, name = "text_prompt"),
    @JsonSubTypes.Type(value = CategoryPromptNodeRequest.class, name = "category_prompt"),
    @JsonSubTypes.Type(value = GroupNodeRequest.class, name = "group")
})
@Schema(description = "노드 생성 요청")
public abstract class NodeRequest {
}