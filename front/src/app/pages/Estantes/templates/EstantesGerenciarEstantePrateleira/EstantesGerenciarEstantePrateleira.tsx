import React, { ChangeEvent, useEffect, useState } from "react";
import styles from "./EstantesGerenciarEstantePrateleira.module.css";

import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheck, faTrash } from "@fortawesome/free-solid-svg-icons";
import Button from "react-bootstrap/Button";
import Spinner from "react-bootstrap/Spinner";
import ToastContainer from "react-bootstrap/ToastContainer";
import Toast from "react-bootstrap/Toast";

interface EstantesGerenciarEstantePrateleiraProps {
  idEstantePrateleira: number;
  formData: {
    estante: string;
    prateleira: string;
  };
  onChange: (e: ChangeEvent<any>) => void;
  onEdit: () => void;
  onDelete: () => void;
}

const SecoesGerenciarSecao: React.FC<EstantesGerenciarEstantePrateleiraProps> = ({
  idEstantePrateleira,
  formData,
  onChange,
  onEdit,
  onDelete,
}) => {
  const [loading, setLoading] = useState<boolean>(true);

  const [showToastError, setShowToastError] = useState(false);
  const [showToastSuccess, setShowToastSuccess] = useState(false);

  useEffect(() => {
    setLoading(false);
  }, []);

  if (loading) {
    return (
      <Spinner animation="border" role="status">
        <span className="visually-hidden">Carregando...</span>
      </Spinner>
    );
  }

  return (
    <>
      <ToastContainer
        className="p-3"
        position="bottom-center"
        style={{ zIndex: 10 }}
      >
        <Toast
          bg="success"
          onClose={() => setShowToastSuccess(false)}
          show={showToastSuccess}
          delay={3000}
          autohide
        >
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
        <Toast
          bg="danger"
          onClose={() => setShowToastError(false)}
          show={showToastError}
          delay={3000}
          autohide
        >
          <Toast.Header>
            <strong className="me-auto">
              Não foi possível concluir a operação. Tente novamente.
            </strong>
          </Toast.Header>
        </Toast>
      </ToastContainer>

      <div className={styles.editar}>
        <div className={styles.editar_title}>Editar</div>

        <Form>
          <Row style={{ alignItems: "flex-end" }}>
            <Col xs={3}>
              <Form.Group>
                <Form.Label>
                  Letra da Estante onde a Prateleira está Localizada <span className="obgr">*</span>
                </Form.Label>

                <Form.Control
                  type="text"
                  placeholder="Digite a letra"
                  name="estante"
                  required
                  value={formData.estante}
                  onChange={onChange}
                />

                <Form.Control.Feedback type="invalid">
                  Campo obrigatório.
                </Form.Control.Feedback>
              </Form.Group>
            </Col>

            <Col xs={5}>
              <Form.Group>
                <Form.Label>Número da Prateleira <span className="obgr">*</span></Form.Label>

                <Form.Control
                  type="text"
                  required
                  placeholder="Digite o número da prateleira"
                  name="prateleira"
                  value={formData.prateleira}
                  onChange={onChange}
                />

                <Form.Control.Feedback type="invalid">
                  Campo obrigatório.
                </Form.Control.Feedback>
              </Form.Group>
            </Col>

            <Button
              variant="success"
              onClick={onEdit}
              style={{ width: "auto" }}
            >
              <FontAwesomeIcon icon={faCheck} /> Salvar
            </Button>

            <Button
              variant="danger"
              onClick={onDelete}
              style={{ width: "auto", marginLeft: "16px" }}
            >
              <FontAwesomeIcon icon={faTrash} /> Excluir Prateleira
            </Button>
          </Row>
        </Form>
      </div>
    </>
  );
};

export default SecoesGerenciarSecao;
