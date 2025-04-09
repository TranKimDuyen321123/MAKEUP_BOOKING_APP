import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getUserById, createUser, updateUser } from '../../services/UserService';

const UserForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [user, setUser] = useState({ name: '', email: '', phone: '', password: '' });

  useEffect(() => {
    if (id) {
      getUserById(id).then(setUser);
    }
  }, [id]);

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (id) {
      await updateUser(id, user);
    } else {
      await createUser(user);
    }
    navigate('/users');
  };

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">{id ? 'Cập nhật' : 'Thêm'} người dùng</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input name="name" placeholder="Tên" value={user.name} onChange={handleChange} className="border p-2 w-full" />
        <input name="email" placeholder="Email" value={user.email} onChange={handleChange} className="border p-2 w-full" />
        <input name="phone" placeholder="Số điện thoại" value={user.phone} onChange={handleChange} className="border p-2 w-full" />
        {!id && <input name="password" placeholder="Mật khẩu" type="password" value={user.password} onChange={handleChange} className="border p-2 w-full" />}
        <button type="submit" className="bg-green-500 text-white px-4 py-2 rounded">{id ? 'Cập nhật' : 'Tạo'}</button>
      </form>
    </div>
  );
};

export default UserForm;