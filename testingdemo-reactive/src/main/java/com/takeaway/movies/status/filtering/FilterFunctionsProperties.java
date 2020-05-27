package com.takeaway.movies.status.filtering;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "function.filter")
class FilterFunctionsProperties {

    int rating;
}
