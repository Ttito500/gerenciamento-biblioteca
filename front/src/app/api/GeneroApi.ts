import axios from "axios";
import { GetGeneroResponse } from "../interfaces/genero";

const API_URL = "http://localhost:8090/generos";

export const getGeneroes = async (genero: string): Promise<GetGeneroResponse[]> => {
  try {
    const url =`${API_URL}/buscar?genero=${genero}`;
    
    const response = await axios.get<GetGeneroResponse[]>(url);
    return response.data;
  } catch (error) {
    console.error("Erro ao buscar generos:", error);
    throw error;
  }
};


export const deleteGeneroesSemAssociacao = async (): Promise<void> => {
  try {
    await axios.delete(`${API_URL}/sem-associacao`);
  } catch (error) {
    console.error("Erro ao deletar generos sem associação:", error);
    throw error;
  }
};

