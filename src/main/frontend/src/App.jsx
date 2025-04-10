import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import UserList from "./pages/users/UserList";
import UserForm from "./pages/users/UserForm";
import BookingHistory from "./pages/appointments/BookingHistory";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Navigate to="/users" />} />

                <Route path="/users" element={<UserList />} />
                <Route path="/users/new" element={<UserForm />} />
                <Route path="/users/edit/:id" element={<UserForm />} />
                <Route path="/users/:userId/history" element={<BookingHistory />} />
            </Routes>
        </Router>
    );
}

export default App;
