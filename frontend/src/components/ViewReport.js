import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const ViewReport = () => {
    const { id } = useParams();
    const [report, setReport] = useState({});

    useEffect(() => {
        axios.get(`http://localhost:8081/api/reports/${id}`)
            .then(response => {
                setReport(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the report!', error);
            });
    }, [id]);

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">View Report</h2>
            <div className="card">
                <div className="card-header">
                    {report.name}
                </div>
                <div className="card-body">
                    <h5 className="card-title">Description</h5>
                    <p className="card-text">{report.description}</p>
                </div>
            </div>
        </div>
    );
};

export default ViewReport;
