package pipy.project.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pipy.global.ApiSuccessResponse;
import pipy.project.presentation.dto.response.CreateProjectResponse;
import pipy.project.domain.Project;
import pipy.project.application.ProjectCommandService;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@RestController
@Tag(name = "프로젝트")
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectCommandController {

    private final ProjectCommandService service;

    @PostMapping
    @Operation(summary = "프로젝트 생성", description = "프로젝트를 생성합니다.")
    @ApiResponses({
       @ApiResponse(responseCode = "201", description = "프로젝트 생성 성공")
    })
    public ResponseEntity<ApiSuccessResult<CreateProjectResponse>> createProject(
        @AuthenticationPrincipal final OAuth2User user
    ) {
        final String email = user.getName();
        final Project project = service.createProject(email);
        final CreateProjectResponse response = CreateProjectResponse.from(project);
        return ApiSuccessResponse.success(HttpStatus.CREATED, response);
    }
}
