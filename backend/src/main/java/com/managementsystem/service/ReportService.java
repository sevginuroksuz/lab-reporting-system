package com.managementsystem.service;


import com.managementsystem.entity.Report;
import com.managementsystem.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public Optional<Report> getReport(Long id) {
        return reportRepository.findById(id);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public boolean deleteReport(Long id) {
        reportRepository.deleteById(id);
        return true;
    }

    public Report updateReport(Long id, Report reportDetails) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isPresent()) {
            Report existingReport = optionalReport.get();
            existingReport.setPatientFirstName(reportDetails.getPatientFirstName());
            existingReport.setPatientLastName(reportDetails.getPatientLastName());
            existingReport.setPatientIdentityNumber(reportDetails.getPatientIdentityNumber());
            existingReport.setDiagnosisTitle(reportDetails.getDiagnosisTitle());
            existingReport.setDiagnosisDetails(reportDetails.getDiagnosisDetails());
            existingReport.setReportDate(reportDetails.getReportDate());
            existingReport.setReportImage(reportDetails.getReportImage());
            return reportRepository.save(existingReport);
        } else {
            throw new IllegalArgumentException("Report with id " + id + " not found.");
        }
    }




    public List<Report> searchReportsByPatient(String firstName, String lastName, String identityNumber) {
        try {
            return reportRepository.findByPatientFirstNameOrPatientLastNameOrPatientIdentityNumber(firstName, lastName, identityNumber);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while searching reports by patient", e);
        }
    }

    public List<Report> searchReportsByLaborant(String laborantFirstName, String laborantLastName) {
        try {
            return reportRepository.findByLaborantFullName(laborantFirstName,laborantLastName);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while searching reports by laborant", e);
        }
    }

    @Transactional
    public List<Report> searchReportsByDate(String sortField, String sortOrder) {
        try {
            return reportRepository.searchReports(null, null, null, null, null, sortField, sortOrder);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while searching reports by date", e);
        }
    }
}
