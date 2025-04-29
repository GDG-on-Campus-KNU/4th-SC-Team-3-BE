package pipy.project.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import pipy.project.domain.Project;

import java.time.LocalDateTime;

public record ProjectResponse(
    @Schema(description = "프로젝트 ID") Long projectId,
    @Schema(description = "프로젝트 이름") String name,
    @Schema(description = "프로젝트 캔버스") String canvas,
    @Schema(description = "프로젝트 생성일") LocalDateTime createdAt,
    @Schema(description = "프로젝트 최근 수정일") LocalDateTime updatedAt
) {

    public static ProjectResponse from(final Project project) {
        return new ProjectResponse(
            project.getId(),
            project.getName(),
            project.getCanvas(),
            project.getCreatedAt(),
            project.getUpdatedAt()
        );
    }
}