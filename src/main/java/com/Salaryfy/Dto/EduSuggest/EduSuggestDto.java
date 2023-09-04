package com.Salaryfy.Dto.EduSuggest;


import com.Salaryfy.Entity.EduSuggestion;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class EduSuggestDto {

    private String board_University;

    private String Education;

    public EduSuggestDto(EduSuggestion eduSuggestion) {
        this.board_University = eduSuggestion.getBoardUniversity();
        this.Education = eduSuggestion.getEducation();
    }
}
