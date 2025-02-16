import Col from "react-bootstrap/esm/Col";
import Form from "react-bootstrap/esm/Form";
import Row from "react-bootstrap/esm/Row";
import AcervoEmprestimosLivro from "./AcervoEmprestimosLivro";

const EmprestimosLivro: React.FC = () => {
    return (
        <section className="Exemplar">
            <div>
                <Form className="mt-0">
                    <Row>
                        <Col xs={3}>
                            <Form.Group className="mb-3">
                                <Form.Label>Data do Empréstimo</Form.Label>
                                <Form.Select
                                    aria-label="Selecione"
                                    required
                                    className="custom-placeholder"
                                >
                                    <option value="1">Últimos 30 dias</option>
                                    <option value="2">Últimos 60 dias</option>
                                    <option value="3">Últimos 90 dias</option>
                                </Form.Select>
                            </Form.Group>
                        </Col>
                    </Row>
                </Form>
            </div>
            <div className="w-100 mt-0">
                <AcervoEmprestimosLivro/>
            </div>
        </section>
    );
};

export default EmprestimosLivro;
