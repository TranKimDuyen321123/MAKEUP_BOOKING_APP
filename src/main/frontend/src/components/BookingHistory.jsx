import { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

const BookingHistory = () => {
    const { userId } = useParams(); // Lấy userId từ URL nếu cần
    const [appointments, setAppointments] = useState([]);

    useEffect(() => {
        fetchAppointments();
    }, []);

    const fetchAppointments = async () => {
        try {
            const res = await axios.get(`http://localhost:8080/appointments/user/${userId}`);
            setAppointments(res.data);
        } catch (err) {
            console.error("Lỗi khi lấy lịch sử đặt lịch:", err);
        }
    };

    return (
        <div className="p-4">
            <h2 className="text-2xl font-semibold mb-4">Lịch sử đặt lịch</h2>
            {appointments.length === 0 ? (
                <p>Không có lịch hẹn nào.</p>
            ) : (
                <table className="w-full table-auto border">
                    <thead>
                    <tr className="bg-gray-100">
                        <th className="border px-4 py-2">Ngày</th>
                        <th className="border px-4 py-2">Giờ</th>
                        <th className="border px-4 py-2">Trạng thái thanh toán</th>
                        <th className="border px-4 py-2">Trạng thái lịch hẹn</th>
                    </tr>
                    </thead>
                    <tbody>
                    {appointments.map((app) => (
                        <tr key={app.id}>
                            <td className="border px-4 py-2">{app.date}</td>
                            <td className="border px-4 py-2">{app.time}</td>
                            <td className="border px-4 py-2">{app.paymentStatus || "Chưa cập nhật"}</td>
                            <td className="border px-4 py-2">{app.status || "Chưa xác nhận"}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default BookingHistory;
