package pipy.node.application.exception;

import pipy.global.PipyException;

public class NodeAnalyzeFailedException extends PipyException {

    private static final String ERROR_MESSAGE = "카테고리 분석 시 오류가 발생했습니다.";

    public NodeAnalyzeFailedException() {
        super(ERROR_MESSAGE);
    }
}
