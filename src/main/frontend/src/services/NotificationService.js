import axios from 'axios';

const API_URL = '/api/notifications'; // Thay đổi URL API nếu cần

const getAllNotifications = () => {
  return axios.get(API_URL);
};

export { getAllNotifications };import axios from 'axios';

const API_URL = '/api/notifications'; // Thay đổi URL API nếu cần

const getAllNotifications = () => {
  return axios.get(API_URL);
};

export { getAllNotifications };