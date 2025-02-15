import axios from "axios";
import { GetAutorResponse } from "../interfaces/autor";

const API_URL = "http://localhost:8090/autor";

export const getAutores = async (nome: string): Promise<GetAutorResponse[]> => {
  try {
    const url =`${API_URL}/buscar?nome=${nome}`;
    
    const response = await axios.get<GetAutorResponse[]>(url);
    return response.data;
  } catch (error) {
    console.error("Erro ao buscar autores:", error);
    throw error;
  }
};


export const deleteAutoresSemAssociacao = async (): Promise<void> => {
  try {
    await axios.delete(`${API_URL}/sem-associacao`);
  } catch (error) {
    console.error("Erro ao deletar autores sem associação:", error);
    throw error;
  }
};

