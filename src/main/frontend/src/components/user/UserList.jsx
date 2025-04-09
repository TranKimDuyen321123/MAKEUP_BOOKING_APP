import { useEffect, useState } from "react";
import { getUsers, deleteUser } from "../../services/userService";
import { useNavigate } from "react-router-dom";

const UserList = () => {
    const [users, setUsers] = useState([]);
    const [search, setSearch] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        const res = await getUsers();
        setUsers(res.data);
    };

    const handleDelete = async (id) => {
        await deleteUser(id);
        loadUsers();
    };

    const filtered = users.filter(
        u =>
            u.name.toLowerCase().includes(search.toLowerCase()) ||
            u.email.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <div>
            <h2 className="text-2xl mb-4">Danh sách khách hàng</h2>
            <input
                className="border p-2 mb-4 w-full"
                placeholder="Tìm theo tên hoặc email..."
                value={search}
                onChange={(e) => setSearch(e.target.value)}
            />
            <button onClick={() => navigate("/users/new")} className="mb-4 px-4 py-2 bg-blue-500 text-white rounded">
                + Thêm khách hàng
            </button>
            <table className="w-full table-auto border">
                <thead>
                <tr className="bg-gray-200">
                    <th className="border px-4 py-2">Tên</th>
                    <th className="border px-4 py-2">Email</th>
                    <th className="border px-4 py-2">Hành động</th>
                </tr>
                </thead>
                <tbody>
                {filtered.map(user => (
                    <tr key={user.id}>
                        <td className="border px-4 py-2">{user.name}</td>
                        <td className="border px-4 py-2">{user.email}</td>
                        <td className="border px-4 py-2">
                            <button onClick={() => navigate(`/users/${user.id}`)} className="text-blue-500 mr-2">Chi tiết</button>
                            <button onClick={() => navigate(`/users/edit/${user.id}`)} className="text-green-500 mr-2">Sửa</button>
                            <button onClick={() => handleDelete(user.id)} className="text-red-500">Xoá</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default UserList;
