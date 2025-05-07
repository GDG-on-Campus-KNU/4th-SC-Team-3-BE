package pipy.node.application.exception;

import pipy.global.PipyException;

public class ImageGenerationFailedException extends PipyException {

    private static final String ERROR_MESSAGE = "사진 생성 시 오류가 발생했습니다.";

    public ImageGenerationFailedException() {
        super(ERROR_MESSAGE);
    }
}
