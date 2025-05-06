package pipy.node.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "사진 생성 요청")
public record ImageGenerationRequest(
    @Schema(description = "프로젝트 ID") Long projectId,
    @Schema(description = "사진 생성 프롬프트 노드 리스트") List<NodeRequest> nodes
) {
}
