package pipy.project.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "프로젝트 이름 수정 요청")
public record UpdateProjectNameRequest(
    @Schema(description = "프로젝트 이름") String name
) {
}