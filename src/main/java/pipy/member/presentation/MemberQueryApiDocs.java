package pipy.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import pipy.auth.application.PipyUser;
import pipy.member.presentation.dto.MemberResponse;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@Tag(name = "회원")
public interface MemberQueryApiDocs {

    @Operation(summary = "회원 정보 조회", description = "회원 정보를 조회합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "프로젝트 조회 성공")
    })
    ResponseEntity<ApiSuccessResult<MemberResponse>> getMember(PipyUser user);
}
