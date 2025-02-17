import axios from 'axios';
import { CreateLivroRequest, CreateLivroResponse, GetLivroResponse, LivroFiltros, UpdateLivroRequest, UpdateLivroResponse } from '../interfaces/acervo';
import { getQueryString } from '../shared/utils';
import { ResponsePagination } from '../interfaces/pagination';
import { GetExemplarResponse } from '../interfaces/exemplar';

const API_URL = 'http://localhost:8090/livros';

export const getLivros = async (filtros: LivroFiltros): Promise<ResponsePagination<GetLivroResponse>> => {
  try {
    const queryString = getQueryString(filtros);
    const url = queryString ? `${API_URL}/filtrar?${queryString}` : `${API_URL}/filtros`;
    
    const response = await axios.get<ResponsePagination<GetLivroResponse>>(url);
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar livros:', error);
    throw error;
  }
};

export const createLivro = async (livro: CreateLivroRequest): Promise<CreateLivroResponse> => {
  try {
    const response = await axios.post<CreateLivroResponse>(API_URL, livro);
    return response.data;
  } catch (error) {
    console.error('Erro ao criar livro:', error);
    throw error;
  }
};

export const updateLivro = async (id: number, livro: UpdateLivroRequest): Promise<UpdateLivroResponse> => {
  try {
    const response = await axios.patch<UpdateLivroResponse>(`${API_URL}/${id}`, livro);
    return response.data;
  } catch (error) {
    console.error('Erro ao atualizar livro:', error);
    throw error;
  }
};

export const deleteLivro = async (id: number): Promise<void> => {
  try {
    await axios.delete(`${API_URL}/${id}`);
  } catch (error) {
    console.error('Erro ao deletar livro:', error);
    throw error;
  }
};

export const getExemplares = async (idLivro: number): Promise<GetExemplarResponse[]> => {
  try {
    const url = `${API_URL}/exemplares/${idLivro}`;
    
    const response = await axios.get<GetExemplarResponse[]>(url);
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar exemplares do livro:', error);
    throw error;
  }
};