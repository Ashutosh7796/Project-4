package com.Salaryfy.Dto.EduSuggest;

import com.Salaryfy.Dto.Job.JobDto;
import lombok.Data;

import java.util.List;

@Data
public class ResponseEducationDto {

        private String message;
        private List<EduSuggestDto> list;
        private String exception;

        public ResponseEducationDto(String message) {
            this.message = message;
        }
    }



