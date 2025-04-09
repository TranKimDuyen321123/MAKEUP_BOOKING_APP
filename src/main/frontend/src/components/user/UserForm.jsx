import { useEffect, useState } from "react";
import { createUser, getUserById, updateUser } from "../../services/userService";
import { useNavigate, useParams } from "react-router-dom";

const UserForm = () => {
    const [form, setForm] = useState({ name: "", email: "", phone: "", role: "USER" });
    const navigate = useNavigate();
    const { id } = useParams();

    const isEdit = Boolean(id);

    useEffect(() => {
        if (isEdit) {
            getUserById(id).then(res => setForm(res.data));
        }
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (isEdit) {
            await updateUser(id, form);
        } else {
            await createUser(form);
        }
        navigate("/users");
    };

    return (
        <div className="max-w-md mx-auto">
            <h2 className="text-2xl mb-4">{isEdit ? "Sửa" : "Thêm"} khách hàng</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
                <input
                    name="name"
                    value={form.name}
                    onChange={handleChange}
                    placeholder="Họ tên"
                    className="w-full border p-2"
                    required
                />
                <input
                    name="email"
                    type="email"
                    value={form.email}
                    onChange={handleChange}
                    placeholder="Email"
                    className="w-full border p-2"
                    required
                />
                <input
                    name="phone"
                    value={form.phone}
                    onChange={handleChange}
                    placeholder="Số điện thoại"
                    className="w-full border p
