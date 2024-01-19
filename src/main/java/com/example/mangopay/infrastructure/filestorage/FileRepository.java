package com.example.mangopay.infrastructure.filestorage;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

public interface FileRepository {

    String save(MultipartFile file);

    Optional<File> getByPath(String path);

}
