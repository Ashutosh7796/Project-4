package com.Salaryfy.Dto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@ToString
public class FilterDto {

    private String companyName;

    private String postName;

    private List<String> location;



    public FilterDto(String companyName, String postName, List<String> location ) {
        this.location = location;

        this.postName = postName;

        this.companyName = companyName;
    }
}
