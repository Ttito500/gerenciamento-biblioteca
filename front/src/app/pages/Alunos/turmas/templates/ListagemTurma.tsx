import React, { useState } from "react";
import { Table, Badge, Button, ButtonGroup } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheck, faEdit, faPowerOff } from "@fortawesome/free-solid-svg-icons";
import Modal from "react-bootstrap/esm/Modal";
import EditarTurma from "./EditarTurma";
import InativarTurma from "./InativarTurma";

const ListagemTurma: React.FC = () => {
  const [showEditar, setShowEditar] = useState(false);
  const handleCloseEditar = () => setShowEditar(false);
  const handleShowEditar = () => setShowEditar(true);

  const [showInativar, setShowInativar] = useState(false);
  const handleCloseInativar = () => setShowInativar(false);
  const handleShowInativar = () => setShowInativar(true);

  return (
    <>
      <Table striped className="tabela">
        <thead>
          <tr>
            <th>Série</th>
            <th>Turma</th>
            <th>Ano de Entrada</th>
            <th>Ativo</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr className="tabela-tr">
            <td>1º Ano</td>
            <td>A</td>
            <td>2023</td>
            <td>
              <Badge className="bibliotech-badge" bg="green">
                Ativo
              </Badge>
            </td>
            <td>
              <ButtonGroup aria-label="Ações" className="tabela-acoes">
                <Button
                  onClick={handleShowEditar}
                  variant="btn-outline-secondary"
                  className="color-green"
                >
                  <FontAwesomeIcon icon={faEdit} />
                </Button>
                <Button
                  onClick={handleShowInativar}
                  variant="btn-outline-secondary"
                  className="color-red"
                >
                  <FontAwesomeIcon icon={faPowerOff} />
                </Button>
              </ButtonGroup>
            </td>
          </tr>
          <tr className="tabela-tr">
            <td>2º Ano</td>
            <td>B</td>
            <td>2022</td>
            <td>
              <Badge className="bibliotech-badge" bg="green">
                Ativo
              </Badge>
            </td>
            <td>
              <ButtonGroup aria-label="Ações" className="tabela-acoes">
                <Button
                  onClick={handleShowEditar}
                  variant="btn-outline-secondary"
                  className="color-green"
                >
                  <FontAwesomeIcon icon={faEdit} />
                </Button>
                <Button
                  onClick={handleShowInativar}
                  variant="btn-outline-secondary"
                  className="color-red"
                >
                  <FontAwesomeIcon icon={faPowerOff} />
                </Button>
              </ButtonGroup>
            </td>
          </tr>
          <tr className="tabela-tr">
            <td>3º Ano</td>
            <td>C</td>
            <td>2021</td>
            <td>
              <Badge className="bibliotech-badge" bg="red">
                Inativo
              </Badge>
            </td>
            <td>
              <ButtonGroup aria-label="Ações" className="tabela-acoes">
                <Button
                  onClick={handleShowEditar}
                  variant="btn-outline-secondary"
                  className="color-green"
                >
                  <FontAwesomeIcon icon={faEdit} />
                </Button>
                <Button
                  onClick={handleShowInativar}
                  variant="btn-outline-secondary"
                  className="color-red"
                >
                  <FontAwesomeIcon icon={faPowerOff} />
                </Button>
              </ButtonGroup>
            </td>
          </tr>
        </tbody>
      </Table>

      <Modal
        show={showEditar}
        onHide={handleCloseEditar}
        size="lg"
        backdrop="static"
        centered
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Ediar Turma</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <EditarTurma />
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseEditar}>
            Desistir
          </Button>
          <Button variant="success">
            <FontAwesomeIcon icon={faCheck} /> Salvar
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal
        show={showInativar}
        onHide={handleCloseInativar}
        size="sm"
        backdrop="static"
        centered
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Confirmação</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <InativarTurma />
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseInativar}>
            Desistir
          </Button>
          <Button variant="danger">
            <FontAwesomeIcon icon={faCheck} /> Inativar
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default ListagemTurma;
