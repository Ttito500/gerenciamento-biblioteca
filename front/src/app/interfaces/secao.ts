export interface CreateSecaoRequest {
  nome: string;
  descricao: string;
}

export interface CreateSecaoResponse {
  id: number;
  nome: string;
  descricao: string;
}

export interface GetSecaoResponse {
  id: number;
  nome: string;
  descricao: string;
}

export interface UpdateSecaoRequest {
  nome: string
  descricao: string;
}

export interface UpdateSecaoResponse {
  id: number;
  nome: string;
  descricao: string;
}

export interface Estante {
  letra: string;
  prateleiras: Prateleira[];
}

export interface Prateleira {
  id: number;
  numero: number;
}