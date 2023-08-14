package com.Salaryfy.Dto.Plan;
import lombok.Data;
import java.util.List;
@Data
public class ResponseGetAllPlanDto {

    private String message;
    private List<PlanDto> list;
    private String exception;

    public ResponseGetAllPlanDto(String message) {
        this.message = message;
    }
}
