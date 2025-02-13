import React from "react";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
import Form from "react-bootstrap/esm/Form";
import Button from "react-bootstrap/esm/Button";

const Login: React.FC = () => {
    return (
        <div className="Login">
            <Form className="Login-acoes text-center">

                <Row className="justify-content-center" style={{ marginTop: "90px" }}>
                    <Col xs={8}>
                        <h1 style={{fontWeight: "bold", fontFamily: "Inter, sans-serif", fontSize: "31px"}}>
                            Acervo Bibliotech
                        </h1>
                    </Col>
                </Row>

                <Row className="justify-content-center mb-3" >
                    <Col xs={8}>
                        <h3 style={{fontWeight: 530, fontFamily: "Inter, sans-serif", fontSize: "18px"}}>
                            Bem-vindo(a) de volta!
                        </h3>
                    </Col>
                </Row>

                <Row className="justify-content-center">
                <Col xs={11}>
                        <Form.Group className="mb-3">
                            <Form.Control type="email" placeholder="Digite o email" required />
                        </Form.Group>
                    </Col>
                </Row>

                <Row className="justify-content-center">
                    <Col xs={11}>
                        <Form.Group className="mb-4">
                            <Form.Control type="password" placeholder="Digite a senha" required />
                        </Form.Group>
                    </Col>
                </Row>

                <Row className="justify-content-center" style={{ marginBottom: "85px" }}>
                    <Col xs={11}>
                        <Button
                            variant="info"
                            className="btn-orange"
                            style={{ width: "100%" }}
                        >
                            Entrar
                        </Button>
                    </Col>
                </Row>

            </Form>
        </div>
    );
};

export default Login;
