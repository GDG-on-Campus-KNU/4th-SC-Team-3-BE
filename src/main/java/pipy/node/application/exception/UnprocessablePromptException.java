package pipy.node.application.exception;

import pipy.global.PipyException;

public class UnprocessablePromptException extends PipyException {

    private static final String ERROR_MESSAGE = "프롬프트가 부족해 처리할 수 없습니다. 조금 더 자세히 설명해 주세요.";

    public UnprocessablePromptException() {
        super(ERROR_MESSAGE);
    }
}
