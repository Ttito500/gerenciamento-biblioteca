import React, { ChangeEvent, useEffect, useState } from "react";
import Button from 'react-bootstrap/Button';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faCheck, faUsersRectangle } from '@fortawesome/free-solid-svg-icons';
import AlunosListagem from './templates/AlunosListagem';
import AlunosFiltros from "./templates/AlunosFiltros";
import Modal from 'react-bootstrap/Modal';
import AlunosCadastrarAluno from "./templates/AlunosCadastrarAluno";
import { AlunoFiltros, CreateAlunoRequest, GetAlunoResponse, UpdateAlunoRequest } from "./../../interfaces/aluno";
import { createAluno, inativarAluno, getAlunos, updateAluno, ativarAluno } from "../../api/AlunosApi";
import Spinner from "react-bootstrap/Spinner";
import ToastContainer from "react-bootstrap/ToastContainer";
import Toast from "react-bootstrap/Toast";
import AlunosEditarAluno from "./templates/AlunosEditarAluno";
import AlunosEmprestimosAluno from "./templates/AlunosEmprestimosAluno";
import GerenciarTurmas from "./turmas/GerenciarTurmas";

const Alunos: React.FC = () => {

  const[showGerenciarTurmas, setShowGerenciarTurmas] = useState(false)
  const handleCloseGerenciarTurmas = () => setShowGerenciarTurmas(false);
  const handleShowGerenciarTurmas = () => setShowGerenciarTurmas(true);

	const [showEditar, setShowEditar] = useState(false);
  const handleCloseEditar = () => setShowEditar(false);
  const handleShowEditar = (aluno: GetAlunoResponse) => {
		setEditingAluno(aluno);
		setFormDataEditarAluno({
      nome: aluno.nome, 
      email: aluno.email, 
      situacao: aluno.situacao, 
      telefone: aluno.telefone, 
      idTurma: aluno.turma.id 
    });
		setShowEditar(true);
	}

  const [showEmprestimos, setShowEmprestimos] = useState(false);
  const handleCloseEmprestimos = () => setShowEmprestimos(false);
  const handleShowEmprestimos = () => setShowEmprestimos(true);

  const [showActiveAluno, setShowActiveAluno] = useState(false);
  const handleCloseActiveAluno = () => setShowActiveAluno(false);
  const handleShowActiveAluno = (id: number) => {
		setActivatingAluno(id);
		setShowActiveAluno(true);
	}

  const [showInactiveAluno, setShowInactiveAluno] = useState(false);
  const handleCloseInactiveAluno = () => setShowInactiveAluno(false);
  const handleShowInactiveAluno = (id: number) => {
		setInactivatingAluno(id);
		setShowInactiveAluno(true);
	}

	const [alunos, setAlunos] = useState<GetAlunoResponse[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
	const [editingAluno, setEditingAluno] = useState<GetAlunoResponse | null>(null);
	const [activatingAluno, setActivatingAluno] = useState<number | null>(null);
	const [inactivatingAluno, setInactivatingAluno] = useState<number | null>(null);
	const [showToastError, setShowToastError] = useState(false);
	const [showToastSuccess, setShowToastSuccess] = useState(false);

  useEffect(() => {
    listarAlunos();
  }, []);

  const listarAlunos = async (): Promise<void> => {
		setLoading(true);

    try {
      const filtros: AlunoFiltros = {
        nome: formDataFiltrar.nome,
        situacao: formDataFiltrar.situacao,
        serie: formDataFiltrar.serie,
        turma: formDataFiltrar.turma,
        ativo: formDataFiltrar.ativo
      }
      const data = await getAlunos(filtros);
      setAlunos(data);
    } catch (err) {
      setShowToastError(true);
    } finally {
      setLoading(false);
    }
  };

	const [showCadastrar, setShowCadastrar] = useState(false);
	const handleCloseCadastrar = () => setShowCadastrar(false);
	const handleShowCadastrar = () => setShowCadastrar(true);

	const [formDataCadastrarAluno, setFormDataCadastrarAluno] = useState({
    idTurma: null as number,
    nome: '',
    telefone: '',
    email: '',
  });

  const [formDataFiltrar, setFormDataFiltrar] = useState({
    nome: '',
    serie: null as number,
    turma: '',
    situacao: '',
    ativo: true
  });

	const handleChangeCadastrarAluno = (e: ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;
    setFormDataCadastrarAluno({ ...formDataCadastrarAluno, [name]: value });
  };

  const handleChangeFiltrar = (e: ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;

    if(name == "ativo") {
      setFormDataFiltrar({...formDataFiltrar,
        ativo: value === "true" ? true : value === "false" ? false : null,
      });
    } else {
      setFormDataFiltrar({ ...formDataFiltrar, [name]: value });
    }
  };

  const handleSubmitCadastrarAluno = async (): Promise<void> => {
    try {
			const body: CreateAlunoRequest = {
				email: formDataCadastrarAluno.email,
				idTurma: Number(formDataCadastrarAluno.idTurma),
				nome: formDataCadastrarAluno.nome,
				telefone: formDataCadastrarAluno.telefone
			}
      await createAluno(body);

      listarAlunos();
			setShowToastSuccess(true);
			setFormDataCadastrarAluno(null);
      handleCloseCadastrar();
    } catch (err) {
			setShowToastError(true);
    }
  };

	const [formDataEditarAluno, setFormDataEditarAluno] = useState({
    idTurma: null as number,
    nome: '',
    telefone: '',
    email: '',
		situacao: ''
  });

	const handleChangeEditarAluno = (e: ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;
    setFormDataEditarAluno({ ...formDataEditarAluno, [name]: value });
  };

  const handleSubmitEditarAluno = async (): Promise<void> => {
    try {
			const body: UpdateAlunoRequest = {
				email: formDataEditarAluno.email,
				situacao: formDataEditarAluno.situacao,
				idTurma: Number(formDataEditarAluno.idTurma),
				nome: formDataEditarAluno.nome,
				telefone: formDataEditarAluno.telefone
			}
      await updateAluno(editingAluno.id, body);

      listarAlunos();
			setShowToastSuccess(true);
			setFormDataEditarAluno(null);
      handleCloseEditar();
    } catch (err) {
      setShowToastError(true);
    }
  };

	const handleSubmitActiveInactiveAluno = async (ativo: boolean): Promise<void> => {
    try {

      if(ativo) {
        await inativarAluno(inactivatingAluno);
        handleCloseInactiveAluno();
      } else {
        await ativarAluno(activatingAluno);
        handleCloseActiveAluno();
      }

      listarAlunos();
			setShowToastSuccess(true);
      
    } catch (err) {
      setShowToastError(true);
    }
  };

	if (loading) {
    return <Spinner animation="border" role="status"><span className="visually-hidden">Carregando...</span></Spinner>;
  }

	return (
		<section className="alunos">
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
				
			<Modal
        show={showEditar}
        onHide={handleCloseEditar}
        size="xl"
        backdrop="static"
        centered
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Editar Aluno</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <AlunosEditarAluno formData={formDataEditarAluno} onChange={handleChangeEditarAluno}/>
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseEditar}>
            Cancelar
          </Button>
          <Button variant="success" onClick={handleSubmitEditarAluno}>
            <FontAwesomeIcon icon={faCheck} /> Salvar
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal
        show={showEmprestimos}
        onHide={handleCloseEmprestimos}
        size="xl"
        backdrop="static"
        centered
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Empréstimos do Aluno</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <AlunosEmprestimosAluno />
        </Modal.Body>

        <Modal.Footer>
          <Button variant="info" className="btn-blue" onClick={handleCloseEmprestimos}>Ok</Button>
        </Modal.Footer>
      </Modal>

      <Modal
        show={showActiveAluno}
        onHide={handleCloseActiveAluno}
        size="lg"
        backdrop="static"
        centered
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Confirmação</Modal.Title>
        </Modal.Header>

        <Modal.Body>Tem certeza que deseja ativar este aluno?</Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseActiveAluno}>
            Cancelar
          </Button>
          <Button variant="success" onClick={() => handleSubmitActiveInactiveAluno(false)}>Ativar</Button>
        </Modal.Footer>
      </Modal>

      <Modal
        show={showInactiveAluno}
        onHide={handleCloseInactiveAluno}
        size="lg"
        backdrop="static"
        centered
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Confirmação</Modal.Title>
        </Modal.Header>

        <Modal.Body>Tem certeza que deseja inativar este aluno?</Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseInactiveAluno}>Cancelar</Button>
          <Button variant="danger" onClick={() => handleSubmitActiveInactiveAluno(true)}>Inativar</Button>
        </Modal.Footer>
      </Modal>

			<div className="alunos-acoes">
				<Button variant="info" className="btn-blue" onClick={handleShowCadastrar}>
					<FontAwesomeIcon icon={faPlus} /> Cadastrar Novo Aluno
				</Button>

				<Modal
					show={showCadastrar}
					onHide={handleCloseCadastrar}
					size="xl"
					backdrop="static"
					centered
					keyboard={false}>

					<Modal.Header closeButton>
						<Modal.Title>Cadastrar Novo Aluno</Modal.Title>
					</Modal.Header>

					<Modal.Body>
						<AlunosCadastrarAluno formData={formDataCadastrarAluno} onChange={handleChangeCadastrarAluno} />
					</Modal.Body>

					<Modal.Footer>
						<Button variant="secondary" onClick={handleCloseCadastrar}>Cancelar</Button>
						<Button variant="success" onClick={handleSubmitCadastrarAluno}><FontAwesomeIcon icon={faCheck} /> Salvar</Button>
					</Modal.Footer>
				</Modal>

        <Button variant="info" className="btn-orange" onClick={handleShowGerenciarTurmas}>
          <FontAwesomeIcon icon={faUsersRectangle} /> Gerenciar Turmas
        </Button>

        <Modal
          show={showGerenciarTurmas}
          onHide={handleCloseGerenciarTurmas}
          size="lg"
          backdrop="static"
          centered
          keyboard={false}>

          <Modal.Header closeButton>
            <Modal.Title>Gerenciar Turmas</Modal.Title>
          </Modal.Header>

          <Modal.Body>
            <GerenciarTurmas/>
          </Modal.Body>

          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseGerenciarTurmas}>Ok</Button>
          </Modal.Footer>
        </Modal>

			</div>

			<div className="w-100">
				<AlunosFiltros formData={formDataFiltrar} onSearch={listarAlunos} onChange={handleChangeFiltrar}  />
			</div>

			<div className="w-100">
				<AlunosListagem 
					alunos={alunos} 
					onEdit={handleShowEditar} 
					onActive={handleShowActiveAluno} 
          onInactive={handleShowInactiveAluno}
					onEmprestimos={handleShowEmprestimos} 
				/>
			</div>
		</section>
	);
};

export default Alunos;
