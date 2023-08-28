package com.Salaryfy.Dto.Filter;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@ToString
public class jobSuggest {



    private List<String> jobType;

    private List<String> location;



    public jobSuggest( List<String> jobType, List<String> location ) {
        this.location = location;

        this.jobType = jobType;

    }
}
