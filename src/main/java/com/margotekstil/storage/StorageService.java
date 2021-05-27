package com.margotekstil.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import org.springframework.core.io.FileSystemResource;

public interface StorageService {

    /** 
     * @param file uploaded file
     * @param clientId so that file can be stored in it's subdirectory
     * @return name of the stored file
     */
    String store(MultipartFile file, Integer id);
    String storeDOC(MultipartFile file, Integer id);
    Path load(Integer id, String filename);

    Resource loadAsResource(Integer id, String filename);
FileSystemResource loadAsFSResource(Integer id, String filename);
    void delete(Integer id, String filename);

}
