import axiosInstance from "../custom-axios/axios.js"

const categoryRepository = {
    findAll: async () => {
        return await axiosInstance.get("/countries");
    },
    findById: async (id) => {
        return await axiosInstance.get(`/countries/${id}`);
    },
};


export default categoryRepository;