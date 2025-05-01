package pipy.node.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "노드 분석 요청")
public record NodeAnalyzeRequest(
    @Schema(description = "분석할 노드의 프롬프트 내용") String content
) {
}