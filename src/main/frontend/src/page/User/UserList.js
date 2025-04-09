import React, { useState, useEffect } from 'react';
import { getAllUsers, deleteUser } from '../../services/UserService';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function UserList() {
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getAllUsers()
            .then(response => setUsers(response.data))
            .catch(error => console.error(error));
    }, []);

    const handleDelete = (id) => {
        deleteUser(id)
            .then(() => {
                setUsers(users.filter(user => user.id !== id));
                toast.success('Xóa người dùng thành công!');
            })
            .catch(error => {
                console.error(error);
                toast.error('Xóa người dùng thất bại!');
            });
    };

    return (
        <div>
            <h2>Danh sách người dùng</h2>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên</th>
                    <th>Email</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                {users.map(user => (
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.name}</td>
                        <td>{user.email}</td>
                        <td>
                            <button onClick={() => navigate(`/users/${user.id}`)}>Xem</button>
                            <button onClick={() => navigate(`/users/edit/${user.id}`)}>Sửa</button>
                            <button onClick={() => handleDelete(user.id)}>Xóa</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <button onClick={() => navigate('/users/add')}>Thêm người dùng</button>
        </div>
    );
}

export default UserList;