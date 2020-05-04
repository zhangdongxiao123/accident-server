package com.coding.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties("app")
public class AppProperties {
    /**
     * appId
     */
    private String title;

    private String endPoint = "http://117.159.17.27:10308";
    private String ak = "admin";
    private String sk = "19941108Aa";
    private String bn = "avatar";
}
