import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const CreateReport = () => {
    const [report, setReport] = useState({
        name: '',
        description: '',
        patientFirstName: '',
        patientLastName: '',
        identityNumber: '',
        laborantFirstName: '',
        laborantLastName: ''
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setReport({ ...report, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8081/api/reports', report)
            .then(() => {
                navigate('/');
            })
            .catch(error => {
                console.error('There was an error creating the report!', error);
            });
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Create Report</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Report Name</label>
                    <input
                        type="text"
                        name="name"
                        value={report.name}
                        onChange={handleChange}
                        className="form-control"
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Description</label>
                    <textarea
                        name="description"
                        value={report.description}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <div className="form-group">
                    <label>Patient First Name</label>
                    <input
                        type="text"
                        name="patientFirstName"
                        value={report.patientFirstName}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <div className="form-group">
                    <label>Patient Last Name</label>
                    <input
                        type="text"
                        name="patientLastName"
                        value={report.patientLastName}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <div className="form-group">
                    <label>Identity Number</label>
                    <input
                        type="text"
                        name="identityNumber"
                        value={report.identityNumber}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <div className="form-group">
                    <label>Laborant First Name</label>
                    <input
                        type="text"
                        name="laborantFirstName"
                        value={report.laborantFirstName}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <div className="form-group">
                    <label>Laborant Last Name</label>
                    <input
                        type="text"
                        name="laborantLastName"
                        value={report.laborantLastName}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <button type="submit" className="btn btn-primary mt-3">Create</button>
            </form>
        </div>
    );
};

export default CreateReport;
