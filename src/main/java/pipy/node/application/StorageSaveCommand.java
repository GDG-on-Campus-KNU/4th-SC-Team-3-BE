package pipy.node.application;

public record StorageSaveCommand(
    String contentType,
    String filename,
    byte[] bytes
) {
}
