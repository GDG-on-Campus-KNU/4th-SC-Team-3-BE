package pipy.project.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "프로젝트 생성 요청")
public record CreateProjectRequest(
    @Schema(description = "프로젝트 캔버스") String canvas
) {
}