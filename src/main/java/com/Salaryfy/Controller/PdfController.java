package com.Salaryfy.Controller;

import com.Salaryfy.Dto.Job.ResponseGetAllJobDto;
import com.Salaryfy.Dto.Job.ResponseJobDto;
import com.Salaryfy.Entity.Job;
import com.Salaryfy.Exception.JobNotFoundException;
import com.Salaryfy.Interfaces.JobService;
import com.Salaryfy.Services.PdfGenerationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pdf")
public class PdfController {

    private final PdfGenerationService pdfGenerationService;

    private final JobService jobService;

    @GetMapping("/generate/{jobId}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Integer jobId) throws IOException {
        try {
            Optional<Job> job = jobService.findjobById(jobId);

            byte[] pdfData = pdfGenerationService.generatePdf(job);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "job_details.pdf");
            headers.setContentLength(pdfData.length);
            return ResponseEntity.ok().headers(headers).body(pdfData);
        } catch (JobNotFoundException e) {

            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("Unsuccess");

            responseGetAllJobDto.setException("Job Not Found");

            byte[] customResponseBytes = serializeResponse(responseGetAllJobDto);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentLength(customResponseBytes.length);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(customResponseBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    private byte[] serializeResponse(Object responseObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(responseObject);
    }

}



