package com.Salaryfy.Services;

import com.Salaryfy.Entity.Job;
import com.Salaryfy.Exception.JobNotFoundException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
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


            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.getDefaultCell().setBorder(PdfPCell.BOX);
            table.setSpacingAfter(10f);

            table.addCell(createCell("Company Name:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getCompanyName(), font, Element.ALIGN_LEFT));

            table.addCell(createCell("Post Name:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getPostName(), font, Element.ALIGN_LEFT));

            table.addCell(createCell("Location:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getLocation(), font, Element.ALIGN_LEFT));

            table.addCell(createCell("No of Posts:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getNoOfPosts().toString(), font, Element.ALIGN_LEFT));

            table.addCell(createCell("Starting Salary:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getStartingSalary(), font, Element.ALIGN_LEFT));

            table.addCell(createCell("Incentive:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getIncentives(), font, Element.ALIGN_LEFT));

            table.addCell(createCell("Job Type:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getJobType(), font, Element.ALIGN_LEFT));

            table.addCell(createCell("Essential Requirements:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getEssentialRequirements(), font, Element.ALIGN_LEFT));

            table.addCell(createCell("Job Details:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getJobDetails(), font, Element.ALIGN_LEFT));

            table.addCell(createCell("Interview Start Date:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getInterviewStartDate().toString(), font, Element.ALIGN_LEFT));

            table.addCell(createCell("Interview End Date:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getInterviewEndDate().toString(), font, Element.ALIGN_LEFT));

            table.addCell(createCell("Interview Location:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(String.valueOf(job.getInterviewLocation()), font, Element.ALIGN_LEFT));

            table.addCell(createCell("Interview Slot 1:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getInterviewTimeSlot1Min()+":00"+" To "+job.getInterviewTimeSlot1Max()+":00", font, Element.ALIGN_LEFT));

            table.addCell(createCell("Interview Slot 2:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getInterviewTimeSlot2Min()+":00"+" To "+job.getInterviewTimeSlot2Max()+":00", font, Element.ALIGN_LEFT));

            table.addCell(createCell("Interview Details:", font, Element.ALIGN_LEFT));
            table.addCell(createCell(job.getInterviewDetails(), font, Element.ALIGN_LEFT));


            document.add(table);

            document.close();
            writer.close();

            return baos.toByteArray();
        } else {
            throw new JobNotFoundException("Job not found");
        }
    }

    private PdfPCell createCell(String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(7f);
        return cell;
    }
}