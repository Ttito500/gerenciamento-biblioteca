import React, { useEffect, useState } from "react";
import ToastContainer from "react-bootstrap/esm/ToastContainer";
import Toast from "react-bootstrap/esm/Toast";
import Button from "react-bootstrap/esm/Button";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheck, faPlus } from "@fortawesome/free-solid-svg-icons";
import Modal from "react-bootstrap/esm/Modal";
import ListagemTurma from "../../Alunos/turmas/templates/ListagemTurma";
import FiltrosTurma from "./templates/FiltrosTurma";
import CadastrarTurma from "./templates/CadastrarTurma";

const GerenciarTurmas: React.FC = () => {
  const [loading, setLoading] = useState<boolean>(true);
  const [showToastError, setShowToastError] = useState(false);
  const [showToastSuccess, setShowToastSuccess] = useState(false);

  useEffect(() => {
    telaTurmas();
  }, []);

  const telaTurmas = async (): Promise<void> => {
    setLoading(true);

    try {
      /* empty */
    } catch (err) {
      setShowToastError(true);
    } finally {
      setLoading(false);
    }
  };

  const [showCadastrar, setShowCadastrar] = useState(false);
  const handleCloseCadastrar = () => setShowCadastrar(false);
  const handleShowCadastrar = () => setShowCadastrar(true);

  const [showEditar, setShowEditar] = useState(false);
  const handleCloseEditar = () => setShowEditar(false);
  const handleShowEditar = () => setShowEditar(true);

  const [showInativar, setShowInativar] = useState(false);
  const handleCloseInativar = () => setShowInativar(false);
  const handleShowInativar = () => setShowInativar(true);

  return (
    <section className="indentacaoPadrao">
      <div className="indentacaoPadrao-acoes">
        <ToastContainer
          className="p-3"
          position="bottom-center"
          style={{ zIndex: 10 }}
        >
          <Toast
            bg="success"
            onClose={() => setShowToastSuccess(false)}
            show={showToastSuccess}
            delay={3000}
            autohide
          >
            <Toast.Header>
              <strong className="me-auto">
                Operação realizada com sucesso!
              </strong>
            </Toast.Header>
          </Toast>
        </ToastContainer>

        <ToastContainer
          className="p-3"
          position="bottom-center"
          style={{ zIndex: 10 }}
        >
          <Toast
            bg="danger"
            onClose={() => setShowToastError(false)}
            show={showToastError}
            delay={3000}
            autohide
          >
            <Toast.Header>
              <strong className="me-auto">
                Não foi possível concluir a operação. Tente novamente.
              </strong>
            </Toast.Header>
          </Toast>
        </ToastContainer>

        <Button
          variant="info"
          className="btn-blue"
          onClick={handleShowCadastrar}
        >
          <FontAwesomeIcon icon={faPlus} /> Cadastrar Nova Turma
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
            <Modal.Title>Cadastrar Nova Turma</Modal.Title>
          </Modal.Header>

          <Modal.Body>
            <CadastrarTurma />
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
        <FiltrosTurma />
      </div>

      <div className="w-100">
        <ListagemTurma />
      </div>
    </section>
  );
};

export default GerenciarTurmas;
