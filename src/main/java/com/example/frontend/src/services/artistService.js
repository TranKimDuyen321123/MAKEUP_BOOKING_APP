import axios from 'axios';
const API_BASE_URL = 'http://localhost:8080/api/artists';

export const fetchArtists = async () => {
  try {
    const response = await axios.get(API_BASE_URL);
    return response.data;
  } catch (error) {
    console.error("Lỗi khi lấy danh sách artist:", error);
    throw error;
  }
};

