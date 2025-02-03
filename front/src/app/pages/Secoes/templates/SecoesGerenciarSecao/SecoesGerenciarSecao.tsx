import React, { ChangeEvent, useState } from "react";
import styles from "./SecoesGerenciarSecao.module.css";

import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheck, faPlus, faTrash } from "@fortawesome/free-solid-svg-icons";
import Button from "react-bootstrap/Button";
import { Estante, GetSecaoResponse } from "./../../../../interfaces/secao";

interface SecoesGerenciarSecaoProps {
  formData: {
    nome: string;
    descricao: string;
  };
  estantes: Estante[];
  onChange: (e: ChangeEvent<any>) => void;
  onEdit: () => void;
  onDelete: () => void;
  onDeletePrateleira: (id: number) => void;
}

const SecoesGerenciarSecao: React.FC<SecoesGerenciarSecaoProps> = ({
  formData,
  estantes,
  onChange,
  onEdit,
  onDelete,
  onDeletePrateleira
}) => {
  return (
    <>
      <div className={styles.editar}>
        <div className={styles.editar_title}>Editar</div>

        <Form>
          <Row style={{ alignItems: "flex-end" }}>
            <Col xs={3}>
              <Form.Group>
                <Form.Label>
                  Nome <span className="obgr">*</span>
                </Form.Label>

                <Form.Control
                  type="text"
                  placeholder="Digite o nome da seção"
                  name="nome"
                  value={formData.nome}
                  onChange={onChange}
                />

                <Form.Control.Feedback type="invalid">
                  Campo obrigatório.
                </Form.Control.Feedback>
              </Form.Group>
            </Col>

            <Col xs={5}>
              <Form.Group>
                <Form.Label>Descrição</Form.Label>

                <Form.Control
                  type="text"
                  placeholder="Digite uma descrição para a seção"
                  name="descricao"
                  value={formData.descricao}
                  onChange={onChange}
                />
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
              <FontAwesomeIcon icon={faTrash} /> Excluir Seção
            </Button>
          </Row>
        </Form>
      </div>

      <div className={styles.estantes}>
        <div className={styles.estantes_title}>
          Estantes e prateleiras desta seção
        </div>

        <Form>
          <Row style={{ alignItems: "flex-end" }}>
            <Col xs={6}>
              <Form.Group>
                <Form.Label>
                  Selecione uma prateleira para adicionar na seção{" "}
                  <span className="obgr">*</span>
                </Form.Label>

                <Form.Select
                  aria-label="Selecione uma prateleira de uma estante"
                  name="prateleira"
                >
                  <option>Selecione</option>
                  <option value="1">Estante A / Prateleira 1</option>
                  <option value="2">Estante A / Prateleira 2</option>
                  <option value="3">Estante B / Prateleira 1</option>
                </Form.Select>

                <Form.Control.Feedback type="invalid">
                  Campo obrigatório.
                </Form.Control.Feedback>
              </Form.Group>
            </Col>

            <Button
              className="btn-orange"
              onClick={onEdit}
              style={{ width: "auto" }}
            >
              <FontAwesomeIcon icon={faPlus} /> Adicionar
            </Button>
          </Row>
        </Form>

        <div className={styles.estantes_listagem}>
          {estantes.map((estante) => (
            <div key={estante.letra} className={styles.estantes_listagem_item}>
              <div className={styles.estantes_listagem_item_estante}>
                Estante {estante.letra}
              </div>

              {estante.prateleiras.map((prateleira) => (
                <div
                  key={prateleira.numero}
                  className={styles.estantes_listagem_item_prateleira}
                >
                  Prateleira {prateleira.numero}

                  <Button
                    variant="btn-outline-secondary"
                    className="color-red"
                    onClick={() => onDeletePrateleira(prateleira.id)}
                  >
                    <FontAwesomeIcon icon={faTrash} />
                  </Button>
                </div>
              ))}
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default SecoesGerenciarSecao;
