import React, { useEffect, useState } from "react";
import {
    getAllUsers,
    getUserById,
    createUser,
    updateUser,
    deleteUser,
} from "../services/UserService";

function UsersPage() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            const response = await getAllUsers();
            setUsers(response.data);
        } catch (error) {
            console.error("Error fetching users:", error);
        }
    };

    return (
        <div style={{ padding: "2rem", fontFamily: "Arial" }}>
            <h1>Quản Lý Khách Hàng</h1>
            <ul>
                {users.map((user) => (
                    <li key={user.id}>{user.name} - {user.email}</li>
                ))}
            </ul>
        </div>
    );
}

export default UsersPage;
