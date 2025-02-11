import axios from 'axios';
import { AlunoFiltros, CreateAlunoRequest, CreateAlunoResponse, GetAlunoResponse, UpdateAlunoRequest, UpdateAlunoResponse } from '../interfaces/aluno';
import { getQueryString } from '../shared/utils';

const API_URL = 'http://localhost:8090/alunos';

export const getAlunos = async (filtros: AlunoFiltros): Promise<GetAlunoResponse[]> => {
  try {
    const queryString = getQueryString(filtros);
    const url = queryString ? `${API_URL}/?${queryString}` : `${API_URL}`;

    const response = await axios.get<GetAlunoResponse[]>(url);
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar alunos:', error);
    throw error;
  }
};

export const createAluno = async (aluno: CreateAlunoRequest): Promise<CreateAlunoResponse> => {
  try {
    const response = await axios.post<CreateAlunoResponse>(API_URL, aluno);
    return response.data;
  } catch (error) {
    console.error('Erro ao criar aluno:', error);
    throw error;
  }
};

export const updateAluno = async (id: number, aluno: UpdateAlunoRequest): Promise<UpdateAlunoResponse> => {
  try {
    const response = await axios.put<UpdateAlunoResponse>(`${API_URL}/${id}`, aluno);
    return response.data;
  } catch (error) {
    console.error('Erro ao atualizar aluno:', error);
    throw error;
  }
};

export const inativarAluno = async (id: number): Promise<void> => {
  try {
    await axios.patch(`${API_URL}/${id}/inativar`);
  } catch (error) {
    console.error('Erro ao inativar aluno:', error);
    throw error;
  }
};