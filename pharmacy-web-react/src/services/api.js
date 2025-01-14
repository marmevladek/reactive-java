import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

export const getPharmacies = async () => {
    const response = await axios.get(`${API_BASE_URL}/pharmacy/`);
    return response.data;
};

export const getMedicinesByPharmacy = async (pharmacyId) => {
    const response = await axios.get(`${API_BASE_URL}/drugs/${pharmacyId}`);
    return response.data;
};

export const findPrice = async (pharmacyId, drugId) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/drugs/getPrice/${pharmacyId}/${drugId}`);
        return response.data;
    } catch (error) {
        console.error(`Ошибка при получении цены: ${error.message}`);
        return null; 
    }
};