package com.Salaryfy.Dto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@ToString
public class FilterDto {

    private List<String> companyName;

    private List<String> jobType;

    private List<String> location;



    public FilterDto(List<String> companyName, List<String> jobType, List<String> location ) {
        this.location = location;

        this.jobType = jobType;

        this.companyName = companyName;
    }
}
