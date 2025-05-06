package pipy.project.presentation;

import com.github.fge.jsonpatch.JsonPatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import pipy.auth.application.PipyUser;
import pipy.global.ApiSuccessResponse;
import pipy.project.presentation.dto.request.CreateProjectRequest;
import pipy.project.presentation.dto.request.UpdateProjectNameRequest;
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
        PipyUser user,
        CreateProjectRequest canvas
    );

    @Operation(summary = "프로젝트 캔버스 저장", description = "프로젝트의 캔버스를 저장합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "프로젝트 캔버스 저장 성공")
    })
    @Parameter(
        name = "projectId",
        description = "프로젝트 ID",
        required = true,
        example = "1"
    )
    @RequestBody(
        description = "프로젝트 캔버스 저장 요청. JSON Patch 형식(RFC 6902)을 따라야 합니다.",
        content = @Content(
            mediaType = "application/json-patch+json",
            schema = @Schema(implementation = JsonPatchOperationDocs[].class)
        )
    )
    ResponseEntity<ApiSuccessResponse.ApiSuccessResult<Void>> updateCanvas(
        PipyUser user,
        Long projectId,
        JsonPatch canvas
    );

    @Operation(summary = "프로젝트 썸네일 갱신", description = "프로젝트의 썸네일을 갱신합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "프로젝트 썸네일 갱신 성공")
    })
    @Parameter(
        name = "projectId",
        description = "프로젝트 ID",
        required = true,
        example = "1"
    )
    @RequestBody(
        description = "프로젝트 썸네일",
        content = @Content(
            mediaType = "multipart/form-data"
        )
    )
    ResponseEntity<ApiSuccessResponse.ApiSuccessResult<Void>> updateThumbnail(
        PipyUser user,
        Long projectId,
        MultipartFile thumbnail
    );

    @Operation(summary = "프로젝트 이름 수정", description = "프로젝트의 이름을 수정합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "프로젝트 이름 수정 성공")
    })
    @Parameter(
        name = "projectId",
        description = "프로젝트 ID",
        required = true,
        example = "1"
    )
    @RequestBody(
        description = "프로젝트 이름 수정 요청",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UpdateProjectNameRequest.class)
        )
    )
    ResponseEntity<ApiSuccessResponse.ApiSuccessResult<Void>> updateName(
        PipyUser user,
        Long projectId,
        UpdateProjectNameRequest request
    );

    @Operation(summary = "프로젝트 삭제", description = "프로젝트를 삭제합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "프로젝트 삭제 성공")
    })
    @Parameter(
        name = "projectId",
        description = "프로젝트 ID",
        required = true,
        example = "1"
    )
    ResponseEntity<ApiSuccessResponse.ApiSuccessResult<Void>> deleteProject(
        PipyUser user,
        Long projectId
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