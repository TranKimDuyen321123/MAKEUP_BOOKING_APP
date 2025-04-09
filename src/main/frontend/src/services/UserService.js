import api from './api';

export const getAllUsers = async () => (await api.get('/users')).data;
export const getUserById = async (id) => (await api.get(`/users/${id}`)).data;
export const createUser = async (user) => (await api.post('/users', user)).data;
export const updateUser = async (id, user) => (await api.put(`/users/${id}`, user)).data;
export const deleteUser = async (id) => (await api.delete(`/users/${id}`)).data;