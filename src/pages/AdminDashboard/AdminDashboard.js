import React, { useState, useEffect } from 'react';
import './AdminDashboard.css'; // Import file CSS (tùy chọn)

function AdminDashboard() {
  const [dashboardData, setDashboardData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('/api/admin/dashboard-data')
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
  }, []);

  if (loading) {
    return <div>Loading dashboard data...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (!dashboardData) {
    return <div>No dashboard data available.</div>;
  }

  return (
    <div className="admin-dashboard-container">
      <h1>Admin Dashboard</h1>
      <div className="data-grid">
        <div className="data-item">
          <p className="label">Total Users:</p>
          <p className="value">{dashboardData.totalUsers}</p>
        </div>
        <div className="data-item">
          <p className="label">New Bookings:</p>
          <p className="value">{dashboardData.newBookings}</p>
        </div>
        <div className="data-item">
          <p className="label">Completed Bookings:</p>
          <p className="value">{dashboardData.completedBookings}</p>
        </div>
        <div className="data-item">
          <p className="label">Cancelled Bookings:</p>
          <p className="value">{dashboardData.cancelledBookings}</p>
        </div>
        {/* Thêm các mục dữ liệu khác nếu cần */}
      </div>
    </div>
  );
}

export default AdminDashboard;
