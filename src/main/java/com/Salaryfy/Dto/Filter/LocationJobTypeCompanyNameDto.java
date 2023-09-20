package com.Salaryfy.Dto.Filter;

import lombok.Data;

import java.util.Set;
@Data
public class LocationJobTypeCompanyNameDto {
    public Set<String> locations;
    public Set<String> companyNames;
    public Set<String> jobTypes;

}
