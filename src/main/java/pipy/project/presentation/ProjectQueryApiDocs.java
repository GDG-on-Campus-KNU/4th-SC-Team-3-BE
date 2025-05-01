package pipy.project.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import pipy.auth.application.PipyUser;
import pipy.project.presentation.dto.response.ProjectCardResponse;
import pipy.project.presentation.dto.response.ProjectResponse;

import java.util.List;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@Tag(name = "프로젝트")
public interface ProjectQueryApiDocs {

    @Operation(summary = "프로젝트 목록 조회", description = "프로젝트 목록을 조회합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "프로젝트 목록 조회 성공")
    })
    ResponseEntity<ApiSuccessResult<List<ProjectCardResponse>>> getProjectList(
        PipyUser user
    );

    @Operation(summary = "프로젝트 조회", description = "프로젝트를 조회합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "프로젝트 조회 성공")
    })
    ResponseEntity<ApiSuccessResult<ProjectResponse>> getProject(Long projectId);
}
