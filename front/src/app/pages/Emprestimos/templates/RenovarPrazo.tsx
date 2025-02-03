import React from "react";
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';

const RenovarPrazo: React.FC = () => {
    return (
        <Form>
            <Row>
                <Col xs={3}>
                    <Form.Group className="mb-3">
                        <Form.Label><strong>Aluno: </strong></Form.Label>
                        <Form.Text className="ms-2">Maria Oliveira</Form.Text>
                    </Form.Group>
                </Col>

                <Col xs={3}>
                    <Form.Group className="mb-3">
                        <Form.Label><strong>Livro: </strong></Form.Label>
                        <Form.Text className="ms-2">A Hora da Estrela</Form.Text>
                    </Form.Group>
                </Col>

                <Col xs={2}>
                    <Form.Group className="mb-3">
                        <Form.Label><strong>Exemplar: </strong></Form.Label>
                        <Form.Text className="ms-2">3</Form.Text>
                    </Form.Group>
                </Col>

            </Row>

            <Row>
                <Col xs={10}>
                    <Form.Group className="mb-3">
                        <Form.Label>Tem certeza que deseja renovar o prazo do empréstimo para mais 7 dias?</Form.Label>
                    </Form.Group>
                </Col>
            </Row>
        </Form>
    );
}

export default RenovarPrazo;