import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080',
  auth: {
    username: 'lucas',
    password: 'Promo2020'
  }
});

export default axiosInstance;
