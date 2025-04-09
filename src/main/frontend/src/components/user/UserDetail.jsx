import { useEffect, useState } from "react";
import { getUserById } from "../../services/userService";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

const UserDetail = () => {
    const { id } = useParams();
    const [user, setUser] = useState(null);
    const [bookings, setBookings] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getUserById(id).then(res => setUser(res.data));
        axios.get(`http://localhost:8080/api/users/${id}/bookings`)
            .then(res => setBookings(res.data))
            .catch(() => setBookings([]));
    }, [id]);

    if (!user) return <div>Đang tải...</div>;

    return (
        <div className="p-4">
            <button onClick={() => navigate("/users")} className="text-blue-500 mb-4">&larr; Quay lại</button>
            <h2 className="text-2xl mb-2">Thông tin khách hàng</h2>
            <p><strong>Tên:</strong> {user.name}</p>
            <p><strong>Email:</strong> {user.email}</p>
            <p><strong>SĐT:</strong> {user.phone}</p>
            <p><strong>Vai trò:</strong> {user.role}</p>

            <h3 className="text-xl mt-6 mb-2">Lịch sử đặt lịch</h3>
            {bookings.length === 0 ? (
                <p>Không có lịch sử đặt lịch</p>
            ) : (
                <table className="w-full border table-auto">
                    <thead className="bg-gray-100">
                    <tr>
                        <th className="border px-4 py-2">Ngày</th>
                        <th className="border px-4 py-2">Dịch vụ</th>
                        <th className="border px-4 py-2">Trạng thái</th>
                    </tr>
                    </thead>
                    <tbody>
                    {bookings.map(b => (
                        <tr key={b.id}>
                            <td className="border px-4 py-2">{b.date}</td>
                            <td className="border px-4 py-2">{b.serviceName}</td>
                            <td className="border px-4 py-2">{b.status}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default UserDetail;
