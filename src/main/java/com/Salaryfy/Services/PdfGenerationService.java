package com.Salaryfy.Services;

import com.Salaryfy.Entity.Job;
import com.Salaryfy.Exception.JobNotFoundException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Service
public class PdfGenerationService {

    @Value("classpath:/logo/image.png")
    private Resource logoImageResource;

    public byte[] generatePdf(Optional<Job> optionalJob) throws Exception {
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, baos);

            document.open();

            Image logoImage = Image.getInstance(logoImageResource.getURL());
            logoImage.scaleToFit(150, 150);
            document.add(logoImage);

            Paragraph blankRow = new Paragraph(" ");
            blankRow.setSpacingAfter(10);
            document.add(blankRow);

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);


            Paragraph companyName = new Paragraph("Company Name: " + job.getCompanyName(), font);
            companyName.setAlignment(Element.ALIGN_LEFT);

            Paragraph interviewStartDate = new Paragraph("Interview Start Date: " + job.getInterviewStartDate(), font);
            interviewStartDate.setAlignment(Element.ALIGN_LEFT);

            Paragraph interviewEndDate = new Paragraph("Interview End Date: " + job.getInterviewEndDate(), font);
            interviewEndDate.setAlignment(Element.ALIGN_LEFT);

            Paragraph jobDetails = new Paragraph("Job Details: " + job.getJobDetails(), font);
            jobDetails.setAlignment(Element.ALIGN_LEFT);

            Paragraph location = new Paragraph("Location: " + job.getLocation(), font);
            location.setAlignment(Element.ALIGN_LEFT);

            Paragraph postName = new Paragraph("Post Name: " + job.getPostName(), font);
            postName.setAlignment(Element.ALIGN_LEFT);

            Paragraph startingSalary = new Paragraph("Starting Salary: " + job.getStartingSalary(), font);
            startingSalary.setAlignment(Element.ALIGN_LEFT);

            Paragraph jobType = new Paragraph("Job Type: " + job.getJobType(), font);
            jobType.setAlignment(Element.ALIGN_LEFT);

            Paragraph Incentive = new Paragraph("Incentive : " + job.getIncentives(), font);
            Incentive.setAlignment(Element.ALIGN_LEFT);

            Paragraph EssentialRequirements = new Paragraph("EssentialRequirements : " + job.getEssentialRequirements(), font);
            EssentialRequirements.setAlignment(Element.ALIGN_LEFT);

            Paragraph noOfPosts = new Paragraph("No of Posts: " + job.getNoOfPosts(), font);
            noOfPosts.setAlignment(Element.ALIGN_LEFT);

            Paragraph InterviewLocation = new Paragraph("Interview Location : " + job.getInterviewLocation(), font);
            InterviewLocation.setAlignment(Element.ALIGN_LEFT);

            Paragraph InterviewSlot1 = new Paragraph("InterviewSlot1 : " + job.getInterviewTimeSlot1(), font);
            InterviewSlot1.setAlignment(Element.ALIGN_LEFT);

            Paragraph InterviewSlot2 = new Paragraph("InterviewSlot2 : " + job.getInterviewTimeSlot1(), font);
            InterviewSlot2.setAlignment(Element.ALIGN_LEFT);

            Paragraph InterviewDetails = new Paragraph("InterviewDetails : " + job.getInterviewDetails(), font);
            InterviewDetails.setAlignment(Element.ALIGN_LEFT);


            document.add(companyName);
            document.add(postName);
            document.add(location);
            document.add(noOfPosts);
            document.add(startingSalary);
            document.add(Incentive);
            document.add(jobType);
            document.add(EssentialRequirements);
            document.add(jobDetails);
            document.add(interviewStartDate);
            document.add(interviewEndDate);
            document.add(InterviewLocation);
            document.add(InterviewSlot1);
            document.add(InterviewSlot2);
            document.add(InterviewDetails);


            document.close();
            writer.close();

            return baos.toByteArray();
        } else {
            throw new JobNotFoundException("Job not found");
        }
    }
    }

