import React from "react";
import { faFileImport, faClipboardList, faPenToSquare, faTrash } from '@fortawesome/free-solid-svg-icons'
import Table from 'react-bootstrap/Table';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import Badge from 'react-bootstrap/Badge';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import Button from 'react-bootstrap/Button';

const AcervoEmprestimosLivro: React.FC = () => {
  return (
    <>
        <Table striped className="tabela">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Aluno</th>
                    <th>Série</th>
                    <th>Turma</th>
                    <th>Data Empréstimo</th>
                    <th>Prazo</th>
                </tr>
            </thead>
            <tbody>
                <tr className="tabela-tr">
                    <td className="text-center">1</td>
                    <td>Pedro Rivaldo</td>
                    <td>1ª</td>
                    <td>A</td>
                    <td>12/11/2024</td>
                    <td className="text-center">
                        <Badge bg="danger">22/11/2024</Badge>
                    </td>
                </tr>	

                <tr className="tabela-tr">
                    <td className="text-center">2</td>
                    <td>João</td>
                    <td>2ª</td>
                    <td>B</td>
                    <td>12/11/2024</td>
                    <td className="text-center">
                        <Badge bg="success">26/11/2024</Badge>
                    </td>
                </tr>
            </tbody>
        </Table>
    </>
  );
}

export default AcervoEmprestimosLivro;