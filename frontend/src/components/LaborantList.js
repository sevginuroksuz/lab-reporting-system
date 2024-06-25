import React, { useEffect, useState } from 'react';
import axios from 'axios';

const LaborantList = () => {
    const [laborants, setLaborants] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8081/api/laborants')
            .then(response => {
                setLaborants(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the laborants!', error);
            });
    }, []);

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Laborant List</h2>
            <table className="table table-hover table-bordered">
                <thead className="thead-dark">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Hospital ID</th>
                </tr>
                </thead>
                <tbody>
                {laborants.map((laborant, index) => (
                    <tr key={index}>
                        <td>{laborant.laborantFirstName}</td>
                        <td>{laborant.laborantLastName}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default LaborantList;
