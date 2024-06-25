package com.managementsystem.repository;

import com.managementsystem.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByPatientFirstNameOrPatientLastNameOrPatientIdentityNumber(String firstName, String lastName, String identityNumber);



    @Query("""
       SELECT r FROM Report r\s
       WHERE (:laborantFirstName IS NULL OR r.laborant.firstName LIKE %:laborantFirstName%)\s
       AND (:laborantLastName IS NULL OR r.laborant.lastName LIKE %:laborantLastName%)
       """)
    List<Report> findByLaborantFullName(@Param("laborantFirstName") String laborantFirstName, @Param("laborantLastName") String laborantLastName);

    @Query("""
       SELECT r FROM Report r
       WHERE (:patientFirstName IS NULL OR r.patientFirstName LIKE %:patientFirstName%)
       AND (:patientLastName IS NULL OR r.patientLastName LIKE %:patientLastName%)
       AND (:patientIdentityNumber IS NULL OR r.patientIdentityNumber LIKE %:patientIdentityNumber%)
       AND (:laborantFirstName IS NULL OR r.laborant.firstName LIKE %:laborantFirstName%)
       AND (:laborantLastName IS NULL OR r.laborant.lastName LIKE %:laborantLastName%)
       ORDER BY
           CASE WHEN :sortField = 'reportDate' AND :sortOrder = 'asc' THEN r.reportDate END ASC NULLS FIRST,
           CASE WHEN :sortField = 'reportDate' AND :sortOrder = 'desc' THEN r.reportDate END DESC NULLS LAST
       """)
    List<Report> searchReports(@Param("patientFirstName") String patientFirstName,
                               @Param("patientLastName") String patientLastName,
                               @Param("patientIdentityNumber") String patientIdentityNumber,
                               @Param("laborantFirstName") String laborantFirstName,
                               @Param("laborantLastName") String laborantLastName,
                               @Param("sortField") String sortField,
                               @Param("sortOrder") String sortOrder);




}
