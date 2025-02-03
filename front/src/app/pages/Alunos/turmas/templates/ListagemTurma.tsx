    import { Table, Badge, Button, ButtonGroup } from 'react-bootstrap';
    import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
    import { faEdit, faTrash, faPowerOff } from '@fortawesome/free-solid-svg-icons';

    const ListagemTurma: React.FC = () => {
        return (
            <Table striped className="tabela">
                <thead>
                <tr>
                    <th>Série</th>
                    <th>Turma</th>
                    <th>Ano de Entrada</th>
                    <th>Ativo</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <tr className="tabela-tr">
                    <td>1º Ano</td>
                    <td>A</td>
                    <td>2023</td>
                    <td>
                        <Badge className="bibliotech-badge" bg="green">Ativo</Badge>
                    </td>
                    <td>
                        <ButtonGroup aria-label="Ações" className="tabela-acoes">
                            <Button variant="btn-outline-secondary" className="color-green">
                                <FontAwesomeIcon icon={faEdit} />
                            </Button>
                            <Button variant="btn-outline-secondary" className="color-red">
                                <FontAwesomeIcon icon={faPowerOff} />
                            </Button>
                        </ButtonGroup>
                    </td>
                </tr>
                <tr className="tabela-tr">
                    <td>2º Ano</td>
                    <td>B</td>
                    <td>2022</td>
                    <td>
                        <Badge className="bibliotech-badge" bg="green">Ativo</Badge>
                    </td>
                    <td>
                        <ButtonGroup aria-label="Ações" className="tabela-acoes">
                            <Button variant="btn-outline-secondary" className="color-green">
                                <FontAwesomeIcon icon={faEdit} />
                            </Button>
                            <Button variant="btn-outline-secondary" className="color-red">
                                <FontAwesomeIcon icon={faPowerOff} />
                            </Button>
                        </ButtonGroup>
                    </td>
                </tr>
                <tr className="tabela-tr">
                    <td>3º Ano</td>
                    <td>C</td>
                    <td>2021</td>
                    <td>
                        <Badge className="bibliotech-badge" bg="red">Inativo</Badge>
                    </td>
                    <td>
                        <ButtonGroup aria-label="Ações" className="tabela-acoes">
                            <Button variant="btn-outline-secondary" className="color-green">
                                <FontAwesomeIcon icon={faEdit} />
                            </Button>
                            <Button variant="btn-outline-secondary" className="color-red">
                                <FontAwesomeIcon icon={faPowerOff} />
                            </Button>
                        </ButtonGroup>
                    </td>
                </tr>
                </tbody>
            </Table>
        );
    };

    export default ListagemTurma;
