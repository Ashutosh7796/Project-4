package com.Salaryfy.Controller;

import com.Salaryfy.Dto.Job.ResponseJobDto;
import com.Salaryfy.Entity.Job;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Interfaces.JobService;
import com.Salaryfy.Services.PdfGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pdf")
public class PdfController {

    private final PdfGenerationService pdfGenerationService;

    private final JobService jobService;

    @GetMapping("/generate/{jobId}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Integer jobId) {
        try {

            Optional<Job> job = jobService.findjobById(jobId);

            byte[] pdfData = pdfGenerationService.generatePdf(job);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "job_details.pdf");
            headers.setContentLength(pdfData.length);
            return ResponseEntity.ok().headers(headers).body(pdfData);
        } catch (JobNotFoundException e) {
            ResponseJobDto responseJobDto = new ResponseJobDto("Unsuccess");
            responseJobDto.setException("Job Not Found With Id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}



