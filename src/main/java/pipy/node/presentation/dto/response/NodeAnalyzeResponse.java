package pipy.node.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import pipy.node.domain.NodeAnalyzeResult;

import java.util.List;

@Schema(description = "노드 분석 결과 응답")
public record NodeAnalyzeResponse(
    @Schema(description = "카테고리 키") String key,
    @Schema(description = "카테고리 값") List<String> value
) {

    public static NodeAnalyzeResponse from(final NodeAnalyzeResult result) {
        return new NodeAnalyzeResponse(
            result.key(),
            result.value()
        );
    }
}