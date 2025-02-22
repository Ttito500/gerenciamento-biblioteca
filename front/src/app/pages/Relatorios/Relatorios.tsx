import Accordion from "react-bootstrap/esm/Accordion";
import Form from "react-bootstrap/esm/Form";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
import Button from "react-bootstrap/esm/Button";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faCheck, faFileExport} from "@fortawesome/free-solid-svg-icons";
import { useState } from "react";
import InputGroup from "react-bootstrap/esm/InputGroup";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const Relatorios: React.FC = () => {
    const [dataInicio, setdataInicio] = useState<Date | null>(null);
    const [dataFinal, setdataFinal] = useState<Date | null>(null);

    return (
        <section className="Exemplar">
            <div className="accordion-container">
                <Accordion defaultActiveKey="0" className="accordion-relatorios">
                    <Accordion.Item eventKey="0">
                        <Accordion.Header className="custon-accordion-header-cyan">
                            Relatório
                        </Accordion.Header>
                        <Accordion.Body className="accordion-body-expanded">
                            <Row className="mb-3">
                                <Col md={6}>
                                    <Form.Group controlId="formData" className="mb-3">
                                        <Form.Label>Data Inicial <span className="obgr">*</span> </Form.Label>
                                        <InputGroup>
                                            <DatePicker
                                                selected={dataInicio}
                                                onChange={(date: Date) => setdataInicio(date)}
                                                dateFormat="dd/MM/yyyy"
                                                customInput={
                                                    <Form.Control
                                                        type="text"
                                                        placeholder="Selecione a data"
                                                        readOnly
                                                        style={{ cursor: "pointer" }}
                                                        className="no-border-radius-right"
                                                    />
                                                }
                                            />
                                        </InputGroup>
                                    </Form.Group>
                                </Col>
                                <Col md={6}>
                                    <Form.Group controlId="formData" className="mb-3">
                                        <Form.Label>Data Final <span className="obgr">*</span> </Form.Label>
                                        <InputGroup>
                                            <DatePicker
                                                selected={dataFinal}
                                                onChange={(date: Date) => setdataFinal(date)}
                                                dateFormat="dd/MM/yyyy"
                                                customInput={
                                                    <Form.Control
                                                        type="text"
                                                        placeholder="Selecione a data"
                                                        readOnly
                                                        style={{ cursor: "pointer" }}
                                                        className="no-border-radius-right"
                                                    />
                                                }
                                            />
                                        </InputGroup>
                                    </Form.Group>
                                </Col>
                            </Row>
                            <p>Gere um relatório detalhado e exporte-o em formato PDF, destacando os 10 alunos com o maior número de livros lidos.</p>

                            <div className="d-flex justify-content-center">
                                <Button variant="success">
                                    <FontAwesomeIcon icon={faFileExport}/> Exportar Relatório
                                </Button>
                            </div>
                        </Accordion.Body>
                    </Accordion.Item>
                </Accordion>

                <Accordion defaultActiveKey="0" className="accordion-relatorios">
                    <Accordion.Item eventKey="0">
                        <Accordion.Header className="custon-accordion-header-blue">
                            Relatório
                        </Accordion.Header>
                        <Accordion.Body className="accordion-body-expanded">
                            <Row className="mb-3">
                                <Col md={6}>
                                    <Form.Group controlId="formData" className="mb-3">
                                        <Form.Label>Data Inicial <span className="obgr">*</span> </Form.Label>
                                        <InputGroup>
                                            <DatePicker
                                                selected={dataInicio}
                                                onChange={(date: Date) => setdataInicio(date)}
                                                dateFormat="dd/MM/yyyy"
                                                customInput={
                                                    <Form.Control
                                                        type="text"
                                                        placeholder="Selecione a data"
                                                        readOnly
                                                        style={{ cursor: "pointer" }}
                                                        className="no-border-radius-right"
                                                    />
                                                }
                                            />
                                        </InputGroup>
                                    </Form.Group>
                                </Col>
                                <Col md={6}>
                                    <Form.Group controlId="formData" className="mb-3">
                                        <Form.Label>Data Final <span className="obgr">*</span> </Form.Label>
                                        <InputGroup>
                                            <DatePicker
                                                selected={dataFinal}
                                                onChange={(date: Date) => setdataFinal(date)}
                                                dateFormat="dd/MM/yyyy"
                                                customInput={
                                                    <Form.Control
                                                        type="text"
                                                        placeholder="Selecione a data"
                                                        readOnly
                                                        style={{ cursor: "pointer" }}
                                                        className="no-border-radius-right"
                                                    />
                                                }
                                            />
                                        </InputGroup>
                                    </Form.Group>
                                </Col>
                            </Row>
                            <p>Gere um relatório detalhado e exporte-o em formato Excel, destacando os 10 alunos com o maior número de livros lidos.</p>

                            <div className="d-flex justify-content-center">
                                <Button variant="success">
                                    <FontAwesomeIcon icon={faFileExport} /> Exportar Relatório
                                </Button>
                            </div>
                        </Accordion.Body>
                    </Accordion.Item>
                </Accordion>
            </div>
        </section>
    );
};

export default Relatorios;