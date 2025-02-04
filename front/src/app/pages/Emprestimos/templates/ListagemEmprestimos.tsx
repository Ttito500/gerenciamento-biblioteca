import React, { useState } from "react";
import {
  faCalendarCheck,
  faCalendarPlus,
  faCheck,
  faCircleXmark,
} from "@fortawesome/free-solid-svg-icons";
import Table from "react-bootstrap/Table";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import Badge from "react-bootstrap/Badge";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/esm/Modal";
import ConfirmarEntrega from "./ConfirmarEntrega";
import RenovarPrazo from "./RenovarPrazo";
import CancelarEmprestimo from "./CancelarEmprestimo";

const ListagemEmprestimos = () => {
  const [showReceber, setShowReceber] = useState(false);
  const handleCloseReceber = () => setShowReceber(false);
  const handleShowReceber = () => setShowReceber(true);

  const [showRenovar, setShowRenovar] = useState(false);
  const handleCloseRenovar = () => setShowRenovar(false);
  const handleShowRenovar = () => setShowRenovar(true);

  const [showCancelar, setShowCancelar] = useState(false);
  const handleCloseCancelar = () => setShowCancelar(false);
  const handleShowCancelar = () => setShowCancelar(true);

  return (
    <>
      <Table striped className="tabela">
        <thead>
          <tr>
            <th>ISBN</th>
            <th>Exemplar</th>
            <th>Título</th>
            <th>Aluno</th>
            <th>Emprestado Por</th>
            <th>Concluído Por</th>
            <th>Observações</th>
            <th>Data Empr.</th>
            <th>Prazo</th>
            <th>Devolução</th>
            <th>Renovações</th>
            <th>Status</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr className="tabela-tr">
            <td>978-3-16-148410-0</td>
            <td>1</td>
            <td>Livro Exemplo</td>
            <td>João Silva</td>
            <td>Monitor N</td>
            <td>Bibliotecária</td>
            <td>Nenhuma</td>
            <td>01/02/2024</td>
            <td>15/02/2024</td>
            <td>14/02/2024</td>
            <td>1</td>
            <td>
              <Badge className="bibliotech-badge" bg="green">
                Entregue
              </Badge>
            </td>
            <td>
              <ButtonGroup aria-label="Ações" className="tabela-acoes">
                <Button
                  onClick={handleShowReceber}
                  variant="btn-outline-secondary"
                  className="color-orange"
                >
                  <FontAwesomeIcon icon={faCalendarCheck} />
                </Button>
                <Button
                  onClick={handleShowRenovar}
                  variant="btn-outline-secondary"
                  className="color-blue"
                >
                  <FontAwesomeIcon icon={faCalendarPlus} />
                </Button>
                <Button
                  onClick={handleShowCancelar}
                  variant="btn-outline-secondary"
                  className="color-red"
                >
                  <FontAwesomeIcon icon={faCircleXmark} />
                </Button>
              </ButtonGroup>
            </td>
          </tr>
          <tr className="tabela-tr">
            <td>978-3-16-148411-7</td>
            <td>2</td>
            <td>Livro Pendente</td>
            <td>Maria Oliveira</td>
            <td>Monitor A</td>
            <td>Bibliotecário X</td>
            <td>Atenção na devolução</td>
            <td>05/02/2024</td>
            <td>20/02/2024</td>
            <td></td>
            <td>2</td>
            <td>
              <Badge className="bibliotech-badge" bg="blue">
                Pendente
              </Badge>
            </td>
            <td>
              <ButtonGroup aria-label="Ações" className="tabela-acoes">
                <Button
                  onClick={handleShowReceber}
                  variant="btn-outline-secondary"
                  className="color-orange"
                >
                  <FontAwesomeIcon icon={faCalendarCheck} />
                </Button>
                <Button
                  onClick={handleShowRenovar}
                  variant="btn-outline-secondary"
                  className="color-blue"
                >
                  <FontAwesomeIcon icon={faCalendarPlus} />
                </Button>
                <Button
                  onClick={handleShowCancelar}
                  variant="btn-outline-secondary"
                  className="color-red"
                >
                  <FontAwesomeIcon icon={faCircleXmark} />
                </Button>
              </ButtonGroup>
            </td>
          </tr>
          <tr className="tabela-tr">
            <td>978-3-16-148412-4</td>
            <td>3</td>
            <td>Livro Atrasado</td>
            <td>Carlos Mendes</td>
            <td>Monitor B</td>
            <td>Bibliotecário Y</td>
            <td>Urgente</td>
            <td>10/01/2024</td>
            <td>25/01/2024</td>
            <td></td>
            <td>0</td>
            <td>
              <Badge className="bibliotech-badge" bg="warning">
                Atrasado
              </Badge>
            </td>
            <td>
              <ButtonGroup aria-label="Ações" className="tabela-acoes">
                <Button
                  onClick={handleShowReceber}
                  variant="btn-outline-secondary"
                  className="color-orange"
                >
                  <FontAwesomeIcon icon={faCalendarCheck} />
                </Button>
                <Button
                  onClick={handleShowRenovar}
                  variant="btn-outline-secondary"
                  className="color-blue"
                >
                  <FontAwesomeIcon icon={faCalendarPlus} />
                </Button>
                <Button
                  onClick={handleShowCancelar}
                  variant="btn-outline-secondary"
                  className="color-red"
                >
                  <FontAwesomeIcon icon={faCircleXmark} />
                </Button>
              </ButtonGroup>
            </td>
          </tr>
          <tr className="tabela-tr">
            <td>978-3-16-148413-1</td>
            <td>4</td>
            <td>Livro Extraviado</td>
            <td>Ana Souza</td>
            <td>Monitor C</td>
            <td>Bibliotecário Z</td>
            <td>Verificar condição</td>
            <td>12/02/2024</td>
            <td>27/02/2024</td>
            <td></td>
            <td>1</td>
            <td>
              <Badge className="bibliotech-badge" bg="red">
                Extraviado
              </Badge>
            </td>
            <td>
              <ButtonGroup aria-label="Ações" className="tabela-acoes">
                <Button
                  onClick={handleShowReceber}
                  variant="btn-outline-secondary"
                  className="color-orange"
                >
                  <FontAwesomeIcon icon={faCalendarCheck} />
                </Button>
                <Button
                  onClick={handleShowRenovar}
                  variant="btn-outline-secondary"
                  className="color-blue"
                >
                  <FontAwesomeIcon icon={faCalendarPlus} />
                </Button>
                <Button
                  onClick={handleShowCancelar}
                  variant="btn-outline-secondary"
                  className="color-red"
                >
                  <FontAwesomeIcon icon={faCircleXmark} />
                </Button>
              </ButtonGroup>
            </td>
          </tr>
        </tbody>
      </Table>

      <Modal
        show={showReceber}
        onHide={handleCloseReceber}
        size="lg"
        backdrop="static"
        centered
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Concluir Empréstimo</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <ConfirmarEntrega />
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseReceber}>
            Desistir
          </Button>
          <Button variant="success">
            <FontAwesomeIcon icon={faCheck} /> Concluir Empréstimo
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal
        show={showRenovar}
        onHide={handleCloseRenovar}
        size="lg"
        backdrop="static"
        centered
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Renovar Prazo</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <RenovarPrazo />
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseRenovar}>
            Desistir
          </Button>
          <Button variant="success">
            <FontAwesomeIcon icon={faCheck} /> Renovar Prazo
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal
        show={showCancelar}
        onHide={handleCloseCancelar}
        size="lg"
        backdrop="static"
        centered
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Cancelar Empréstimo</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <CancelarEmprestimo />
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseCancelar}>
            Desistir
          </Button>
          <Button variant="danger">
            <FontAwesomeIcon icon={faCheck} /> Cancelar Empréstimo
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default ListagemEmprestimos;
