package pipy.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pipy.auth.application.PipyUser;
import pipy.global.ApiSuccessResponse;
import pipy.member.presentation.dto.MemberResponse;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberQueryController implements MemberQueryApiDocs {

    @GetMapping
    public ResponseEntity<ApiSuccessResult<MemberResponse>> getMember(
        @AuthenticationPrincipal final PipyUser user
    ) {
        final MemberResponse response = MemberResponse.from(user.getMember());
        return ApiSuccessResponse.success(HttpStatus.OK, response);
    }
}
