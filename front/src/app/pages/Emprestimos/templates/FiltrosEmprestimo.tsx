import React from "react";
import Accordion from "react-bootstrap/Accordion";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import Button from "react-bootstrap/Button";

const FiltrosEmprestimo: React.FC = () => {
  return (
    <Accordion defaultActiveKey="0">
      <Accordion.Item eventKey="0">
        <Accordion.Header>Filtros</Accordion.Header>
        <Accordion.Body>
          <Form>
            {/* Primeira linha com 3 campos e 1 de seleção */}
            <Row>
              <Col>
                <Form.Group className="mb-3" controlId="isbnInput">
                  <Form.Label>ISBN</Form.Label>
                  <Form.Control type="text" placeholder="Digite o ISBN" />
                </Form.Group>
              </Col>

              <Col>
                <Form.Group className="mb-3" controlId="nomeAlunoInput">
                  <Form.Label>Nome do Aluno</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Digite o nome do aluno"
                  />
                </Form.Group>
              </Col>

              <Col>
                <Form.Group className="mb-3" controlId="tituloLivroInput">
                  <Form.Label>Título do Livro</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Digite o título do livro"
                  />
                </Form.Group>
              </Col>

              <Col>
                <Form.Group className="mb-3" controlId="situacaoSelect">
                  <Form.Label>Situação</Form.Label>
                  <Form.Select aria-label="Selecione uma situação">
                    <option>Todos</option>
                    <option value="1">Entregue</option>
                    <option value="2">Pendente</option>
                    <option value="3">Atrasado</option>
                    <option value="4">Extraviado</option>
                  </Form.Select>
                </Form.Group>
              </Col>
            </Row>

            {/* Segunda linha com 5 campos de entrada */}
            <Row>
              <Col>
                <Form.Group className="mb-3" controlId="campo1Input">
                  <Form.Label>Emprestado Por</Form.Label>
                  <Form.Control type="text" placeholder="Digite o nome" />
                </Form.Group>
              </Col>
              <Col>
                <Form.Group className="mb-3" controlId="campo2Input">
                  <Form.Label>Concluído Por</Form.Label>
                  <Form.Control type="text" placeholder="Digite o nome" />
                </Form.Group>
              </Col>
              <Col>
                <Form.Group className="mb-3" controlId="campo3Input">
                  <Form.Label>Data do Empréstimo</Form.Label>
                  <Form.Control type="text" placeholder="__/__/____" />
                </Form.Group>
              </Col>
              <Col>
                <Form.Group className="mb-3" controlId="campo4Input">
                  <Form.Label>Dato do Prazo</Form.Label>
                  <Form.Control type="text" placeholder="__/__/____" />
                </Form.Group>
              </Col>
              <Col>
                <Form.Group className="mb-3" controlId="campo5Input">
                  <Form.Label>Data da Devolução</Form.Label>
                  <Form.Control type="text" placeholder="__/__/____" />
                </Form.Group>
              </Col>
            </Row>

            {/* Última linha com o botão Filtrar */}
            <Row>
              <Col>
                <div className="w-100 h-100 d-flex justify-content-end align-items-end">
                  <Button type="submit" className="btn-orange">
                    <FontAwesomeIcon icon={faMagnifyingGlass} /> Filtrar
                  </Button>
                </div>
              </Col>
            </Row>
          </Form>
        </Accordion.Body>
      </Accordion.Item>
    </Accordion>
  );
};

export default FiltrosEmprestimo;
