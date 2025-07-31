import axios from 'axios';

const API_BASE_URL = 'http://recrutory-web-dev.cloudapp.net:8080/recrutory/';

// Create axios instance with default config
const apiClient = axios.create({
	baseURL: API_BASE_URL,
	headers: {
		'Content-Type': 'application/json'
	},
	timeout: 10000
});

// Request interceptor for loading states
apiClient.interceptors.request.use(
	(config) => {
		console.log('API Request:', config.method?.toUpperCase(), config.url);
		return config;
	},
	(error) => {
		console.error('Request error:', error);
		return Promise.reject(error);
	}
);

// Response interceptor for error handling
apiClient.interceptors.response.use(
	(response) => {
		console.log('API Response successful:', response.config.url);
		return response;
	},
	(error) => {
		console.error('API Error:', error.response?.status, error.message);
		return Promise.reject(error);
	}
);

export const apiService = {
	// GET request
	async get(url) {
		try {
			const response = await apiClient.get(url);
			return response.data;
		} catch (error) {
			throw new Error(`GET ${url} failed: ${error.message}`);
		}
	},

	// POST request
	async post(url, data) {
		try {
			const response = await apiClient.post(url, data);
			return response.data;
		} catch (error) {
			throw new Error(`POST ${url} failed: ${error.message}`);
		}
	},

	// PUT request
	async put(url, data) {
		try {
			const response = await apiClient.put(url, data);
			return response.data;
		} catch (error) {
			throw new Error(`PUT ${url} failed: ${error.message}`);
		}
	},

	// DELETE request
	async delete(url) {
		try {
			const response = await apiClient.delete(url);
			return response.data;
		} catch (error) {
			throw new Error(`DELETE ${url} failed: ${error.message}`);
		}
	}
};

// Specific API endpoints for your recruitment app
export const vacancyAPI = {
	getAll: () => apiService.get('vacancies'),
	getById: (id) => apiService.get(`vacancies/${id}`),
	create: (vacancy) => apiService.post('vacancies', vacancy),
	update: (id, vacancy) => apiService.put(`vacancies/${id}`, vacancy),
	delete: (id) => apiService.delete(`vacancies/${id}`)
};

export const candidateAPI = {
	getAll: () => apiService.get('candidates'),
	getById: (id) => apiService.get(`candidates/${id}`),
	create: (candidate) => apiService.post('candidates', candidate),
	update: (id, candidate) => apiService.put(`candidates/${id}`, candidate),
	delete: (id) => apiService.delete(`candidates/${id}`),
	uploadCV: (formData) => apiService.post('candidates/cv', formData)
};
