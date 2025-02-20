import React, { useState, useEffect } from "react";
import Form from "react-bootstrap/esm/Form";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
import { faCalendarDay, faTrash } from "@fortawesome/free-solid-svg-icons";
import Button from "react-bootstrap/esm/Button";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import InputGroup from "react-bootstrap/InputGroup";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import ListagemFrequencias from "./ListagemFrequencias";
import { FrequenciaFiltros, GetFrequenciaResponse } from "./../../../interfaces/frequencia";
import { deleteFrequencia, getFrequencias } from "./../../../api/FrequenciaApi";

import { format } from "date-fns/format";
import { registerLocale } from 'react-datepicker';
import { ptBR } from 'date-fns/locale';
import Modal from "react-bootstrap/esm/Modal";
import ConfirmacaoFrequencia from "./ConfirmacaoFrequencia";

registerLocale('ptBR', ptBR);

const VerFrequencias: React.FC = () => {

  const [showConfirmarDelete, setShowConfirmarDelete] = useState(false);
  const handleCloseConfirmarDelete = () => setShowConfirmarDelete(false);
  const handleShowConfirmarDelete = (id: number) => {
    setEditingFrequenciaId(id);
    setShowConfirmarDelete(true);
  }

  const [editingFrequenciaId, setEditingFrequenciaId] = useState<number>(null);

  const handleSubmitDeletarFrequencia = async () => {
    try {
      await deleteFrequencia(editingFrequenciaId);
      listarFrequencias();
      handleCloseConfirmarDelete();
    } catch(err) {
      console.log(err)
    }
  }

  const [dataFrequencia, setDataFrequencia] = useState<Date | null>(new Date());
  const [frequencias, setFrequencias] = useState<GetFrequenciaResponse[]>([]);
  
  const listarFrequencias = async (): Promise<void> => {
    try {
      const filtros: FrequenciaFiltros = {
        data: format(dataFrequencia, "yyyy-MM-dd")
      }
      const data = await getFrequencias(filtros);
      setFrequencias(data);
    } catch(err) {
      console.log(err)
    }
  };

  useEffect(() => {
    listarFrequencias()
  }, [dataFrequencia]);

  return (
    <section className="Exemplar">
      <div className="Exemplar-acoes">
        <Form className="mt-0">
          <Row>
            <Col xs={4}>
              <Form.Group controlId="formData" className="mb-3">
                <Form.Label>
                  Data da Frequência <span className="obgr">*</span>
                </Form.Label>
                <InputGroup>
                  <DatePicker
                    selected={dataFrequencia}
                    onChange={(date: Date) => setDataFrequencia(date)}
                    dateFormat="dd/MM/yyyy"
                    placeholderText="Selecione a data"
                    customInput={
                      <InputGroup>
                        <Form.Control
                          type="text"
                          placeholder="Selecione a data"
                          readOnly
                          value={format(dataFrequencia, "dd/MM/yyyy")}
                          style={{ cursor: "pointer" }}
                          className="no-border-radius-right"
                        />
                        <InputGroup.Text className="btn-orange">
                          <FontAwesomeIcon icon={faCalendarDay} />
                        </InputGroup.Text>
                      </InputGroup>
                    }
                  />
                </InputGroup>
              </Form.Group>
            </Col>
          </Row>
        </Form>

        <Modal
          show={showConfirmarDelete}
          onHide={handleCloseConfirmarDelete}
          size="lg"
          backdrop="static"
          centered
          keyboard={false}
          className="Modais-Confirmacao-Custon"
        >
          <Modal.Header closeButton>
            <Modal.Title>Confirmação</Modal.Title>
          </Modal.Header>

          <Modal.Body>
            <ConfirmacaoFrequencia />
          </Modal.Body>

          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseConfirmarDelete}>
              Cancelar
            </Button>
            <Button variant="danger" onClick={handleSubmitDeletarFrequencia}>
              <FontAwesomeIcon icon={faTrash} /> Excluir
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
      <div className="w-100 list-scroll">
        <ListagemFrequencias frequencias={frequencias} onDelete={handleShowConfirmarDelete} />
      </div>
    </section>
  );
};

export default VerFrequencias;
