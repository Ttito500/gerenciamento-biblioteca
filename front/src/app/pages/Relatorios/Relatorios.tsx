import Accordion from "react-bootstrap/esm/Accordion";
import Form from "react-bootstrap/esm/Form";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
import Button from "react-bootstrap/esm/Button";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBullhorn, faPlus} from "@fortawesome/free-solid-svg-icons";

const Relatorios: React.FC = () => {
    return(
        <section className="Exemplar">
            <div className="accordion-container">
                <Accordion defaultActiveKey="0" className="accordion-relatorios">
                    <Accordion.Item eventKey="0">
                        <Accordion.Header className="custon-accordion-header-cyan">
                            Relatório
                        </Accordion.Header>
                        <Accordion.Body className="accordion-body-expanded text-center">
                            <p>Gere um relatório detalhado e exporte-o em formato Excel, destacando os 10 alunos com o
                                maior número de livros lidos.</p>
                            <Button variant="info" className="btn-blue mt-3">
                                <FontAwesomeIcon icon={faPlus}/> Exportar Relatório
                            </Button>
                        </Accordion.Body>
                    </Accordion.Item>
                </Accordion>

                <Accordion defaultActiveKey="0" className="accordion-relatorios">
                    <Accordion.Item eventKey="0">
                        <Accordion.Header className="custon-accordion-header-cyan">
                            Relatório
                        </Accordion.Header>
                        <Accordion.Body className="accordion-body-expanded text-center">
                            <p>Gere um relatório detalhado e exporte-o em formato Excel, destacando os 10 alunos com o
                                maior número de livros lidos.</p>
                            <Button variant="info" className="btn-blue mt-3">
                                <FontAwesomeIcon icon={faPlus}/> Exportar Relatório
                            </Button>
                        </Accordion.Body>
                    </Accordion.Item>
                </Accordion>
            </div>


        </section>
    );
};

export default Relatorios;