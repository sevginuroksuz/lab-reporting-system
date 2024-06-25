import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

const EditReport = () => {
    const { id } = useParams();
    const [report, setReport] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`http://localhost:8081/api/reports/${id}`)
            .then(response => {
                setReport(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the report!', error);
            });
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setReport({ ...report, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.put(`http://localhost:8081/api/reports/update/${id}`, report)
            .then(() => {
                navigate('/');
            })
            .catch(error => {
                console.error('There was an error updating the report!', error);
            });
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Edit Report</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Report Name</label>
                    <input
                        type="text"
                        name="name"
                        value={report.name || ''}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <div className="form-group">
                    <label>Description</label>
                    <textarea
                        name="description"
                        value={report.description || ''}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <button type="submit" className="btn btn-primary mt-3">Update</button>
            </form>
        </div>
    );
};

export default EditReport;
