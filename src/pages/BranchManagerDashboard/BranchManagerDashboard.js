import React, { useState, useEffect, useRef } from 'react';
import { useParams } from 'react-router-dom';
import { Chart } from 'chart.js/auto';
import './BranchManagerDashboard.css'; // Import CSS

function BranchManagerDashboard() {
    const { branchId } = useParams();
    const [dashboardData, setDashboardData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const chartRef = useRef(null);

    useEffect(() => {
        if (branchId) {
            fetch(`http://localhost:8080/api/branch/${branchId}/dashboard-data`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    setDashboardData(data);
                    setLoading(false);
                })
                .catch(error => {
                    setError(error);
                    setLoading(false);
                });
        } else {
            setError(new Error("Branch ID is missing in the URL."));
            setLoading(false);
        }
    }, [branchId]);

    useEffect(() => {
        if (dashboardData && dashboardData.serviceStats && chartRef.current) {
            // ... (Code vẽ biểu đồ như bạn đã có)
        }
    }, [dashboardData]);

    if (loading) {
        return <div>Loading branch dashboard data...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    if (!dashboardData) {
        return <div>No branch dashboard data available.</div>;
    }

    return (
        <div className="branch-manager-dashboard">
            <h1>Branch Manager Dashboard</h1>
            <div className="branch-info">
                <h3>Branch Name: {dashboardData.branchName}</h3>
                <p>Branch ID: {branchId}</p>
            </div>

            <div className="data-summary">
                <div className="data-item">
                    <p className="label">Bookings Today:</p>
                    <p className="value">{dashboardData.bookingsToday}</p>
                </div>
                <div className="data-item">
                    <p className="label">Today's Revenue:</p>
                    <p className="value">${dashboardData.todaysRevenue}</p>
                </div>
                <div className="data-item">
                  <p className="label">Pending Appointments:</p>
                  <p className="value">{dashboardData.pendingAppointments}</p>
                </div>
            </div>
            <div className="chart-container">
              <canvas ref={chartRef} width={400} height={200}></canvas>
            </div>
            {/* Các thông tin và component khác */}
        </div>
    );
}

export default BranchManagerDashboard;
