package pipy.node.application;

public interface StorageManager {

    String save(StorageSaveCommand command);
    void delete(String filename);
}
