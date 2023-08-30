package com.Salaryfy.Dto.Filter;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@ToString
public class jobSuggest {



    private List<String> postName;

    private List<String> location;



    public jobSuggest( List<String> postName, List<String> location ) {
        this.location = location;

        this.postName = postName;

    }
}
