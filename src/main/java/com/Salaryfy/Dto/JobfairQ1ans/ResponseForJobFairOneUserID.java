package com.Salaryfy.Dto.JobfairQ1ans;

import com.Salaryfy.Entity.JobfairQ1ans;
import com.Salaryfy.Entity.JobfairQue;
import lombok.Data;

import java.util.List;
@Data
public class ResponseForJobFairOneUserID {

        private String status;
        private Integer totalItems;
        private JobfairQ1ans response;
        private Integer totalPages;
        private Integer currentPage;
        private String exception;

    public ResponseForJobFairOneUserID(String status) {
        this.status = status;
    }
}
