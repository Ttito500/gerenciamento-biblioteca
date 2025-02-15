import axios from "axios";

const API_URL = "http://localhost:8090/emprestimos";

export const notificarAtrasos = async (): Promise<any> => {
  try {
    const url =`${API_URL}/emprestimos/enviar-email`;
    
    const response = await axios.post(url);
    return response.data;
  } catch (error) {
    console.error("Erro ao notiicar atrasos:", error);
    throw error;
  }
};

