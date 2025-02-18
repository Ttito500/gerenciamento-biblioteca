import React, { useState } from "react";
import { Table } from "react-bootstrap";

const Cronograma: React.FC = () => {

    return (
        <section className="Exemplar">
            <div className="Exemplar-acoes"></div>

            <div className="w-100">
                <Table striped className="cronograma">
                    <thead>
                    <tr>
                        <th className="text-center">Segunda</th>
                        <th className="text-center">Terça</th>
                        <th className="text-center">Quarta</th>
                        <th className="text-center">Quinta</th>
                        <th className="text-center">Sexta</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr className="cronograma-tr">
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                    </tr>
                    <tr className="cronograma-tr">
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                    </tr>
                    <tr className="cronograma-tr">
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                    </tr>
                    <tr className="cronograma-tr">
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center">Aluno Monitor</td>
                        <td className="text-center"></td>
                        <td className="text-center">Aluno Monitor</td>
                    </tr>
                    </tbody>
                </Table>
            </div>

        </section>
    );
};

export default Cronograma;