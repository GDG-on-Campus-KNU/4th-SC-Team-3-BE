package pipy.project.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import pipy.global.ApiSuccessResponse;
import pipy.project.presentation.dto.response.CreateProjectResponse;

@Tag(name = "프로젝트")
public interface ProjectCommandApiDocs {

    @PostMapping
    @Operation(summary = "프로젝트 생성", description = "프로젝트를 생성합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "프로젝트 생성 성공")
    })
    ResponseEntity<ApiSuccessResponse.ApiSuccessResult<CreateProjectResponse>> createProject(OAuth2User user);
}
