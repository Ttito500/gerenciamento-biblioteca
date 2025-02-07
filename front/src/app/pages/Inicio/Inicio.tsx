import React, { useEffect, useState } from "react";
import Button from "react-bootstrap/esm/Button";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faClipboardList, faPlus, faBullhorn} from "@fortawesome/free-solid-svg-icons";
import Accordion from "react-bootstrap/esm/Accordion";
import Form from "react-bootstrap/esm/Form";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
import Spinner from "react-bootstrap/esm/Spinner";

const Inicio: React.FC = () => {

  const [loading, setLoading] = useState<boolean>(true);
  const [showToastError, setShowToastError] = useState(false);
  const [showToastSuccess, setShowToastSuccess] = useState(false);

  useEffect(() => {
    TelaInicio();
  }, []);

  const TelaInicio = async (): Promise<void> => {
    setLoading(true);

    try {
      /* empty */
    } catch (err) {
      setShowToastError(true);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
        <Spinner animation="border" role="status">
          <span className="visually-hidden">Carregando...</span>
        </Spinner>
    );
  }

  return (
      <section className="Exemplar">
        <div className="Exemplar-acoes">

        </div>

        <div className="w-100">
            <Accordion defaultActiveKey="0">
                <Accordion.Item eventKey="0">
                    <Accordion.Header className="custon-accordion-header-green">Frequência</Accordion.Header>
                    <Accordion.Body className="accordion-body-expanded">
                        <Form className="mt-0">
                            <Row>
                                <Col xs={6}>
                                    <Form.Group className="mb-3">
                                        <Form.Label>
                                            Nome <span className="obgr">*</span>
                                        </Form.Label>
                                        <Form.Control
                                            type="text"
                                            placeholder="Busque pelo nome do aluno"
                                            required
                                        />
                                        <Form.Control.Feedback type="invalid">
                                            Campo obrigatório.
                                        </Form.Control.Feedback>
                                    </Form.Group>
                                </Col>
                                <Col xs={4}>
                                    <Form.Group className="mb-3">
                                        <Form.Label>
                                            Atividade <span className="obgr">*</span>
                                        </Form.Label>
                                        <Form.Select aria-label="Selecione" required className="custom-placeholder">
                                            <option value="" disabled selected hidden>Atividade que o aluno está fazendo</option>
                                            <option value="1">Estudando</option>
                                            <option value="2">Dormindo</option>
                                            <option value="3">Vivendo</option>
                                        </Form.Select>
                                        <Form.Control.Feedback type="invalid">
                                            Campo obrigatório.
                                        </Form.Control.Feedback>
                                    </Form.Group>
                                </Col>
                                <Col xs={2} className="d-flex justify-content-end" style={{ marginTop: "30px" }}>
                                    <Button variant="info" className="btn-orange resizable-button">
                                    <FontAwesomeIcon icon={faPlus} /> Registrar</Button>
                                </Col>
                            </Row>
                            <Row>
                                <Col className="justify-content-start">
                                    <Button variant="info" className="btn-blue">
                                    <FontAwesomeIcon icon={faPlus} /> Cadstrar Novo Aluno
                                    </Button>
                                </Col>
                                <Col xs ={2} className="d-flex justify-content-end">
                                    <Button variant="info" className="btn-blue resizable-button">
                                        <FontAwesomeIcon icon={faClipboardList} /> Ver Frequências
                                    </Button>
                                </Col>
                            </Row>
                        </Form>
                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
        </div>

        <div className="w-100">
            <Accordion defaultActiveKey="0">
                <Accordion.Item eventKey="0">
                    <Accordion.Header className="custon-accordion-header-red">Ocorrência</Accordion.Header>
                    <Accordion.Body className="accordion-body-expanded">
                        <Form className="mt-0">
                            <Row>
                                <Col xs={6}>
                                    <Form.Group className="mb-3">
                                        <Form.Label>
                                            Nome <span className="obgr">*</span>
                                        </Form.Label>
                                        <Form.Control
                                            type="text"
                                            placeholder="Busque pelo nome do aluno"
                                            required
                                        />
                                        <Form.Control.Feedback type="invalid">
                                            Campo obrigatório.
                                        </Form.Control.Feedback>
                                    </Form.Group>
                                </Col>
                                <Col xs={4}>
                                    <Form.Group className="mb-3">
                                        <Form.Label>
                                            Detalhes <span className="obgr">*</span>
                                        </Form.Label>
                                        <Form.Control
                                            type="text"
                                            placeholder="Digite os detalhes da ocorrência"
                                            required
                                        />
                                        <Form.Control.Feedback type="invalid">
                                            Campo obrigatório.
                                        </Form.Control.Feedback>
                                    </Form.Group>
                                </Col>
                                <Col xs={2} className="d-flex justify-content-end" style={{ marginTop: "30px" }}>
                                    <Button variant="info" className="btn-danger resizable-button">
                                        <FontAwesomeIcon icon={faPlus} /> Ocorrência</Button>
                                </Col>
                            </Row>
                            <Row>
                                <Col className="justify-content-start">
                                    <Button variant="info" className="btn-blue">
                                        <FontAwesomeIcon icon={faPlus} /> Cadstrar Novo Aluno
                                    </Button>
                                </Col>
                                <Col xs ={2} className="d-flex justify-content-end">
                                    <Button variant="info" className="btn-blue resizable-button">
                                        <FontAwesomeIcon icon={faBullhorn} /> Ver Ocorrências
                                    </Button>
                                </Col>
                            </Row>
                        </Form>
                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
        </div>
      </section>
  );
};

export default Inicio;