package com.Salaryfy.Dto;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@ToString
public class FilterDto {
    private String location;
    private String postName;
    private String companyName;

    public FilterDto(String location, String postName, String companyName) {
        this.location = location;
        this.postName = postName;
        this.companyName = companyName;
    }
}
