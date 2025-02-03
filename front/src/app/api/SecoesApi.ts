import axios from "axios";
import {
  GetSecaoResponse,
  CreateSecaoRequest,
  CreateSecaoResponse,
  UpdateSecaoRequest,
  UpdateSecaoResponse,
} from "../interfaces/secao";

const API_URL = "http://localhost:8090/secoes";

export const getSecoes = async (): Promise<GetSecaoResponse[]> => {
  try {
    const response = await axios.get<GetSecaoResponse[]>(API_URL);
    return response.data;
  } catch (error) {
    console.error("Erro ao buscar seções:", error);
    throw error;
  }
};

export const createSecao = async (
  secao: CreateSecaoRequest
): Promise<CreateSecaoResponse> => {
  try {
    const response = await axios.post<CreateSecaoResponse>(API_URL, secao);
    return response.data;
  } catch (error) {
    console.error("Erro ao criar seção:", error);
    throw error;
  }
};

export const updateSecao = async (
  id: number,
  aluno: UpdateSecaoRequest
): Promise<UpdateSecaoResponse> => {
  try {
    const response = await axios.put<UpdateSecaoResponse>(
      `${API_URL}/${id}`,
      aluno
    );
    return response.data;
  } catch (error) {
    console.error("Erro ao atualizar seção:", error);
    throw error;
  }
};

export const deleteSecao = async (id: number): Promise<void> => {
  try {
    await axios.delete(`${API_URL}/${id}`);
  } catch (error) {
    console.error("Erro ao deletar seção:", error);
    throw error;
  }
};
