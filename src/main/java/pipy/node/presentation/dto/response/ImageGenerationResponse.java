package pipy.node.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사진 생성 결과 응답")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ImageGenerationResponse(
    @Schema(description = "이벤트") String event,
    @Schema(description = "데이터") ImageUrlResponse data,
    @Schema(description = "에러 메시지") String message
) {

    public static ImageGenerationResponse start() {
        return new ImageGenerationResponse("generate_image_start", null, null);
    }

    public static ImageGenerationResponse end() {
        return new ImageGenerationResponse("generate_image_end", null, null);
    }

    public static ImageGenerationResponse generated(final String url) {
        return new ImageGenerationResponse("generated_image", new ImageUrlResponse(url), null);
    }

    public static ImageGenerationResponse error(final String message) {
        return new ImageGenerationResponse("error", null, message);
    }
}

record ImageUrlResponse(
    @Schema(description = "이미지 URL") String url
) {
}