import React, { useState } from 'react';
import axios from 'axios';

const ReportList = () => {
    const [reports, setReports] = useState([]);
    const [patientSearchCriteria, setPatientSearchCriteria] = useState({
        firstName: '',
        lastName: '',
        identityNumber: ''
    });
    const [laborantSearchCriteria, setLaborantSearchCriteria] = useState({
        laborantFirstName: '',
        laborantLastName: ''
    });
    const [dateSearchCriteria, setDateSearchCriteria] = useState({
        sortField: 'date',
        sortOrder: 'asc'
    });

    const handlePatientInputChange = (e) => {
        const { name, value } = e.target;
        setPatientSearchCriteria(prevCriteria => ({
            ...prevCriteria,
            [name]: value
        }));
    };

    const handleLaborantInputChange = (e) => {
        const { name, value } = e.target;
        setLaborantSearchCriteria(prevCriteria => ({
            ...prevCriteria,
            [name]: value
        }));
    };

    const handleDateInputChange = (e) => {
        const { name, value } = e.target;
        setDateSearchCriteria(prevCriteria => ({
            ...prevCriteria,
            [name]: value
        }));
    };

    const searchByPatient = () => {
        axios.get('http://localhost:8081/api/reports/search-by-patient', { params: patientSearchCriteria })
            .then(response => {
                setReports(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching the reports by patient!", error);
            });
    };

    const searchByLaborant = () => {
        axios.get('http://localhost:8081/api/reports/search-by-laborant', { params: laborantSearchCriteria })
            .then(response => {
                setReports(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching the reports by laborant!", error);
            });
    };

    const searchByDate = () => {
        axios.get('http://localhost:8081/api/reports/search-by-date', { params: dateSearchCriteria })
            .then(response => {
                setReports(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching the reports by date!", error);
            });
    };

    return (
        <div>
            <h2 className="text-center mb-4">Reports</h2>
            <div className="card mb-4">
                <div className="card-header">Search by Patient</div>
                <div className="card-body">
                    <div className="form-group">
                        <label>First Name:</label>
                        <input
                            type="text"
                            name="firstName"
                            value={patientSearchCriteria.firstName}
                            onChange={handlePatientInputChange}
                            className="form-control"
                        />
                    </div>
                    <div className="form-group">
                        <label>Last Name:</label>
                        <input
                            type="text"
                            name="lastName"
                            value={patientSearchCriteria.lastName}
                            onChange={handlePatientInputChange}
                            className="form-control"
                        />
                    </div>
                    <div className="form-group">
                        <label>Identity Number:</label>
                        <input
                            type="text"
                            name="identityNumber"
                            value={patientSearchCriteria.identityNumber}
                            onChange={handlePatientInputChange}
                            className="form-control"
                        />
                    </div>
                    <button onClick={searchByPatient} className="btn btn-primary mt-3">Search</button>
                </div>
            </div>
            <div className="card mb-4">
                <div className="card-header">Search by Laborant</div>
                <div className="card-body">
                    <div className="form-group">
                        <label>Laborant First Name:</label>
                        <input
                            type="text"
                            name="laborantFirstName"
                            value={laborantSearchCriteria.laborantFirstName}
                            onChange={handleLaborantInputChange}
                            className="form-control"
                        />
                    </div>
                    <div className="form-group">
                        <label>Laborant Last Name:</label>
                        <input
                            type="text"
                            name="laborantLastName"
                            value={laborantSearchCriteria.laborantLastName}
                            onChange={handleLaborantInputChange}
                            className="form-control"
                        />
                    </div>
                    <button onClick={searchByLaborant} className="btn btn-primary mt-3">Search</button>
                </div>
            </div>
            <div className="card mb-4">
                <div className="card-header">Search by Date</div>
                <div className="card-body">
                    <div className="form-group">
                        <label>Sort Field:</label>
                        <input
                            type="text"
                            name="sortField"
                            value={dateSearchCriteria.sortField}
                            onChange={handleDateInputChange}
                            className="form-control"
                        />
                    </div>
                    <div className="form-group">
                        <label>Sort Order:</label>
                        <input
                            type="text"
                            name="sortOrder"
                            value={dateSearchCriteria.sortOrder}
                            onChange={handleDateInputChange}
                            className="form-control"
                        />
                    </div>
                    <button onClick={searchByDate} className="btn btn-primary mt-3">Search</button>
                </div>
            </div>
            <ul className="list-group">
                {reports.map(report => (
                    <li key={report.id} className="list-group-item">
                        {report.name}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ReportList;
