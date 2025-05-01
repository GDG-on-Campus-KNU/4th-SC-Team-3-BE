package pipy.project.presentation;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pipy.auth.application.PipyUser;
import pipy.global.ApiSuccessResponse;
import pipy.member.domain.Member;
import pipy.project.application.CreateProjectCommand;
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
        @AuthenticationPrincipal final PipyUser user,
        @RequestBody final CreateProjectRequest request
    ) {
        final Member member = user.getMember();
        final CreateProjectCommand command = new CreateProjectCommand(
            member,
            request.name(),
            request.canvas()
        );
        final Project project = service.createProject(command);
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

    @PutMapping(value = "/{projectId}/thumbnail", consumes = "multipart/form-data")
    public ResponseEntity<ApiSuccessResult<Void>> updateThumbnail(
        @PathVariable final Long projectId,
        @RequestPart(value = "thumbnail") final MultipartFile thumbnail
    ) {
        service.updateThumbnail(projectId, thumbnail);
        return ApiSuccessResponse.success(HttpStatus.OK);
    }
}
