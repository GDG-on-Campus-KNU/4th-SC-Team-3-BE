package pipy.node.application;

public record ImageSaveCommand(
    String contentType,
    String filename,
    byte[] bytes
) {
}
