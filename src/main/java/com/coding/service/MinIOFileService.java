package com.coding.service;

import ch.qos.logback.core.util.CloseUtil;
import com.coding.config.AppProperties;
import com.guanweiming.common.utils.StringUtil;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class MinIOFileService {

    private final AppProperties appProperties;

    public String upload(InputStream inputStream, String name) throws InvalidPortException,
            InvalidEndpointException,
            IOException,
            InvalidKeyException,
            NoSuchAlgorithmException,
            InsufficientDataException,
            InvalidResponseException,
            InternalException,
            XmlParserException,
            InvalidBucketNameException,
            ErrorResponseException,
            RegionConflictException {
        String endPoint = appProperties.getEndPoint();
        String ak = appProperties.getAk();
        String sk = appProperties.getSk();
        String bn = appProperties.getBn();
        MinioClient minioClient = new MinioClient(endPoint, ak, sk);

        // Check if the bucket already exists.
        boolean isExist = minioClient.bucketExists(bn);
        if (isExist) {
            System.out.println("Bucket already exists.");
        } else {
            // Make a new bucket called asiatrip to hold a zip file of photos.
            minioClient.makeBucket(bn);
        }
        int available = inputStream.available();
        PutObjectOptions options = new PutObjectOptions(available, 5 * 1024 * 1024);
        options.setContentType("image/jpeg");
        String suffix = StringUtil.getExtend(name);

        // 上传文件流。
        String fileName = suffix + "/" + UUID.randomUUID().toString().replace("-", "") + ".jpg";
        // Upload the zip file to the bucket with putObject
        minioClient.putObject(bn, fileName, inputStream, options);
        CloseUtil.closeQuietly(inputStream);
        return endPoint + "/" + bn + "/" + fileName;
    }
}
