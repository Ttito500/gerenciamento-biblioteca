import Col from "react-bootstrap/esm/Col";
import Form from "react-bootstrap/esm/Form";
import Row from "react-bootstrap/esm/Row";
import Table from "react-bootstrap/Table";
import Badge from "react-bootstrap/Badge";

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
                <Table striped className="tabela">
                    <thead>
                    <tr>
                        <th className="th-center-size-eight">Exemplar</th>
                        <th>Aluno</th>
                        <th className="th-center-size-eight">Série</th>
                        <th className="th-center-size-eight">Turma</th>
                        <th className="th-center-size-eighteen">Data de Empréstimo</th>
                        <th className="th-center-size-eight">Prazo</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr className="tabela-tr">
                        <td className="text-center">4</td>
                        <td>João Silva</td>
                        <td className="text-center">1</td>
                        <td className="text-center">A</td>
                        <td className="text-center">07/02/2025</td>
                        <td className="text-center">
                            <Badge className="bibliotech-badge" bg="green">21/02/2025</Badge>
                        </td>
                    </tr>
                    <tr className="tabela-tr">
                        <td className="text-center">2</td>
                        <td>Ana Oliveira</td>
                        <td className="text-center">2</td>
                        <td className="text-center">B</td>
                        <td className="text-center">05/02/2025</td>
                        <td className="text-center">
                            <Badge className="bibliotech-badge" bg="red">12/02/2025</Badge>
                        </td>
                    </tr>
                    <tr className="tabela-tr">
                        <td className="text-center">1</td>
                        <td>Pedro Souza</td>
                        <td className="text-center">3</td>
                        <td className="text-center">C</td>
                        <td className="text-center">06/02/2025</td>
                        <td className="text-center">
                            <Badge className="bibliotech-badge" bg="green">16/02/2025</Badge>
                        </td>
                    </tr>
                    </tbody>
                </Table>
            </div>
        </section>
    );
};

export default EmprestimosLivro;
