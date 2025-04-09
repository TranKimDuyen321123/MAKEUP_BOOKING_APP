import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import UserList from './pages/User/UserList';
import UserForm from './pages/User/UserForm';
import NotificationPage from './pages/Notification/NotificationPage';

const App = () => (
    <Router>
        <Routes>
            <Route path="/users" element={<UserList />} />
            <Route path="/users/new" element={<UserForm />} />
            <Route path="/users/edit/:id" element={<UserForm />} />
            <Route path="/notifications" element={<NotificationPage />} />
        </Routes>
    </Router>
);

export default App;
