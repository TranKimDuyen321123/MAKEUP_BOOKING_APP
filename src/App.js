import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AdminDashboard from './pages/AdminDashboard/AdminDashboard';
import BranchManagerDashboard from './pages/BranchManagerDashboard/BranchManagerDashboard';
// import HomePage from './pages/HomePage'; // Nếu bạn có trang chủ

function App() {
  return (
    <Router>
      <Routes>
        {/* <Route path="/" element={<HomePage />} /> */}
        <Route path="/admin/dashboard" element={<AdminDashboard />} />
        <Route path="/branch/:branchId/dashboard" element={<BranchManagerDashboard />} />
      </Routes>
    </Router>
  );
}

export default App;