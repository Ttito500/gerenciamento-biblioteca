import React from "react";
import Button from 'react-bootstrap/Button';
import Accordion from 'react-bootstrap/Accordion';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPlus, faMagnifyingGlass, faFileImport, faClipboardList, faPenToSquare, faTrash } from '@fortawesome/free-solid-svg-icons'
import Table from 'react-bootstrap/Table';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import Badge from 'react-bootstrap/Badge';


const Acervo: React.FC = () => {
  return (
    <section className="acervo">
      <div className="acervo-acoes">
        <Button variant="info" className="btn-blue">
			<FontAwesomeIcon icon={faPlus} /> Cadastrar Novo Livro
		</Button>
      </div>

		<div className="w-100">
			<Accordion defaultActiveKey="0">
				<Accordion.Item eventKey="0">
					<Accordion.Header>Filtros</Accordion.Header>
					<Accordion.Body>
						<Form>
							<Row>

								<Col>
									<Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
										<Form.Label>ISBN</Form.Label>
										<Form.Control type="text" placeholder="Digite o ISBN" />
									</Form.Group>
								</Col>

								<Col>
									<Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
										<Form.Label>Título</Form.Label>
										<Form.Control type="text" placeholder="Digite o título" />
									</Form.Group>
								</Col>

								<Col>
									<Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
										<Form.Label>Autor</Form.Label>
										<Form.Control type="text" placeholder="Digite o nome do autor" />
									</Form.Group>
								</Col>
								
							</Row>

							<Row>
								
								<Col>
									<Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
										<Form.Label>Gênero</Form.Label>
										<Form.Select aria-label="Selecione um gênero">
											<option>Todos</option>
											<option value="1">Romance</option>
											<option value="2">Drama</option>
											<option value="3">Terror</option>
										</Form.Select>
									</Form.Group>
								</Col>

								<Col>
									<Form.Group as={Row} className="mb-3">
										<Form.Label as="legend" column sm={2}>
											Status
										</Form.Label>

										<Row className="m-0">

											<Form.Check
												className="acervo-filtros-radio"
												type="radio"
												label="Todos"
												checked
												name="formHorizontalRadios"
												id="formHorizontalRadios1"
											/>
											<Form.Check
												className="acervo-filtros-radio"
												type="radio"
												label="Disponível"
												name="formHorizontalRadios"
												id="formHorizontalRadios2"
											/>
											<Form.Check
												className="acervo-filtros-radio"
												type="radio"
												label="Indisponível"
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
		</div>

		<div className="w-100">
			<Table striped className="acervo-tabela">
				<thead>
					<tr>
						<th>#</th>
						<th>ISBN</th>
						<th>Título</th>
						<th>Autor</th>
						<th>Gênero</th>
						<th>Local</th>
						<th>Total</th>
						<th>Empr.</th>
						<th>Atrasos</th>
						<th>Extrav.</th>
						<th>Disp.</th>
						<th>Status</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<tr className="">
						<td className="text-center">1</td>
						<td>123-12-12345-12-1</td>
						<td>A Hora da Estrela</td>
						<td>Clarice Lispector</td>
						<td>Romance</td>
						<td className="text-center">E3 / P1</td>
						<td className="text-center">10</td>
						<td className="text-center">3</td>
						<td className="text-center">
							<Badge bg="danger">1</Badge>
						</td>
						<td className="text-center">0</td>
						<td className="text-center">7</td>
						<td className="text-center">
							<Badge bg="success">Disponível</Badge>
						</td>
						<td>
							<ButtonGroup aria-label="Ações" className="acervo-tabela-acoes">
								<Button variant="btn-outline-secondary" className="color-orange">
									<FontAwesomeIcon icon={faFileImport} />
								</Button>

								<Button variant="btn-outline-secondary" className="color-blue">
									<FontAwesomeIcon icon={faClipboardList} />
								</Button>

								<Button variant="btn-outline-secondary" className="color-green">
									<FontAwesomeIcon icon={faPenToSquare} />
								</Button>
								
								<Button variant="btn-outline-secondary" className="color-red">
									<FontAwesomeIcon icon={faTrash} />
								</Button>
							</ButtonGroup>
						</td>
					</tr>	

					<tr className="">
						<td className="text-center">2</td>
						<td>723-82-12345-12-3</td>
						<td>A Hora da Moto</td>
						<td>Clarice Lispector</td>
						<td>Romance</td>
						<td className="text-center">E1 / P2</td>
						<td className="text-center">5</td>
						<td className="text-center">4</td>
						<td className="text-center">0</td>
						<td className="text-center">
							<Badge bg="danger">1</Badge>
						</td>
						<td className="text-center">
							<Badge bg="danger">0</Badge>
						</td>
						<td className="text-center">
							<Badge bg="danger">Indisponível</Badge>
						</td>
						<td>
							<ButtonGroup aria-label="Ações" className="acervo-tabela-acoes">
								<Button variant="btn-outline-secondary" className="color-orange">
									<FontAwesomeIcon icon={faFileImport} />
								</Button>

								<Button variant="btn-outline-secondary" className="color-blue">
									<FontAwesomeIcon icon={faClipboardList} />
								</Button>

								<Button variant="btn-outline-secondary" className="color-green">
									<FontAwesomeIcon icon={faPenToSquare} />
								</Button>
								
								<Button variant="btn-outline-secondary" className="color-red">
									<FontAwesomeIcon icon={faTrash} />
								</Button>
							</ButtonGroup>
						</td>
					</tr>	
				</tbody>
			</Table>
		</div>
    </section>
  );
};

export default Acervo;
