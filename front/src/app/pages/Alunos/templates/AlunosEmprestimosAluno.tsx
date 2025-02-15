import React from "react";
import Table from "react-bootstrap/Table";
import Badge from "react-bootstrap/Badge";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

const AlunosEmprestimosAluno: React.FC = () => {
  return (
    <>
      <Table striped className="tabela">
        <thead>
          <tr>
            <th className="th-size-fifteen">ISBN</th>
            <th>Título</th>
            <th>Autores</th>
            <th className="th-size-ten">Gênero</th>
            <th className="th-center-size-twelve">Status da Entrega</th>
            <th className="th-center-size-twelve">Data Entrega</th>
          </tr>
        </thead>
        <tbody>
          <tr className="tabela-tr">
            <td>123-12-12345-12-1</td>
            <td>A Hora da Estrela</td>
            <td>Clarice Lispector</td>
            <td>Romance</td>
            <td className="text-center">
              <Badge className="bibliotech-badge" bg="info">Pendente</Badge>
            </td>
            <td className="th-center-size-twelve"></td>
          </tr>

          <tr className="tabela-tr">
            <td>523-32-12345-12-1</td>
            <td>A Hora da Estrela</td>
            <td>Clarice Lispector</td>
            <td>Romance</td>
            <td className="text-center">
              <Badge className="bibliotech-badge" bg="warning">Em Atraso</Badge>
            </td>
            <td className="th-center-size-twelve"></td>
          </tr>

          <tr className="tabela-tr">
            <td>321-12-54321-12-9</td>
            <td>A Hora da Estrela</td>
            <td>Clarice Lispector</td>
            <td>Romance</td>
            <td className="text-center">
              <Badge className="bibliotech-badge" bg="danger">Extraviado</Badge>
            </td>
            <td className="th-center-size-twelve"></td>
          </tr>

          <tr className="tabela-tr">
            <td>321-12-54321-12-9</td>
            <td>A Hora da Estrela</td>
            <td>Clarice Lispector</td>
            <td>Romance</td>
            <td className="text-center">
              <Badge className="bibliotech-badge" bg="success">Entregue</Badge>
            </td>
            <td className="th-center-size-twelve">22/11/2024</td>
          </tr>
          
        </tbody>
      </Table>
    </>
  );
};

export default AlunosEmprestimosAluno;
