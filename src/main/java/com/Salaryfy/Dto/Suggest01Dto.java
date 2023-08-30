package com.Salaryfy.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@NoArgsConstructor
@Builder
@ToString
public class Suggest01Dto {


    private List<String> postName;

    private List<String> location;



    public Suggest01Dto(List<String> jobType, List<String> location ) {
        this.location = location;

        this.postName = postName;


    }

}
