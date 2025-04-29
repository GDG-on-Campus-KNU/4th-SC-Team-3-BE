package pipy.project.presentation;

import com.github.fge.jsonpatch.JsonPatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pipy.global.ApiSuccessResponse;
import pipy.project.presentation.dto.request.CreateProjectRequest;
import pipy.project.presentation.dto.response.CreateProjectResponse;

@Tag(name = "프로젝트")
public interface ProjectCommandApiDocs {

    @Operation(summary = "프로젝트 생성", description = "프로젝트를 생성합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "프로젝트 생성 성공")
    })
    @RequestBody(
        description = "프로젝트 생성 요청",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = CreateProjectRequest.class)
        )
    )
    ResponseEntity<ApiSuccessResponse.ApiSuccessResult<CreateProjectResponse>> createProject(
        OAuth2User user,
        CreateProjectRequest canvas
    );

    @Operation(summary = "프로젝트 캔버스 저장", description = "프로젝트의 캔버스를 저장합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "프로젝트 캔버스 저장 성공")
    })
    @RequestBody(
        description = "프로젝트 캔버스 저장 요청. JSON Patch 형식(RFC 6902)을 따라야 합니다.",
        content = @Content(
            mediaType = "application/json-patch+json",
            schema = @Schema(implementation = JsonPatchOperationDocs[].class)
        )
    )
    ResponseEntity<ApiSuccessResponse.ApiSuccessResult<Void>> updateCanvas(
        Long projectId,
        JsonPatch canvas
    );
}

@Schema(description = "JSON Patch 작업")
record JsonPatchOperationDocs(
    @Schema(description = "작업 종류", example = "replace (add, remove, replace, move, copy, test)")
    String op,
    @Schema(description = "수정 경로", example = "/name")
    String path,
    @Schema(description = "값", example = "new value")
    Object value
) {
}