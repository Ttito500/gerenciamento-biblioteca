import React, { ChangeEvent, useEffect, useState } from "react";
import Button from 'react-bootstrap/Button';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faPlus, faCheck, faTrash, faTableList, faClipboardList} from '@fortawesome/free-solid-svg-icons';
import AcervoListagem from './templates/AcervoListagem';
import AcervoFiltros from "./templates/AcervoFiltros";
import Modal from 'react-bootstrap/Modal';
import AcervoCadastrarLivro from "./templates/AcervoCadastrarLivro";
import { createLivro, deleteLivro, getLivros, updateLivro } from './../../api/AcervoApi';
import { CreateLivroRequest, GetLivroResponse, UpdateLivroRequest } from "./../../interfaces/acervo";
import AcervoEditarLivro from "./templates/AcervoEditarLivro";
import AcervoEmprestimosLivro from "./templates/AcervoEmprestimosLivro";
import AcervoRealizarEmprestimo from "./templates/AcervoRealizarEmprestimo";
import Exemplares from "./Exemplares/Exemplares";
import EmprestimosLivro from "./templates/EmprestimosLivro";

const Acervo: React.FC = () => {

	const [showEditar, setShowEditar] = useState(false);
  const handleCloseEditar = () => setShowEditar(false);
  const handleShowEditar = (livro: GetLivroResponse) => {
		setEditingLivro(livro);
		setFormDataEditarLivro(livro);
		setShowEditar(true);
	}

  const [showEmprestimos, setShowEmprestimos] = useState(false);
  const handleCloseEmprestimos = () => setShowEmprestimos(false);
  const handleShowEmprestimos = () => setShowEmprestimos(true);

  const [showRealizarEmprestimo, setShowRealizarEmprestimo] = useState(false);
  const handleCloseRealizarEmprestimo = () => setShowRealizarEmprestimo(false);
  const handleShowRealizarEmprestimo = () => setShowRealizarEmprestimo(true);

  const [showExcluirLivro, setShowExcluirLivro] = useState(false);
  const handleCloseExcluirLivro = () => setShowExcluirLivro(false);
  const handleShowExcluirLivro = (id: number) => {
		setDeletingLivro(id);
		setShowExcluirLivro(true);
	}

	const [livros, setLivros] = useState<GetLivroResponse[]>([]);
	const [editingLivro, setEditingLivro] = useState<GetLivroResponse | null>(null);
	const [deletingLivro, setDeletingLivro] = useState<number | null>(null);

	const [showExemplares, setShowExemplares] = useState(false);
	const handleCloseExemplares = () => setShowExemplares(false);
	const handleShowExemplares = () => setShowExemplares(true);

	const [showVerEmprestimos, setShowVerEmprestimos] = useState(false);
	const handleCloseVerEmprestimos = () => setShowVerEmprestimos(false);
	const handleShowVerEmprestimos = () => setShowVerEmprestimos(true);

  useEffect(() => {
    listarLivros();
  }, []);

  const listarLivros = async (): Promise<void> => {
		const data = await getLivros();
		setLivros(data);
  };

	const [showCadastrar, setShowCadastrar] = useState(false);
	const handleCloseCadastrar = () => setShowCadastrar(false);
	const handleShowCadastrar = () => setShowCadastrar(true);

	const [formDataCadastrarLivro, setFormDataCadastrarLivro] = useState({
    isbn: '',
    titulo: '',
    autor: ''
  });

	const handleChangeCadastrarLivro = (e: ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;
    setFormDataCadastrarLivro({ ...formDataCadastrarLivro, [name]: value });
  };

  const handleSubmitCadastrarLivro = async (): Promise<void> => {
		const body: CreateLivroRequest = {
			isbn: formDataCadastrarLivro.isbn,
			autor: formDataCadastrarLivro.autor,
			titulo: formDataCadastrarLivro.titulo,
			observacao: "",
			situacao: "disponivel",
			idEstantePrateleira: {
				id: 1,
				estante: 3,
				prateleira: 6
			},
			idSecao: {
				id: 1,
				nome: "literatura portuguesa"
			}
		}
		await createLivro(body);

		listarLivros();
		setFormDataCadastrarLivro(null);
		handleCloseCadastrar();
  };

	const [formDataEditarLivro, setFormDataEditarLivro] = useState({
    isbn: '',
    titulo: '',
    autor: ''
  });

	const handleChangeEditarLivro = (e: ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;
    setFormDataEditarLivro({ ...formDataEditarLivro, [name]: value });
  };

  const handleSubmitEditarLivro = async (): Promise<void> => {
		const body: UpdateLivroRequest = {
			isbn: formDataEditarLivro.isbn,
			autor: formDataEditarLivro.autor,
			titulo: formDataEditarLivro.titulo,
			observacao: "",
			situacao: "disponivel",
			idEstantePrateleira: {
				id: 1,
				estante: 3,
				prateleira: 6
			},
			idSecao: {
				id: 1,
				nome: "literatura portuguesa"
			}
		}
		await updateLivro(editingLivro.id, body);

		listarLivros();
		setFormDataEditarLivro(null);
		handleCloseEditar();
  };

	const handleSubmitExcluirLivro = async (): Promise<void> => {
		await deleteLivro(deletingLivro);

		listarLivros();
		handleCloseExcluirLivro();
  };

	return (
		<section className="indentacaoPadrao">
			<div className="indentacaoPadrao-acoes">
				<Button variant="info" className="btn-blue" onClick={handleShowCadastrar}>
					<FontAwesomeIcon icon={faPlus} /> Cadastrar Novo Livro
				</Button>

				<Modal
					show={showCadastrar}
					onHide={handleCloseCadastrar}
					size="xl"
					backdrop="static"
					centered
					keyboard={false}>

					<Modal.Header closeButton>
						<Modal.Title>Cadastrar Novo Livro</Modal.Title>
					</Modal.Header>

					<Modal.Body>
						<AcervoCadastrarLivro formData={formDataCadastrarLivro} onChange={handleChangeCadastrarLivro} />
					</Modal.Body>

					<Modal.Footer>
						<Button variant="secondary" onClick={handleCloseCadastrar}>Desistir</Button>
						<Button variant="success" onClick={handleSubmitCadastrarLivro}><FontAwesomeIcon icon={faCheck} /> Salvar</Button>
					</Modal.Footer>
				</Modal>

				<Modal
					show={showEditar}
					onHide={handleCloseEditar}
					size="xl"
					backdrop="static"
					centered
					keyboard={false}
				>
					<Modal.Header closeButton>
						<Modal.Title>Editar Livro</Modal.Title>
					</Modal.Header>

					<Modal.Body>
						<AcervoEditarLivro formData={formDataEditarLivro} onChange={handleChangeEditarLivro} />
					</Modal.Body>

					<Modal.Footer>
						<Button variant="secondary" onClick={handleCloseEditar}>
							Desistir
						</Button>
						<Button variant="success" onClick={handleSubmitEditarLivro}>
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
						<Modal.Title>
							Emprestimos do Livro: A Hora da Estrela - Clarice Lispector
						</Modal.Title>
					</Modal.Header>

					<Modal.Body>
						<AcervoEmprestimosLivro />
					</Modal.Body>

					<Modal.Footer>
						<Button
							variant="info"
							className="btn-blue"
							onClick={handleCloseEmprestimos}
						>
							Ok
						</Button>
					</Modal.Footer>
				</Modal>

				<Modal
					show={showRealizarEmprestimo}
					onHide={handleCloseRealizarEmprestimo}
					size="xl"
					backdrop="static"
					centered
					keyboard={false}
				>
					<Modal.Header closeButton>
						<Modal.Title>
							Realizar Emprestimo do Livro: A Hora da Estrela - Clarice Lispector
						</Modal.Title>
					</Modal.Header>

					<Modal.Body>
						<AcervoRealizarEmprestimo />
					</Modal.Body>

					<Modal.Footer>
						<Button variant="secondary" onClick={handleCloseRealizarEmprestimo}>
							Desistir
						</Button>
						<Button variant="success">
							<FontAwesomeIcon icon={faCheck} /> Realizar Empréstimo
						</Button>
					</Modal.Footer>
				</Modal>

				<Modal
					show={showExcluirLivro}
					onHide={handleCloseExcluirLivro}
					size="lg"
					backdrop="static"
					centered
					keyboard={false}
					className="Modais-Confirmacao-Custon"
				>
					<Modal.Header closeButton>
						<Modal.Title>Confirmação</Modal.Title>
					</Modal.Header>

					<Modal.Body>Tem certeza que deseja excluir este livro?</Modal.Body>

					<Modal.Footer>
						<Button variant="secondary" onClick={handleCloseExcluirLivro}>
							Desistir
						</Button>
						<Button variant="danger" onClick={handleSubmitExcluirLivro}>
							<FontAwesomeIcon icon={faTrash} /> Excluir
						</Button>
					</Modal.Footer>
				</Modal>

				{/*botap para teste de exemplares(LEMBRAR DE APAGAR)*/}
				<Button variant="info" className="btn-orange" onClick={handleShowExemplares	}>
					<FontAwesomeIcon icon={faTableList} />
				</Button>

				{/*botap para teste(LEMBRAR DE APAGAR)*/}
				<Button variant="info" className="btn-blue" onClick={handleShowVerEmprestimos	}>
					<FontAwesomeIcon icon={faClipboardList} />
				</Button>

				<Modal
					show={showVerEmprestimos}
					onHide={handleCloseVerEmprestimos}
					size="xl"
					backdrop="static"
					centered
					keyboard={false}
					className="Modais-VerEmprestimos-Custon"
				>

					<Modal.Header closeButton>
						<Modal.Title>Emprestimos do Livro: <span className="custom-variavel">Titulo do Livro</span></Modal.Title>
					</Modal.Header>

					<Modal.Body>
						<EmprestimosLivro/>
					</Modal.Body>

					<Modal.Footer>
						<Button variant="secondary" onClick={handleCloseVerEmprestimos}>Ok</Button>

					</Modal.Footer>
				</Modal>

				<Modal
					show={showExemplares}
					onHide={handleCloseExemplares}
					size="xl"
					backdrop="static"
					centered
					keyboard={false}>

					<Modal.Header closeButton>
						<Modal.Title>Gerenciar Exemplares</Modal.Title>
					</Modal.Header>

					<Modal.Body>
						<Exemplares/>
					</Modal.Body>

					<Modal.Footer>
						<Button variant="secondary" onClick={handleCloseExemplares}>Ok</Button>

					</Modal.Footer>
				</Modal>

			</div>

			<div className="w-100">
				<AcervoFiltros />
			</div>

			<div className="w-100">
				<AcervoListagem 
					livros={livros} 
					onEdit={handleShowEditar} 
					onDelete={handleShowExcluirLivro} 
					onEmprestimos={handleShowEmprestimos} 
					onRealizarEmprestimo={handleShowRealizarEmprestimo} 
				/>
			</div>
		</section>
	);
};

export default Acervo;
