import axios from 'axios';

const API_BASE = 'http://localhost:8080/api/users';

export const getUsers = () => axios.get(API_BASE);
export const getUserById = (id) => axios.get(`${API_BASE}/${id}`);
export const createUser = (data) => axios.post(API_BASE, data);
export const updateUser = (id, data) => axios.put(`${API_BASE}/${id}`, data);
export const deleteUser = (id) => axios.delete(`${API_BASE}/${id}`);
