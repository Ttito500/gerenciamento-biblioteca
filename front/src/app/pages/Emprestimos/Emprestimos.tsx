import React, { ChangeEvent, useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheck, faFileImport } from "@fortawesome/free-solid-svg-icons";
import ListagemEmprestimos from "./templates/ListagemEmprestimos";
import FiltrosEmprestimo from "./templates/FiltrosEmprestimo";
import Modal from "react-bootstrap/Modal";
import CadastrarEmprestimo from "./templates/CadastrarEmprestimo";
import Spinner from "react-bootstrap/Spinner";
import Toast from "react-bootstrap/Toast";
import ToastContainer from "react-bootstrap/ToastContainer";
import ConfirmarEntrega from "./templates/ConfirmarEntrega";
import RenovarPrazo from "./templates/RenovarPrazo";
import CancelarEmprestimo from "./templates/CancelarEmprestimo";
import { CreateEmprestimoRequest, EmprestimosFiltros, GetEmprestimoResponse } from "./../../interfaces/emprestimo";
import { createEmprestimo, getEmprestimos } from "./../../api/EmprestimoApi";
import { ResponsePagination } from "./../../interfaces/pagination";
import Pagination from "react-bootstrap/Pagination";

const Emprestimo: React.FC = () => {
  const [loading, setLoading] = useState<boolean>(true);
  const [showToastError, setShowToastError] = useState(false);
  const [showToastSuccess, setShowToastSuccess] = useState(false);

  const [showReceber, setShowReceber] = useState(false);
  const handleCloseReceber = () => setShowReceber(false);
  const handleShowReceber = () => setShowReceber(true);

  const [showRenovar, setShowRenovar] = useState(false);
  const handleCloseRenovar = () => setShowRenovar(false);
  const handleShowRenovar = () => setShowRenovar(true);

  const [showCancelar, setShowCancelar] = useState(false);
  const handleCloseCancelar = () => setShowCancelar(false);
  const handleShowCancelar = () => setShowCancelar(true);

  const [emprestimos, setEmprestimos] = useState<ResponsePagination<GetEmprestimoResponse>>();

  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(0);
  const sizePage = 10;

  const [formDataFiltrar, setFormDataFiltrar] = useState({
    isbn: '',
    nomeAluno: '',
    tituloLivro: '',
    situacao: '',
    nomeRealizadoPor: '',
    nomeConcluidoPor: '',
    dataEmprestimo: '',
    dataConclusao: '',
    dataPrazo: '',
    page: currentPage,
    size: sizePage
  } as EmprestimosFiltros);

  const handleChangeFiltros = (e: ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;
    setFormDataFiltrar({ ...formDataFiltrar, [name]: value });
  };

  const handlePageChange = (pageNumber: number) => {
    setCurrentPage(pageNumber);
  };

  useEffect(() => {
    listarEmprestimos();
  }, []);

  useEffect(() => {
    listarEmprestimos();
  }, [currentPage]);

  const listarEmprestimos = async (): Promise<void> => {
    setLoading(true);

    try {
      const filtros: EmprestimosFiltros = {
        isbn: formDataFiltrar.isbn,
        nomeAluno: formDataFiltrar.nomeAluno,
        tituloLivro: formDataFiltrar.tituloLivro,
        situacao: formDataFiltrar.situacao,
        nomeRealizadoPor: formDataFiltrar.nomeRealizadoPor,
        nomeConcluidoPor: formDataFiltrar.nomeConcluidoPor,
        dataEmprestimo: formDataFiltrar.dataEmprestimo,
        dataConclusao: formDataFiltrar.dataConclusao,
        dataPrazo: formDataFiltrar.dataPrazo,
        page: (currentPage - 1),
        size: sizePage
      }
      console.log(filtros)
      const data = await getEmprestimos(filtros);
      setEmprestimos(data);
      setTotalPages(data.totalPages);
    } catch (err) {
      setShowToastError(true);
    } finally {
      setLoading(false);
    }
  };

  const [showCadastrar, setShowCadastrar] = useState(false);
  const handleCloseCadastrar = () => setShowCadastrar(false);
  const handleShowCadastrar = () => setShowCadastrar(true);

  const [formDataCadastrarEmprestimo, setFormDataCadastrarEmprestimo] = useState({
    idAluno: null as number,
    idExemplar: null as number,
    idUsuario: null as number,
    observacao: ""
  } as CreateEmprestimoRequest);

  const handleChangeCadastrarEmprestimo = (e: ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;
    setFormDataCadastrarEmprestimo({ ...formDataCadastrarEmprestimo, [name]: value });
  };

  const handleSubmitCadastrarEmprestimo = async (): Promise<void> => {
    try {
      const body: CreateEmprestimoRequest = {
        idAluno: Number(formDataCadastrarEmprestimo.idAluno),
        idExemplar: Number(formDataCadastrarEmprestimo.idExemplar),
        idUsuario: 1,
        observacao: formDataCadastrarEmprestimo.observacao
      }
      await createEmprestimo(body);

      listarEmprestimos();
      setShowToastSuccess(true);
      setFormDataCadastrarEmprestimo({
        idAluno: null as number,
        idExemplar: null as number,
        idUsuario: null as number,
        observacao: ""
      });
      handleCloseCadastrar();
    } catch (err) {
      setShowToastError(true);
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
          <FontAwesomeIcon icon={faFileImport} /> Realizar Empréstimo
        </Button>

        <Modal
          show={showCadastrar}
          onHide={handleCloseCadastrar}
          size="xl"
          backdrop="static"
          centered
          keyboard={false}
        >
          <Modal.Header closeButton>
            <Modal.Title>Realizar Empréstimo</Modal.Title>
          </Modal.Header>

          <Modal.Body>
            <CadastrarEmprestimo formData={formDataCadastrarEmprestimo} onChange={handleChangeCadastrarEmprestimo} />
          </Modal.Body>

          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseCadastrar}>
              Cancelar
            </Button>
            <Button variant="success" onClick={handleSubmitCadastrarEmprestimo}>
              <FontAwesomeIcon icon={faCheck}/> Salvar
            </Button>
          </Modal.Footer>
        </Modal>

        <Modal
          show={showReceber}
          onHide={handleCloseReceber}
          size="lg"
          backdrop="static"
          centered
          keyboard={false}
        >
          <Modal.Header closeButton>
            <Modal.Title>Concluir Empréstimo</Modal.Title>
          </Modal.Header>

          <Modal.Body>
            <ConfirmarEntrega />
          </Modal.Body>

          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseReceber}>
              Cancelar
            </Button>
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
          keyboard={false}
        >
          <Modal.Header closeButton>
            <Modal.Title>Renovar Prazo</Modal.Title>
          </Modal.Header>

          <Modal.Body>
            <RenovarPrazo />
          </Modal.Body>

          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseRenovar}>
              Cancelar
            </Button>
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
          keyboard={false}
        >
          <Modal.Header closeButton>
            <Modal.Title>Cancelar Empréstimo</Modal.Title>
          </Modal.Header>

          <Modal.Body>
            <CancelarEmprestimo />
          </Modal.Body>

          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseCancelar}>
              Cancelar
            </Button>
            <Button variant="danger">
              <FontAwesomeIcon icon={faCheck} /> Cancelar Empréstimo
            </Button>
          </Modal.Footer>
        </Modal>
      </div>

      <div className="w-100">
        <FiltrosEmprestimo 
          formData={formDataFiltrar} 
          onChange={handleChangeFiltros} 
          onSearch={listarEmprestimos} 
        />
      </div>

      <div className="w-100">
        <ListagemEmprestimos 
          emprestimos={emprestimos} 
          onCancelar={handleShowCancelar} 
          onConcluir={handleShowReceber} 
          onRenovar={handleShowRenovar} 
        />

        <Pagination>
          <Pagination.First onClick={() => handlePageChange(1)} disabled={currentPage === 1} />
          <Pagination.Prev onClick={() => handlePageChange((currentPage - 1))} disabled={currentPage === 1} />
          <Pagination.Item
            active={true}
          >
            {currentPage}
          </Pagination.Item>
          <Pagination.Next onClick={() => handlePageChange((currentPage + 1))} disabled={currentPage === totalPages} />
          <Pagination.Last onClick={() => handlePageChange(totalPages)} disabled={currentPage === totalPages} />
        </Pagination>
      </div>
    </section>
  );
};

export default Emprestimo;
