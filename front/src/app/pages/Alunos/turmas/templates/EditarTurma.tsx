import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/esm/Button";

const EditarTurma: React.FC = () => {


    return(
        <Form>
            <Row>
                <Col xs={3}>
                    <Form.Group className="mb-3">
                        <Form.Label>
                            Série <span className="obgr">*</span>
                        </Form.Label>
                        <Form.Control type="number" placeholder="Digite a série" required />
                    </Form.Group>
                </Col>

                <Col xs={3}>
                    <Form.Group className="mb-3">
                        <Form.Label>
                            Turma <span className="obgr">*</span>
                        </Form.Label>
                        <Form.Control type="text" placeholder="Digite a turma" required />
                    </Form.Group>
                </Col>

                <Col xs={3}>
                    <Form.Group className="mb-3">
                        <Form.Label>
                            Ano de Entrada <span className="obgr">*</span>
                        </Form.Label>
                        <Form.Control type="number" placeholder="Digite o ano" required />
                    </Form.Group>
                </Col>
            </Row>

            <Row>
                <Col className="d-flex justify-content-end">
                    <Button variant="success">Ativar Turma</Button>
                </Col>

            </Row>
        </Form>

    );
};

export default EditarTurma;