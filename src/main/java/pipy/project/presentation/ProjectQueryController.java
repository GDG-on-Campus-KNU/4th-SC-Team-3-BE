package pipy.project.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pipy.auth.application.PipyUser;
import pipy.global.ApiSuccessResponse;
import pipy.member.domain.Member;
import pipy.project.application.ProjectQueryService;
import pipy.project.presentation.dto.response.ProjectCardResponse;
import pipy.project.presentation.dto.response.ProjectResponse;

import java.util.List;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectQueryController implements ProjectQueryApiDocs {

    private final ProjectQueryService queryService;

    @GetMapping
    public ResponseEntity<ApiSuccessResult<List<ProjectCardResponse>>> getProjectList(
        @AuthenticationPrincipal final PipyUser user
    ) {
        final Member member = user.getMember();
        final List<ProjectCardResponse> response = queryService.getProjectsByOwner(member)
            .stream()
            .map(ProjectCardResponse::from)
            .toList();
        return ApiSuccessResponse.success(HttpStatus.OK, response);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ApiSuccessResult<ProjectResponse>> getProject(
        @PathVariable final Long projectId
    ) {
        final ProjectResponse response = ProjectResponse.from(queryService.getProjectById(projectId));
        return ApiSuccessResponse.success(HttpStatus.OK, response);
    }
}
