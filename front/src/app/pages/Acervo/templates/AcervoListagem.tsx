import React from "react";
import {
  faClipboardList,
  faPenToSquare,
  faPowerOff,
  faTableList,
} from "@fortawesome/free-solid-svg-icons";
import Table from "react-bootstrap/Table";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import Badge from "react-bootstrap/Badge";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Button from "react-bootstrap/Button";
import { GetLivroResponse } from "./../../../interfaces/acervo";
import { ResponsePagination } from "./../../../interfaces/pagination";

interface AcervoListagemProps {
  livros: ResponsePagination<GetLivroResponse>;
  onEdit: (livro: GetLivroResponse) => void;
  onAtivar: (id: number) => void;
  onInativar: (id: number) => void;
  onEmprestimos: (livro: GetLivroResponse) => void;
}

const AcervoListagem: React.FC<AcervoListagemProps> = ({ livros, onEdit, onAtivar, onInativar, onEmprestimos }) => {

  return (
    <>
      <Table striped className="tabela">
        <thead>
          <tr>
            <th>ISBN</th>
            <th>Título</th>
            <th>Autores</th>
            <th>Gêneros</th>
            <th className="text-center">Total</th>
            <th className="text-center">Empr.</th>
            <th className="text-center">Extrav.</th>
            <th className="text-center">Disp.</th>
            <th className="text-center">Ativo</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {livros?.content.map((livro) => (
            <tr key={livro.id} className="tabela-tr">
              <td>{livro.isbn}</td>
              <td>{livro.titulo}</td>
              <td>
                {livro.autores.map((autor, index) => (
                  <span key={index}>
                    {index > 0 && <span>, </span>}
                    {autor.nome}
                  </span>
                ))}
              </td>
              <td>
                {livro.generos.map((genero, index) => (
                  <span key={index}>
                    {index > 0 && <span>, </span>}
                    {genero.genero}
                  </span>
                ))}
              </td>
              <td className="text-center">{livro.totalExemplares}</td>
              <td className="text-center">{livro.totalEmprestados}</td>
              <td className="text-center">
                { livro.totalExtraviados > 0 &&
                  <Badge className="bibliotech-badge" bg="danger">
                    {livro.totalExtraviados}
                  </Badge>
                }

                { livro.totalExtraviados == 0 &&
                  <span>
                    {livro.totalExtraviados}
                  </span>
                } 
              </td>
              <td className="text-center">
                { livro.totalDisponiveis == 0 &&
                  <Badge className="bibliotech-badge" bg="danger">
                    {livro.totalDisponiveis}
                  </Badge>
                }

                { livro.totalDisponiveis > 0 &&
                  <span>
                    {livro.totalDisponiveis}
                  </span>
                } 
              </td>
              <td className="text-center">
                <Badge className="bibliotech-badge" bg={livro.ativo ? 'success' : 'danger'}>
                  {livro.ativo ? 'Ativo' : 'Inativo'}
                </Badge>
              </td>
              <td>
                <ButtonGroup aria-label="Ações" className="tabela-acoes">
                  <Button
                    variant="btn-outline-secondary"
                    className="color-orange"
                  >
                    <FontAwesomeIcon icon={faTableList} />
                  </Button>

                  <Button
                    variant="btn-outline-secondary"
                    className="color-blue"
                    onClick={() => onEmprestimos(livro)}
                  >
                    <FontAwesomeIcon icon={faClipboardList} />
                  </Button>

                  <Button
                    variant="btn-outline-secondary"
                    className="color-green"
                    onClick={() => onEdit(livro)}
                  >
                    <FontAwesomeIcon icon={faPenToSquare} />
                  </Button>

                  { !livro.ativo &&
                    <Button
                      variant="btn-outline-secondary"
                      className="color-green"
                      onClick={() => onAtivar(livro.id)}
                    >
                      <FontAwesomeIcon icon={faPowerOff} />
                    </Button>
                  }

                  { livro.ativo &&
                    <Button
                      variant="btn-outline-secondary"
                      className="color-red"
                      onClick={() => onInativar(livro.id)}
                    >
                      <FontAwesomeIcon icon={faPowerOff} />
                    </Button>
                  }
                </ButtonGroup>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </>
  );
};

export default AcervoListagem;
