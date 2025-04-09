import axios from 'axios';
import App from "./App";

const API_URL = '/api/users';

const getAllUsers = () => {
    return axios.get(API_URL);
};

const getUserById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

const createUser = (user) => {
    return axios.post(API_URL, user);
};

const updateUser = (id, user) => {
    return axios.put(`${API_URL}/${id}`, user);
};

const deleteUser = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};

export { getAllUsers, getUserById, createUser, updateUser, deleteUser };
export default App;