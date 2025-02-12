import React, { ChangeEvent, useEffect, useState } from "react";
import Col from "react-bootstrap/Col";
import Spinner from "react-bootstrap/Spinner";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import { GetTurmaResponse, TurmaFiltros } from "./../../../interfaces/turma";
import { getTurmas } from "./../../../api/TurmaApi";
import ToastContainer from "react-bootstrap/ToastContainer";
import Toast from "react-bootstrap/Toast";

interface AlunosCadastrarAlunoProps {
  formData: {
    nome: string;
    telefone: string;
    email: string;
    idTurma: number;
  };
  onChange: (e: ChangeEvent<any>) => void;
}

const AlunosCadastrarAluno: React.FC<AlunosCadastrarAlunoProps> = ({ formData, onChange }) => {

  const [turmas, setTurmas] = useState<GetTurmaResponse[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  const [showToastError, setShowToastError] = useState(false);
  const [showToastSuccess, setShowToastSuccess] = useState(false);

  useEffect(() => {
    listarTurmas();
  }, []);
  
  const listarTurmas = async (): Promise<void> => {
    setLoading(true);

    try {
      const filtros: TurmaFiltros = {
        ativo: true
      }
      const data = await getTurmas(filtros);
      setTurmas(data);
    } catch (err) {
      setShowToastError(true);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <Spinner animation="border" role="status"><span className="visually-hidden">Carregando...</span></Spinner>;
  }

  return (
    <>
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

      <Form>
        <Row>
          <Col>
            <Form.Group className="mb-3">
              <Form.Label>
                Série / Turma <span className="obgr">*</span>
              </Form.Label>

              <Form.Select
                aria-label="Selecione uma série e turma"
                name="idTurma"
                value={formData.idTurma}
                onChange={onChange}
                required
              >
                <option value="">Selecione</option>
                {turmas.map((turma) => (
                  <option key={turma.id} value={turma.id}>
                    Série: {turma.serie} / Turma: {turma.turma}
                  </option>
                ))}
              </Form.Select>

              <Form.Control.Feedback type="invalid">
                Campo obrigatório.
              </Form.Control.Feedback>
            </Form.Group>
          </Col>

          <Col xs={6}>
            <Form.Group className="mb-3">
              <Form.Label>
                Nome <span className="obgr">*</span>
              </Form.Label>

              <Form.Control 
                type="text" 
                placeholder="Digite o nome" 
                name="nome" 
                value={formData.nome} 
                required
                onChange={onChange} />

              <Form.Control.Feedback type="invalid">
                Campo obrigatório.
              </Form.Control.Feedback>
            </Form.Group>
          </Col>
        </Row>

        <Row>
          <Col xs={6}>
            <Form.Group className="mb-3">
              <Form.Label>
                Email
              </Form.Label>
              <Form.Control 
                type="email" 
                placeholder="Digite o email" 
                name="email" 
                required
                value={formData.email} 
                onChange={onChange} 
              />
            </Form.Group>
          </Col>

          <Col xs={6}>
            <Form.Group className="mb-3">
              <Form.Label>
                Telefone
              </Form.Label>
              
              <Form.Control 
                type="text" 
                placeholder="Digite o telefone" 
                name="telefone" 
                value={formData.telefone} 
                onChange={onChange} 
              />
            </Form.Group>
          </Col>

          <Col></Col>
        </Row>
      </Form>
    </>
    
  );
};

export default AlunosCadastrarAluno;
