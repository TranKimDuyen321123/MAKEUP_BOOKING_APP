import React, { useState, useEffect } from 'react';
import { createUser, updateUser, getUserById } from '../../services/UserService';
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function UserForm() {
    const [user, setUser] = useState({ name: '', email: '' });
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) {
            getUserById(id)
                .then(response => setUser(response.data))
                .catch(error => console.error(error));
        }
    }, [id]);

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (id) {
            updateUser(id, user)
                .then(() => {
                    toast.success('Cập nhật người dùng thành công!');
                    navigate('/users');
                })
                .catch(error => {
                    console.error(error);
                    toast.error('Cập nhật người dùng thất bại!');
                });
        } else {
            createUser(user)
                .then(() => {
                    toast.success('Thêm người dùng thành công!');
                    navigate('/users');
                })
                .catch(error => {
                    console.error(error);
                    toast.error('Thêm người dùng thất bại!');
                });
        }
    };

    return (
        <div>
            <h2>{id ? 'Sửa người dùng' : 'Thêm người dùng'}</h2>
            <form onSubmit={handleSubmit}>
                <label>Tên:</label>
                <input type="text" name="name" value={user.name} onChange={handleChange} />
                <label>Email:</label>
                <input type="email" name="email" value={user.email} onChange={handleChange} />
                <button type="submit">{id ? 'Cập nhật' : 'Thêm'}</button>
            </form>
        </div>
    );
}

export default UserForm;