package pipy.project.presentation;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import pipy.global.ApiSuccessResponse;
import pipy.project.application.ProjectCommandService;
import pipy.project.domain.Project;
import pipy.project.presentation.dto.request.CreateProjectRequest;
import pipy.project.presentation.dto.response.CreateProjectResponse;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectCommandController implements ProjectCommandApiDocs {

    private final ProjectCommandService service;

    @PostMapping
    public ResponseEntity<ApiSuccessResult<CreateProjectResponse>> createProject(
        @AuthenticationPrincipal final OAuth2User user,
        @RequestBody final CreateProjectRequest request
    ) {
        final String email = user.getName();
        final Project project = service.createProject(email, request.canvas());
        final CreateProjectResponse response = CreateProjectResponse.from(project);
        return ApiSuccessResponse.success(HttpStatus.CREATED, response);
    }

    @PatchMapping(value = "/{projectId}/canvas", consumes = "application/json-patch+json")
    public ResponseEntity<ApiSuccessResult<Void>> updateCanvas(
        @PathVariable final Long projectId,
        @RequestBody final JsonPatch canvas
    ) {
        service.updateCanvas(projectId, canvas);
        return ApiSuccessResponse.success(HttpStatus.OK);
    }
}
