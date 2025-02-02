import React from "react";
import { faCalendarCheck, faCalendarPlus, faCircleXmark } from "@fortawesome/free-solid-svg-icons";
import Table from "react-bootstrap/Table";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import Badge from "react-bootstrap/Badge";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Button from "react-bootstrap/Button";

const ListagemEmprestimos = () => {
    return (
        <>
            <Table striped className="tabela">
                <thead>
                <tr>
                    <th>ISBN</th>
                    <th>Exemplar</th>
                    <th>Título</th>
                    <th>Aluno</th>
                    <th>Emprestado Por</th>
                    <th>Concluído Por</th>
                    <th>Observações</th>
                    <th>Data Empr.</th>
                    <th>Prazo</th>
                    <th>Devolução</th>
                    <th>Renovações</th>
                    <th>Status</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <tr className="tabela-tr">
                    <td>978-3-16-148410-0</td>
                    <td>1</td>
                    <td>Livro Exemplo</td>
                    <td>João Silva</td>
                    <td>Monitor N</td>
                    <td>Bibliotecária</td>
                    <td>Nenhuma</td>
                    <td>01/02/2024</td>
                    <td>15/02/2024</td>
                    <td>14/02/2024</td>
                    <td>1</td>
                    <td>
                        <Badge className="bibliotech-badge" bg="green">Entregue</Badge>
                    </td>
                    <td>
                        <ButtonGroup aria-label="Ações" className="tabela-acoes">
                            <Button variant="btn-outline-secondary" className="color-orange">
                                <FontAwesomeIcon icon={faCalendarCheck} />
                            </Button>
                            <Button variant="btn-outline-secondary" className="color-blue">
                                <FontAwesomeIcon icon={faCalendarPlus} />
                            </Button>
                            <Button variant="btn-outline-secondary" className="color-red">
                                <FontAwesomeIcon icon={faCircleXmark} />
                            </Button>
                        </ButtonGroup>
                    </td>
                </tr>
                <tr className="tabela-tr">
                    <td>978-3-16-148411-7</td>
                    <td>2</td>
                    <td>Livro Pendente</td>
                    <td>Maria Oliveira</td>
                    <td>Monitor A</td>
                    <td>Bibliotecário X</td>
                    <td>Atenção na devolução</td>
                    <td>05/02/2024</td>
                    <td>20/02/2024</td>
                    <td></td>
                    <td>2</td>
                    <td>
                        <Badge className="bibliotech-badge" bg="blue">Pendente</Badge>
                    </td>
                    <td>
                        <ButtonGroup aria-label="Ações" className="tabela-acoes">
                            <Button variant="btn-outline-secondary" className="color-orange">
                                <FontAwesomeIcon icon={faCalendarCheck} />
                            </Button>
                            <Button variant="btn-outline-secondary" className="color-blue">
                                <FontAwesomeIcon icon={faCalendarPlus} />
                            </Button>
                            <Button variant="btn-outline-secondary" className="color-red">
                                <FontAwesomeIcon icon={faCircleXmark} />
                            </Button>
                        </ButtonGroup>
                    </td>
                </tr>
                <tr className="tabela-tr">
                    <td>978-3-16-148412-4</td>
                    <td>3</td>
                    <td>Livro Atrasado</td>
                    <td>Carlos Mendes</td>
                    <td>Monitor B</td>
                    <td>Bibliotecário Y</td>
                    <td>Urgente</td>
                    <td>10/01/2024</td>
                    <td>25/01/2024</td>
                    <td></td>
                    <td>0</td>
                    <td>
                        <Badge className="bibliotech-badge" bg="warning">Atrasado</Badge>
                    </td>
                    <td>
                        <ButtonGroup aria-label="Ações" className="tabela-acoes">
                            <Button variant="btn-outline-secondary" className="color-orange">
                                <FontAwesomeIcon icon={faCalendarCheck} />
                            </Button>
                            <Button variant="btn-outline-secondary" className="color-blue">
                                <FontAwesomeIcon icon={faCalendarPlus} />
                            </Button>
                            <Button variant="btn-outline-secondary" className="color-red">
                                <FontAwesomeIcon icon={faCircleXmark} />
                            </Button>
                        </ButtonGroup>
                    </td>
                </tr>
                <tr className="tabela-tr">
                    <td>978-3-16-148413-1</td>
                    <td>4</td>
                    <td>Livro Extraviado</td>
                    <td>Ana Souza</td>
                    <td>Monitor C</td>
                    <td>Bibliotecário Z</td>
                    <td>Verificar condição</td>
                    <td>12/02/2024</td>
                    <td>27/02/2024</td>
                    <td></td>
                    <td>1</td>
                    <td>
                        <Badge className="bibliotech-badge" bg="red">Extraviado</Badge>
                    </td>
                    <td>
                        <ButtonGroup aria-label="Ações" className="tabela-acoes">
                            <Button variant="btn-outline-secondary" className="color-orange">
                                <FontAwesomeIcon icon={faCalendarCheck} />
                            </Button>
                            <Button variant="btn-outline-secondary" className="color-blue">
                                <FontAwesomeIcon icon={faCalendarPlus} />
                            </Button>
                            <Button variant="btn-outline-secondary" className="color-red">
                                <FontAwesomeIcon icon={faCircleXmark} />
                            </Button>
                        </ButtonGroup>
                    </td>
                </tr>

                </tbody>
            </Table>
        </>
    );
};

export default ListagemEmprestimos;
