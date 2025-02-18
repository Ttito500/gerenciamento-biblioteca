import React, { useState } from "react";
import { Table } from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheck, faPlus} from "@fortawesome/free-solid-svg-icons";
import Button from "react-bootstrap/esm/Button";
import Modal from "react-bootstrap/esm/Modal";
import CadastraAlunoMonitor from "./Templates/CadastraAlunoMonitor";

const Cronograma: React.FC = () => {

    const [showCadastrar, setShowCadastrar] = useState(false);
    const handleCloseCadastrar = () => setShowCadastrar(false);
    const handleShowCadastrar = () => setShowCadastrar(true);


    return (
        <section className="Exemplar">
            <div className="Exemplar-acoes"></div>

            <div className="w-100">

            </div>

            <div className="w-100">
                <Button
                    variant="info"
                    className="btn-orange"
                    onClick={handleShowCadastrar}
                >
                    <FontAwesomeIcon icon={faPlus} /> Adicionar Monitor ao Cronograma
                </Button>

                <Modal
                    show={showCadastrar}
                    onHide={handleCloseCadastrar}
                    size="lg"
                    backdrop="static"
                    centered
                    keyboard={false}
                >
                    <Modal.Header closeButton>
                        <Modal.Title>Adicionar Aluno</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <CadastraAlunoMonitor />
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleCloseCadastrar}>
                            Cancelar
                        </Button>
                        <Button variant="success">
                            <FontAwesomeIcon icon={faCheck} /> Salvar
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>

            <div className="w-100">
                <Table striped className="cronograma">
                    <thead>
                    <tr>
                        <th className="text-center">Segunda</th>
                        <th className="text-center">Terça</th>
                        <th className="text-center">Quarta</th>
                        <th className="text-center">Quinta</th>
                        <th className="text-center">Sexta</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr className="cronograma-tr">
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                    </tr>
                    <tr className="cronograma-tr">
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                    </tr>
                    <tr className="cronograma-tr">
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                    </tr>
                    <tr className="cronograma-tr">
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                    </tr>
                    </tbody>
                </Table>
            </div>

        </section>
    );
};

export default Cronograma;