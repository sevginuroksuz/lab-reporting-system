package com.managementsystem.controller;

import com.managementsystem.entity.Report;
import com.managementsystem.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<String> createReport(@RequestBody Report report) {
        if (report == null) {
            return ResponseEntity.badRequest().build();
        }

        Report createdReport = reportService.createReport(report);

        if (createdReport != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Report has been created successfully with ID: " + createdReport.getId());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create report.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReport(@PathVariable Long id) {
        Optional<Report> report = reportService.getReport(id);
        return report.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report reportDetails) {
        if (reportDetails == null) {
            return ResponseEntity.badRequest().build();
        }

        Report updatedReport = reportService.updateReport(id, reportDetails);
        return new ResponseEntity<>(updatedReport, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReport(@PathVariable Long id) {
        boolean deleted = reportService.deleteReport(id);
        if (deleted) {
            return ResponseEntity.ok("Report with ID " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //search
    @GetMapping("/search-by-patient")
    public ResponseEntity<List<Report>> searchByPatient(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String identityNumber) {
        List<Report> reports = reportService.searchReportsByPatient(firstName, lastName, identityNumber);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/search-by-laborant")
    public ResponseEntity<List<Report>> searchByLaborant(
            @RequestParam(required = false) String laborantFirstName,
            @RequestParam(required = false) String laborantLastName) {
        List<Report> reports = reportService.searchReportsByLaborant(laborantFirstName, laborantLastName);
        return ResponseEntity.ok(reports);
    }


    @GetMapping("/search-by-date")
    public ResponseEntity<List<Report>> searchByDate(
            @RequestParam String sortField,
            @RequestParam String sortOrder) {
        List<Report> reports = reportService.searchReportsByDate(sortField, sortOrder);
        return ResponseEntity.ok(reports);
    }


}
