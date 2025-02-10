import { Button, Form, Row, Col, Accordion } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

const FiltrosTurma: React.FC = () => {
  return (
    <>
      <Accordion defaultActiveKey="0">
        <Accordion.Item eventKey="0">
          <Accordion.Header>Filtros</Accordion.Header>
          <Accordion.Body>
            <Form>
              <Row>
                <Col>
                  <Form.Group className="mb-3">
                    <Form.Label>
                      Série <span className="obgr">*</span>
                    </Form.Label>
                    <Form.Select aria-label="Selecione">
                      <option>Selecione</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                    </Form.Select>
                    <Form.Control.Feedback type="invalid">
                      Campo obrigatório.
                    </Form.Control.Feedback>
                  </Form.Group>
                </Col>
                <Col>
                  <Form.Group className="mb-3">
                    <Form.Label>
                      Turma <span className="obgr">*</span>
                    </Form.Label>
                    <Form.Select aria-label="Selecione">
                      <option>Selecione</option>
                      <option value="1">A</option>
                      <option value="2">B</option>
                      <option value="3">C</option>
                    </Form.Select>
                    <Form.Control.Feedback type="invalid">
                      Campo obrigatório.
                    </Form.Control.Feedback>
                  </Form.Group>
                </Col>
                <Col>
                  <Form.Group className="mb-3">
                    <Form.Label>
                      Ano de Entrada <span className="obgr">*</span>
                    </Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="Digite o ano de entrada"
                    />
                    <Form.Control.Feedback type="invalid">
                      Campo obrigatório.
                    </Form.Control.Feedback>
                  </Form.Group>
                </Col>
              </Row>
              <Row>
                <Col>
                  <Form.Group as={Row} className="mb-3">
                    <Form.Label as="legend" column sm={2}>
                      Ativo
                    </Form.Label>
                    <Row className="m-0">
                      <Form.Check
                        className="alunos-filtros-radio"
                        type="radio"
                        label="Todos"
                        defaultChecked
                        name="formHorizontalRadios"
                        id="formHorizontalRadios1"
                      />
                      <Form.Check
                        className="alunos-filtros-radio"
                        type="radio"
                        label="Sim"
                        name="formHorizontalRadios"
                        id="formHorizontalRadios2"
                      />
                      <Form.Check
                        className="alunos-filtros-radio"
                        type="radio"
                        label="Não"
                        name="formHorizontalRadios"
                        id="formHorizontalRadios3"
                      />
                    </Row>
                  </Form.Group>
                </Col>
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
    </>
  );
};

export default FiltrosTurma;
