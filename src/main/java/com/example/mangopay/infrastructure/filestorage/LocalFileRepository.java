package com.example.mangopay.infrastructure.filestorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.Optional;

@Component
public class LocalFileRepository implements FileRepository {

    private static Logger logger = LoggerFactory.getLogger(LocalFileRepository.class);

    private final String PATH = System.getProperty("user.dir") + "/mangopay/files/";

    @Override
    public String save(MultipartFile file) {
        try {
            String path = PATH + file.getOriginalFilename();
            logger.info("Saving file to {}", path);
            var localFile = new File(path);
            createNewFile(localFile);

            file.transferTo(localFile);
            return path;
        } catch (IOException e) {
            throw new FileCreationFailedException(e);
        }
    }

    private void createNewFile(File localFile) throws IOException {
        if(!localFile.exists()) {
            Files.createDirectories(localFile.getParentFile().toPath());
            localFile.createNewFile();
        }
        else {
            logger.error("File with path {} already exists", localFile.getAbsolutePath());
            throw new FileCreationFailedException("File already exists");
        }
    }

    @Override
    public Optional<File> getByPath(String path) {
        logger.info("Getting file by path {}", path);
        var file = new File(path);
        if(!file.exists()) {
            logger.debug("File with path {} does not exist", path);
            return Optional.empty();
        }
        return Optional.of(new File(path));
    }
}
