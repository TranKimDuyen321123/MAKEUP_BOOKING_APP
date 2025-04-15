import React, { useState, useEffect, useRef } from 'react';
import { useParams } from 'react-router-dom';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Tooltip, Legend } from 'chart.js';
import { Bar } from 'react-chartjs-2';
import './BranchManagerDashboard.css'; // Import CSS

ChartJS.register(CategoryScale, LinearScale, BarElement, Tooltip, Legend);

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

    if (loading) {
        return <div>Loading branch dashboard data...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    if (!dashboardData) {
        return <div>No branch dashboard data available.</div>;
    }

    const chartData = {
        labels: dashboardData.topServices?.map(s => s.serviceName) || [],
        datasets: [
            {
                label: 'Lượt đặt dịch vụ',
                data: dashboardData.topServices?.map(s => s.appointmentCount) || [],
                backgroundColor: 'rgba(75, 192, 192, 0.6)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1,
            }
        ]
    };

    const chartOptions = {
        responsive: true,
        plugins: {
            legend: { position: 'top' },
            title: { display: true, text: 'Top Dịch Vụ Được Đặt Nhiều Nhất' },
        },
    };

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

            {dashboardData?.topServices?.length > 0 && (
                <div className="chart-container">
                    <h3>Dịch vụ được đặt nhiều nhất</h3>
                    <Bar data={chartData} options={chartOptions} />
                </div>
            )}

            {/* Các thông tin và component khác */}
        </div>
    );
}

export default BranchManagerDashboard;
