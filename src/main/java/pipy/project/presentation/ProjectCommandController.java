package pipy.project.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pipy.global.ApiSuccessResponse;
import pipy.project.application.ProjectCommandService;
import pipy.project.domain.Project;
import pipy.project.presentation.dto.response.CreateProjectResponse;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectCommandController implements ProjectCommandApiDocs {

    private final ProjectCommandService service;

    @PostMapping
    public ResponseEntity<ApiSuccessResult<CreateProjectResponse>> createProject(
        @AuthenticationPrincipal final OAuth2User user
    ) {
        final String email = user.getName();
        final Project project = service.createProject(email);
        final CreateProjectResponse response = CreateProjectResponse.from(project);
        return ApiSuccessResponse.success(HttpStatus.CREATED, response);
    }
}
