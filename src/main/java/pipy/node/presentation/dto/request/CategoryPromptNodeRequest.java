package pipy.node.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "카테고리 프롬프트 노드 생성 요청, type: category_prompt")
public class CategoryPromptNodeRequest extends NodeRequest {

    @Schema(description = "카테고리 프롬프트 키")
    private String key;

    @Schema(description = "카테고리 프롬프트 값")
    private List<String> value;
}