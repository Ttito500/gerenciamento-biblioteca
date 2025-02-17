import { GetEstantePrateleiraResponse } from "./estante-prateleira"
import { GetSecaoResponse } from "./secao"

export interface GetExemplarResponse {
  id: number
  estantePrateleira: GetEstantePrateleiraResponse
  secao: GetSecaoResponse
  observacao: string
  numero: number
  situacao: string
}