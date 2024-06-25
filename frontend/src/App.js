import React from 'react';
import { Routes, Route } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import ReportList from './components/ReportList';
import EditReport from './components/EditReport';
import ViewReport from './components/ViewReport';
import LaborantList from './components/LaborantList';

function App() {
    return (
        <div className="container mt-5">
            <Routes>
                <Route path="/" element={<ReportList />} />
                <Route path="/edit-report/:id" element={<EditReport />} />
                <Route path="/view-report/:id" element={<ViewReport />} />
                <Route path="/laborants" element={<LaborantList />} />
            </Routes>
        </div>
    );
}

export default App;
