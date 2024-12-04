import axios from 'axios';

const api = axios.create({
  baseURL: 'https://sua-api.com/api', // Substitua pela URL da API
  timeout: 10000,
});

export default api;