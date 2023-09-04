package com.Salaryfy.Controller;

import com.Salaryfy.Dto.EduSuggest.EduSuggestDto;
import com.Salaryfy.Interfaces.EduSuggest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("ESuggest")
public class EduSuggetionController {

    private final EduSuggest eduSuggest;

    @GetMapping("/get-suggestions")
    public List<EduSuggestDto> getSuggestions(@RequestParam String userInput) {
        return eduSuggest.getSuggestions(userInput);

    }


}
