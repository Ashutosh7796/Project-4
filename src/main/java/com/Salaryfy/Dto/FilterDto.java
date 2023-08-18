package com.Salaryfy.Dto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@ToString
public class FilterDto {

    private String companyName;

    private String jobType;

    private List<String> location;



    public FilterDto(String companyName, String jobType, List<String> location ) {
        this.location = location;

        this.jobType = jobType;

        this.companyName = companyName;
    }
}
