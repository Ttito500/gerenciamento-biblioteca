import React from "react";
import Table from "react-bootstrap/Table";
import Badge from "react-bootstrap/Badge";

const AcervoEmprestimosLivro: React.FC = () => {
  return (
    <>
      <Table striped className="tabela">
        <thead>
        <tr>
          <th className="th-center-size-eight">Exemplar</th>
          <th>Aluno</th>
          <th className="th-center-size-eight">Série</th>
          <th className="th-center-size-eight">Turma</th>
          <th className="th-center-size-eighteen">Data de Empréstimo</th>
          <th className="th-center-size-eight">Prazo</th>
        </tr>
        </thead>
        <tbody>
        <tr className="tabela-tr">
          <td className="text-center">4</td>
          <td>João Silva</td>
          <td className="text-center">1</td>
          <td className="text-center">A</td>
          <td className="text-center">07/02/2025</td>
          <td className="text-center">
            <Badge className="bibliotech-badge" bg="green">21/02/2025</Badge>
          </td>
        </tr>
        <tr className="tabela-tr">
          <td className="text-center">2</td>
          <td>Ana Oliveira</td>
          <td className="text-center">2</td>
          <td className="text-center">B</td>
          <td className="text-center">05/02/2025</td>
          <td className="text-center">
            <Badge className="bibliotech-badge" bg="red">12/02/2025</Badge>
          </td>
        </tr>
        <tr className="tabela-tr">
          <td className="text-center">1</td>
          <td>Pedro Souza</td>
          <td className="text-center">3</td>
          <td className="text-center">C</td>
          <td className="text-center">06/02/2025</td>
          <td className="text-center">
            <Badge className="bibliotech-badge" bg="green">16/02/2025</Badge>
          </td>
        </tr>
        </tbody>
      </Table>
    </>
  );
};

export default AcervoEmprestimosLivro;
