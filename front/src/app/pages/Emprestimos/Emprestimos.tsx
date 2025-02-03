import React, { ChangeEvent, useEffect, useState } from "react";
import Button from 'react-bootstrap/Button';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faCheck, faFileImport, faCalendarCheck, faCalendarPlus, faCircleXmark} from '@fortawesome/free-solid-svg-icons';
import ListagemEmprestimos from './templates/ListagemEmprestimos';
import FiltrosEmprestimo from "./templates/FiltrosEmprestimo";
import Modal from 'react-bootstrap/Modal';
import CadastrarEmprestimo from "./templates/CadastrarEmprestimo";
import Spinner from "react-bootstrap/Spinner";
import ConfirmarEntrega from "./templates/ConfirmarEntrega";
import CancelarEmprestimo from "./templates/CancelarEmprestimo";
import RenovarPrazo from "./templates/RenovarPrazo";
import Toast from 'react-bootstrap/Toast';
import ToastContainer from 'react-bootstrap/ToastContainer';

const Emprestimo: React.FC = () => {


    const [loading, setLoading] = useState<boolean>(true);
    const [showToastError, setShowToastError] = useState(false);
    const [showToastSuccess, setShowToastSuccess] = useState(false);

    useEffect(() => {
        TelaEmprestimo();
    }, []);

    const TelaEmprestimo = async (): Promise<void> => {
        setLoading(true);

        try { /* empty */ } catch (err) {
            setShowToastError(true);
        } finally {
            setLoading(false);
        }
    };

    const [showCadastrar, setShowCadastrar] = useState(false);
    const handleCloseCadastrar = () => setShowCadastrar(false);
    const handleShowCadastrar = () => setShowCadastrar(true);

    const [showReceber, setShowReceber] = useState(false)
    const handleCloseReceber = () =>setShowReceber(false);
    const handleShowReceber = () => setShowReceber(true);

    const [showRenovar, setShowRenovar] = useState(false)
    const handleCloseRenovar = () => setShowRenovar(false);
    const handleShowRenovar = () => setShowRenovar(true);

    const[showCancelar, setShowCancelar] = useState(false)
    const handleCloseCancelar = () => setShowCancelar(false);
    const handleShowCancelar = () => setShowCancelar(true);

    if (loading) {
        return <Spinner animation="border" role="status"><span className="visually-hidden">Carregando...</span></Spinner>;
    }

    return (
        <section className="emprestimos">
            <div className="emprestimos-acoes">

                <ToastContainer
                    className="p-3"
                    position="bottom-center"
                    style={{ zIndex: 10 }}
                >
                    <Toast bg="success" onClose={() => setShowToastSuccess(false)} show={showToastSuccess} delay={3000} autohide>
                        <Toast.Header>
                            <strong className="me-auto">Operação realizada com sucesso!</strong>
                        </Toast.Header>
                    </Toast>
                </ToastContainer>

                <ToastContainer
                    className="p-3"
                    position="bottom-center"
                    style={{ zIndex: 10 }}
                >
                    <Toast bg="danger" onClose={() => setShowToastError(false)} show={showToastError} delay={3000} autohide>
                        <Toast.Header>
                            <strong className="me-auto">Não foi possível concluir a operação. Tente novamente.</strong>
                        </Toast.Header>
                    </Toast>
                </ToastContainer>

                <Button variant="info" className="btn-blue" onClick={handleShowCadastrar}>
                    <FontAwesomeIcon icon={faFileImport} /> Realizar Empréstimo
                </Button>

                <Modal
                    show={showCadastrar}
                    onHide={handleCloseCadastrar}
                    size="xl"
                    backdrop="static"
                    centered
                    keyboard={false}>

                    <Modal.Header closeButton>
                        <Modal.Title>Realizar Empréstimo</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <CadastrarEmprestimo/>
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleCloseCadastrar}>Desistir</Button>
                        <Button variant="success">
                            <FontAwesomeIcon icon={faCheck} /> Salvar
                        </Button>

                    </Modal.Footer>
                </Modal>

                {/*botoes para testar modais pq eu não consigui por na tabela KSksks*/}
                <Button variant="info" className="btn-orange" onClick={handleShowReceber}>
                    <FontAwesomeIcon icon={faCalendarCheck} />
                </Button>

                <Button variant="info" className="btn-blue" onClick={handleShowRenovar}>
                    <FontAwesomeIcon icon={faCalendarPlus} />
                </Button>

                <Button variant="info" className="btn-danger" onClick={handleShowCancelar}>
                    <FontAwesomeIcon icon={faCircleXmark} />
                </Button>

                <Modal
                    show={showReceber}
                    onHide={handleCloseReceber}
                    size="lg"
                    backdrop="static"
                    centered
                    keyboard={false}>

                    <Modal.Header closeButton>
                        <Modal.Title>Concluir Empréstimo</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <ConfirmarEntrega/>
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleCloseReceber}>Desistir</Button>
                        <Button variant="success">
                            <FontAwesomeIcon icon={faCheck} /> Concluir Empréstimo
                        </Button>

                    </Modal.Footer>
                </Modal>

                <Modal
                    show={showRenovar}
                    onHide={handleCloseRenovar}
                    size="lg"
                    backdrop="static"
                    centered
                    keyboard={false}>

                    <Modal.Header closeButton>
                        <Modal.Title>Renovar Prazo</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <RenovarPrazo/>
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleCloseRenovar}>Desistir</Button>
                        <Button variant="success">
                            <FontAwesomeIcon icon={faCheck} /> Renovar Prazo
                        </Button>

                    </Modal.Footer>
                </Modal>

                <Modal
                    show={showCancelar}
                    onHide={handleCloseCancelar}
                    size="lg"
                    backdrop="static"
                    centered
                    keyboard={false}>

                    <Modal.Header closeButton>
                        <Modal.Title>Cancelar Empréstimo</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <CancelarEmprestimo/>
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleCloseCancelar}>Desistir</Button>
                        <Button variant="danger">
                            <FontAwesomeIcon icon={faCheck} /> Cancelar Empréstimo
                        </Button>

                    </Modal.Footer>
                </Modal>

            </div>

            <div className="w-100">
                <FiltrosEmprestimo />
            </div>

            <div className="w-100">
                <ListagemEmprestimos/>
            </div>
        </section>
    );
};

export default Emprestimo;