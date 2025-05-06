package pipy.node.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "텍스트 프롬프트 노드 생성 요청, type: text_prompt")
public class TextPromptNodeRequest extends NodeRequest {

    @Schema(description = "텍스트 프롬프트 내용")
    private String content;
}