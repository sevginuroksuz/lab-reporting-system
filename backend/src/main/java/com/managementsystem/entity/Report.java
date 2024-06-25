package com.managementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_number")
    @NotNull
    private String fileNumber;

    @Column(name = "patient_first_name")
    @NotNull
    private String patientFirstName;

    @Column(name = "patient_last_name")
    @NotNull
    private String patientLastName;

    @Column(name = "patient_identity_number")
    @NotNull
    private String patientIdentityNumber;

    @Column(name = "diagnosis_title")
    @NotNull
    private String diagnosisTitle;

    @Column(name = "diagnosis_details")
    @NotNull
    private String diagnosisDetails;

    @Column(name = "report_date")
    @NotNull
    private LocalDate reportDate;

    @Lob
    private byte[] reportImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "laborant_id")
    private Laborant laborant;

    // Getters ve Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientIdentityNumber() {
        return patientIdentityNumber;
    }

    public void setPatientIdentityNumber(String patientIdentityNumber) {
        this.patientIdentityNumber = patientIdentityNumber;
    }

    public String getDiagnosisTitle() {
        return diagnosisTitle;
    }

    public void setDiagnosisTitle(String diagnosisTitle) {
        this.diagnosisTitle = diagnosisTitle;
    }

    public String getDiagnosisDetails() {
        return diagnosisDetails;
    }

    public void setDiagnosisDetails(String diagnosisDetails) {
        this.diagnosisDetails = diagnosisDetails;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public byte[] getReportImage() {
        return reportImage;
    }

    public void setReportImage(byte[] reportImage) {
        this.reportImage = reportImage;
    }

}
