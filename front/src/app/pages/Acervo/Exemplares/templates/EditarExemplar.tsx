import React from "react";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
import Form from "react-bootstrap/esm/Form";

const EditarExemplar: React.FC = () => {

    return (
            <Form>
                <Row>
                    <Col xs={3}>
                        <Form.Group className="mb-3">
                            <Form.Label>Estante</Form.Label>
                            <Form.Select>
                                <option>Selecione</option>
                                <option>A</option>
                                <option>B</option>
                            </Form.Select>
                        </Form.Group>
                    </Col>

                    <Col xs={3}>
                        <Form.Group className="mb-3">
                            <Form.Label>Prateleira</Form.Label>
                            <Form.Select>
                                <option>Selecione</option>
                                <option> 1 </option>
                                <option> 2 </option>
                            </Form.Select>
                        </Form.Group>
                    </Col>

                    <Col xs={3}>
                        <Form.Group className="mb-3">
                            <Form.Label>Seção</Form.Label>
                            <Form.Select>
                                <option>Selecione</option>
                                <option>Romance</option>
                                <option>Dorama</option>
                            </Form.Select>
                        </Form.Group>
                    </Col>

                    <Col xs={3}>
                        <Form.Group className="mb-3">
                            <Form.Label>Situação</Form.Label>
                            <Form.Select>
                                <option>Selecione</option>
                                <option>Disponível</option>
                                <option>Emprestado</option>
                                <option>Extraviado</option>
                            </Form.Select>
                        </Form.Group>
                    </Col>
                </Row>

                <Row>
                    <Col>
                        <Form.Group className="mb-3">
                            <Form.Label>Observação</Form.Label>
                            <Form.Control type="text" placeholder="Digite uma observação" />
                        </Form.Group>
                    </Col>
                </Row>
            </Form>
    );
};

export default EditarExemplar;
