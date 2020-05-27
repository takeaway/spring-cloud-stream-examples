package com.takeaway.movies.status.describing;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "function.description")
public class DescribeFunctionsProperties {
    String[] batmanTitles;
}
