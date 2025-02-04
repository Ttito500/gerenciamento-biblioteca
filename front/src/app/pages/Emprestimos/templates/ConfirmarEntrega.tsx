import React from "react";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";

const ConfirmarEntrega: React.FC = () => {
  return (
    <Form>
      <Row>
        <Col xs={3}>
          <Form.Group className="mb-3">
            <Form.Label>
              <strong>Aluno: </strong>
            </Form.Label>
            <Form.Text className="ms-2">Maria Oliveira</Form.Text>
          </Form.Group>
        </Col>

        <Col xs={3}>
          <Form.Group className="mb-3">
            <Form.Label>
              <strong>Livro: </strong>
            </Form.Label>
            <Form.Text className="ms-2">A Hora da Estrela</Form.Text>
          </Form.Group>
        </Col>

        <Col xs={2}>
          <Form.Group className="mb-3">
            <Form.Label>
              <strong>Exemplar: </strong>
            </Form.Label>
            <Form.Text className="ms-2">3</Form.Text>
          </Form.Group>
        </Col>

        <Col xs={2}>
          <Form.Group className="mb-3">
            <Form.Label>
              <strong>Estante: </strong>
            </Form.Label>
            <Form.Text className="ms-2">A</Form.Text>
          </Form.Group>
        </Col>

        <Col xs={2}>
          <Form.Group className="mb-3">
            <Form.Label>
              <strong>Prateleira: </strong>
            </Form.Label>
            <Form.Text className="ms-2">2</Form.Text>
          </Form.Group>
        </Col>
      </Row>

      <Row>
        <Col xs={12}>
          <Form.Group className="mb-3">
            <Form.Label>Observação</Form.Label>
            <Form.Control
              as="textarea"
              rows={1}
              placeholder="Digite uma observação (opcional)"
            />
          </Form.Group>
        </Col>
      </Row>
    </Form>
  );
};

export default ConfirmarEntrega;
